package cn.com.zhshzh.system.interfaceLog.service;

import cn.com.zhshzh.system.interfaceLog.po.SysInterfaceLogPO;

/**
 * 接口日志service
 *
 * @author WBT
 * @since 2019/12/26
 */
public interface SysInterfaceLogService {
    /**
     * 保存接口日志
     *
     * @param sysInterfaceLogPO 接口请求信息
     */
    void saveInterfaceLog(SysInterfaceLogPO sysInterfaceLogPO);
}
