package cn.com.zhshzh.core.aspect;

import cn.com.zhshzh.core.constant.RedisKeyConstants;
import cn.com.zhshzh.system.interfaceLog.po.SysInterfaceLogPO;
import cn.com.zhshzh.system.interfaceLog.service.SysInterfaceLogService;
import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 系统接口调用日志的切面
 *
 * @author WBT
 * @since 2019/12/28
 */
@Aspect
@Component
public class SysInterfaceLogAspect {
    private static final Logger logger = LogManager.getLogger(SysInterfaceLogAspect.class);

    private SysInterfaceLogService sysInterfaceLogService;
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private SysInterfaceLogAspect(SysInterfaceLogService sysInterfaceLogService, StringRedisTemplate stringRedisTemplate) {
        this.sysInterfaceLogService = sysInterfaceLogService;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * 记录系统接口调用日志的所有切入点
     */
    @Pointcut("execution(* cn.com.zhshzh..*Controller.*(..))")
    private void controllerPointcut() {
    }

    /**
     * 分析并保存系统接口调用信息
     *
     * @param pjp 切入点信息
     * @return 响应数据
     */
    @Around("controllerPointcut()")
    private Object around(ProceedingJoinPoint pjp) {
        Object result = null;
        SysInterfaceLogPO sysInterfaceLogPO = new SysInterfaceLogPO();
        try {
            // 请求时间
            Long requestTime = System.currentTimeMillis();
            // ServletRequest信息
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            // 执行切入点的逻辑
            result = pjp.proceed();

            // 请求处理类的信息
            Signature signature = pjp.getSignature();
            // 请求的类名
            sysInterfaceLogPO.setClassName(signature.getDeclaringType().getName());
            // 请求的方法名
            sysInterfaceLogPO.setMethodName(signature.getName());
            // 如果获取不到request信息，不保存接口请求日志
            if (attributes == null) {
                return pjp.proceed();
            }
            HttpServletRequest request = attributes.getRequest();
            // 请求URL
            sysInterfaceLogPO.setRequestUrl(new String(request.getRequestURL()));
            // 请求用户名
            String username = request.getRemoteUser();
            sysInterfaceLogPO.setUserName(username);
            // 根据用户名从redis中获取用户id
            String userIdKey = RedisKeyConstants.USERID_PREFIX + username + RedisKeyConstants.USERID_SUFFIX;
            String userId = stringRedisTemplate.opsForValue().get(userIdKey);
            if (!StringUtils.isEmpty(userId)) {
                sysInterfaceLogPO.setUserId(Long.parseLong(userId));
                sysInterfaceLogPO.setCreateBy(userId);
                sysInterfaceLogPO.setUpdateBy(userId);
            }
            // 请求用户的权限信息
            sysInterfaceLogPO.setPrincipal(JSON.toJSONString(request.getUserPrincipal()));
            // 客户端ip
            sysInterfaceLogPO.setClientIp(request.getRemoteAddr());
            // 服务端ip
            sysInterfaceLogPO.setServerIp(request.getServerName());
            // 服务端端口号
            sysInterfaceLogPO.setServerPort(request.getServerPort());
            // 响应时间
            Long responseTime = System.currentTimeMillis();
            // 处理时长
            sysInterfaceLogPO.setProcessTime(responseTime - requestTime);
            // 响应数据
            sysInterfaceLogPO.setResponseData(JSON.toJSONString(result));
            // 请求方式
            sysInterfaceLogPO.setRequestType(request.getMethod());
            if (HttpMethod.GET.matches(request.getMethod())) {
                // GET请求获取参数
                sysInterfaceLogPO.setRequestData(request.getQueryString());
            } else {
                // POST/PUT请求获取参数
                Object[] args = pjp.getArgs();
                if (args != null) {
                    List<Object> objects = new ArrayList<>();
                    for (Object arg : args) {
                        if (arg instanceof ServletRequest || arg instanceof ServletResponse
                                || arg instanceof MultipartFile || arg instanceof BindingResult) {
                            continue;
                        }
                        objects.add(arg);
                    }
                    sysInterfaceLogPO.setRequestData(JSON.toJSONString(objects));
                }
            }
        } catch (Throwable throwable) {
            logger.error(throwable.getMessage(), throwable);
            sysInterfaceLogPO.setRequestException(throwable.getMessage());
        }
        // 保存接口请求日志
        sysInterfaceLogService.saveInterfaceLog(sysInterfaceLogPO);
        return result;
    }
}
