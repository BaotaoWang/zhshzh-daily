package cn.com.zhshzh.core.constant;

/**
 * redis中key的常量类
 * key的命名规范(暂定):
 * A:B:C:D
 * A->数据所在表名
 * B->数据所属对象名
 * C->数据所属对象
 * D->数据别名(所在表的列)
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

    // 用户角色id-前缀
    public static final String ROLEID_PREFIX = "sys_role_info:userid:";
    // 用户角色id-后缀
    public static final String ROLEID_SUFFIX = ":roleid";
}
