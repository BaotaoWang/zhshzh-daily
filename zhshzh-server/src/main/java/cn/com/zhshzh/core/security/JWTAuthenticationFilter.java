package cn.com.zhshzh.core.security;

import cn.com.zhshzh.core.constant.HttpResultEnum;
import cn.com.zhshzh.core.constant.RedisKeyConstants;
import cn.com.zhshzh.core.util.ResponseUtil;
import cn.com.zhshzh.system.user.dto.SysUserInfoInDTO;
import cn.com.zhshzh.system.user.po.SysUserInfoPO;
import cn.com.zhshzh.system.user.service.SysUserInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.*;
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
 * @author WBT
 * @since 2019/10/10
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private static final Logger logger = LogManager.getLogger(JWTAuthenticationFilter.class);

    private StringRedisTemplate stringRedisTemplate;
    private AuthenticationManager authenticationManager;
    private SysUserInfoService sysUserInfoService;

    JWTAuthenticationFilter(AuthenticationManager authenticationManager, StringRedisTemplate stringRedisTemplate,
                            SysUserInfoService sysUserInfoService) {
        this.authenticationManager = authenticationManager;
        this.stringRedisTemplate = stringRedisTemplate;
        this.sysUserInfoService = sysUserInfoService;
    }

    /**
     * 用户登录时，在此拦截，从request中获取用户名、密码、是否记住我
     *
     * @param request  request
     * @param response response
     * @return 用户信息
     * @throws AuthenticationException 异常
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

            // MyUserDetailsService.loadUserByUsername()接收不到rememberMe,所以将其放入redis中
            String key = RedisKeyConstants.REMEMBERME_PREFIX + myUser.getUsername() + RedisKeyConstants.REMEMBERME_SUFFIX;
            String rememberMe = Boolean.toString(myUser.isRememberMe());
            stringRedisTemplate.opsForValue().set(key, rememberMe);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(myUser.getUsername(),
                    myUser.getPassword()));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 成功验证后调用的方法
     * 如果验证成功，就生成token并返回
     *
     * @param request    request
     * @param response   response
     * @param chain      chain
     * @param authResult authResult
     * @throws IOException      IO异常
     * @throws ServletException Servlet异常
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        // 查看源代码会发现调用getPrincipal()方法会返回一个实现了`UserDetails`接口的对象
        // 所以就是MyUser啦
        MyUser myUser = (MyUser) authResult.getPrincipal();
        // 用户名
        String username = myUser.getUsername();
        // 获取存入redis中的rememberMe
        String key = RedisKeyConstants.REMEMBERME_PREFIX + username + RedisKeyConstants.REMEMBERME_SUFFIX;
        String rememberMe = stringRedisTemplate.opsForValue().get(key);
        // 获取到rememberMe后，将其从redis中删除
        stringRedisTemplate.delete(key);
        String token = JwtTokenUtils.createToken(username, myUser.getAuthorities(), Boolean.parseBoolean(rememberMe));
        // 返回创建成功的token
        // 但是这里创建的token只是单纯的token
        // 按照jwt的规定，最后请求的格式应该是 `Bearer token`
        response.setHeader(JwtTokenUtils.TOKEN, JwtTokenUtils.TOKEN_PREFIX + token);
        // 根据用户名查询用户信息
        SysUserInfoPO sysUserInfoPO = sysUserInfoService.getSysUserInfoByUsername(username);
        SysUserInfoInDTO sysUserInfoInDTO = new SysUserInfoInDTO();
        BeanUtils.copyProperties(sysUserInfoPO, sysUserInfoInDTO);
        // 返回成功的消息
        ResponseUtil.writeMessage(response, sysUserInfoInDTO);
    }

    /**
     * 验证失败时候调用的方法
     *
     * @param request  request
     * @param response response
     * @param failed   failed
     * @throws IOException      IO异常
     * @throws ServletException Servlet异常
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        String failedMessage = failed.getMessage();
        logger.error(failedMessage);
        // 登录验证失败后，不处理的话，可以直接返回英文消息，这里转换为中文比较友好
        if (failed instanceof UsernameEmptyException) {
            ResponseUtil.writeMessage(response, HttpResultEnum.EMPTY_USERNAME);
            return;
        }
        if (failed instanceof PasswordEmptyException) {
            ResponseUtil.writeMessage(response, HttpResultEnum.EMPTY_PASSWORD);
            return;
        }
        if (failed instanceof BadCredentialsException) {
            ResponseUtil.writeMessage(response, HttpResultEnum.BAD_CREDENTIALS);
            return;
        }
        if (failed instanceof LockedException) {
            ResponseUtil.writeMessage(response, HttpResultEnum.USER_ACCOUNT_IS_LOCKED);
            return;
        }
        if (failed instanceof DisabledException) {
            ResponseUtil.writeMessage(response, HttpResultEnum.USER_IS_DISABLED);
            return;
        }
        if (failed instanceof AccountExpiredException) {
            ResponseUtil.writeMessage(response, HttpResultEnum.USER_ACCOUNT_HAS_EXPIRED);
            return;
        }
        if (failed instanceof CredentialsExpiredException) {
            ResponseUtil.writeMessage(response, HttpResultEnum.USER_CREDENTIALS_HAVE_EXPIRED);
            return;
        }
        ResponseUtil.writeMessage(response, HttpResultEnum.FAIL, failedMessage);
    }
}
