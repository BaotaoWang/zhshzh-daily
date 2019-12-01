package cn.com.zhshzh.core.security;

import org.springframework.security.authentication.AccountStatusException;

/**
 * 密码为空时抛出的异常
 *
 * @author WBT
 * @since 2019/12/01
 */
public class PasswordEmptyException extends AccountStatusException {
    /**
     * 构造方法
     *
     * @param msg 详细信息
     */
    public PasswordEmptyException(String msg) {
        super(msg);
    }
}
