package cn.com.zhshzh.core.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

/**
 * token生成与解析的工具类
 *
 * @author WBT
 * @since 2019/10/10
 */
class JwtTokenUtils {

    static final String TOKEN = "token";
    static final String TOKEN_HEADER = "Authorization";
    static final String TOKEN_PREFIX = "Bearer ";
    // 自定义的签名Key
    private static final String SECRET_KEY = "zhshzh-secret-key";
    // 签发者
    private static final String ISS = "zhshzh";
    // 过期时间是3600秒，即1个小时
    private static final long EXPIRATION = 3600L;
    // 选择了记住我之后的过期时间为7天
    private static final long EXPIRATION_REMEMBER = 604800L;
    private static final String AUTHORITY_KEY = "authorities";

    /**
     * 创建token
     *
     * @param username     用户名
     * @param authorities  用户权限
     * @param isRememberMe 记住我
     * @return token
     */
    static String createToken(String username, Set<GrantedAuthority> authorities, boolean isRememberMe) {
        long expiration = isRememberMe ? EXPIRATION_REMEMBER : EXPIRATION;
        Map<String, Object> map = new HashMap<>();
        map.put(AUTHORITY_KEY, authorities);
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
     * @param token token
     * @return username
     */
    static String getUsername(String token) {
        return getTokenBody(token).getSubject();
    }

    /**
     * 从token中获取权限
     *
     * @param token token
     * @return 用户权限
     */
    @SuppressWarnings("unchecked")
    static Set<GrantedAuthority> getClaims(String token) {
        List<Map<String, String>> authorityList = (List<Map<String, String>>) getTokenBody(token).get(AUTHORITY_KEY);
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorityList.forEach(authority -> {
            String role = authority.get("authority");
            authorities.add(new SimpleGrantedAuthority(role));
        });
        return authorities;
    }

    /**
     * 用签名去解析token
     *
     * @param token token
     * @return 解析token后的对象
     */
    private static Claims getTokenBody(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }
}
