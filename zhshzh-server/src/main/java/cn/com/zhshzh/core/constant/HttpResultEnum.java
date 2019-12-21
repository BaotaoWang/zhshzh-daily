package cn.com.zhshzh.core.constant;

/**
 * 接口返回消息的枚举类
 *
 * @author WBT
 * @since 2019/11/30
 */
public enum HttpResultEnum {
    SUCCESS(10000, "成功"),
    FAIL(9000, "失败"),

    EMPTY_USERNAME(9101, "用户名为空"),
    EMPTY_PASSWORD(9102, "密码为空"),
    BAD_CREDENTIALS(9103, "用户名或密码错误"),
    USER_ACCOUNT_IS_LOCKED(9104, "用户帐户已锁定"),
    USER_IS_DISABLED(9105, "用户被禁用"),
    USER_ACCOUNT_HAS_EXPIRED(9106, "用户帐户已过期"),
    USER_CREDENTIALS_HAVE_EXPIRED(9107, "用户凭据已过期"),
    INVALID_TOKEN(9108, "无效的Token"),
    INVALID_SIGNATURE(9109, "无效的JWT签名"),

    GENERATOR_ERROR(9120, "代码生成器异常");
    private int code;
    private String message;

    HttpResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
