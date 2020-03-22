package cn.com.zhshzh.system.user.dao;

import cn.com.zhshzh.system.user.po.SysUserInfoPO;
import cn.com.zhshzh.core.model.WhereConditions;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * sys_user_info
 * 系统用户Mapper
 *
 * @author Generator
 * @since 2020/03/22
 */
@Mapper
public interface SysUserInfoMapper {
    /**
     * 新增系统用户
     *
     * @param sysUserInfoPO 系统用户
     */
    void insertSysUserInfo(SysUserInfoPO sysUserInfoPO);

    /**
     * 批量新增系统用户
     *
     * @param sysUserInfoPOList 系统用户List
     */
    void insertSysUserInfoBatch(@Param("sysUserInfoPOList") List<SysUserInfoPO> sysUserInfoPOList);

    /**
     * 根据id逻辑删除系统用户
     *
     * @param userInfoId 主键id
     * @param updateBy 用户id
     */
    void deleteByIdLogical(@Param("userInfoId") Long userInfoId, @Param("updateBy") Long updateBy);

    /**
     * 批量逻辑删除系统用户
     *
     * @param userInfoIds 主键id数组
     * @param updateBy 用户id
     */
    void deleteBatchLogical(@Param("userInfoIds") Long[] userInfoIds, @Param("updateBy") Long updateBy);

    /**
     * 根据id物理删除系统用户
     *
     * @param userInfoId 主键id
     */
    void deleteByIdPhysical(@Param("userInfoId") Long userInfoId);

    /**
     * 批量物理删除系统用户
     *
     * @param sysUserInfos 主键id的数组
     */
    void deleteBatchPhysical(@Param("sysUserInfos") String[] sysUserInfos);

    /**
     * 修改系统用户
     *
     * @param sysUserInfoPO 系统用户
     */
    void updateSysUserInfo(SysUserInfoPO sysUserInfoPO);

    /**
     * 批量修改系统用户
     *
     * @param sysUserInfoPOList 系统用户List
     */
    void updateSysUserInfoBatch(@Param("sysUserInfoPOList") List<SysUserInfoPO> sysUserInfoPOList);

    /**
     * 根据id查询系统用户
     *
     * @param userInfoId 主键id
     * @return 系统用户
     */
    SysUserInfoPO getSysUserInfo(@Param("userInfoId") Long userInfoId);

    /**
     * 条件查询系统用户
     *
     * @param whereConditions 查询条件
     * @return 系统用户list
     */
    List<SysUserInfoPO> listSysUserInfos(WhereConditions whereConditions);

    /**
     * 查询所有的系统用户
     *
     * @return 系统用户list
     */
    List<SysUserInfoPO> listAllSysUserInfos();

    /**
     * 条件查询系统用户条数
     *
     * @param whereConditions 查询条件
     * @return 条数
     */
    Integer countSysUserInfos(WhereConditions whereConditions);

    // 以上代码由代码生成器自动生成，原则上不允许任何修改, 如需自定义，请在下面补充
}
