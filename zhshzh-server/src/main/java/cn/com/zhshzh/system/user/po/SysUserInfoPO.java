package cn.com.zhshzh.system.user.po;

import lombok.Data;

import java.util.Date;

@Data
public class SysUserInfoPO {
    private Long userInfoId;

    private String userAccount;

    private String userPassword;

    private String userName;

    private String userSerialNumber;

    private String userSex;

    private Date userBirth;

    private String userPhoneNumber;

    private String userMailBox;

    private int isDelete;

    private Date createTime;

    private Date updateTime;

    private Long createBy;

    private Long updateBy;

    public SysUserInfoPO() {
        super();
    }

}