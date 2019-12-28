package cn.com.zhshzh.system.interfaceLog.dao;

import cn.com.zhshzh.system.interfaceLog.po.SysInterfaceLogPO;
import cn.com.zhshzh.core.model.DeleteBatchLogicalModel;
import cn.com.zhshzh.core.model.WhereConditions;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * sys_interface_log
 * 系统接口日志Mapper
 *
 * @author Generator
 * @since 2019/12/28
 */
@Mapper
public interface SysInterfaceLogMapper {
    /**
     * 新增系统接口日志
     *
     * @param sysInterfaceLogPO 系统接口日志
     */
    void insertSysInterfaceLog(SysInterfaceLogPO sysInterfaceLogPO);

    /**
     * 批量新增系统接口日志
     *
     * @param sysInterfaceLogPOList 系统接口日志List
     */
    void insertSysInterfaceLogBatch(@Param("sysInterfaceLogPOList") List<SysInterfaceLogPO> sysInterfaceLogPOList);

    /**
     * 根据id逻辑删除系统接口日志
     *
     * @param sysInterfaceLogPO 系统接口日志
     */
    void deleteByIdLogical(SysInterfaceLogPO sysInterfaceLogPO);

    /**
     * 批量逻辑删除系统接口日志
     *
     * @param deleteBatchLogicalModel 批量逻辑删除的模型对象
     */
    void deleteBatchLogical(DeleteBatchLogicalModel deleteBatchLogicalModel);

    /**
     * 根据id物理删除系统接口日志
     *
     * @param interfaceLogId 主键id
     */
    void deleteByIdPhysical(@Param("interfaceLogId") Long interfaceLogId);

    /**
     * 批量物理删除系统接口日志
     *
     * @param sysInterfaceLogs 主键id的数组
     */
    void deleteBatchPhysical(@Param("sysInterfaceLogs") String[] sysInterfaceLogs);

    /**
     * 修改系统接口日志
     *
     * @param sysInterfaceLogPO 系统接口日志
     */
    void updateSysInterfaceLog(SysInterfaceLogPO sysInterfaceLogPO);

    /**
     * 批量修改系统接口日志
     *
     * @param sysInterfaceLogPOList 系统接口日志List
     */
    void updateSysInterfaceLogBatch(@Param("sysInterfaceLogPOList") List<SysInterfaceLogPO> sysInterfaceLogPOList);

    /**
     * 根据id查询系统接口日志
     *
     * @param interfaceLogId 主键id
     * @return 系统接口日志
     */
    SysInterfaceLogPO getSysInterfaceLog(@Param("interfaceLogId") Long interfaceLogId);

    /**
     * 条件查询系统接口日志
     *
     * @param whereConditions 查询条件
     * @return 系统接口日志list
     */
    List<SysInterfaceLogPO> listSysInterfaceLogs(WhereConditions whereConditions);

    /**
     * 条件查询系统接口日志条数
     *
     * @param whereConditions 查询条件
     * @return 条数
     */
    Integer countSysInterfaceLogs(WhereConditions whereConditions);

    // 以上代码由代码生成器自动生成，原则上不允许任何修改, 如需自定义，请在下面补充
}
