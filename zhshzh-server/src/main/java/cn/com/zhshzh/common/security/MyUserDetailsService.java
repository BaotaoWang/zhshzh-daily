package cn.com.zhshzh.common.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cn.com.zhshzh.common.constant.PatternMatchesConstants;
import cn.com.zhshzh.system.user.po.SysUserInfoPO;
import cn.com.zhshzh.system.user.service.SysUserInfoService;
import io.jsonwebtoken.lang.Collections;

/**
 * security身份认证授权服务 根据用户名从数据库获取用户密码和角色，然后交给security去验证身份
 *
 * @author wbt
 * @since 2019/10/10
 */
@Service
public class MyUserDetailsService implements UserDetailsService {
	Logger logger = LogManager.getLogger(MyUserDetailsService.class);
	private SysUserInfoService sysUserInfoService;

	@Autowired
	private MyUserDetailsService(SysUserInfoService sysUserInfoService) {
		this.sysUserInfoService = sysUserInfoService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info(username);
		logger.error(username);
		SysUserInfoPO sysUserInfoPO = new SysUserInfoPO();
		// 判断用户登录方式
		if (username.matches(PatternMatchesConstants.EMAIL)) {
			sysUserInfoPO.setUserMailBox(username);
		} else if (username.matches(PatternMatchesConstants.MOBILE_NUMBER)) {
			sysUserInfoPO.setUserPhoneNumber(username);
		} else if (username.matches(PatternMatchesConstants.USER_SERIAL_NUMBER)) {
			sysUserInfoPO.setUserSerialNumber(username);
		} else if (username.matches(PatternMatchesConstants.USER_ACCOUNT)) {
			sysUserInfoPO.setUserAccount(username);
		} else {
			throw new UsernameNotFoundException("User " + username + " was not found in database");
		}

		// 根据用户名查询用户密码
		String encodedPassword = "";
		List<SysUserInfoPO> sysUserInfoList = sysUserInfoService.listSysUserInfo(sysUserInfoPO);
		if (Collections.isEmpty(sysUserInfoList)) {
			throw new UsernameNotFoundException("User " + username + " was not found in database");
		} else {
			encodedPassword = sysUserInfoList.get(0).getUserPassword();
		}

		// 设置用户角色
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		if ("admin".equals(username)) {
			authorities = AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER");
		} else if ("user".equals(username)) {
			authorities = AuthorityUtils.createAuthorityList("ROLE_USER");
		}
		UserDetails user = new User(username, encodedPassword, authorities);
		return user;
	}
}
