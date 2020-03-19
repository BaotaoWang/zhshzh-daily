package cn.com.zhshzh.system.user.po;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * sys_menu_info
 * 系统菜单PO
 *
 * @author Generator
 * @since 2020/03/19
 */
@Data
public class SysMenuInfoPO implements Serializable {

    /**
     * menu_info_id
     * 菜单id
     */
    private Long menuInfoId;

    /**
     * menu_name
     * 菜单名称
     */
    private String menuName;

    /**
     * menu_route
     * 菜单路由
     */
    private String menuRoute;

    /**
     * parent_id
     * 父级菜单id
     */
    private Long parentId;

    /**
     * menu_order
     * 菜单序号
     */
    private Short menuOrder;

    /**
     * menu_icon
     * 菜单图标
     */
    private String menuIcon;

    /**
     * menu_description
     * 菜单描述
     */
    private String menuDescription;

    /**
     * is_disabled
     * 是否禁用（0：false-不禁用； 1：true-禁用）
     */
    private Boolean disabled;

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
    public SysMenuInfoPO() {
        super();
    }

    // 以上代码由代码生成器自动生成，原则上不允许任何修改, 如需自定义，请在下面补充
}