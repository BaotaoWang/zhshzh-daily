package cn.com.zhshzh.system.user.po;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * sys_user_info
 * 系统用户PO
 *
 * @author Generator
 * @since 2020/03/22
 */
@Data
public class SysUserInfoPO implements Serializable {

    /**
     * user_info_id
     * 用户id
     */
    private Long userInfoId;

    /**
     * user_name
     * 用户账号
     */
    private String userName;

    /**
     * password
     * 用户密码
     */
    private String password;

    /**
     * full_name
     * 用户姓名
     */
    private String fullName;

    /**
     * serial_number
     * 用户编号
     */
    private String serialNumber;

    /**
     * sex
     * 性别(M：男； W：女)
     */
    private String sex;

    /**
     * birth
     * 出生日期
     */
    private LocalDate birth;

    /**
     * phone_number
     * 联系电话
     */
    private String phoneNumber;

    /**
     * email
     * 员工邮箱
     */
    private String email;

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
    public SysUserInfoPO() {
        super();
    }

    // 以上代码由代码生成器自动生成，原则上不允许任何修改, 如需自定义，请在下面补充
}