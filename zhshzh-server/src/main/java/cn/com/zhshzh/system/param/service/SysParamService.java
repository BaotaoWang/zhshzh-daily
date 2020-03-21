package cn.com.zhshzh.system.param.service;

import cn.com.zhshzh.core.model.CascaderDataModel;
import cn.com.zhshzh.core.model.HttpResult;
import cn.com.zhshzh.core.model.PageResult;
import cn.com.zhshzh.core.model.SelectDataModel;
import cn.com.zhshzh.system.param.dto.SysParamGroupInDTO;
import cn.com.zhshzh.system.param.dto.SysParamGroupOutDTO;
import cn.com.zhshzh.system.param.dto.SysParamItemInDTO;
import cn.com.zhshzh.system.param.dto.SysParamItemOutDTO;

import java.util.List;

/**
 * 系统参数service
 *
 * @author WBT
 * @since 2020/03/16
 */
public interface SysParamService {
    /**
     * 根据id查询参数组信息
     *
     * @param paramGroupId
     * @return
     */
    HttpResult<SysParamGroupOutDTO> getParamGroup(long paramGroupId);

    /**
     * 条件查询参数组信息
     *
     * @param keyWord
     * @param pageNum
     * @param pageSize
     * @return
     */
    HttpResult<PageResult<SysParamGroupOutDTO>> listParamGroups(String keyWord, Integer pageNum, Integer pageSize);

    /**
     * 新增参数组信息
     *
     * @param sysParamGroupInDTO
     * @param userInfoId
     * @return
     */
    HttpResult<?> saveParamGroup(SysParamGroupInDTO sysParamGroupInDTO, long userInfoId);

    /**
     * 修改参数组信息
     *
     * @param paramGroupId
     * @param sysParamGroupInDTO
     * @param userInfoId
     * @return
     */
    HttpResult<?> updateParamGroup(long paramGroupId, SysParamGroupInDTO sysParamGroupInDTO, long userInfoId);

    /**
     * 删除参数组信息
     *
     * @param paramGroupId
     * @param userInfoId
     * @return
     */
    HttpResult<?> deleteParamGroup(long paramGroupId, long userInfoId);

    /**
     * 根据id查询参数信息
     *
     * @param paramItemId
     * @return
     */
    HttpResult<SysParamItemOutDTO> getParamItem(long paramItemId);

    /**
     * 条件查询参数信息
     *
     * @param paramGroupId
     * @param keyWord
     * @param pageNum
     * @param pageSize
     * @return
     */
    HttpResult<PageResult<SysParamItemOutDTO>> listParamItems(long paramGroupId, String keyWord, Integer pageNum, Integer pageSize);

    /**
     * 新增参数信息
     *
     * @param sysParamItemInDTOList
     * @param userInfoId
     * @return
     */
    HttpResult<?> saveParamItem(List<SysParamItemInDTO> sysParamItemInDTOList, long userInfoId);

    /**
     * 修改参数信息
     *
     * @param paramItemId
     * @param sysParamItemInDTO
     * @param userInfoId
     * @return
     */
    HttpResult<?> updateParamItem(long paramItemId, SysParamItemInDTO sysParamItemInDTO, long userInfoId);

    /**
     * 删除参数信息
     *
     * @param paramItemId
     * @param userInfoId
     * @return
     */
    HttpResult<?> deleteParamItem(long paramItemId, long userInfoId);

    /**
     * 获取参数列表
     *
     * @param paramGroupName
     * @return
     */
    HttpResult<List<SelectDataModel>> listParams(String paramGroupName);

    /**
     * 获取树状参数列表
     *
     * @param paramGroupName
     * @return
     */
    HttpResult<List<CascaderDataModel>> listParamsTree(String paramGroupName);
}
