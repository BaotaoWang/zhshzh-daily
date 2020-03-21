package cn.com.zhshzh.system.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 头像信息模型(返回)
 *
 * @author WBT
 * @since 2020/03/14
 */
@Data
@ApiModel(description = "头像信息模型(返回)")
public class FileHeadPortraitOutDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "头像版本", dataType = "Integer")
    private Integer version; // 头像版本

    @ApiModelProperty(value = "base64位的头像字符串", dataType = "String")
    private String headPortrait; // base64位的头像字符串
}
