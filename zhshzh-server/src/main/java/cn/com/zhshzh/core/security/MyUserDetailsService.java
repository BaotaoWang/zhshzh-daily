package cn.com.zhshzh.core.security;

import cn.com.zhshzh.core.constant.RedisKeyConstants;
import cn.com.zhshzh.core.constant.WhereConditionEnum;
import cn.com.zhshzh.core.model.ConditionModel;
import cn.com.zhshzh.core.model.WhereConditions;
import cn.com.zhshzh.system.user.dao.SysRoleInfoMapper;
import cn.com.zhshzh.system.user.dao.SysUserRoleRelationMapper;
import cn.com.zhshzh.system.user.po.SysRoleInfoPO;
import cn.com.zhshzh.system.user.po.SysUserInfoPO;
import cn.com.zhshzh.system.user.po.SysUserRoleRelationPO;
import cn.com.zhshzh.system.user.service.SysUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * security身份认证授权服务 根据用户名从数据库获取用户密码和角色，然后交给security去验证身份
 *
 * @author WBT
 * @since 2019/10/10
 */
@Service
public class MyUserDetailsService implements UserDetailsService {
    private SysUserInfoService sysUserInfoService;
    private StringRedisTemplate stringRedisTemplate;
    private SysUserRoleRelationMapper sysUserRoleRelationMapper;
    private SysRoleInfoMapper sysRoleInfoMapper;

    @Autowired
    private void setSysUserInfoService(SysUserInfoService sysUserInfoService) {
        this.sysUserInfoService = sysUserInfoService;
    }

    @Autowired
    private void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Autowired
    private void setSysUserRoleRelationMapper(SysUserRoleRelationMapper sysUserRoleRelationMapper) {
        this.sysUserRoleRelationMapper = sysUserRoleRelationMapper;
    }

    @Autowired
    private void setSysRoleInfoMapper(SysRoleInfoMapper sysRoleInfoMapper) {
        this.sysRoleInfoMapper = sysRoleInfoMapper;
    }

    /**
     * 根据用户名去数据库查用户密码及用户角色
     *
     * @param username 用户名
     * @return 用户信息
     * @throws UsernameNotFoundException 用户不存在异常
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 获取存入redis中的rememberMe
        String key = RedisKeyConstants.REMEMBERME_PREFIX + username + RedisKeyConstants.REMEMBERME_SUFFIX;
        String rememberMe = stringRedisTemplate.opsForValue().get(key);
        // 获取到rememberMe后，将其从redis中删除
        stringRedisTemplate.delete(key);

        // 根据用户名查询用户密码
        SysUserInfoPO sysUserInfoPO = sysUserInfoService.getSysUserInfoForLogin(username);
        username = sysUserInfoPO.getUserName();
        // 以用户名作为新的key重新放入到redis中
        String newKey = RedisKeyConstants.REMEMBERME_PREFIX + username + RedisKeyConstants.REMEMBERME_SUFFIX;
        if (!StringUtils.isEmpty(rememberMe)) {
            stringRedisTemplate.opsForValue().set(newKey, rememberMe);
        }

        // 将用户id转为String
        String userInfoId = String.valueOf(sysUserInfoPO.getUserInfoId());
        String userIdKey = RedisKeyConstants.USERID_PREFIX + username + RedisKeyConstants.USERID_SUFFIX;
        // 将用户id放入redis中
        stringRedisTemplate.opsForValue().set(userIdKey, userInfoId);
        // 用户加密后的密码
        String encodedPassword = sysUserInfoPO.getPassword();

        // 根据用户id获取用户角色
        Set<GrantedAuthority> authorities = getAuthorities(userInfoId);
        return new MyUser(username, encodedPassword, authorities);
    }

    /**
     * 根据用户id获取用户角色
     *
     * @param userInfoId 用户id
     * @return 用户角色集合
     */
    private Set<GrantedAuthority> getAuthorities(String userInfoId) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        // 查询用户关联的角色
        ConditionModel userRoleConditionModel = new ConditionModel("user_info_id", userInfoId);
        List<ConditionModel> userRoleConditionModelList = new ArrayList<>();
        userRoleConditionModelList.add(userRoleConditionModel);
        WhereConditions userRoleWhereConditions = new WhereConditions(userRoleConditionModelList);
        List<SysUserRoleRelationPO> sysUserRoleRelationPOList = sysUserRoleRelationMapper.listSysUserRoleRelations(userRoleWhereConditions);
        // redis中存放用户角色的key
        String roleIdsKey = RedisKeyConstants.ROLEID_PREFIX + userInfoId + RedisKeyConstants.ROLEID_SUFFIX;
        // 每次登录的时候，先将该用户之前的角色id从redis中删除，后面重新存
        stringRedisTemplate.delete(roleIdsKey);
        if (!CollectionUtils.isEmpty(sysUserRoleRelationPOList)) {
            // 如果用户有关联的角色，则用Lambda表达式取出所有角色的id
            String[] roleInfoIds = sysUserRoleRelationPOList.stream().map(sysUserRoleRelationPO -> {
                String roleInfoId = String.valueOf(sysUserRoleRelationPO.getRoleInfoId());
                // 将用户所拥有的所有角色id依次放入redis中
                stringRedisTemplate.opsForList().leftPush(roleIdsKey, roleInfoId);
                return roleInfoId;
            }).collect(Collectors.toList()).toArray(new String[sysUserRoleRelationPOList.size()]);

            // 根据角色id查询所有的角色信息
            ConditionModel roleConditionModel = new ConditionModel("role_info_id", WhereConditionEnum.IN, roleInfoIds);
            List<ConditionModel> roleConditionModelList = new ArrayList<>();
            roleConditionModelList.add(roleConditionModel);
            WhereConditions roleWhereConditions = new WhereConditions(roleConditionModelList);
            List<SysRoleInfoPO> sysRoleInfoPOList = sysRoleInfoMapper.listSysRoleInfos(roleWhereConditions);
            // 用Lambda表达式将所有的roleCode放入authorities中
            // spring security规定角色命令为ROLE_**格式，比如ROLE_ADMIN
            sysRoleInfoPOList.forEach(sysRoleInfoPO -> authorities.add(new SimpleGrantedAuthority(sysRoleInfoPO.getRoleCode())));
        }
        return authorities;
    }
}
