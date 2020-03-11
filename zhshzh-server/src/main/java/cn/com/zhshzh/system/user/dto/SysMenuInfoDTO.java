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

    @ApiModelProperty(value = "父级菜单id", dataType = "long", required = true)
    private Long parentId; // 用户id

    @ApiModelProperty(value = "菜单名称", dataType = "String", required = true)
    private String menuName; // 菜单名称

    @ApiModelProperty(value = "菜单路由", dataType = "String", required = true)
    private String menuRoute; // 菜单路由

    @ApiModelProperty(value = "菜单序号", dataType = "Integer", required = true)
    private Short menuOrder; // 菜单序号

    @ApiModelProperty(value = "菜单图标", dataType = "String", required = true)
    private String menuIcon; // 菜单图标

    @ApiModelProperty(value = "菜单描述", dataType = "String")
    private String menuDescription; // 菜单描述
}
