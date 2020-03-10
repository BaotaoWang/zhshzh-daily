package cn.com.zhshzh.system.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 菜单信息模型(返回)
 *
 * @author WBT
 * @since 2020/03/10
 */
@Data
@ApiModel(description = "菜单信息模型(返回)")
public class SysMenuInfoResultDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "菜单名称", dataType = "String")
    private String menuInfoName;
}
