package cn.com.zhshzh.core.security;

import cn.com.zhshzh.business.user.po.SysUserInfoPO;
import cn.com.zhshzh.business.user.service.SysUserInfoService;
import cn.com.zhshzh.core.constant.PatternMatchesConstants;
import io.jsonwebtoken.lang.Collections;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
    private static final Logger logger = LogManager.getLogger(MyUserDetailsService.class);
    private SysUserInfoService sysUserInfoService;

    @Autowired
    private MyUserDetailsService(SysUserInfoService sysUserInfoService) {
        this.sysUserInfoService = sysUserInfoService;
    }

    /**
     * 根据用户名去数据库查用户密码及用户角色
     *
     * @param username 用户名
     * @return
     * @throws UsernameNotFoundException 用户不存在异常
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUserInfoPO sysUserInfoPO = new SysUserInfoPO();
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
        String encodedPassword = "";
        List<SysUserInfoPO> sysUserInfoList = sysUserInfoService.listSysUserInfo(sysUserInfoPO);
        if (Collections.isEmpty(sysUserInfoList)) {
            throw new UsernameNotFoundException("User " + username + " was not found in database");
        } else {
            encodedPassword = sysUserInfoList.get(0).getPassword();
        }

        // 查询用户的所有角色
        List<String> roles = new ArrayList<>();
        // 设置用户角色
        Set<GrantedAuthority> authorities = new HashSet<>();
        if ("admin".equals(username)) {
            roles.add("ROLE_ADMIN");
            roles.add("ROLE_USER");
            roles.stream().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role));
            });
        } else if ("user".equals(username)) {
            roles.add("ROLE_USER");
            roles.stream().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role));
            });
        }
        MyUser myUser = new MyUser(username, encodedPassword, authorities);
        return myUser;
    }
}
