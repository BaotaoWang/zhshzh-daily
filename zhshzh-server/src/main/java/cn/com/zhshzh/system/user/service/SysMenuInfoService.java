package cn.com.zhshzh.system.user.service;

import cn.com.zhshzh.core.model.HttpResult;
import cn.com.zhshzh.system.user.dto.SysMenuInfoDTO;
import cn.com.zhshzh.system.user.dto.SysMenuInfoResultDTO;
import cn.com.zhshzh.system.user.dto.SysMenuInfoTreeDTO;

import java.util.List;

/**
 * 系统菜单信息service
 *
 * @author WBT
 * @since 2020/03/04
 */
public interface SysMenuInfoService {
    /**
     * 根据id查询菜单信息
     *
     * @param menuInfoId
     * @return
     */
    HttpResult<SysMenuInfoResultDTO> getSysMenuInfo(long menuInfoId);

    /**
     * 查询用户有权查看的树状菜单
     *
     * @param userInfoId
     * @return
     */
    HttpResult<List<SysMenuInfoTreeDTO>> listSysMenuInfos(long userInfoId);

    /**
     * 查询所有的菜单
     *
     * @return
     */
    HttpResult<List<SysMenuInfoTreeDTO>> listAllSysMenuInfos();

    /**
     * 新增菜单信息
     *
     * @param sysMenuInfoDTO
     * @return
     */
    HttpResult<?> insertSysMenuInfo(SysMenuInfoDTO sysMenuInfoDTO);

    /**
     * 修改菜单信息
     *
     * @param menuInfoId
     * @param sysMenuInfoDTO
     * @return
     */
    HttpResult<?> updateSysMenuInfo(long menuInfoId, SysMenuInfoDTO sysMenuInfoDTO);

    /**
     * 修改菜单状态（启用/禁用）
     *
     * @param menuInfoId
     * @param disabled
     * @param userInfoId
     * @return
     */
    HttpResult<?> changeMenuState(long menuInfoId, Boolean disabled, long userInfoId);

    /**
     * 删除菜单信息
     *
     * @param id
     * @param request
     * @return
     */
    HttpResult<?> deleteSysMenuInfo(long menuInfoId, long userInfoId);
}
