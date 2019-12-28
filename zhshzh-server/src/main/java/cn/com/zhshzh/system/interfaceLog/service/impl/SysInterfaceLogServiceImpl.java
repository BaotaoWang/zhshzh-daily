package cn.com.zhshzh.system.interfaceLog.service.impl;

import cn.com.zhshzh.system.interfaceLog.dao.SysInterfaceLogMapper;
import cn.com.zhshzh.system.interfaceLog.po.SysInterfaceLogPO;
import cn.com.zhshzh.system.interfaceLog.service.SysInterfaceLogService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 接口日志impl
 *
 * @author WBT
 * @since 2019/12/26
 */
@Service
public class SysInterfaceLogServiceImpl implements SysInterfaceLogService {
    private static final Logger logger = LogManager.getLogger(SysInterfaceLogServiceImpl.class);
    private SysInterfaceLogMapper sysInterfaceLogMapper;

    @Autowired
    private SysInterfaceLogServiceImpl(SysInterfaceLogMapper sysInterfaceLogMapper) {
        this.sysInterfaceLogMapper = sysInterfaceLogMapper;
    }

    /**
     * 保存接口日志
     *
     * @param sysInterfaceLogPO 接口请求信息
     */
    @Override
    public void saveInterfaceLog(SysInterfaceLogPO sysInterfaceLogPO) {
        try {
            sysInterfaceLogMapper.insertSysInterfaceLog(sysInterfaceLogPO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
