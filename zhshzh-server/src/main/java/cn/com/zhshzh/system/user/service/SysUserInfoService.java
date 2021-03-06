package cn.com.zhshzh.system.user.service;

import cn.com.zhshzh.core.model.HttpResult;
import cn.com.zhshzh.system.user.dto.SysUserInfoInDTO;
import cn.com.zhshzh.system.user.po.SysUserInfoPO;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

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
    SysUserInfoInDTO getSysUserInfo(Long userInfoId);

    /**
     * 根据用户登录账号查询用户信息（用户登录时用）
     *
     * @param username 用户的登录账号
     * @return 用户信息
     */
    SysUserInfoPO getSysUserInfoForLogin(String username) throws UsernameNotFoundException;

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    SysUserInfoPO getSysUserInfoByUsername(String username);

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
     */
    HttpResult<SysUserInfoInDTO> saveSysUserInfo(SysUserInfoPO sysUserInfoPO);
}
