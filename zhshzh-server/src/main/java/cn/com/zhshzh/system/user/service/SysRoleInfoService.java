package cn.com.zhshzh.system.user.service;

import cn.com.zhshzh.system.user.dto.SysRoleInfoInDTO;

/**
 * 系统角色信息service
 *
 * @author WBT
 * @since 2020/01/07
 */
public interface SysRoleInfoService {
    /**
     * 根据用户id查询用户信息
     *
     * @param roleInfoId 角色id
     * @return 用户信息
     */
    SysRoleInfoInDTO getSysUserInfo(Long roleInfoId);
}
