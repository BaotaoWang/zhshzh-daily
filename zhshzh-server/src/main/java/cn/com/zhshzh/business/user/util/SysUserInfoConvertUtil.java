package cn.com.zhshzh.business.user.util;

import cn.com.zhshzh.business.user.po.SysUserInfoPO;
import cn.com.zhshzh.core.security.MyUserDetailsService;
import cn.com.zhshzh.core.util.Convertable;
import cn.com.zhshzh.core.util.DateFormatUtil;
import cn.com.zhshzh.business.user.dto.SysUserInfoDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;

import java.text.ParseException;

/**
 * 系统用户DTO对象和PO对象相互转换的工具类
 *
 * @author WBT
 * @since 2019/11/27
 */
public class SysUserInfoConvertUtil implements Convertable<SysUserInfoPO, SysUserInfoDTO> {
    private static final Logger logger = LogManager.getLogger(MyUserDetailsService.class);

    /**
     * 系统用户的PO对象转换为DTO对象
     *
     * @param sysUserInfoPO 系统用户PO对象
     * @return 系统用户DTO对象
     */
    @Override
    public SysUserInfoDTO convertToDTO(SysUserInfoPO sysUserInfoPO) {
        SysUserInfoDTO sysUserInfoDTO = new SysUserInfoDTO();
        BeanUtils.copyProperties(sysUserInfoPO, sysUserInfoDTO);
        sysUserInfoDTO.setBirth(DateFormatUtil.getDateString(sysUserInfoPO.getBirth()));
        return sysUserInfoDTO;
    }

    /**
     * 系统用户的DTO对象转换为PO对象
     *
     * @param sysUserInfoDTO 系统用户DTO对象
     * @return 系统用户PO对象
     */
    @Override
    public SysUserInfoPO convertToPO(SysUserInfoDTO sysUserInfoDTO) {
        SysUserInfoPO sysUserInfoPO = new SysUserInfoPO();
        BeanUtils.copyProperties(sysUserInfoDTO, sysUserInfoPO);
        try {
            sysUserInfoPO.setBirth(DateFormatUtil.getDate(sysUserInfoDTO.getBirth()));
        } catch (ParseException e) {
            logger.error(e);
        }
        return sysUserInfoPO;
    }
}
