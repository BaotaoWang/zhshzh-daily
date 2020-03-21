package cn.com.zhshzh.core.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Element-ui 选择器数据模型
 *
 * @author WBT
 * @since 2020/03/14
 */
@Data
@ApiModel(description = "参数组数据模型")
public class SelectDataModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "参数值", dataType = "String")
    private String value; // 参数值

    @ApiModelProperty(value = "展示名称", dataType = "String")
    private String label; // 展示名称

    @ApiModelProperty(value = "参数顺序", dataType = "Integer")
    private Short order; // 参数顺序
}
