package cn.com.zhshzh.business.user.po;

import lombok.Data;

import java.util.Date;

/**
 * 系统用户信息的实体对象
 */
@Data
public class SysUserInfoPO {
    /**
     * 用户id
     */
    private Long userInfoId;

    /**
     * 用户账号
     */
    private String userName;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户姓名
     */
    private String fullName;

    /**
     * 用户编号
     */
    private String serialNumber;

    /**
     * 性别(M：男； W：女)
     */
    private String sex;

    /**
     * 出生日期
     */
    private Date birth;

    /**
     * 联系电话
     */
    private String phoneNumber;

    /**
     * 员工邮箱
     */
    private String email;

    /**
     * 是否已删除（0：未删除； 1：已删除）
     */
    private int isDelete;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 创建人
     */
    private Long createBy;

    /**
     * 修改人
     */
    private Long updateBy;

    public SysUserInfoPO() {
        super();
    }

}
