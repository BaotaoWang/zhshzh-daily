package cn.com.zhshzh.core.constant;

/**
 * 接口返回消息的枚举类
 * (每组异常消息至少预留20个)
 *
 * @author WBT
 * @since 2019/11/30
 */
public enum HttpResultEnum {
    SUCCESS(10000, "成功"),
    FAIL(9000, "失败"),

    /* 系统级异常 */
    EMPTY_DATA(9001, "数据不存在"),
    ERROR_PARAM(9002, "参数错误"),

    /* security异常 */
    EMPTY_USERNAME(9101, "用户名为空"),
    EMPTY_PASSWORD(9102, "密码为空"),
    BAD_CREDENTIALS(9103, "用户名或密码错误"),
    USER_ACCOUNT_IS_LOCKED(9104, "用户帐户已锁定"),
    USER_IS_DISABLED(9105, "用户被禁用"),
    USER_ACCOUNT_HAS_EXPIRED(9106, "用户帐户已过期"),
    USER_CREDENTIALS_HAVE_EXPIRED(9107, "用户凭据已过期"),
    INVALID_TOKEN(9108, "无效的Token"),
    INVALID_SIGNATURE(9109, "无效的JWT签名"),

    /* 代码生成器异常 */
    TABLE_NOT_EXISTS(9121, "数据库表不存在"),
    GENERATOR_ERROR(9122, "代码生成器异常"),
    GENERATOR_NOT_ALLOW(9123, "禁止远程生成代码"),

    /* 头像异常 */
    EMPTY_HEAD_PORTRAIT(9141, "未发现头像文件"),

    /* minio异常 */
    MINIO_EXCEPTION(9161, "文件系统异常"),

    /* 菜单异常 */
    NULL_MENU_STATE(9181, "菜单状态为空");


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
