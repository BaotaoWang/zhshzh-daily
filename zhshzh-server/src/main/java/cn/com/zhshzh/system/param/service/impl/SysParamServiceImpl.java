package cn.com.zhshzh.system.param.service.impl;

import cn.com.zhshzh.core.constant.HttpResultEnum;
import cn.com.zhshzh.core.constant.OrderByEnum;
import cn.com.zhshzh.core.constant.WhereConditionEnum;
import cn.com.zhshzh.core.model.*;
import cn.com.zhshzh.core.util.ConvertBeanUtil;
import cn.com.zhshzh.system.param.dao.SysParamGroupMapper;
import cn.com.zhshzh.system.param.dao.SysParamItemMapper;
import cn.com.zhshzh.system.param.dto.SysParamGroupInDTO;
import cn.com.zhshzh.system.param.dto.SysParamGroupOutDTO;
import cn.com.zhshzh.system.param.dto.SysParamItemInDTO;
import cn.com.zhshzh.system.param.dto.SysParamItemOutDTO;
import cn.com.zhshzh.system.param.po.SysParamGroupPO;
import cn.com.zhshzh.system.param.po.SysParamItemPO;
import cn.com.zhshzh.system.param.service.SysParamService;
import com.github.pagehelper.PageHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统参数service
 *
 * @author WBT
 * @since 2020/03/16
 */
@Service
public class SysParamServiceImpl implements SysParamService {
    private static final Logger logger = LogManager.getLogger(SysParamServiceImpl.class);

    private SysParamGroupMapper sysParamGroupMapper;
    private SysParamItemMapper sysParamItemMapper;

    @Autowired
    void setSysParamGroupMapper(SysParamGroupMapper sysParamGroupMapper) {
        this.sysParamGroupMapper = sysParamGroupMapper;
    }

    @Autowired
    void setSysParamItemMapper(SysParamItemMapper sysParamItemMapper) {
        this.sysParamItemMapper = sysParamItemMapper;
    }

    /**
     * 根据id查询参数组信息
     *
     * @param paramGroupId
     * @return
     */
    @Override
    public HttpResult<SysParamGroupOutDTO> getParamGroup(long paramGroupId) {
        SysParamGroupPO sysParamGroupPO = sysParamGroupMapper.getSysParamGroup(paramGroupId);
        if (ObjectUtils.isEmpty(sysParamGroupPO)) {
            return HttpResult.error(HttpResultEnum.EMPTY_DATA);
        }
        SysParamGroupOutDTO sysParamGroupOutDTO = new SysParamGroupOutDTO();
        BeanUtils.copyProperties(sysParamGroupPO, sysParamGroupOutDTO);
        return HttpResult.success(sysParamGroupOutDTO);
    }

    /**
     * 条件查询参数组信息
     *
     * @param keyWord
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public HttpResult<PageResult<SysParamGroupOutDTO>> listParamGroups(String keyWord, Integer pageNum, Integer pageSize) {
        // 拼接查询条件
        List<ConditionModel> conditionModelList = new ArrayList<>();
        conditionModelList.add(new ConditionModel(WhereConditionEnum.OR_START));
        conditionModelList.add(new ConditionModel("param_group_name", WhereConditionEnum.LIKE, keyWord));
        conditionModelList.add(new ConditionModel(WhereConditionEnum.OR));
        conditionModelList.add(new ConditionModel("description", WhereConditionEnum.LIKE, keyWord));
        conditionModelList.add(new ConditionModel(WhereConditionEnum.OR_END));
        // 设置要排序的字段及顺序
        List<OrderByModel> orderByList = new ArrayList<>();
        orderByList.add(new OrderByModel("param_group_name", OrderByEnum.ASC));
        // 用PageHelper插件进行分页
        PageHelper.startPage(pageNum, pageSize);
        // 对参数组名及参数描述进行模糊查询
        List<SysParamGroupPO> sysParamGroupPOList = sysParamGroupMapper.listSysParamGroups(new WhereConditions(conditionModelList, orderByList));
        // 将分页数据返回给前台
        return HttpResult.success(ConvertBeanUtil.getPageResult(sysParamGroupPOList, SysParamGroupOutDTO.class));
    }

    /**
     * 新增参数组信息
     *
     * @param sysParamGroupInDTO
     * @param userInfoId
     * @return
     */
    @Override
    public HttpResult<?> saveParamGroup(SysParamGroupInDTO sysParamGroupInDTO, long userInfoId) {
        String paramGroupName = sysParamGroupInDTO.getParamGroupName();
        // 拼接查询条件
        List<ConditionModel> paramGroupConditionModelList = new ArrayList<>();
        paramGroupConditionModelList.add(new ConditionModel("param_group_name", paramGroupName));
        // 根据参数组名查询参数组信息
        List<SysParamGroupPO> sysParamGroupPOList = sysParamGroupMapper.listSysParamGroups(new WhereConditions(paramGroupConditionModelList, 1));
        if (!CollectionUtils.isEmpty(sysParamGroupPOList)) {
            return HttpResult.error("该参数组名已存在");
        }
        SysParamGroupPO sysParamGroupPO = new SysParamGroupPO();
        BeanUtils.copyProperties(sysParamGroupInDTO, sysParamGroupPO);
        sysParamGroupPO.setCreateBy(userInfoId);
        sysParamGroupPO.setUpdateBy(userInfoId);
        sysParamGroupMapper.insertSysParamGroup(sysParamGroupPO);
        return HttpResult.success();
    }

