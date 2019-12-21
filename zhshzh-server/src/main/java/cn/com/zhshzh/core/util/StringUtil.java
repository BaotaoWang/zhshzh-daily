package cn.com.zhshzh.core.util;

import org.springframework.util.StringUtils;

/**
 * 操作字符串的工具类
 *
 * @author WBT
 * @since 2019/12/15
 */
public class StringUtil {

    /**
     * 下划线转驼峰
     *
     * @param string      下划线字符串
     * @param isLowerCase 首字母是否小写
     * @return 驼峰字符串
     */
    public static String underlineToCamelCase(String string, boolean isLowerCase) {
        if (StringUtils.isEmpty(string) || !string.contains("_")) {
            return string;
        }
        // 全部字符转小写
        string = string.toLowerCase();
        String[] array = string.split("_");
        StringBuilder newString = new StringBuilder();
        // 转驼峰
        for (String str : array) {
            str = str.trim();
            if (!StringUtils.isEmpty(str)) {
                newString.append(str.substring(0, 1).toUpperCase()).append(str.substring(1));
            }
        }
        String camelCaseString = newString.toString();
        // 如果首字母小写
        if (isLowerCase && camelCaseString.length() > 1) {
            camelCaseString = camelCaseString.substring(0, 1).toLowerCase() + camelCaseString.substring(1);
        }
        return camelCaseString;
    }
}
