package cn.com.zhshzh.core.model;

import cn.com.zhshzh.core.constant.OrderByEnum;
import cn.com.zhshzh.core.constant.WhereConditionEnum;
import lombok.Data;

import java.util.List;

/**
 * 封装查询条件的对象
 *
 * @author WBT
 * @since 2019/12/22
 */
@Data
public class WhereConditions {
    /**
     * 查询条件
     */
    private List<Condition> conditionList;

    /**
     * 排序
     */
    private List<OrderBy> orderByList;

    /**
     * 条件查询时的查询条件
     *
     * @author WBT
     * @since 2019/12/22
     */
    @Data
    public static class Condition {
        /**
         * 查询条件中的字段
         */
        private String columnName;

        /**
         * 查询条件的枚举
         */
        private WhereConditionEnum whereConditionEnum;

        /**
         * 查询条件中的值（等于、模糊查询时用）
         */
        private String value;

        /**
         * 查询条件中的开始值（大于等于时用）
         */
        private String startValue;

        /**
         * 查询条件中的结束值（小于等于时用）
         */
        private String endValue;

        /**
         * 查询条件中值的数组（IN和NOT IN时用）
         */
        private String[] values;

        /**
         * 构造方法（查询条件是等于时专用）
         *
         * @param columnName 查询条件中的字段
         * @param value      查询条件中的值
         */
        public Condition(String columnName, String value) {
            this.columnName = columnName;
            this.value = value;
            this.whereConditionEnum = WhereConditionEnum.EQUALS;
        }

        /**
         * 构造方法（查询条件是等于、大于等于、小于等于和模糊查询时用）
         *
         * @param columnName         查询条件中的字段
         * @param whereConditionEnum 查询条件的枚举
         * @param value              查询条件中的值
         */
        public Condition(String columnName, WhereConditionEnum whereConditionEnum, String value) {
            this.columnName = columnName;
            this.whereConditionEnum = whereConditionEnum;
            this.value = value;
        }

        /**
         * 区间查询时专用
         *
         * @param columnName 查询条件中的字段
         * @param startValue 开始值
         * @param endValue   结束值
         */
        public Condition(String columnName, String startValue, String endValue) {
            this.columnName = columnName;
            this.startValue = startValue;
            this.endValue = endValue;
        }

        /**
         * 查询条件是IN或者NOT IN时专用
         *
         * @param columnName         查询条件中的字段
         * @param whereConditionEnum 查询条件的枚举
         * @param values             查询条件中值的数组
         */
        public Condition(String columnName, WhereConditionEnum whereConditionEnum, String[] values) {
            this.columnName = columnName;
            this.whereConditionEnum = whereConditionEnum;
            this.values = values;
        }
    }

    /**
     * 条件查询时的排序
     *
     * @author WBT
     * @since 2019/12/22
     */
    @Data
    public static class OrderBy {
        /**
         * 要排序的字段
         */
        private String sort;

        /**
         * 顺序的枚举
         */
        private OrderByEnum orderByEnum;

        public OrderBy(String sort, OrderByEnum orderByEnum) {
            this.sort = sort;
            this.orderByEnum = orderByEnum;
        }
    }
}