    /**
     * 修改参数组信息
     *
     * @param paramGroupId
     * @param sysParamGroupInDTO
     * @return
     */
    @Override
    public HttpResult<?> updateParamGroup(long paramGroupId, SysParamGroupInDTO sysParamGroupInDTO, long userInfoId) {
        SysParamGroupPO sysParamGroupPO = sysParamGroupMapper.getSysParamGroup(paramGroupId);
        if (ObjectUtils.isEmpty(sysParamGroupPO)) {
            return HttpResult.error(HttpResultEnum.EMPTY_DATA);
        }
        sysParamGroupPO = new SysParamGroupPO();
        BeanUtils.copyProperties(sysParamGroupInDTO, sysParamGroupPO);
        sysParamGroupPO.setParamGroupId(paramGroupId);
        sysParamGroupPO.setUpdateBy(userInfoId);
        sysParamGroupMapper.updateSysParamGroup(sysParamGroupPO);
        return HttpResult.success();
    }

    /**
     * 删除参数组信息
     *
     * @param paramGroupId
     * @param userInfoId
     * @return
     */
    @Transactional
    @Override
    public HttpResult<?> deleteParamGroup(long paramGroupId, long userInfoId) {
        // 先删除参数组信息
        sysParamGroupMapper.deleteByIdLogical(paramGroupId, userInfoId);
        // 拼接查询参数信息的条件
        List<ConditionModel> conditionModelList = new ArrayList<>();
        conditionModelList.add(new ConditionModel("param_group_id", String.valueOf(paramGroupId)));
        // 查询该参数组下的所有参数信息
        List<SysParamItemPO> sysParamItemPOList = sysParamItemMapper.listSysParamItems(new WhereConditions(conditionModelList));
        // 如果该参数组下无参数信息，直接返回
        if (CollectionUtils.isEmpty(sysParamItemPOList)) {
            return HttpResult.success();
        }
        // 从查询出的参数集合中提取paramItemId
        Long[] paramItemIds = sysParamItemPOList.stream().map(SysParamItemPO::getParamItemId).toArray(Long[]::new);
        // 批量逻辑删除参数信息
        sysParamItemMapper.deleteBatchLogical(paramItemIds, userInfoId);
        return HttpResult.success();
    }

    /**
     * 根据id查询参数信息
     *
     * @param paramItemId
     * @return
     */
    @Override
    public HttpResult<SysParamItemOutDTO> getParamItem(long paramItemId) {
        SysParamItemPO sysParamItemPO = sysParamItemMapper.getSysParamItem(paramItemId);
        if (ObjectUtils.isEmpty(sysParamItemPO)) {
            return HttpResult.error(HttpResultEnum.EMPTY_DATA);
        }
        SysParamItemOutDTO sysParamItemOutDTO = new SysParamItemOutDTO();
        BeanUtils.copyProperties(sysParamItemPO, sysParamItemOutDTO);
        return HttpResult.success(sysParamItemOutDTO);
    }

