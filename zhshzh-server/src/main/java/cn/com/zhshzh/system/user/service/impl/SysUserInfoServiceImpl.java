package cn.com.zhshzh.system.user.service.impl;

import cn.com.zhshzh.core.constant.PatternMatchesConstants;
import cn.com.zhshzh.core.model.ConditionModel;
import cn.com.zhshzh.core.model.WhereConditions;
import cn.com.zhshzh.core.util.JsonResultUtil;
import cn.com.zhshzh.system.user.dao.SysUserInfoMapper;
import cn.com.zhshzh.system.user.dto.SysUserInfoDTO;
import cn.com.zhshzh.system.user.po.SysUserInfoPO;
import cn.com.zhshzh.system.user.service.SysUserInfoService;
import cn.com.zhshzh.system.user.util.SysUserInfoConvertUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统用户信息impl
 *
 * @author WBT
 * @since 2019/10/15
 */
@Service
public class SysUserInfoServiceImpl implements SysUserInfoService {
    private static final Logger logger = LogManager.getLogger(SysUserInfoServiceImpl.class);
    private SysUserInfoMapper sysUserInfoMapper;

    @Autowired
    private SysUserInfoServiceImpl(SysUserInfoMapper sysUserInfoMapper) {
        this.sysUserInfoMapper = sysUserInfoMapper;
    }

    /**
     * 根据用户id查询用户信息
     *
     * @param userInfoId 用户id
     * @return 用户基本信息
     */
    @Override
    public SysUserInfoDTO getSysUserInfo(Long userInfoId) {
        SysUserInfoPO sysUserInfoPO = sysUserInfoMapper.getSysUserInfo(userInfoId);
        SysUserInfoConvertUtil sysUserInfoConvertUtil = new SysUserInfoConvertUtil();
        return sysUserInfoConvertUtil.convertToDTO(sysUserInfoPO);
    }

    /**
     * 根据用户登录账号查询用户信息（用户登录时用）
     *
     * @param username 用户的登录账号
     * @return 用户信息
     */
    public SysUserInfoPO getSysUserInfoForLogin(String username) throws UsernameNotFoundException {
        // 查询条件
        List<ConditionModel> conditionModels = new ArrayList<>();
        // 判断用户登录方式
        if (username.matches(PatternMatchesConstants.EMAIL)) {
            // 邮箱登录
            conditionModels.add(new ConditionModel("email", username));
        } else if (username.matches(PatternMatchesConstants.MOBILE_NUMBER)) {
            // 手机号登录
            conditionModels.add(new ConditionModel("phone_number", username));
        } else if (username.matches(PatternMatchesConstants.SERIAL_NUMBER)) {
            // 员工号登录
            conditionModels.add(new ConditionModel("serial_number", username));
        } else if (username.matches(PatternMatchesConstants.USER_NAME)) {
            // 用户名登录
            conditionModels.add(new ConditionModel("user_name", username));
        } else {
            throw new UsernameNotFoundException("User " + username + " was not found in database");
        }
        // 条件查询系统用户信息
        List<SysUserInfoPO> sysUserInfoPOS = sysUserInfoMapper.listSysUserInfos(new WhereConditions(conditionModels));
        // 如果未查到用户信息，则抛异常
        if (CollectionUtils.isEmpty(sysUserInfoPOS)) {
            throw new UsernameNotFoundException("User " + username + " was not found in database");
        }
        return sysUserInfoPOS.get(0);
    }

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    @Override
    public SysUserInfoPO getSysUserInfoByUsername(String username) {
        // 查询条件
        List<ConditionModel> conditionModels = new ArrayList<>();
        conditionModels.add(new ConditionModel("user_name", username));
        // 根据用户名查询用户信息
        List<SysUserInfoPO> sysUserInfoPOS = sysUserInfoMapper.listSysUserInfos(new WhereConditions(conditionModels));
        if (!CollectionUtils.isEmpty(sysUserInfoPOS)) {
            return sysUserInfoPOS.get(0);
        }
        return null;
    }

    /**
     * 条件查询用户信息
     *
     * @param sysUserInfoPO 查询条件对象
     * @return 用户信息数组
     */
    @Override
    public List<SysUserInfoPO> listSysUserInfo(SysUserInfoPO sysUserInfoPO) {
        return null;
    }

    /**
     * 保存用户信息
     *
     * @param sysUserInfoPO 用户基本信息
     */
    @Override
    public JsonResultUtil saveSysUserInfo(SysUserInfoPO sysUserInfoPO) {
        Long userInfoId = sysUserInfoPO.getUserInfoId();
        if (userInfoId == null) {
            String password = new BCryptPasswordEncoder().encode(sysUserInfoPO.getPassword());
            sysUserInfoPO.setPassword(password);
            sysUserInfoPO.setCreateBy(11111L);
            sysUserInfoPO.setUpdateBy(11111L);
            sysUserInfoMapper.insertSysUserInfo(sysUserInfoPO);
        } else {
            sysUserInfoPO.setCreateBy(11111L);
            sysUserInfoMapper.updateSysUserInfo(sysUserInfoPO);
        }
        return new JsonResultUtil();
    }
}
