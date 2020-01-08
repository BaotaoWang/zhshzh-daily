package cn.com.zhshzh.system.user.dao;

import cn.com.zhshzh.system.user.po.SysRoleInfoPO;
import cn.com.zhshzh.core.model.DeleteBatchLogicalModel;
import cn.com.zhshzh.core.model.WhereConditions;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * sys_role_info
 * 系统角色Mapper
 *
 * @author Generator
 * @since 2020/01/07
 */
@Mapper
public interface SysRoleInfoMapper {
    /**
     * 新增系统角色
     *
     * @param sysRoleInfoPO 系统角色
     */
    void insertSysRoleInfo(SysRoleInfoPO sysRoleInfoPO);

    /**
     * 批量新增系统角色
     *
     * @param sysRoleInfoPOList 系统角色List
     */
    void insertSysRoleInfoBatch(@Param("sysRoleInfoPOList") List<SysRoleInfoPO> sysRoleInfoPOList);

    /**
     * 根据id逻辑删除系统角色
     *
     * @param sysRoleInfoPO 系统角色
     */
    void deleteByIdLogical(SysRoleInfoPO sysRoleInfoPO);

    /**
     * 批量逻辑删除系统角色
     *
     * @param deleteBatchLogicalModel 批量逻辑删除的模型对象
     */
    void deleteBatchLogical(DeleteBatchLogicalModel deleteBatchLogicalModel);

    /**
     * 根据id物理删除系统角色
     *
     * @param roleInfoId 主键id
     */
    void deleteByIdPhysical(@Param("roleInfoId") Long roleInfoId);

    /**
     * 批量物理删除系统角色
     *
     * @param sysRoleInfos 主键id的数组
     */
    void deleteBatchPhysical(@Param("sysRoleInfos") String[] sysRoleInfos);

    /**
     * 修改系统角色
     *
     * @param sysRoleInfoPO 系统角色
     */
    void updateSysRoleInfo(SysRoleInfoPO sysRoleInfoPO);

    /**
     * 批量修改系统角色
     *
     * @param sysRoleInfoPOList 系统角色List
     */
    void updateSysRoleInfoBatch(@Param("sysRoleInfoPOList") List<SysRoleInfoPO> sysRoleInfoPOList);

    /**
     * 根据id查询系统角色
     *
     * @param roleInfoId 主键id
     * @return 系统角色
     */
    SysRoleInfoPO getSysRoleInfo(@Param("roleInfoId") Long roleInfoId);

    /**
     * 条件查询系统角色
     *
     * @param whereConditions 查询条件
     * @return 系统角色list
     */
    List<SysRoleInfoPO> listSysRoleInfos(WhereConditions whereConditions);

    /**
     * 条件查询系统角色条数
     *
     * @param whereConditions 查询条件
     * @return 条数
     */
    Integer countSysRoleInfos(WhereConditions whereConditions);

    // 以上代码由代码生成器自动生成，原则上不允许任何修改, 如需自定义，请在下面补充
}
