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
@ApiModel(description = "参数组信息模型")
public class SysParamGroupInDTO {

    @ApiModelProperty(value = "参数组名", dataType = "String")
    private String paramGroupName; // 参数组名

    @ApiModelProperty(value = "参数组描述", dataType = "String")
    private String description; // 参数组描述
}
