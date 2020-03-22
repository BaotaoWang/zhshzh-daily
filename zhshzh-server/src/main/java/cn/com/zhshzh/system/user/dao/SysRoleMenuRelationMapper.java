package cn.com.zhshzh.system.user.dao;

import cn.com.zhshzh.system.user.po.SysRoleMenuRelationPO;
import cn.com.zhshzh.core.model.WhereConditions;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * sys_role_menu_relation
 * 角色-菜单关系Mapper
 *
 * @author Generator
 * @since 2020/03/22
 */
@Mapper
public interface SysRoleMenuRelationMapper {
    /**
     * 新增角色-菜单关系
     *
     * @param sysRoleMenuRelationPO 角色-菜单关系
     */
    void insertSysRoleMenuRelation(SysRoleMenuRelationPO sysRoleMenuRelationPO);

    /**
     * 批量新增角色-菜单关系
     *
     * @param sysRoleMenuRelationPOList 角色-菜单关系List
     */
    void insertSysRoleMenuRelationBatch(@Param("sysRoleMenuRelationPOList") List<SysRoleMenuRelationPO> sysRoleMenuRelationPOList);

    /**
     * 根据id逻辑删除角色-菜单关系
     *
     * @param rmRelationId 主键id
     * @param updateBy 用户id
     */
    void deleteByIdLogical(@Param("rmRelationId") Long rmRelationId, @Param("updateBy") Long updateBy);

    /**
     * 批量逻辑删除角色-菜单关系
     *
     * @param rmRelationIds 主键id数组
     * @param updateBy 用户id
     */
    void deleteBatchLogical(@Param("rmRelationIds") Long[] rmRelationIds, @Param("updateBy") Long updateBy);

    /**
     * 根据id物理删除角色-菜单关系
     *
     * @param rmRelationId 主键id
     */
    void deleteByIdPhysical(@Param("rmRelationId") Long rmRelationId);

    /**
     * 批量物理删除角色-菜单关系
     *
     * @param sysRoleMenuRelations 主键id的数组
     */
    void deleteBatchPhysical(@Param("sysRoleMenuRelations") String[] sysRoleMenuRelations);

    /**
     * 修改角色-菜单关系
     *
     * @param sysRoleMenuRelationPO 角色-菜单关系
     */
    void updateSysRoleMenuRelation(SysRoleMenuRelationPO sysRoleMenuRelationPO);

    /**
     * 批量修改角色-菜单关系
     *
     * @param sysRoleMenuRelationPOList 角色-菜单关系List
     */
    void updateSysRoleMenuRelationBatch(@Param("sysRoleMenuRelationPOList") List<SysRoleMenuRelationPO> sysRoleMenuRelationPOList);

    /**
     * 根据id查询角色-菜单关系
     *
     * @param rmRelationId 主键id
     * @return 角色-菜单关系
     */
    SysRoleMenuRelationPO getSysRoleMenuRelation(@Param("rmRelationId") Long rmRelationId);

    /**
     * 条件查询角色-菜单关系
     *
     * @param whereConditions 查询条件
     * @return 角色-菜单关系list
     */
    List<SysRoleMenuRelationPO> listSysRoleMenuRelations(WhereConditions whereConditions);

    /**
     * 查询所有的角色-菜单关系
     *
     * @return 角色-菜单关系list
     */
    List<SysRoleMenuRelationPO> listAllSysRoleMenuRelations();

    /**
     * 条件查询角色-菜单关系条数
     *
     * @param whereConditions 查询条件
     * @return 条数
     */
    Integer countSysRoleMenuRelations(WhereConditions whereConditions);

    // 以上代码由代码生成器自动生成，原则上不允许任何修改, 如需自定义，请在下面补充
}
