package cn.com.zhshzh.system.user.service;

import cn.com.zhshzh.core.model.HttpResult;
import cn.com.zhshzh.system.user.dto.FileHeadPortraitResultDTO;
import org.springframework.web.multipart.MultipartFile;

/**
 * 用户头像信息service
 *
 * @author WBT
 * @since 2020/03/11
 */
public interface FileHeadPortraitService {

    /**
     * 保存头像信息
     *
     * @param multipartFile
     * @param userInfoId
     * @return
     */
    HttpResult<?> savePortrait(MultipartFile multipartFile, long userInfoId);

    /**
     * 获取头像信息
     *
     * @param userInfoId
     * @return
     */
    HttpResult<FileHeadPortraitResultDTO> getPortrait(long userInfoId);
}
