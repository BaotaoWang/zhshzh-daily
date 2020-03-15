package cn.com.zhshzh.core.model;

import lombok.Data;

import java.util.List;

/**
 * Element-ui 级联选择器数据模型
 *
 * @author WBT
 * @since 2020/03/14
 */
@Data
public class CascaderDataModel {
    // 绑定值
    private String value;
    // 展示名称
    private String label;
    // 顺序
    private Integer order;
    // 子级数据
    private List<CascaderDataModel> children;
}
