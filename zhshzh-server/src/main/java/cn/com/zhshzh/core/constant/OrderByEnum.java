package cn.com.zhshzh.core.constant;

/**
 * mybatis中查询数据顺序的枚举
 *
 * @author WBT
 * @since 2019/12/22
 */
public enum OrderByEnum {
    ASC("ASC", "升序"),
    DESC("DESC", "降序");

    private String order;
    private String description;

    OrderByEnum(String order, String description) {
        this.order = order;
        this.description = description;
    }

    public String getOrder() {
        return order;
    }

    public String getDescription() {
        return description;
    }
}
