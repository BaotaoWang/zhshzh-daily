package cn.com.zhshzh.system.user.controller;

import cn.com.zhshzh.core.constant.HttpResultEnum;
import cn.com.zhshzh.core.model.HttpResult;
import cn.com.zhshzh.system.user.dto.SysUserInfoInDTO;
import cn.com.zhshzh.system.user.po.SysUserInfoPO;
import cn.com.zhshzh.system.user.service.SysUserInfoService;
import cn.com.zhshzh.system.user.util.SysUserInfoConvertUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public SysUserInfoInDTO getSysUserInfo(@ApiParam(name = "userInfoId", value = "用户id", required = true) Long userInfoId) {
        String code = HttpResultEnum.SUCCESS.getMessage();
        return sysUserInfoService.getSysUserInfo(userInfoId);
    }

    /**
     * 保存用户信息
     *
     * @param sysUserInfoInDTO 用户基本信息
     * @return 用户基本信息
     */
    @ApiOperation(value = "保存用户信息", notes = "注意问题点")
    @PostMapping("/saveSysUserInfo")
    public HttpResult<SysUserInfoInDTO> saveSysUserInfo(SysUserInfoInDTO sysUserInfoInDTO) {
        SysUserInfoConvertUtil sysUserInfoConvertUtil = new SysUserInfoConvertUtil();
        SysUserInfoPO sysUserInfoPO = sysUserInfoConvertUtil.convertToPO(sysUserInfoInDTO);
        return sysUserInfoService.saveSysUserInfo(sysUserInfoPO);
    }
}
