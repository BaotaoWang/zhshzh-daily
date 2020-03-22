package cn.com.zhshzh.system.user.dao;

import cn.com.zhshzh.system.user.po.SysUserRoleRelationPO;
import cn.com.zhshzh.core.model.WhereConditions;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * sys_user_role_relation
 * 用户-角色关系Mapper
 *
 * @author Generator
 * @since 2020/03/22
 */
@Mapper
public interface SysUserRoleRelationMapper {
    /**
     * 新增用户-角色关系
     *
     * @param sysUserRoleRelationPO 用户-角色关系
     */
    void insertSysUserRoleRelation(SysUserRoleRelationPO sysUserRoleRelationPO);

    /**
     * 批量新增用户-角色关系
     *
     * @param sysUserRoleRelationPOList 用户-角色关系List
     */
    void insertSysUserRoleRelationBatch(@Param("sysUserRoleRelationPOList") List<SysUserRoleRelationPO> sysUserRoleRelationPOList);

    /**
     * 根据id逻辑删除用户-角色关系
     *
     * @param urRelationId 主键id
     * @param updateBy 用户id
     */
    void deleteByIdLogical(@Param("urRelationId") Long urRelationId, @Param("updateBy") Long updateBy);

    /**
     * 批量逻辑删除用户-角色关系
     *
     * @param urRelationIds 主键id数组
     * @param updateBy 用户id
     */
    void deleteBatchLogical(@Param("urRelationIds") Long[] urRelationIds, @Param("updateBy") Long updateBy);

    /**
     * 根据id物理删除用户-角色关系
     *
     * @param urRelationId 主键id
     */
    void deleteByIdPhysical(@Param("urRelationId") Long urRelationId);

    /**
     * 批量物理删除用户-角色关系
     *
     * @param sysUserRoleRelations 主键id的数组
     */
    void deleteBatchPhysical(@Param("sysUserRoleRelations") String[] sysUserRoleRelations);

    /**
     * 修改用户-角色关系
     *
     * @param sysUserRoleRelationPO 用户-角色关系
     */
    void updateSysUserRoleRelation(SysUserRoleRelationPO sysUserRoleRelationPO);

    /**
     * 批量修改用户-角色关系
     *
     * @param sysUserRoleRelationPOList 用户-角色关系List
     */
    void updateSysUserRoleRelationBatch(@Param("sysUserRoleRelationPOList") List<SysUserRoleRelationPO> sysUserRoleRelationPOList);

    /**
     * 根据id查询用户-角色关系
     *
     * @param urRelationId 主键id
     * @return 用户-角色关系
     */
    SysUserRoleRelationPO getSysUserRoleRelation(@Param("urRelationId") Long urRelationId);

    /**
     * 条件查询用户-角色关系
     *
     * @param whereConditions 查询条件
     * @return 用户-角色关系list
     */
    List<SysUserRoleRelationPO> listSysUserRoleRelations(WhereConditions whereConditions);

    /**
     * 查询所有的用户-角色关系
     *
     * @return 用户-角色关系list
     */
    List<SysUserRoleRelationPO> listAllSysUserRoleRelations();

    /**
     * 条件查询用户-角色关系条数
     *
     * @param whereConditions 查询条件
     * @return 条数
     */
    Integer countSysUserRoleRelations(WhereConditions whereConditions);

    // 以上代码由代码生成器自动生成，原则上不允许任何修改, 如需自定义，请在下面补充
}
