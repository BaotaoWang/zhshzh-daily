package cn.com.zhshzh.core.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 输入建议模型（for element-ui <el-autocomplete />）
 *
 * @author WBT
 * @since 2020/03/15
 */
@Data
@ApiModel(description = "输入建议模型")
public class SuggestionModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "输入建议值", dataType = "String")
    private String value;
}
