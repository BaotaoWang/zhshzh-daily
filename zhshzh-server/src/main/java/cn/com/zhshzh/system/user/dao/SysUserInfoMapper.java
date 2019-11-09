package cn.com.zhshzh.system.user.dao;

import cn.com.zhshzh.system.user.po.SysUserInfoPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统用户信息
 *
 * @author wbt
 * @since 2019/10/15
 */
@Mapper
public interface SysUserInfoMapper {
    /**
     * 根据用户id查询用户信息
     *
     * @param userInfoId
     * @return
     */
    SysUserInfoPO getSysUserInfo(Long userInfoId);

    /**
     * 条件查询用户信息
     *
     * @param sysUserInfoPO
     * @return
     */
    List<SysUserInfoPO> listSysUserInfo(SysUserInfoPO sysUserInfoPO);

    /**
     * 新增用户信息
     *
     * @param sysUserInfoPO 用户基本信息
     * @return 新增记录条数
     */
    int insertSysUserInfo(SysUserInfoPO sysUserInfoPO);

    /**
     * 更新用户信息
     *
     * @param sysUserInfoPO 用户基本信息
     * @return 更新记录条数
     */
    int updateSysUserInfo(SysUserInfoPO sysUserInfoPO);
}