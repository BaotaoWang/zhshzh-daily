package cn.com.zhshzh.system.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 菜单状态模型(禁用/启用)
 *
 * @author WBT
 * @since 2020/03/14
 */
@Data
@ApiModel(description = "菜单状态模型(禁用/启用)")
public class SysMenuInfoStateDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id", dataType = "long", required = true, hidden = true)
    private Long userInfoId; // 用户id

    @ApiModelProperty(value = "是否禁用", required = true, dataType = "Boolean")
    private Boolean disabled; // 是否禁用
}
