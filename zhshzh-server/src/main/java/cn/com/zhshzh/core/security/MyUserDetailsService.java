package cn.com.zhshzh.core.security;

import cn.com.zhshzh.business.user.po.SysUserInfoPO;
import cn.com.zhshzh.business.user.service.SysUserInfoService;
import cn.com.zhshzh.core.constant.PatternMatchesConstants;
import io.jsonwebtoken.lang.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
        SysUserInfoPO sysUserInfoPO = new SysUserInfoPO();
        // 获取存入redis中的rememberMe
        String key = JWTAuthenticationFilter.KEY_PREFIX + username + JWTAuthenticationFilter.KEY_SUFFIX;
        String rememberMe = stringRedisTemplate.opsForValue().get(key);
        // 获取到rememberMe后，将其从redis中删除
        stringRedisTemplate.delete(key);
        // 判断用户登录方式
        if (username.matches(PatternMatchesConstants.EMAIL)) {
            // 邮箱登录
            sysUserInfoPO.setEmail(username);
        } else if (username.matches(PatternMatchesConstants.MOBILE_NUMBER)) {
            // 手机号登录
            sysUserInfoPO.setPhoneNumber(username);
        } else if (username.matches(PatternMatchesConstants.SERIAL_NUMBER)) {
            // 员工号登录
            sysUserInfoPO.setSerialNumber(username);
        } else if (username.matches(PatternMatchesConstants.USER_NAME)) {
            // 用户名登录
            sysUserInfoPO.setUserName(username);
        } else {
            throw new UsernameNotFoundException("User " + username + " was not found in database");
        }

        // 根据用户名查询用户密码
        String encodedPassword;
        List<SysUserInfoPO> sysUserInfoList = sysUserInfoService.listSysUserInfo(sysUserInfoPO);
        if (Collections.isEmpty(sysUserInfoList)) {
            throw new UsernameNotFoundException("User " + username + " was not found in database");
        } else {
            username = sysUserInfoList.get(0).getUserName();
            // 以用户名作为新的key重新放入到redis中
            String newKey = JWTAuthenticationFilter.KEY_PREFIX + username + JWTAuthenticationFilter.KEY_SUFFIX;
            stringRedisTemplate.opsForValue().set(newKey, rememberMe);
            encodedPassword = sysUserInfoList.get(0).getPassword();
        }

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
