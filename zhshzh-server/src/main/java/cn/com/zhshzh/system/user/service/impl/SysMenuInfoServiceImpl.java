package cn.com.zhshzh.system.user.service.impl;

import cn.com.zhshzh.core.constant.HttpResultEnum;
import cn.com.zhshzh.core.constant.RedisKeyConstants;
import cn.com.zhshzh.core.constant.WhereConditionEnum;
import cn.com.zhshzh.core.model.ConditionModel;
import cn.com.zhshzh.core.model.DeleteBatchLogicalModel;
import cn.com.zhshzh.core.model.HttpResult;
import cn.com.zhshzh.core.model.WhereConditions;
import cn.com.zhshzh.system.user.dao.SysMenuInfoMapper;
import cn.com.zhshzh.system.user.dao.SysRoleMenuRelationMapper;
import cn.com.zhshzh.system.user.dto.SysMenuInfoDTO;
import cn.com.zhshzh.system.user.dto.SysMenuInfoResultDTO;
import cn.com.zhshzh.system.user.dto.SysMenuInfoTreeDTO;
import cn.com.zhshzh.system.user.po.SysMenuInfoPO;
import cn.com.zhshzh.system.user.po.SysRoleMenuRelationPO;
import cn.com.zhshzh.system.user.service.SysMenuInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统菜单信息service
 *
 * @author WBT
 * @since 2020/03/10
 */
@Service
public class SysMenuInfoServiceImpl implements SysMenuInfoService {
    private StringRedisTemplate stringRedisTemplate;
    private SysRoleMenuRelationMapper sysRoleMenuRelationMapper;
    private SysMenuInfoMapper sysMenuInfoMapper;

    // 菜单根节点id(一级菜单的parentId)
    public static final Long ROOT_NODE_ID = 0L;

