package cn.com.zhshzh.system.param.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 参数组信息模型
 *
 * @author WBT
 * @since 2020/03/20
 */
@Data
@ApiModel(description = "参数信息模型")
public class SysParamItemInDTO {

    @ApiModelProperty(value = "父级参数id", dataType = "Long")
    private Long parentId; // 父级参数id

    @ApiModelProperty(value = "参数组id", dataType = "Long", required = true)
    private Long paramGroupId; // 参数组id

    @ApiModelProperty(value = "参数值", dataType = "String", required = true)
    private String paramItemValue; // 参数值

    @ApiModelProperty(value = "参数名", dataType = "String", required = true)
    private String paramItemName; // 参数名

    @ApiModelProperty(value = "参数序号", dataType = "Integer", required = true)
    private Short paramItemOrder; // 参数序号

    @ApiModelProperty(value = "参数描述", dataType = "String")
    private String description; // 参数描述
}
