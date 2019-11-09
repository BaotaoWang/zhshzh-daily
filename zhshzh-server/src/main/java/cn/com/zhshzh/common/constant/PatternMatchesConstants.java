package cn.com.zhshzh.common.constant;

/**
 * 正则表达式常量类
 *
 * @author wbt
 * @since 2019/10/17
 */
public class PatternMatchesConstants {
    /**
     * 匹配邮箱
     */
    public static final String EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

    /**
     * 匹配固定电话号码("XXX-XXXXXXX"、"XXXX-XXXXXXXX"、"XXX-XXXXXXX"、"XXX-XXXXXXXX"、"XXXXXXX"和"XXXXXXXX)
     */
    public static final String PHONE_NUMBER = "^(\\(\\d{3,4}-)|\\d{3.4}-)?\\d{7,8}$";

    /**
     * 匹配手机号码
     */
    public static final String MOBILE_NUMBER = "^1[3|4|5|6|7|8|9]\\d{9}$";

    /**
     * 匹配登录账号(字母开头，允许4-16字节，允许字母数字下划线)
     */
    public static final String USER_ACCOUNT = "^[a-zA-Z][a-zA-Z0-9_]{3,15}$";

    /**
     * 匹配中文
     */
    public static final String CHINESE = "[\\u4e00-\\u9fa5]";

    /**
     * 匹配邮编
     */
    public static final String POST_CODE = "[1-9]\\d{5}(?!\\d)";

    /**
     * 匹配IP地址
     */
    public static final String IP_ADDRESS = "((?:(?:25[0-5]|2[0-4]\\\\d|[01]?\\\\d?\\\\d)\\\\.){3}(?:25[0-5]|2[0-4]\\\\d|[01]?\\\\d?\\\\d))";

    /**
     * 匹配员工号
     */
    public static final String USER_SERIAL_NUMBER = "^0\\d{5}$";
}
