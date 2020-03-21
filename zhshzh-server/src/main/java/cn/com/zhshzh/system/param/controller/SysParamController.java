package cn.com.zhshzh.system.param.controller;

import cn.com.zhshzh.core.model.CascaderDataModel;
import cn.com.zhshzh.core.model.HttpResult;
import cn.com.zhshzh.core.model.PageResult;
import cn.com.zhshzh.core.model.SelectDataModel;
import cn.com.zhshzh.core.util.RedisUtil;
import cn.com.zhshzh.system.param.dto.SysParamGroupInDTO;
import cn.com.zhshzh.system.param.dto.SysParamGroupOutDTO;
import cn.com.zhshzh.system.param.dto.SysParamItemInDTO;
import cn.com.zhshzh.system.param.dto.SysParamItemOutDTO;
import cn.com.zhshzh.system.param.service.SysParamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 系统参数controller
 *
 * @author WBT
 * @since 2020/03/16
 */
@Api(tags = "系统参数API")
@RestController
@RequestMapping("/params")
public class SysParamController {
    private SysParamService sysParamService;

    @Autowired
    void setSysParamService(SysParamService sysParamService) {
        this.sysParamService = sysParamService;
    }

    /**
     * 根据id查询参数组信息
     *
     * @param paramGroupId
     * @return
     */
    @ApiOperation(value = "根据id查询参数组信息", notes = "关键词支持模糊查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "参数组id", required = true, paramType = "path", dataType = "long", example = "0")
    })
    @GetMapping("/groups/{id}/admin")
    public HttpResult<SysParamGroupOutDTO> getParamGroup(@PathVariable("id") long paramGroupId) {
        return sysParamService.getParamGroup(paramGroupId);
    }

    /**
     * 条件查询参数组信息
     *
     * @param keyWord
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/groups/admin")
    @ApiOperation(value = "条件查询参数组信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyWord", value = "关键词", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true, paramType = "query", dataType = "Integer")
    })
    public HttpResult<PageResult<SysParamGroupOutDTO>> listParamGroups(@RequestParam(value = "keyWord", required = false) String keyWord,
                                                                       @RequestParam(value = "pageNum") Integer pageNum,
                                                                       @RequestParam(value = "pageSize") Integer pageSize) {
        return sysParamService.listParamGroups(keyWord, pageNum, pageSize);
    }

    /**
     * 新增参数组信息
     *
     * @param sysParamGroupInDTO
     * @param request
     * @return
     */
    @PostMapping("/groups/admin")
    @ApiOperation("新增参数组信息")
    public HttpResult<?> saveParamGroup(@RequestBody SysParamGroupInDTO sysParamGroupInDTO, HttpServletRequest request) {
        return sysParamService.saveParamGroup(sysParamGroupInDTO, RedisUtil.getUserInfoId(request));
    }

    /**
     * 修改参数组信息
     *
     * @param paramGroupId
     * @param sysParamGroupInDTO
     * @param request
     * @return
     */
    @PutMapping("/groups/{id}/admin")
    @ApiOperation("修改参数组信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "参数组id", required = true, paramType = "path", dataType = "long", example = "0")
    })
    public HttpResult<?> updateParamGroup(@PathVariable("id") long paramGroupId, @RequestBody SysParamGroupInDTO sysParamGroupInDTO,
                                          HttpServletRequest request) {
        return sysParamService.updateParamGroup(paramGroupId, sysParamGroupInDTO, RedisUtil.getUserInfoId(request));
    }

    /**
     * 删除参数组信息
     *
     * @param paramGroupId
     * @param request
     * @return
     */
    @DeleteMapping("/groups/{id}/admin")
    @ApiOperation("删除参数组信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "参数组id", required = true, paramType = "path", dataType = "long", example = "0")
    })
    public HttpResult<?> deleteParamGroup(@PathVariable("id") long paramGroupId, HttpServletRequest request) {
        return sysParamService.deleteParamGroup(paramGroupId, RedisUtil.getUserInfoId(request));
    }

    /**
     * 根据id查询参数信息
     *
     * @param paramItemId
     * @return
     */
    @ApiOperation(value = "根据id查询参数信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "参数id", required = true, paramType = "path", dataType = "long", example = "0")
    })
    @GetMapping("/items/{id}/admin")
    public HttpResult<SysParamItemOutDTO> getParamItem(@PathVariable("id") long paramItemId) {
        return sysParamService.getParamItem(paramItemId);
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
    @GetMapping("/items/admin")
    @ApiOperation(value = "条件查询参数信息", notes = "关键词支持模糊查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "paramGroupId", value = "参数组id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "keyWord", value = "关键词", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true, paramType = "query", dataType = "Integer")
    })
    public HttpResult<PageResult<SysParamItemOutDTO>> listParamItems(@RequestParam(value = "paramGroupId") long paramGroupId,
                                                                     @RequestParam(value = "keyWord", required = false) String keyWord,
                                                                     @RequestParam(value = "pageNum") Integer pageNum,
                                                                     @RequestParam(value = "pageSize") Integer pageSize) {
        return sysParamService.listParamItems(paramGroupId, keyWord, pageNum, pageSize);
    }

    /**
     * 新增参数信息
     *
     * @param sysParamItemInDTOList
     * @param request
     * @return
     */
    @PostMapping("/items/admin")
    @ApiOperation("新增参数信息")
    public HttpResult<?> saveParamItem(@RequestBody List<SysParamItemInDTO> sysParamItemInDTOList, HttpServletRequest request) {
        return sysParamService.saveParamItem(sysParamItemInDTOList, RedisUtil.getUserInfoId(request));
    }

    /**
     * 修改参数信息
     *
     * @param paramItemId
     * @param sysParamItemInDTO
     * @param request
     * @return
     */
    @PutMapping("/items/{id}/admin")
    @ApiOperation("修改参数信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "参数id", required = true, paramType = "path", dataType = "long", example = "0")
    })
    public HttpResult<?> updateParamItem(@PathVariable("id") long paramItemId, @RequestBody SysParamItemInDTO sysParamItemInDTO,
                                         HttpServletRequest request) {
        return sysParamService.updateParamItem(paramItemId, sysParamItemInDTO, RedisUtil.getUserInfoId(request));
    }

    /**
     * 删除参数信息
     *
     * @param paramItemId
     * @param request
     * @return
     */
    @DeleteMapping("/items/{id}/admin")
    @ApiOperation("删除参数信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "参数id", required = true, paramType = "path", dataType = "long", example = "0")
    })
    public HttpResult<?> deleteParamItem(@PathVariable("id") long paramItemId, HttpServletRequest request) {
        return sysParamService.deleteParamItem(paramItemId, RedisUtil.getUserInfoId(request));
    }

    /**
     * 获取参数列表
     *
     * @param paramGroupName
     * @return
     */
    @GetMapping
    @ApiOperation(value = "获取参数列表", notes = "必须指定参数组名")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "paramGroupName", value = "参数组名", required = true, paramType = "query", dataType = "String")
    })
    public HttpResult<List<SelectDataModel>> listParams(@RequestParam(value = "paramGroupName") String paramGroupName) {
        return sysParamService.listParams(paramGroupName);
    }

    /**
     * 获取树状参数列表
     *
     * @param paramGroupName
     * @return
     */
    @GetMapping("/tree")
    @ApiOperation(value = "获取树状参数列表", notes = "必须指定参数组名")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "paramGroupName", value = "参数组名", required = true, paramType = "query", dataType = "String")
    })
    public HttpResult<List<CascaderDataModel>> listParamsTree(@RequestParam(value = "paramGroupName") String paramGroupName) {
        return sysParamService.listParamsTree(paramGroupName);
    }
}
