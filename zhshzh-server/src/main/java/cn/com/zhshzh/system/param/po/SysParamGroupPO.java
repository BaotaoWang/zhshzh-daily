package cn.com.zhshzh.system.param.po;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * sys_param_group
 * 系统参数组PO
 *
 * @author Generator
 * @since 2020/03/22
 */
@Data
public class SysParamGroupPO implements Serializable {

    /**
     * param_group_id
     * 参数组id
     */
    private Long paramGroupId;

    /**
     * param_group_name
     * 参数组名
     */
    private String paramGroupName;

    /**
     * description
     * 参数组描述
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
    public SysParamGroupPO() {
        super();
    }

    // 以上代码由代码生成器自动生成，原则上不允许任何修改, 如需自定义，请在下面补充
}