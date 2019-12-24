package cn.com.zhshzh.core.model;

import cn.com.zhshzh.core.constant.OrderByEnum;

/**
 * 条件查询时的排序
 * 无get、set方法，只允许通过构造器初始化值
 *
 * @author WBT
 * @since 2019/12/23
 */
public class OrderByModel {
    /**
     * 要排序的字段
     */
    private String sort;

    /**
     * 顺序的枚举
     */
    private OrderByEnum orderByEnum;

    public OrderByModel(String sort, OrderByEnum orderByEnum) {
        this.sort = sort;
        this.orderByEnum = orderByEnum;
    }
}
