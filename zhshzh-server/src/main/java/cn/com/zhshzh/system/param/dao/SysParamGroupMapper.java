package cn.com.zhshzh.system.param.dao;

import cn.com.zhshzh.system.param.po.SysParamGroupPO;
import cn.com.zhshzh.core.model.WhereConditions;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * sys_param_group
 * 系统参数组Mapper
 *
 * @author Generator
 * @since 2020/03/22
 */
@Mapper
public interface SysParamGroupMapper {
    /**
     * 新增系统参数组
     *
     * @param sysParamGroupPO 系统参数组
     */
    void insertSysParamGroup(SysParamGroupPO sysParamGroupPO);

    /**
     * 批量新增系统参数组
     *
     * @param sysParamGroupPOList 系统参数组List
     */
    void insertSysParamGroupBatch(@Param("sysParamGroupPOList") List<SysParamGroupPO> sysParamGroupPOList);

    /**
     * 根据id逻辑删除系统参数组
     *
     * @param paramGroupId 主键id
     * @param updateBy 用户id
     */
    void deleteByIdLogical(@Param("paramGroupId") Long paramGroupId, @Param("updateBy") Long updateBy);

    /**
     * 批量逻辑删除系统参数组
     *
     * @param paramGroupIds 主键id数组
     * @param updateBy 用户id
     */
    void deleteBatchLogical(@Param("paramGroupIds") Long[] paramGroupIds, @Param("updateBy") Long updateBy);

    /**
     * 根据id物理删除系统参数组
     *
     * @param paramGroupId 主键id
     */
    void deleteByIdPhysical(@Param("paramGroupId") Long paramGroupId);

    /**
     * 批量物理删除系统参数组
     *
     * @param sysParamGroups 主键id的数组
     */
    void deleteBatchPhysical(@Param("sysParamGroups") String[] sysParamGroups);

    /**
     * 修改系统参数组
     *
     * @param sysParamGroupPO 系统参数组
     */
    void updateSysParamGroup(SysParamGroupPO sysParamGroupPO);

    /**
     * 批量修改系统参数组
     *
     * @param sysParamGroupPOList 系统参数组List
     */
    void updateSysParamGroupBatch(@Param("sysParamGroupPOList") List<SysParamGroupPO> sysParamGroupPOList);

    /**
     * 根据id查询系统参数组
     *
     * @param paramGroupId 主键id
     * @return 系统参数组
     */
    SysParamGroupPO getSysParamGroup(@Param("paramGroupId") Long paramGroupId);

    /**
     * 条件查询系统参数组
     *
     * @param whereConditions 查询条件
     * @return 系统参数组list
     */
    List<SysParamGroupPO> listSysParamGroups(WhereConditions whereConditions);

    /**
     * 查询所有的系统参数组
     *
     * @return 系统参数组list
     */
    List<SysParamGroupPO> listAllSysParamGroups();

    /**
     * 条件查询系统参数组条数
     *
     * @param whereConditions 查询条件
     * @return 条数
     */
    Integer countSysParamGroups(WhereConditions whereConditions);

    // 以上代码由代码生成器自动生成，原则上不允许任何修改, 如需自定义，请在下面补充
}