    /**
     * 条件查询参数信息
     *
     * @param paramGroupId
     * @param keyWord
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public HttpResult<PageResult<SysParamItemOutDTO>> listParamItems(long paramGroupId, String keyWord, Integer pageNum, Integer pageSize) {
        // 拼接查询条件
        List<ConditionModel> conditionModelList = new ArrayList<>();
        conditionModelList.add(new ConditionModel("param_group_id", String.valueOf(paramGroupId)));
        conditionModelList.add(new ConditionModel(WhereConditionEnum.OR_START));
        conditionModelList.add(new ConditionModel("param_item_value", WhereConditionEnum.LIKE, keyWord));
        conditionModelList.add(new ConditionModel(WhereConditionEnum.OR));
        conditionModelList.add(new ConditionModel("param_item_name", WhereConditionEnum.LIKE, keyWord));
        conditionModelList.add(new ConditionModel(WhereConditionEnum.OR));
        conditionModelList.add(new ConditionModel("description", WhereConditionEnum.LIKE, keyWord));
        conditionModelList.add(new ConditionModel(WhereConditionEnum.OR_END));
        // 设置要排序的字段及顺序
        List<OrderByModel> orderByList = new ArrayList<>();
        orderByList.add(new OrderByModel("param_item_order", OrderByEnum.ASC));
        // 用PageHelper插件进行分页
        PageHelper.startPage(pageNum, pageSize);
        // 对参数值、参数名及参数描述进行模糊查询
        List<SysParamItemPO> sysParamItemPOList = sysParamItemMapper.listSysParamItems(new WhereConditions(conditionModelList, orderByList));
        // 将分页数据返回给前台
        return HttpResult.success(ConvertBeanUtil.getPageResult(sysParamItemPOList, SysParamItemOutDTO.class));
    }

    /**
     * 新增参数信息
     *
     * @param sysParamItemInDTOList
     * @param userInfoId
     * @return
     */
    @Override
    public HttpResult<?> saveParamItem(List<SysParamItemInDTO> sysParamItemInDTOList, long userInfoId) {
        List<SysParamItemPO> sysParamItemPOList = sysParamItemInDTOList.stream().map(sysParamItemInDTO -> {
            SysParamItemPO sysParamItemPO = new SysParamItemPO();
            BeanUtils.copyProperties(sysParamItemInDTO, sysParamItemPO);
            sysParamItemPO.setCreateBy(userInfoId);
            sysParamItemPO.setUpdateBy(userInfoId);
            return sysParamItemPO;
        }).collect(Collectors.toList());
        sysParamItemMapper.insertSysParamItemBatch(sysParamItemPOList);
        return HttpResult.success();
    }

    /**
     * 修改参数信息
     *
     * @param paramItemId
     * @param sysParamItemInDTO
     * @param userInfoId
     * @return
     */
    @Override
    public HttpResult<?> updateParamItem(long paramItemId, SysParamItemInDTO sysParamItemInDTO, long userInfoId) {
        SysParamItemPO sysParamItemPO = sysParamItemMapper.getSysParamItem(paramItemId);
        if (ObjectUtils.isEmpty(sysParamItemPO)) {
            return HttpResult.error(HttpResultEnum.EMPTY_DATA);
        }
        sysParamItemPO = new SysParamItemPO();
        BeanUtils.copyProperties(sysParamItemInDTO, sysParamItemPO);
        sysParamItemPO.setParamItemId(paramItemId);
        sysParamItemPO.setUpdateBy(userInfoId);
        sysParamItemMapper.updateSysParamItem(sysParamItemPO);
        return HttpResult.success();
    }

    /**
     * 删除参数信息
     *
     * @param paramItemId
     * @param userInfoId
     * @return
     */
    @Override
    public HttpResult<?> deleteParamItem(long paramItemId, long userInfoId) {
        sysParamItemMapper.deleteByIdLogical(paramItemId, userInfoId);
        return HttpResult.success();
    }

