package cn.com.zhshzh.business.user.dto;

import lombok.Data;

/**
 * 系统用户信息的数据传输对象
 */
@Data
public class SysUserInfoDTO {
    /**
     * 用户id
     */
    private Long userInfoId;

    /**
     * 用户账号
     */
    private String userName;

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
    private String birth;

    /**
     * 联系电话
     */
    private String phoneNumber;

    /**
     * 员工邮箱
     */
    private String email;

    public SysUserInfoDTO() {
        super();
    }
}
