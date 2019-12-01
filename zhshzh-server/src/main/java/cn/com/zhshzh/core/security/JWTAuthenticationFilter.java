package cn.com.zhshzh.core.security;

import cn.com.zhshzh.core.constant.HttpResultEnum;
import cn.com.zhshzh.core.util.ResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * security身份认证过滤器
 *
 * @author wbt
 * @since 2019/10/10
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private static final Logger logger = LogManager.getLogger(JWTAuthenticationFilter.class);

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * 用户登录时，在此拦截，从request中获取用户名、密码、是否记住我
     *
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        // 从输入流中获取到登录的信息
        try {
            MyUser myUser = new ObjectMapper().readValue(request.getInputStream(), MyUser.class);
            if (StringUtils.isEmpty(myUser.getUsername())) {
                throw new UsernameEmptyException("Empty username");
            }
            if (StringUtils.isEmpty(myUser.getPassword())) {
                throw new PasswordEmptyException("Empty password");
            }
            // TODO 系统加上redis后，要把myUser.isRememberMe()放入缓存中（by:WBT）
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(myUser.getUsername(),
                    myUser.getPassword()));
        } catch (IOException e) {
            logger.error(e);
            return null;
        }
    }

    /**
     * 成功验证后调用的方法
     * 如果验证成功，就生成token并返回
     *
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        // 查看源代码会发现调用getPrincipal()方法会返回一个实现了`UserDetails`接口的对象
        // 所以就是JwtUser啦
        MyUser myUser = (MyUser) authResult.getPrincipal();
        // TODO 系统加上redis后，要从缓存中获取myUser.isRememberMe()，目前取不到值（by:WBT）
        String token = JwtTokenUtils.createToken(myUser.getUsername(), myUser.getAuthorities(), myUser.isRememberMe());
        // 返回创建成功的token
        // 但是这里创建的token只是单纯的token
        // 按照jwt的规定，最后请求的格式应该是 `Bearer token`
        response.setHeader(JwtTokenUtils.TOKEN, JwtTokenUtils.TOKEN_PREFIX + token);
        // 返回成功的消息
        ResponseUtil.writeMessage(response);
    }

    /**
     * 验证失败时候调用的方法
     *
     * @param request
     * @param response
     * @param failed
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        String failedMessage = failed.getMessage();
        // 登录验证失败后，不处理的话，可以直接返回英文消息，这里转换为中文比较友好
        switch (failedMessage) {
            case "Empty username":
                ResponseUtil.writeMessage(response, HttpResultEnum.EMPTY_USERNAME);
                break;
            case "Empty password":
                ResponseUtil.writeMessage(response, HttpResultEnum.EMPTY_PASSWORD);
                break;
            case "Bad credentials":
                ResponseUtil.writeMessage(response, HttpResultEnum.BAD_CREDENTIALS);
                break;
            case "User account is locked":
                ResponseUtil.writeMessage(response, HttpResultEnum.USER_ACCOUNT_IS_LOCKED);
                break;
            case "User is disabled":
                ResponseUtil.writeMessage(response, HttpResultEnum.USER_IS_DISABLED);
                break;
            case "User account has expired":
                ResponseUtil.writeMessage(response, HttpResultEnum.USER_ACCOUNT_HAS_EXPIRED);
                break;
            case "User credentials have expired":
                ResponseUtil.writeMessage(response, HttpResultEnum.USER_CREDENTIALS_HAVE_EXPIRED);
                break;
            default:
                ResponseUtil.writeMessage(response, HttpResultEnum.FAIL, failedMessage);
        }
    }
}
