package cn.com.zhshzh.system.user.po;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * sys_role_menu_relation
 * 角色-菜单关系PO
 *
 * @author Generator
 * @since 2020/03/22
 */
@Data
public class SysRoleMenuRelationPO implements Serializable {

    /**
     * rm_relation_id
     * 角色-菜单关系id
     */
    private Long rmRelationId;

    /**
     * role_info_id
     * 角色id
     */
    private Long roleInfoId;

    /**
     * menu_info_id
     * 菜单id
     */
    private Long menuInfoId;

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
    public SysRoleMenuRelationPO() {
        super();
    }

    // 以上代码由代码生成器自动生成，原则上不允许任何修改, 如需自定义，请在下面补充
}