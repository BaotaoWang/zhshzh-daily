package cn.com.zhshzh.core.interceptor;

import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 系统请求拦截器，用于系统的日志审计
 *
 * @author WBT
 * @since 2019/12/14
 */
@Component
public class LogInterceptor implements HandlerInterceptor {
    private static final Logger logger = LogManager.getLogger(LogInterceptor.class);
    private static final String REQUEST_TIME = "REQUEST_TIME";

    /**
     * 请求方法之前被调用
     *
     * @param request  request
     * @param response response
     * @param handler  handler
     * @return true
     * @throws Exception Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            // 请求时间
            long requestTime = System.currentTimeMillis();
            request.setAttribute(REQUEST_TIME, requestTime);
        } catch (Exception e) {
            logger.error(e);
        }
        return true;
    }

    /**
     * 请求方法之后被调用，该方法总会被调用，如果出现了异常，将被封装到Exception对象中
     *
     * @param request  request
     * @param response response
     * @param handler  handler
     * @param ex       ex
     * @throws Exception Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        try {
            // 请求时间
            long requestTime = (long) request.getAttribute(REQUEST_TIME);
            // 请求路径
            String path = request.getServletPath();
            // 请求方式
            String type = request.getMethod();
            // 请求参数
            String param = request.getQueryString();
            // 请求用户
            String userName = request.getRemoteUser();
            // 用户权限信息
            String principal = JSON.toJSONString(request.getUserPrincipal());
            // 客户端的IP地址
            String remoteAddr = request.getRemoteAddr();
            // 客户端浏览器的主机名
            String remoteHost = request.getRemoteHost();

            // 响应时间
            long responseTime = System.currentTimeMillis();
            // 处理时长
            long processTime = responseTime - requestTime;

            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            Class<?> clazz = method.getDeclaringClass();
            // 请求的类名
            String className = clazz.getName();
            // 请求的方法名
            String methodName = method.getName();
        } catch (Exception e) {
            logger.error(e);
        }
    }
}
