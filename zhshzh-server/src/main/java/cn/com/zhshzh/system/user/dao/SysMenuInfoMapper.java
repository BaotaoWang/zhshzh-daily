package cn.com.zhshzh.system.user.dao;

import cn.com.zhshzh.system.user.po.SysMenuInfoPO;
import cn.com.zhshzh.core.model.WhereConditions;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * sys_menu_info
 * 系统菜单Mapper
 *
 * @author Generator
 * @since 2020/03/22
 */
@Mapper
public interface SysMenuInfoMapper {
    /**
     * 新增系统菜单
     *
     * @param sysMenuInfoPO 系统菜单
     */
    void insertSysMenuInfo(SysMenuInfoPO sysMenuInfoPO);

    /**
     * 批量新增系统菜单
     *
     * @param sysMenuInfoPOList 系统菜单List
     */
    void insertSysMenuInfoBatch(@Param("sysMenuInfoPOList") List<SysMenuInfoPO> sysMenuInfoPOList);

    /**
     * 根据id逻辑删除系统菜单
     *
     * @param menuInfoId 主键id
     * @param updateBy 用户id
     */
    void deleteByIdLogical(@Param("menuInfoId") Long menuInfoId, @Param("updateBy") Long updateBy);

    /**
     * 批量逻辑删除系统菜单
     *
     * @param menuInfoIds 主键id数组
     * @param updateBy 用户id
     */
    void deleteBatchLogical(@Param("menuInfoIds") Long[] menuInfoIds, @Param("updateBy") Long updateBy);

    /**
     * 根据id物理删除系统菜单
     *
     * @param menuInfoId 主键id
     */
    void deleteByIdPhysical(@Param("menuInfoId") Long menuInfoId);

    /**
     * 批量物理删除系统菜单
     *
     * @param sysMenuInfos 主键id的数组
     */
    void deleteBatchPhysical(@Param("sysMenuInfos") String[] sysMenuInfos);

    /**
     * 修改系统菜单
     *
     * @param sysMenuInfoPO 系统菜单
     */
    void updateSysMenuInfo(SysMenuInfoPO sysMenuInfoPO);

    /**
     * 批量修改系统菜单
     *
     * @param sysMenuInfoPOList 系统菜单List
     */
    void updateSysMenuInfoBatch(@Param("sysMenuInfoPOList") List<SysMenuInfoPO> sysMenuInfoPOList);

    /**
     * 根据id查询系统菜单
     *
     * @param menuInfoId 主键id
     * @return 系统菜单
     */
    SysMenuInfoPO getSysMenuInfo(@Param("menuInfoId") Long menuInfoId);

    /**
     * 条件查询系统菜单
     *
     * @param whereConditions 查询条件
     * @return 系统菜单list
     */
    List<SysMenuInfoPO> listSysMenuInfos(WhereConditions whereConditions);

    /**
     * 查询所有的系统菜单
     *
     * @return 系统菜单list
     */
    List<SysMenuInfoPO> listAllSysMenuInfos();

    /**
     * 条件查询系统菜单条数
     *
     * @param whereConditions 查询条件
     * @return 条数
     */
    Integer countSysMenuInfos(WhereConditions whereConditions);

    // 以上代码由代码生成器自动生成，原则上不允许任何修改, 如需自定义，请在下面补充
}
