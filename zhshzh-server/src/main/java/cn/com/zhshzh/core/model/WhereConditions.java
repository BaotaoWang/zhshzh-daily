package cn.com.zhshzh.core.model;

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
    private List<ConditionModel> conditionList;

    /**
     * 排序
     */
    private List<OrderByModel> orderByList;

    /**
     * 条数限制-起始
     */
    private int limitFrom;

    /**
     * 条数限制-条数
     */
    private int limitNumber;

    public WhereConditions(List<ConditionModel> conditionList) {
        this.conditionList = conditionList;
    }

    public WhereConditions(List<ConditionModel> conditionList, List<OrderByModel> orderByList) {
        this.conditionList = conditionList;
        this.orderByList = orderByList;
    }

    public WhereConditions(List<ConditionModel> conditionList, int limitNumber) {
        this.conditionList = conditionList;
        this.limitNumber = limitNumber;
    }

    public WhereConditions(List<ConditionModel> conditionList, int limitFrom, int limitNumber) {
        this.conditionList = conditionList;
        this.limitFrom = limitFrom;
        this.limitNumber = limitNumber;
    }

    public WhereConditions(List<ConditionModel> conditionList, List<OrderByModel> orderByList, int limitNumber) {
        this.conditionList = conditionList;
        this.orderByList = orderByList;
        this.limitNumber = limitNumber;
    }

    public WhereConditions(List<ConditionModel> conditionList, List<OrderByModel> orderByList, int limitFrom, int limitNumber) {
        this.conditionList = conditionList;
        this.orderByList = orderByList;
        this.limitFrom = limitFrom;
        this.limitNumber = limitNumber;
    }

}
