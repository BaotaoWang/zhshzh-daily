package cn.com.zhshzh.system.user.service.impl;

import cn.com.zhshzh.common.scheduleTask.Log4jScheduleTask;
import cn.com.zhshzh.system.user.dao.SysUserInfoMapper;
import cn.com.zhshzh.system.user.po.SysUserInfoPO;
import cn.com.zhshzh.system.user.service.SysUserInfoService;
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
    public SysUserInfoPO getSysUserInfo(Long userInfoId) {
        logger.error(new BCryptPasswordEncoder().encode("admin"));
        return sysUserInfoMapper.getSysUserInfo(userInfoId);
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
     * 新增用户信息
     *
     * @param sysUserInfoPO 用户基本信息
     * @return 新增记录条数
     */
    @Override
    public int insertSysUserInfo(SysUserInfoPO sysUserInfoPO) {
        return sysUserInfoMapper.insertSysUserInfo(sysUserInfoPO);
    }

    /**
     * 更新用户信息
     *
     * @param sysUserInfoPO 用户基本信息
     * @return 更新记录条数
     */
    @Override
    public int updateSysUserInfo(SysUserInfoPO sysUserInfoPO) {
        return sysUserInfoMapper.insertSysUserInfo(sysUserInfoPO);
    }
}
