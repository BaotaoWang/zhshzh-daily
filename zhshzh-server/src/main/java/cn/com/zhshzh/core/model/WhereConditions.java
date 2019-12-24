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
}