    @Autowired
    private SysMenuInfoServiceImpl(StringRedisTemplate stringRedisTemplate,
                                   SysRoleMenuRelationMapper sysRoleMenuRelationMapper, SysMenuInfoMapper sysMenuInfoMapper) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.sysRoleMenuRelationMapper = sysRoleMenuRelationMapper;
        this.sysMenuInfoMapper = sysMenuInfoMapper;
    }

    /**
     * 根据id查询菜单信息
     *
     * @param menuInfoId
     * @return
     */
    @Override
    public HttpResult<SysMenuInfoResultDTO> getSysMenuInfo(long menuInfoId) {
        // 根据菜单id查询菜单信息
        SysMenuInfoPO sysMenuInfoPO = sysMenuInfoMapper.getSysMenuInfo(menuInfoId);
        if (ObjectUtils.isEmpty(sysMenuInfoPO)) {
            return HttpResult.error(HttpResultEnum.EMPTY_DATA);
        }
        SysMenuInfoResultDTO sysMenuInfoResultDTO = new SysMenuInfoResultDTO();
        // 将菜单信息po转为dto
        BeanUtils.copyProperties(sysMenuInfoPO, sysMenuInfoResultDTO);
        return HttpResult.success(sysMenuInfoResultDTO);
    }

    /**
     * 查询用户有权查看的树状菜单
     *
     * @param userInfoId
     * @return
     */
    @Override
    public HttpResult<List<SysMenuInfoTreeDTO>> listSysMenuInfos(long userInfoId) {
        // 组装角色id在redis中的key
        String roleIdsKey = RedisKeyConstants.ROLEID_PREFIX + userInfoId + RedisKeyConstants.ROLEID_SUFFIX;
        // 在redis中获取当前用户的所有角色id
        List<String> roleInfoIdList = stringRedisTemplate.opsForList().range(roleIdsKey, 0, -1);
        // 用户无角色信息时返回空数据
        if (CollectionUtils.isEmpty(roleInfoIdList)) {
            return HttpResult.success();
        }
        String[] roleInfoIds = roleInfoIdList.toArray(new String[0]);
        ConditionModel roleConditionModel = new ConditionModel("role_info_id", WhereConditionEnum.IN, roleInfoIds);
        List<ConditionModel> roleConditionModelList = new ArrayList<>();
        roleConditionModelList.add(roleConditionModel);
        // 封装查询角色-菜单信息的查询条件
        WhereConditions roleWhereConditions = new WhereConditions(roleConditionModelList);
        // 根据角色id查询角色下所有的菜单id
        List<SysRoleMenuRelationPO> sysRoleMenuRelationPOList = sysRoleMenuRelationMapper.listSysRoleMenuRelations(roleWhereConditions);
        // 用户无菜单信息时返回空数据
        if (CollectionUtils.isEmpty(sysRoleMenuRelationPOList)) {
            return HttpResult.success();
        }
        String[] menuInfoIds = sysRoleMenuRelationPOList.stream().map(sysRoleMenuRelationPO -> String.valueOf(sysRoleMenuRelationPO.getMenuInfoId()))
                .collect(Collectors.toList()).toArray(new String[sysRoleMenuRelationPOList.size()]);
        List<ConditionModel> menuConditionModelList = new ArrayList<>();
        ConditionModel menuConditionModel = new ConditionModel("menu_info_id", WhereConditionEnum.IN, menuInfoIds);
        menuConditionModelList.add(menuConditionModel);
        // 只查询启用的菜单
        menuConditionModel = new ConditionModel("is_disabled", "0");
        menuConditionModelList.add(menuConditionModel);
        // 封装查询菜单信息的查询条件
        WhereConditions menuWhereConditions = new WhereConditions(menuConditionModelList);
        // 查询用户有权查看的所有菜单
        List<SysMenuInfoPO> sysMenuInfoPOList = sysMenuInfoMapper.listSysMenuInfos(menuWhereConditions);
        List<SysMenuInfoTreeDTO> sysMenuInfoTreeDTOList = new ArrayList<>();
        // 对查询出所有的菜单进行递归操作，得到树状结构的菜单
        this.getMenuInfoTree(sysMenuInfoPOList, sysMenuInfoTreeDTOList, ROOT_NODE_ID);
        return HttpResult.success(sysMenuInfoTreeDTOList);
    }

    /**
     * 查询所有的菜单
     *
     * @return
     */
    @Override
    public HttpResult<List<SysMenuInfoTreeDTO>> listAllSysMenuInfos() {
        List<SysMenuInfoPO> sysMenuInfoPOList = sysMenuInfoMapper.listAllSysMenuInfos();
        List<SysMenuInfoTreeDTO> sysMenuInfoTreeDTOList = new ArrayList<>();
        // 对查询出所有的菜单进行递归操作，得到树状结构的菜单
        this.getMenuInfoTree(sysMenuInfoPOList, sysMenuInfoTreeDTOList, ROOT_NODE_ID);
        return HttpResult.success(sysMenuInfoTreeDTOList);
    }

    /**
     * 新增菜单信息
     *
     * @param sysMenuInfoDTO
     * @return
     */
    @Override
    public HttpResult<?> insertSysMenuInfo(SysMenuInfoDTO sysMenuInfoDTO) {
        SysMenuInfoPO sysMenuInfoPO = new SysMenuInfoPO();
        BeanUtils.copyProperties(sysMenuInfoDTO, sysMenuInfoPO);
        // 自增主键，防止乱设置id
        sysMenuInfoPO.setMenuInfoId(null);
        // 新增的菜单默认启用
        sysMenuInfoPO.setDisabled(false);
        sysMenuInfoPO.setCreateBy(sysMenuInfoDTO.getUserInfoId());
        sysMenuInfoPO.setUpdateBy(sysMenuInfoDTO.getUserInfoId());
        // 新增菜单信息
        sysMenuInfoMapper.insertSysMenuInfo(sysMenuInfoPO);
        return HttpResult.success();
    }

    /**
     * 修改菜单信息
     *
     * @param menuInfoId
     * @param sysMenuInfoDTO
     * @return
     */
    @Override
    public HttpResult<?> updateSysMenuInfo(long menuInfoId, SysMenuInfoDTO sysMenuInfoDTO) {
        // 先根据要更新的菜单id查询是否有这条菜单信息
        SysMenuInfoPO sysMenuInfoPO = sysMenuInfoMapper.getSysMenuInfo(menuInfoId);
        // 如果未查到要更新的数据，直接返回
        if (ObjectUtils.isEmpty(sysMenuInfoPO)) {
            return HttpResult.error(HttpResultEnum.EMPTY_DATA);
        }
        BeanUtils.copyProperties(sysMenuInfoDTO, sysMenuInfoPO);
        sysMenuInfoPO.setUpdateBy(sysMenuInfoDTO.getUserInfoId());
        // 更新菜单信息
        sysMenuInfoMapper.updateSysMenuInfo(sysMenuInfoPO);
        return HttpResult.success();
    }

    /**
     * 修改菜单状态（启用/禁用）
     *
     * @param menuInfoId
     * @param disabled
     * @param userInfoId
     * @return
     */
    @Override
    public HttpResult<?> changeMenuState(long menuInfoId, Boolean disabled, long userInfoId) {
        if (disabled == null) {
            return HttpResult.error(HttpResultEnum.NULL_MENU_STATE);
        }
        // 根据要更新的菜单id查询是否有这条菜单信息
        SysMenuInfoPO sysMenuInfoPO = sysMenuInfoMapper.getSysMenuInfo(menuInfoId);
        // 如果未查到要更新的数据，直接返回
        if (ObjectUtils.isEmpty(sysMenuInfoPO)) {
            return HttpResult.error(HttpResultEnum.EMPTY_DATA);
        }
        List<SysMenuInfoPO> sysMenuInfoPOList = new ArrayList<>();
        if (disabled) {
            // 禁用。禁用菜单时，所有的子级菜单也禁用
            // 要禁用的菜单id的集合
            List<String> menuInfoIdList = new ArrayList<>();
            // 递归获取该菜单下所有的子级菜单id，并添加到集合中
            this.getChildMenuInfoIds(menuInfoIdList, menuInfoId);
            menuInfoIdList.forEach(id -> {
                SysMenuInfoPO sysMenuInfo = new SysMenuInfoPO();
                sysMenuInfo.setMenuInfoId(Long.parseLong(id));
                sysMenuInfo.setUpdateBy(userInfoId);
                sysMenuInfo.setDisabled(true);
                sysMenuInfoPOList.add(sysMenuInfo);
            });
        } else {
            // 启用。启用菜单时，所有的父级菜单也启用
            List<Long> menuInfoIdList = new ArrayList<>();
            // 递归获取该菜单下所有的父级菜单id，并添加到集合中
            this.getParentInfoIds(menuInfoIdList, menuInfoId);
            menuInfoIdList.forEach(id -> {
                SysMenuInfoPO sysMenuInfo = new SysMenuInfoPO();
                sysMenuInfo.setMenuInfoId(id);
                sysMenuInfo.setUpdateBy(userInfoId);
                sysMenuInfo.setDisabled(false);
                sysMenuInfoPOList.add(sysMenuInfo);
            });
        }
        // 批量更新菜单状态
        sysMenuInfoMapper.updateSysMenuInfoBatch(sysMenuInfoPOList);
        return HttpResult.success();
    }

    /**
     * 删除菜单信息
     *
     * @param menuInfoId
     * @param userInfoId
     * @return
     */
    @Override
    public HttpResult<?> deleteSysMenuInfo(long menuInfoId, long userInfoId) {
        // 要删除的菜单id的集合
        List<String> menuInfoIdList = new ArrayList<>();
        // 递归获取该菜单下所有的子级菜单id，并添加到集合中
        this.getChildMenuInfoIds(menuInfoIdList, menuInfoId);
        DeleteBatchLogicalModel deleteBatchLogicalModel = new DeleteBatchLogicalModel(userInfoId, menuInfoIdList.toArray(new String[0]));
        // 批量逻辑删除菜单
        sysMenuInfoMapper.deleteBatchLogical(deleteBatchLogicalModel);
        return HttpResult.success();
    }

    /**
     * 递归得到树状结构的菜单
     *
     * @param sysMenuInfoPOList
     * @param sysMenuInfoTreeDTOList
     * @param parentId
     */
    private void getMenuInfoTree(List<SysMenuInfoPO> sysMenuInfoPOList, List<SysMenuInfoTreeDTO> sysMenuInfoTreeDTOList, long parentId) {
        // 遍历菜单集合
        for (SysMenuInfoPO sysMenuInfoPO : sysMenuInfoPOList) {
            // 当前菜单的parentId等于传入的parentId时，添加到树状菜单中
            if (parentId == sysMenuInfoPO.getParentId()) {
                SysMenuInfoTreeDTO sysMenuInfoTreeDTO = new SysMenuInfoTreeDTO();
                // 将菜单的po对象转为树状对象
                BeanUtils.copyProperties(sysMenuInfoPO, sysMenuInfoTreeDTO);
                List<SysMenuInfoTreeDTO> childrenList = new ArrayList<>();
                this.getMenuInfoTree(sysMenuInfoPOList, childrenList, sysMenuInfoPO.getMenuInfoId());
                sysMenuInfoTreeDTO.setChildren(childrenList);
                sysMenuInfoTreeDTOList.add(sysMenuInfoTreeDTO);
                // 利用Lambda表达式进行排序(按order升序)
                sysMenuInfoTreeDTOList.sort(Comparator.comparingInt(SysMenuInfoTreeDTO::getMenuOrder));
            }
        }
    }

    /**
     * 递归获取指定菜单下所有的子级菜单id
     *
     * @param menuInfoIdList
     * @param parentId
     */
    private void getChildMenuInfoIds(List<String> menuInfoIdList, long parentId) {
        String id = String.valueOf(parentId);
        // 将该菜单id添加到集合中
        menuInfoIdList.add(id);
        ConditionModel conditionModel = new ConditionModel("parent_id", id);
        List<ConditionModel> conditionModelList = new ArrayList<>();
        conditionModelList.add(conditionModel);
        // 封装查询菜单信息的查询条件
        WhereConditions whereConditions = new WhereConditions(conditionModelList);
        // 根据菜单id查询该菜单所有的子菜单
        List<SysMenuInfoPO> sysMenuInfoPOList = sysMenuInfoMapper.listSysMenuInfos(whereConditions);
        if (!CollectionUtils.isEmpty(sysMenuInfoPOList)) {
            sysMenuInfoPOList.forEach(sysMenuInfoPO -> {
                long menuInfoId = sysMenuInfoPO.getMenuInfoId();
                this.getChildMenuInfoIds(menuInfoIdList, menuInfoId);
            });
        }
    }

    /**
     * 递归查询指定菜单的所有父级菜单
     *
     * @param menuInfoIdList
     * @param menuInfoId
     */
    private void getParentInfoIds(List<Long> menuInfoIdList, long menuInfoId) {
        SysMenuInfoPO sysMenuInfoPO = sysMenuInfoMapper.getSysMenuInfo(menuInfoId);
        if (!ObjectUtils.isEmpty(sysMenuInfoPO)) {
            menuInfoIdList.add(menuInfoId);
            getParentInfoIds(menuInfoIdList, sysMenuInfoPO.getParentId());
        }
    }
}
