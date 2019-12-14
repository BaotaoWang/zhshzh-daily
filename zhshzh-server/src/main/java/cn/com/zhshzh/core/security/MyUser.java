package cn.com.zhshzh.core.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

@Data
public class MyUser implements UserDetails {

    private static final long serialVersionUID = -8972455339484022256L;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 是否记住我
     */
    private boolean rememberMe;

    /**
     * 用户权限的集合
     */
    private Set<GrantedAuthority> authorities;

    /**
     * 账户是否过期,过期无法验证
     */
    private boolean accountNonExpired;

    /**
     * 指定用户是否被锁定或者解锁,锁定的用户无法进行身份验证
     */
    private boolean accountNonLocked;

    /**
     * 指示是否已过期的用户的凭据(密码),过期的凭据防止认证
     */
    private boolean credentialsNonExpired;

    /**
     * 是否被禁用,禁用的用户不能身份验证
     */
    private boolean enabled;

    public MyUser() {
        super();
    }

    MyUser(String username, String password, Set<GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    /**
     * 重写UserDetails的方法
     * 返回false的话，会验证失败(User account is locked)
     *
     * @return true
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 重写UserDetails的方法
     * 返回false的话，会验证失败(User is disabled)
     *
     * @return true
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * 重写UserDetails的方法
     * 返回false的话，会验证失败(User account has expired)
     *
     * @return true
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 重写UserDetails的方法
     * 返回false的话，会验证失败(User credentials have expired)
     *
     * @return true
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
