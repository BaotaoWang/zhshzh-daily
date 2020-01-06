package cn.com.zhshzh.system.user.controller;

import cn.com.zhshzh.core.util.JsonResultUtil;
import cn.com.zhshzh.system.user.dto.SysUserInfoDTO;
import cn.com.zhshzh.system.user.po.SysUserInfoPO;
import cn.com.zhshzh.system.user.service.SysUserInfoService;
import cn.com.zhshzh.system.user.util.SysUserInfoConvertUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统用户信息controller
 *
 * @author WBT
 * @since 2019/10/15
 */
@Api(value = "系统用户信息服务", tags = {"用户操作接口"})
@RestController
@RequestMapping("/userInfo")
public class SysUserInfoController {
    @Autowired
    private SysUserInfoService sysUserInfoService;

    /**
     * 根据用户id查询用户信息
     *
     * @param userInfoId 用户id
     * @return 用户基本信息
     */
    @ApiOperation(value = "获取用户信息", notes = "注意问题点")
    @GetMapping("/getSysUserInfo")
    public SysUserInfoDTO getSysUserInfo(@ApiParam(name = "userInfoId", value = "用户id", required = true) Long userInfoId) {
        return sysUserInfoService.getSysUserInfo(userInfoId);
    }

    /**
     * 保存用户信息
     *
     * @param sysUserInfoDTO 用户基本信息
     * @return 用户基本信息
     */
    @ApiOperation(value = "保存用户信息", notes = "注意问题点")
    @GetMapping("/saveSysUserInfo")
    public JsonResultUtil saveSysUserInfo(SysUserInfoDTO sysUserInfoDTO) {
        SysUserInfoConvertUtil sysUserInfoConvertUtil = new SysUserInfoConvertUtil();
        SysUserInfoPO sysUserInfoPO = sysUserInfoConvertUtil.convertToPO(sysUserInfoDTO);
        return sysUserInfoService.saveSysUserInfo(sysUserInfoPO);
    }
}