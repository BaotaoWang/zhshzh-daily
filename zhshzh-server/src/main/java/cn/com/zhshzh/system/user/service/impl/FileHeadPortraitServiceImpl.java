package cn.com.zhshzh.system.user.service.impl;

import cn.com.zhshzh.core.constant.HttpResultEnum;
import cn.com.zhshzh.core.constant.OrderByEnum;
import cn.com.zhshzh.core.minio.DailyMinioException;
import cn.com.zhshzh.core.minio.MinioService;
import cn.com.zhshzh.core.model.ConditionModel;
import cn.com.zhshzh.core.model.HttpResult;
import cn.com.zhshzh.core.model.OrderByModel;
import cn.com.zhshzh.core.model.WhereConditions;
import cn.com.zhshzh.system.user.dao.FileHeadPortraitMapper;
import cn.com.zhshzh.system.user.dto.FileHeadPortraitOutDTO;
import cn.com.zhshzh.system.user.po.FileHeadPortraitPO;
import cn.com.zhshzh.system.user.service.FileHeadPortraitService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户头像信息service
 *
 * @author WBT
 * @since 2020/03/11
 */
@Service
public class FileHeadPortraitServiceImpl implements FileHeadPortraitService {
    private static final Logger logger = LogManager.getLogger(FileHeadPortraitServiceImpl.class);

    private FileHeadPortraitMapper fileHeadPortraitMapper;
    private MinioService minioService;

    public static final String FILE_PATH_PREFIX = "portrait";

    @Autowired
    void setFileHeadPortraitMapper(FileHeadPortraitMapper fileHeadPortraitMapper) {
        this.fileHeadPortraitMapper = fileHeadPortraitMapper;
    }

    @Autowired
    void setMinioService(MinioService minioService) {
        this.minioService = minioService;
    }

    /**
     * 保存头像信息
     *
     * @param multipartFile
     * @param userInfoId
     * @return
     */
    @Override
    public HttpResult<?> savePortrait(MultipartFile multipartFile, long userInfoId) {
        // 原文件名
        String originalFileName = multipartFile.getOriginalFilename();
        // 如果无图片信息，直接返回
        if (StringUtils.isEmpty(originalFileName)) {
            return HttpResult.error(HttpResultEnum.EMPTY_HEAD_PORTRAIT);
        }

        // 查询用户最新的头像信息
        FileHeadPortraitPO fileHeadPortraitPO = this.getLastHeadPortraitInfo(userInfoId);
        // 如果未查到头像信息，version值为1。如果上传过头像，则头像版本号加1
        int version = ObjectUtils.isEmpty(fileHeadPortraitPO) ? 1 : fileHeadPortraitPO.getVersion() + 1;

        // 当前时间毫秒值
        long time = System.currentTimeMillis();
        // 图片文件后缀
        String imageSuffix = originalFileName.substring(originalFileName.lastIndexOf("."));
        // 拼接文件全路径。注意：不能用File.separator或者"\"，windows下会报错
        String fileName = FILE_PATH_PREFIX + "/" + userInfoId + "/" + time + imageSuffix;
        InputStream stream = null;
        try {
            stream = multipartFile.getInputStream();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        // 图片大小
        long size = multipartFile.getSize();
        // 图片类型
        String contentType = multipartFile.getContentType();
        if (StringUtils.isEmpty(contentType)) {
            contentType = "application/octet-stream";
        }
        // 将图片上传到minio文件管理系统
        try {
            minioService.putFile(fileName, stream, size, contentType);
        } catch (DailyMinioException e) {
            logger.error(e.getMessage(), e);
            return HttpResult.error(HttpResultEnum.MINIO_EXCEPTION);
        }
        FileHeadPortraitPO newFileHeadPortraitPO = new FileHeadPortraitPO();
        newFileHeadPortraitPO.setImageName(originalFileName);
        newFileHeadPortraitPO.setImageSize(size);
        newFileHeadPortraitPO.setImageType(contentType);
        newFileHeadPortraitPO.setVersion(version);
        newFileHeadPortraitPO.setImagePath(fileName);
        newFileHeadPortraitPO.setUserInfoId(userInfoId);
        newFileHeadPortraitPO.setCreateBy(userInfoId);
        newFileHeadPortraitPO.setUpdateBy(userInfoId);
        // 将图片信息保存到数据库
        fileHeadPortraitMapper.insertFileHeadPortrait(newFileHeadPortraitPO);
        return HttpResult.success();
    }

    /**
     * 获取头像信息
     *
     * @param userInfoId
     * @return
     */
    public HttpResult<FileHeadPortraitOutDTO> getPortrait(long userInfoId) {
        // 查询数据库中最新的头像信息
        FileHeadPortraitPO fileHeadPortraitPO = this.getLastHeadPortraitInfo(userInfoId);
        // 如果未上传过头像，直接返回
        if (ObjectUtils.isEmpty(fileHeadPortraitPO)) {
            return HttpResult.success();
        }
        FileHeadPortraitOutDTO fileHeadPortraitOutDTO = new FileHeadPortraitOutDTO();
        fileHeadPortraitOutDTO.setVersion(fileHeadPortraitPO.getVersion());
        try {
            // 从文件系统minio中获取base64位的头像
            fileHeadPortraitOutDTO.setHeadPortrait(minioService.getBase64File(fileHeadPortraitPO.getImagePath()));
        } catch (DailyMinioException e) {
            logger.error(e.getMessage(), e);
        }
        return HttpResult.success(fileHeadPortraitOutDTO);
    }

    /**
     * 查询用户最新的头像信息
     *
     * @param userInfoId
     * @return
     */
    private FileHeadPortraitPO getLastHeadPortraitInfo(long userInfoId) {
        // 封装查询条件-user_info_id=userInfoId
        ConditionModel conditionModel = new ConditionModel("user_info_id", String.valueOf(userInfoId));
        List<ConditionModel> conditionModelList = new ArrayList<>();
        conditionModelList.add(conditionModel);
        // 对version字段倒序排序
        OrderByModel orderByModel = new OrderByModel("version", OrderByEnum.DESC);
        List<OrderByModel> orderByModelList = new ArrayList<>();
        orderByModelList.add(orderByModel);
        WhereConditions whereConditions = new WhereConditions(conditionModelList, orderByModelList, 1);
        // 查询该用户version最大的头像信息
        List<FileHeadPortraitPO> fileHeadPortraitPOList = fileHeadPortraitMapper.listFileHeadPortraits(whereConditions);
        // 如果未上传过头像，返回null;否则返回最新的头像信息
        return CollectionUtils.isEmpty(fileHeadPortraitPOList) ? null : fileHeadPortraitPOList.get(0);
    }
}
