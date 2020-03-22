package cn.com.zhshzh.system.param.dao;

import cn.com.zhshzh.system.param.po.SysParamItemPO;
import cn.com.zhshzh.core.model.WhereConditions;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * sys_param_item
 * 系统参数Mapper
 *
 * @author Generator
 * @since 2020/03/22
 */
@Mapper
public interface SysParamItemMapper {
    /**
     * 新增系统参数
     *
     * @param sysParamItemPO 系统参数
     */
    void insertSysParamItem(SysParamItemPO sysParamItemPO);

    /**
     * 批量新增系统参数
     *
     * @param sysParamItemPOList 系统参数List
     */
    void insertSysParamItemBatch(@Param("sysParamItemPOList") List<SysParamItemPO> sysParamItemPOList);

    /**
     * 根据id逻辑删除系统参数
     *
     * @param paramItemId 主键id
     * @param updateBy 用户id
     */
    void deleteByIdLogical(@Param("paramItemId") Long paramItemId, @Param("updateBy") Long updateBy);

    /**
     * 批量逻辑删除系统参数
     *
     * @param paramItemIds 主键id数组
     * @param updateBy 用户id
     */
    void deleteBatchLogical(@Param("paramItemIds") Long[] paramItemIds, @Param("updateBy") Long updateBy);

    /**
     * 根据id物理删除系统参数
     *
     * @param paramItemId 主键id
     */
    void deleteByIdPhysical(@Param("paramItemId") Long paramItemId);

    /**
     * 批量物理删除系统参数
     *
     * @param sysParamItems 主键id的数组
     */
    void deleteBatchPhysical(@Param("sysParamItems") String[] sysParamItems);

    /**
     * 修改系统参数
     *
     * @param sysParamItemPO 系统参数
     */
    void updateSysParamItem(SysParamItemPO sysParamItemPO);

    /**
     * 批量修改系统参数
     *
     * @param sysParamItemPOList 系统参数List
     */
    void updateSysParamItemBatch(@Param("sysParamItemPOList") List<SysParamItemPO> sysParamItemPOList);

    /**
     * 根据id查询系统参数
     *
     * @param paramItemId 主键id
     * @return 系统参数
     */
    SysParamItemPO getSysParamItem(@Param("paramItemId") Long paramItemId);

    /**
     * 条件查询系统参数
     *
     * @param whereConditions 查询条件
     * @return 系统参数list
     */
    List<SysParamItemPO> listSysParamItems(WhereConditions whereConditions);

    /**
     * 查询所有的系统参数
     *
     * @return 系统参数list
     */
    List<SysParamItemPO> listAllSysParamItems();

    /**
     * 条件查询系统参数条数
     *
     * @param whereConditions 查询条件
     * @return 条数
     */
    Integer countSysParamItems(WhereConditions whereConditions);

    // 以上代码由代码生成器自动生成，原则上不允许任何修改, 如需自定义，请在下面补充
}
