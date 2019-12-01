package cn.com.zhshzh.business.user.service;

import cn.com.zhshzh.business.user.dto.SysUserInfoDTO;
import cn.com.zhshzh.business.user.po.SysUserInfoPO;

import java.util.List;

/**
 * 系统用户信息service
 *
 * @author WBT
 * @since 2019/10/15
 */
public interface SysUserInfoService {
    /**
     * 根据用户id查询用户信息
     *
     * @param userInfoId 用户id
     * @return 用户信息
     */
    SysUserInfoDTO getSysUserInfo(Long userInfoId);

    /**
     * 条件查询用户信息
     *
     * @param sysUserInfoPO 封装查询条件的用户对象
     * @return 用户信息list
     */
    List<SysUserInfoPO> listSysUserInfo(SysUserInfoPO sysUserInfoPO);

    /**
     * 保存用户信息
     *
     * @param sysUserInfoPO 用户基本信息
     * @return 新增记录条数
     */
    int saveSysUserInfo(SysUserInfoPO sysUserInfoPO);
}
