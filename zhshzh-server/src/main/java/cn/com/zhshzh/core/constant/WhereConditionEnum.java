package cn.com.zhshzh.core.constant;

/**
 * mybatis中查询条件的枚举
 *
 * @author WBT
 * @since 2019/12/21
 */
public enum WhereConditionEnum {
    EQUALS("=", "等于"),
    NOT_EQUALS("<>", "不等于"),
    LIKE("LIKE", "模糊查询（包含）"),
    NOT_LIKE("NOT LIKE", "模糊查询（不包含）"),
    GREATER_THAN(">", "大于"),
    LESS_THAN("<", "小于"),
    GREATER_THAN_OR_EQUAL_TO(">=", "大于等于"),
    LESS_THAN_OR_EQUAL_TO("<=", "小于等于"),
    BETWEEN("BETWEEN", "区间"),
    IN("IN", "IN"),
    NOT_IN("NOT IN", "NOT IN");

    private String condition;
    private String description;

    WhereConditionEnum(String condition, String description) {
        this.condition = condition;
        this.description = description;
    }

    public String getCondition() {
        return condition;
    }

    public String getDescription() {
        return description;
    }
}
