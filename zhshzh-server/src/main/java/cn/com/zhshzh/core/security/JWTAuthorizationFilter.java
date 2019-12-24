package cn.com.zhshzh.core.security;

import cn.com.zhshzh.core.constant.HttpResultEnum;
import cn.com.zhshzh.core.util.ResponseUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * security身份授权过滤器
 *
 * @author WBT
 * @since 2019/10/10
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    private static final Logger logger = LogManager.getLogger(JWTAuthorizationFilter.class);

    JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    /**
     * 带着token的请求会在此进行初步校验
     *
     * @param request  request
     * @param response response
     * @param chain    chain
     * @throws IOException      IO异常
     * @throws ServletException Servlet异常
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
        // 如果请求头中没有Authorization信息则直接放行了
        if (tokenHeader == null || !tokenHeader.startsWith(JwtTokenUtils.TOKEN_PREFIX)) {
            // TODO 借用的别人的代码，想不明白为什么没发现token，还要执行过滤器，暂时注掉，有空再看（by:WBT）
            // chain.doFilter(request, response);
            ResponseUtil.writeMessage(response, HttpResultEnum.INVALID_TOKEN);
            return;
        }
        // 如果请求头中有token，则进行解析，并且设置认证信息
        try {
            SecurityContextHolder.getContext().setAuthentication(getAuthentication(tokenHeader));
            super.doFilterInternal(request, response, chain);
        } catch (ExpiredJwtException e) {
            logger.error(e);
            ResponseUtil.writeMessage(response, HttpResultEnum.USER_CREDENTIALS_HAVE_EXPIRED, e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error(e);
            ResponseUtil.writeMessage(response, HttpResultEnum.INVALID_TOKEN, e.getMessage());
        } catch (SignatureException e) {
            logger.error(e);
            ResponseUtil.writeMessage(response, HttpResultEnum.INVALID_SIGNATURE, e.getMessage());
        }
        // 有异常时，不往前台返回，直接往外抛。
        // 如果捕获到ServletException，向前台返回的话，会报java.lang.IllegalStateException: getWriter() has already been called for this response
        /* catch (IOException e) {
            logger.error(e);
            ResponseUtil.writeMessage(response, HttpResultEnum.FAIL, e.getMessage());
            throw new IOException(e);
        } catch (ServletException e) {
            logger.error(e);
            ResponseUtil.writeMessage(response, HttpResultEnum.FAIL, e.getMessage());
            throw new ServletException(e);
        } */
    }

    /**
     * 从token中获取用户信息并新建一个token
     *
     * @param tokenHeader tokenHeader
     * @return 返回用户信息
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader) {
        String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        String username = JwtTokenUtils.getUsername(token);
        Set<GrantedAuthority> authorities = JwtTokenUtils.getClaims(token);
        if (username != null) {
            return new UsernamePasswordAuthenticationToken(username, null, authorities);
        }
        return null;
    }
}
