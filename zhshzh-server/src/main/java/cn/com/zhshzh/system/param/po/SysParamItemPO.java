package cn.com.zhshzh.system.param.po;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * sys_param_item
 * 系统参数PO
 *
 * @author Generator
 * @since 2020/03/22
 */
@Data
public class SysParamItemPO implements Serializable {

    /**
     * param_item_id
     * 参数id
     */
    private Long paramItemId;

    /**
     * parent_id
     * 父级参数id
     */
    private Long parentId;

    /**
     * param_group_id
     * 参数组id
     */
    private Long paramGroupId;

    /**
     * param_item_value
     * 参数值
     */
    private String paramItemValue;

    /**
     * param_item_name
     * 参数名
     */
    private String paramItemName;

    /**
     * param_item_order
     * 参数序号
     */
    private Short paramItemOrder;

    /**
     * description
     * 参数描述
     */
    private String description;

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
    public SysParamItemPO() {
        super();
    }

    // 以上代码由代码生成器自动生成，原则上不允许任何修改, 如需自定义，请在下面补充
}