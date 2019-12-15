package cn.com.zhshzh.core.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置自定义的拦截器
 *
 * @author WBT
 * @since 2019/12/14
 */
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {
    /**
     * 注册日志审计的拦截器
     *
     * @param registry registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor()).addPathPatterns("/**");
    }
}
