package cn.com.zhshzh.core.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Element-ui 级联选择器数据模型
 *
 * @author WBT
 * @since 2020/03/14
 */
@Data
@ApiModel(description = "级联参数数据模型")
public class CascaderDataModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "参数值", dataType = "String")
    private String value; // 参数值

    @ApiModelProperty(value = "展示名称", dataType = "String")
    private String label; // 展示名称

    @ApiModelProperty(value = "顺序", dataType = "String")
    private Integer order; // 顺序

    @ApiModelProperty(value = "子级数据", dataType = "String")
    private List<CascaderDataModel> children; // 子级数据
}
