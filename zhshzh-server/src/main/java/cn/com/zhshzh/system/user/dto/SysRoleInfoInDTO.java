package cn.com.zhshzh.system.user.dto;

import lombok.Data;

/**
 * 系统角色信息的数据传输对象
 */
@Data
public class SysRoleInfoInDTO {
    /**
     * 角色id
     */
    private Long roleInfoId;

    /**
     * 角色标识
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色描述
     */
    private String roleDescription;

    public SysRoleInfoInDTO() {
        super();
    }
}
