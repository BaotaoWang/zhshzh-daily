package cn.com.zhshzh.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Spring Security 权限配置
 *
 * @author WBT
 * @since 2019/10/10
 */
@Configuration
@EnableWebSecurity
public class MySecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    /**
     * 密码生成策略
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    /**
     * 配置根据用户名获取用户密码、角色信息的类
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(myUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    /**
     * 配置要忽略拦截的请求
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                // 忽略对所有swagger请求的拦截
                .antMatchers("/swagger-ui.html", "/v2/api-docs/**", "/webjars/**", "/swagger-resources/**");
    }

    /**
     * 配置用户角色请求路径的权限
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 禁用 Spring Security 自带的跨域处理
        http.csrf().disable();

        // 因为本系统采用JWT进行用户身份认证，所以禁用security的session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // 添加过滤器
        http.addFilter(new JWTAuthenticationFilter(authenticationManager()));
        http.addFilter(new JWTAuthorizationFilter(authenticationManager()));

        // 1.先配置放行不需要认证的 permitAll()
        http.authorizeRequests()
                .antMatchers("/signup", "/test").permitAll();

        // 2.再配置需要特定权限的 hasRole()
        http.authorizeRequests()
                .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN") // "/admin/"开头的URL必须要是管理员用户，譬如"admin"用户
                .antMatchers("/product/**").hasRole("USER")
                .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')");

        // 3.最后配置所有其他的URL都需要用户进行验证
        http.authorizeRequests()
                .anyRequest().authenticated();

        // 使用Java配置默认值设置了基于表单的验证。使用POST提交到"/login"时，需要用"username"和"password"进行验证。
        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successForwardUrl("/user/index")
                .failureUrl("/login-error");
    }
}
