package cn.com.zhshzh.system.user.util;

import cn.com.zhshzh.core.util.Convertable;
import cn.com.zhshzh.system.user.dto.SysUserInfoInDTO;
import cn.com.zhshzh.system.user.po.SysUserInfoPO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;

/**
 * 系统用户DTO对象和PO对象相互转换的工具类
 *
 * @author WBT
 * @since 2019/11/27
 */
public class SysUserInfoConvertUtil implements Convertable<SysUserInfoPO, SysUserInfoInDTO> {
    private static final Logger logger = LogManager.getLogger(SysUserInfoConvertUtil.class);

    /**
     * 系统用户的PO对象转换为DTO对象
     *
     * @param sysUserInfoPO 系统用户PO对象
     * @return 系统用户DTO对象
     */
    @Override
    public SysUserInfoInDTO convertToDTO(SysUserInfoPO sysUserInfoPO) {
        SysUserInfoInDTO sysUserInfoInDTO = new SysUserInfoInDTO();
        BeanUtils.copyProperties(sysUserInfoPO, sysUserInfoInDTO);
        return sysUserInfoInDTO;
    }

    /**
     * 系统用户的DTO对象转换为PO对象
     *
     * @param sysUserInfoInDTO 系统用户DTO对象
     * @return 系统用户PO对象
     */
    @Override
    public SysUserInfoPO convertToPO(SysUserInfoInDTO sysUserInfoInDTO) {
        SysUserInfoPO sysUserInfoPO = new SysUserInfoPO();
        BeanUtils.copyProperties(sysUserInfoInDTO, sysUserInfoPO);
        return sysUserInfoPO;
    }
}
