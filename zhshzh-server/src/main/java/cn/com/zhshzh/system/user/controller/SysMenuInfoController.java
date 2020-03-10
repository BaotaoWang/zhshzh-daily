package cn.com.zhshzh.system.user.controller;

import cn.com.zhshzh.core.model.HttpResult;
import cn.com.zhshzh.core.util.RedisUtil;
import cn.com.zhshzh.system.user.dto.SysMenuInfoDTO;
import cn.com.zhshzh.system.user.dto.SysMenuInfoResultDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 系统菜单信息controller
 *
 * @author WBT
 * @since 2020/03/10
 */
@Api(tags = "菜单信息API")
@RestController
@RequestMapping("/menuInfos")
public class SysMenuInfoController {

    /**
     * 根据id查询菜单信息
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}/admin")
    @ApiOperation(value = "根据id查询菜单信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "菜单id", required = true, paramType = "path", dataType = "long", example = "0")
    })
    public HttpResult<SysMenuInfoResultDTO> getSysMenuInfo(@PathVariable("id") long id) {
        return HttpResult.success();
    }

    /**
     * 查询用户有权查看的树状菜单
     *
     * @param request
     * @return
     */
    @GetMapping
    @ApiOperation(value = "查询用户有权查看的树状菜单")
    public HttpResult<List<SysMenuInfoResultDTO>> listSysMenuInfos(HttpServletRequest request) {
        long userInfoId = RedisUtil.getUserInfoId(request);
        System.out.println(userInfoId);
        return HttpResult.success();
    }

    /**
     * 查询所有的菜单
     *
     * @return
     */
    @GetMapping("/all/admin")
    @ApiOperation(value = "查询所有的菜单")
    public HttpResult<List<SysMenuInfoResultDTO>> listAllSysMenuInfos() {
        return HttpResult.success();
    }

    /**
     * 新增菜单信息
     *
     * @param sysMenuInfoDTO
     * @param request
     * @return
     */
    @PostMapping("/admin")
    @ApiOperation("新增菜单信息")
    public HttpResult<?> insertSysMenuInfo(@RequestBody SysMenuInfoDTO sysMenuInfoDTO, HttpServletRequest request) {
        RedisUtil.copyUserInfoId(request, sysMenuInfoDTO);
        return HttpResult.success();
    }

    /**
     * 修改菜单信息
     *
     * @param id
     * @param sysMenuInfoDTO
     * @param request
     * @return
     */
    @PutMapping("/{id}/admin")
    @ApiOperation("修改菜单信息")
    public HttpResult<?> updateSysMenuInfo(@PathVariable("id") long id, @RequestBody SysMenuInfoDTO sysMenuInfoDTO,
                                           HttpServletRequest request) {
        return HttpResult.success();
    }

    /**
     * 删除菜单信息
     *
     * @param id
     * @param request
     * @return
     */
    @DeleteMapping("/{id}/admin")
    @ApiOperation("删除菜单信息")
    public HttpResult<?> deleteSysMenuInfo(@PathVariable("id") long id, HttpServletRequest request) {
        return HttpResult.success();
    }
}
