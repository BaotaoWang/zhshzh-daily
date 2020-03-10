package cn.com.zhshzh.system.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 菜单信息模型(新增/更新)
 *
 * @author WBT
 * @since 2020/03/10
 */
@Data
@ApiModel(description = "菜单信息模型(新增/更新)")
public class SysMenuInfoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id", dataType = "long", required = true, hidden = true)
    private Long userInfoId; // 用户id

    @ApiModelProperty(value = "菜单url", dataType = "String", required = true)
    private String menuInfoUrl; // 菜单url
}
