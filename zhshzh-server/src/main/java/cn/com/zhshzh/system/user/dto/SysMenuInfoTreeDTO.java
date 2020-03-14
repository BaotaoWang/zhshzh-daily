package cn.com.zhshzh.system.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 菜单信息模型(树状结构)
 *
 * @author WBT
 * @since 2020/03/10
 */
@Data
@ApiModel(description = "菜单信息模型(树状结构)")
public class SysMenuInfoTreeDTO {
    @ApiModelProperty(value = "菜单id", dataType = "Long")
    private Long menuInfoId; // 菜单id

    @ApiModelProperty(value = "菜单名称", dataType = "String")
    private String menuName; // 菜单名称

    @ApiModelProperty(value = "菜单路由", dataType = "String")
    private String menuRoute; // 菜单路由

    @ApiModelProperty(value = "菜单序号", dataType = "Integer")
    private Short menuOrder; // 菜单序号

    @ApiModelProperty(value = "菜单图标", dataType = "String")
    private String menuIcon; // 菜单图标

    @ApiModelProperty(value = "菜单描述", dataType = "String")
    private String menuDescription; // 菜单描述

    @ApiModelProperty(value = "子菜单", dataType = "List")
    private List<SysMenuInfoTreeDTO> children; // 子菜单

    @ApiModelProperty(value = "是否禁用", dataType = "Boolean")
    private Boolean disabled; // 是否禁用
}
