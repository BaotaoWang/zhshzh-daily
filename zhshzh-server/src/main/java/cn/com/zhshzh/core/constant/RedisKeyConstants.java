package cn.com.zhshzh.core.constant;

/**
 * redis中key的常量类
 *
 * @author WBT
 * @since 2019/12/28
 */
public class RedisKeyConstants {
    // rememberMe-前缀
    public static final String REMEMBERME_PREFIX = "sys_user_info:username:";
    // rememberMe-后缀
    public static final String REMEMBERME_SUFFIX = ":rememberMe";

    // 用户id-前缀
    public static final String USERID_PREFIX = "sys_user_info:username:";
    // 用户id-后缀
    public static final String USERID_SUFFIX = ":userid";
}
