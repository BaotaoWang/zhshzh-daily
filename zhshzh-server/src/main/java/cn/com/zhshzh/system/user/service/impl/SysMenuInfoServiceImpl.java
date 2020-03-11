package cn.com.zhshzh.system.user.service.impl;

import cn.com.zhshzh.core.constant.HttpResultEnum;
import cn.com.zhshzh.core.constant.RedisKeyConstants;
import cn.com.zhshzh.core.constant.WhereConditionEnum;
import cn.com.zhshzh.core.model.ConditionModel;
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
        ConditionModel menuConditionModel = new ConditionModel("menu_info_id", WhereConditionEnum.IN, menuInfoIds);
        List<ConditionModel> menuConditionModelList = new ArrayList<>();
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
     * 删除菜单信息
     *
     * @param menuInfoId
     * @param userInfoId
     * @return
     */
    @Override
    public HttpResult<?> deleteSysMenuInfo(long menuInfoId, long userInfoId) {
        sysMenuInfoMapper.deleteByIdLogical(menuInfoId, userInfoId);
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
}
