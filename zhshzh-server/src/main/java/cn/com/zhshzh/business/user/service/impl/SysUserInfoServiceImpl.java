package cn.com.zhshzh.business.user.service.impl;

import cn.com.zhshzh.business.user.dao.SysUserInfoMapper;
import cn.com.zhshzh.business.user.dto.SysUserInfoDTO;
import cn.com.zhshzh.business.user.po.SysUserInfoPO;
import cn.com.zhshzh.business.user.service.SysUserInfoService;
import cn.com.zhshzh.business.user.util.SysUserInfoConvertUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
        SysUserInfoDTO sysUserInfoDTO = sysUserInfoConvertUtil.convertToDTO(sysUserInfoPO);
        return sysUserInfoDTO;
    }

    /**
     * 条件查询用户信息
     *
     * @param sysUserInfoPO 查询条件对象
     * @return 用户信息数组
     */
    @Override
    public List<SysUserInfoPO> listSysUserInfo(SysUserInfoPO sysUserInfoPO) {
        return sysUserInfoMapper.listSysUserInfo(sysUserInfoPO);
    }

    /**
     * 保存用户信息
     *
     * @param sysUserInfoPO 用户基本信息
     * @return 新增记录条数
     */
    @Override
    public int saveSysUserInfo(SysUserInfoPO sysUserInfoPO) {
        Long userInfoId = sysUserInfoPO.getUserInfoId();
        if (userInfoId == null) {
            String password = new BCryptPasswordEncoder().encode(sysUserInfoPO.getPassword());
            sysUserInfoPO.setPassword(password);
            sysUserInfoPO.setCreateBy(11111L);
            sysUserInfoPO.setUpdateBy(11111L);
            return sysUserInfoMapper.insertSysUserInfo(sysUserInfoPO);
        } else {
            sysUserInfoPO.setCreateBy(11111L);
            return sysUserInfoMapper.updateSysUserInfo(sysUserInfoPO);
        }
    }
}
