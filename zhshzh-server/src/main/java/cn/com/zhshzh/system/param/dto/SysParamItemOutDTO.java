package cn.com.zhshzh.system.param.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 参数信息模型
 *
 * @author WBT
 * @since 2020/03/16
 */
@Data
@ApiModel(description = "参数信息模型")
public class SysParamItemOutDTO {

    @ApiModelProperty(value = "参数id", dataType = "Long")
    private Long paramItemId; // 参数id

    @ApiModelProperty(value = "参数值", dataType = "String")
    private String paramItemValue; // 参数值

    @ApiModelProperty(value = "参数名", dataType = "String")
    private String paramItemName; // 参数名

    @ApiModelProperty(value = "参数序号", dataType = "Integer")
    private Short paramItemOrder; // 参数序号

    @ApiModelProperty(value = "参数描述", dataType = "String")
    private String description; // 参数描述
}
