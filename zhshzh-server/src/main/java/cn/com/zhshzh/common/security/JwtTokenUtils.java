package cn.com.zhshzh.common.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtTokenUtils {

	public static final String TOKEN_HEADER = "Authorization";
	public static final String TOKEN_PREFIX = "Bearer ";
	// 自定义的签名Key
	private static final String SECRET_KEY = "zhshzh";
	// 签发者
	private static final String ISS = "zhshzh";

	// 过期时间是3600秒，即1个小时
	private static final long EXPIRATION = 3600L;

	// 选择了记住我之后的过期时间为7天
	private static final long EXPIRATION_REMEMBER = 604800L;

	/**
	 * 创建token
	 * 
	 * @param username
	 * @param authorityList
	 * @param isRememberMe
	 * @return
	 */
	public static String createToken(String username, List<String> authorityList, boolean isRememberMe) {
		long expiration = isRememberMe ? EXPIRATION_REMEMBER : EXPIRATION;
		Map<String, Object> map = new HashMap<>();
        map.put("authorityList", authorityList);
		return Jwts.builder()
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY)
				// 这里要早set一点，放到后面会覆盖别的字段
                .setClaims(map)
                .setIssuer(ISS)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .compact();
	}

	/**
	 * 从token中获取用户名
	 * 
	 * @param token
	 * @return
	 */
	public static String getUsername(String token) {
		return getTokenBody(token).getSubject();
	}

	/**
	 * 从token中获取权限
	 * 
	 * @param token
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<GrantedAuthority> getClaims(String token) {
		List<String> authorityList = (List<String>)getTokenBody(token).get("authorityList");
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (String authority : authorityList) {
			authorities.addAll(AuthorityUtils.createAuthorityList(authority));
		}
		return authorities;
	}

	/**
	 * 是否已过期
	 * 
	 * @param token
	 * @return
	 */
	public static boolean isExpiration(String token) {
		return getTokenBody(token).getExpiration().before(new Date());
	}

	/**
	 *
	 * @param token
	 * @return
	 */
	private static Claims getTokenBody(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}
}