    /**
     * 获取参数列表
     *
     * @param paramGroupName
     * @return
     */
    @Override
    public HttpResult<List<SelectDataModel>> listParams(String paramGroupName) {
        List<SysParamItemPO> sysParamItemPOList = this.listSysParamItems(paramGroupName);
        if (CollectionUtils.isEmpty(sysParamItemPOList)) {
            return HttpResult.success();
        }
        List<SelectDataModel> selectDataModelList = sysParamItemPOList.stream().map(sysParamItemPO -> {
            SelectDataModel selectDataModel = new SelectDataModel();
            selectDataModel.setValue(sysParamItemPO.getParamItemValue());
            selectDataModel.setLabel(sysParamItemPO.getParamItemName());
            selectDataModel.setOrder(sysParamItemPO.getParamItemOrder());
            return selectDataModel;
        }).collect(Collectors.toList());
        return HttpResult.success(selectDataModelList);
    }

    /**
     * 获取树状参数列表
     *
     * @param paramGroupName
     * @return
     */
    @Override
    public HttpResult<List<CascaderDataModel>> listParamsTree(String paramGroupName) {
        List<SysParamItemPO> sysParamItemPOList = this.listSysParamItems(paramGroupName);
        if (CollectionUtils.isEmpty(sysParamItemPOList)) {
            return HttpResult.success();
        }
        List<CascaderDataModel> cascaderDataModelList = new ArrayList<>();
        this.getParamInfoCascader(sysParamItemPOList, cascaderDataModelList, 0);
        return HttpResult.success(cascaderDataModelList);
    }

    /**
     * 根据参数组名查询参数信息
     *
     * @param paramGroupName
     * @return
     */
    private List<SysParamItemPO> listSysParamItems(String paramGroupName) {
        // 拼接查询条件
        List<ConditionModel> paramGroupConditionModelList = new ArrayList<>();
        paramGroupConditionModelList.add(new ConditionModel("param_group_name", paramGroupName));
        // 根据参数组名查询参数组信息
        List<SysParamGroupPO> sysParamGroupPOList = sysParamGroupMapper.listSysParamGroups(new WhereConditions(paramGroupConditionModelList, 1));
        if (CollectionUtils.isEmpty(sysParamGroupPOList)) {
            return null;
        }
        // 参数组id
        long paramGroupId = sysParamGroupPOList.get(0).getParamGroupId();
        // 拼接查询条件
        List<ConditionModel> paramItemConditionModelList = new ArrayList<>();
        paramItemConditionModelList.add(new ConditionModel("param_group_id", String.valueOf(paramGroupId)));
        // 设置要排序的字段及顺序
        List<OrderByModel> orderByList = new ArrayList<>();
        orderByList.add(new OrderByModel("param_item_order", OrderByEnum.ASC));
        return sysParamItemMapper.listSysParamItems(new WhereConditions(paramItemConditionModelList, orderByList));
    }


    /**
     * 递归得到树状结构的参数（element-ui级联用）
     *
     * @param sysParamItemPOList
     * @param cascaderDataModelList
     * @param parentId
     */
    private void getParamInfoCascader(List<SysParamItemPO> sysParamItemPOList, List<CascaderDataModel> cascaderDataModelList, long parentId) {
        // 遍历参数集合
        for (SysParamItemPO sysParamItemPO : sysParamItemPOList) {
            // 当前参数的parentId等于传入的parentId时，添加到树状菜单中
            if (parentId == sysParamItemPO.getParentId()) {
                CascaderDataModel cascaderDataModel = new CascaderDataModel();
                // 将参数的po对象转为级联选择器数据模型
                cascaderDataModel.setValue(sysParamItemPO.getParamItemValue());
                cascaderDataModel.setLabel(sysParamItemPO.getParamItemName());
                cascaderDataModel.setOrder(Integer.valueOf(sysParamItemPO.getParamItemOrder()));
                List<CascaderDataModel> childrenList = new ArrayList<>();
                this.getParamInfoCascader(sysParamItemPOList, childrenList, sysParamItemPO.getParamItemId());
                if (!CollectionUtils.isEmpty(childrenList)) {
                    cascaderDataModel.setChildren(childrenList);
                }
                cascaderDataModelList.add(cascaderDataModel);
                // 利用Lambda表达式进行排序(按order升序)
                cascaderDataModelList.sort(Comparator.comparingInt(CascaderDataModel::getOrder));
            }
        }
    }
}
