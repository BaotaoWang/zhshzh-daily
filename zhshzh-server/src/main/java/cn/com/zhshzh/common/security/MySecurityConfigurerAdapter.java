package cn.com.zhshzh.common.security;

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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable() // 禁用 Spring Security 自带的跨域处理
                .authorizeRequests() // 配置路径拦截，表明路径访问所对应的权限，角色，认证信息。
                .antMatchers("/signup", "/test").permitAll() // 任何人(包括没有经过验证的)都可以访问"/signup"和"/about"
                .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN") // "/admin/"开头的URL必须要是管理员用户，譬如"admin"用户
                .antMatchers("/product/**").hasRole("USER")
                .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
                .anyRequest().authenticated() // 所有其他的URL都需要用户进行验证
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 调整为让 Spring Security不创建和使用 session
                .and() // 每个模块配置使用and结尾
                .formLogin() // 使用Java配置默认值设置了基于表单的验证。使用POST提交到"/login"时，需要用"username"和"password"进行验证。
                .usernameParameter("username")
                .passwordParameter("password")
                .failureForwardUrl("/login?error")
                .loginPage("/login") //  注明了登陆页面，意味着用GET访问"/login"时，显示登陆页面
                .permitAll() //任何人(包括没有经过验证的)都可以访问"/login"和"/login?error"。permitAll()是指用户可以访问formLogin()相关的任何URL。
                .and() // 每个模块配置使用and结尾
                .logout() // 对应了注销相关的配置
                .logoutUrl("/logout") // 指定注销路径
                .logoutSuccessUrl("/index") // 指定成功注销后跳转到指定的页面
                //.logoutSuccessHandler(logoutSuccessHandler)  // 指定成功注销后处理类 如果使用了logoutSuccessHandler()的话， logoutSuccessUrl()就会失效
                //.invalidateHttpSession(true)  // httpSession是否有效时间，如果使用了 SecurityContextLogoutHandler，其将被覆盖
                //.addLogoutHandler(logoutHandler)  // 在最后增加默认的注销处理类LogoutHandler
                //.deleteCookies(cookieNamesToClear); // 指定注销成功后remove cookies
                .permitAll();
        //.and() // 每个模块配置使用and结尾
        //.httpBasic(); // 可以配置basic登录
        //.disable();
    }
}
