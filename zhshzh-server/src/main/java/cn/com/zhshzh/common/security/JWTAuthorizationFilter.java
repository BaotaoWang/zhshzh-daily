package cn.com.zhshzh.common.security;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * security身份授权过滤器
 *
 * @author wbt
 * @since 2019/10/10
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
		// 如果请求头中没有Authorization信息则直接放行了
		if (tokenHeader == null || !tokenHeader.startsWith(JwtTokenUtils.TOKEN_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}
		// 如果请求头中有token，则进行解析，并且设置认证信息
		SecurityContextHolder.getContext().setAuthentication(getAuthentication(tokenHeader));
		super.doFilterInternal(request, response, chain);
	}

	/**
	 * 从token中获取用户信息并新建一个token
	 * @param tokenHeader
	 * @return
	 */
	private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader) {
		String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, "");
		String username = JwtTokenUtils.getUsername(token);
		List<GrantedAuthority> authorities = JwtTokenUtils.getClaims(token);
        if (username != null){
            return new UsernamePasswordAuthenticationToken(username, null, authorities);
        }
		return null;
	}
}
