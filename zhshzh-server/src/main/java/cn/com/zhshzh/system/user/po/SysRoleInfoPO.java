package cn.com.zhshzh.system.user.po;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * sys_role_info
 * 系统角色PO
 *
 * @author Generator
 * @since 2020/03/22
 */
@Data
public class SysRoleInfoPO implements Serializable {

    /**
     * role_info_id
     * 角色id
     */
    private Long roleInfoId;

    /**
     * role_code
     * 角色标识
     */
    private String roleCode;

    /**
     * role_name
     * 角色名称
     */
    private String roleName;

    /**
     * role_description
     * 角色描述
     */
    private String roleDescription;

    /**
     * is_deleted
     * 是否已删除（0：false-未删除； 1：true-已删除）
     */
    private Boolean deleted;

    /**
     * create_time
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * update_time
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * create_by
     * 创建人
     */
    private Long createBy;

    /**
     * update_by
     * 修改人
     */
    private Long updateBy;

    /**
     * 无参构造方法
     */
    public SysRoleInfoPO() {
        super();
    }

    // 以上代码由代码生成器自动生成，原则上不允许任何修改, 如需自定义，请在下面补充
}