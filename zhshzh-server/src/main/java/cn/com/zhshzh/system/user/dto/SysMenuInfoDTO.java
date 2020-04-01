package cn.com.zhshzh.system.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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

    @NotBlank(message = "【父级菜单】不能为空")
    @ApiModelProperty(value = "父级菜单id", dataType = "long", required = true)
    private Long parentId; // 用户id

    @NotBlank(message = "【菜单名称】不能为空")
    @Size(max = 32, message = "【菜单名称】字符长度不得大于32")
    @ApiModelProperty(value = "菜单名称", dataType = "String", required = true)
    private String menuName; // 菜单名称

    @NotBlank(message = "【菜单路由】不能为空")
    @Size(max = 32, message = "【菜单路由】字符长度不得大于32")
    @ApiModelProperty(value = "菜单路由", dataType = "String", required = true)
    private String menuRoute; // 菜单路由

    @NotBlank(message = "【菜单序号】不能为空")
    @Max(value = 10000, message = "【菜单序号】不得大于10000")
    @ApiModelProperty(value = "菜单序号", dataType = "Integer", required = true)
    private Short menuOrder; // 菜单序号

    @Size(max = 32, message = "【菜单图标】字符长度不得大于32")
    @ApiModelProperty(value = "菜单图标", dataType = "String")
    private String menuIcon; // 菜单图标

    @Size(max = 32, message = "【菜单描述】字符长度不得大于200")
    @ApiModelProperty(value = "菜单描述", dataType = "String")
    private String menuDescription; // 菜单描述
}
