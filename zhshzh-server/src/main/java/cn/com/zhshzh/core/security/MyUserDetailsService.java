package cn.com.zhshzh.core.security;

import cn.com.zhshzh.business.user.po.SysUserInfoPO;
import cn.com.zhshzh.business.user.service.SysUserInfoService;
import cn.com.zhshzh.core.constant.RedisKeyConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Autowired
    private MyUserDetailsService(SysUserInfoService sysUserInfoService, StringRedisTemplate stringRedisTemplate) {
        this.sysUserInfoService = sysUserInfoService;
        this.stringRedisTemplate = stringRedisTemplate;
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
        // 将用户id放入redis中
        String userIdKey = RedisKeyConstants.USERID_PREFIX + username + RedisKeyConstants.USERID_SUFFIX;
        stringRedisTemplate.opsForValue().set(userIdKey, sysUserInfoPO.getUserInfoId().toString());
        String encodedPassword = sysUserInfoPO.getPassword();

        // 查询用户的所有角色
        List<String> roles = new ArrayList<>();
        // 设置用户角色
        Set<GrantedAuthority> authorities = new HashSet<>();
        if ("admin".equals(username)) {
            roles.add("ROLE_ADMIN");
            roles.add("ROLE_USER");
            roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
        } else if ("user".equals(username)) {
            roles.add("ROLE_USER");
            roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
        }
        return new MyUser(username, encodedPassword, authorities);
    }
}
