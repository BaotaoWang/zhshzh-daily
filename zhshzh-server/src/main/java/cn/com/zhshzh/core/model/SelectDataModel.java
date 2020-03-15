package cn.com.zhshzh.core.model;

import lombok.Data;

/**
 * Element-ui 选择器数据模型
 *
 * @author WBT
 * @since 2020/03/14
 */
@Data
public class SelectDataModel {
    // 绑定值
    private String value;
    // 展示名称
    private String label;
}
