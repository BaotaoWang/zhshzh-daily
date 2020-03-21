package cn.com.zhshzh.system.user.controller;

import cn.com.zhshzh.core.model.HttpResult;
import cn.com.zhshzh.core.util.RedisUtil;
import cn.com.zhshzh.system.user.dto.FileHeadPortraitOutDTO;
import cn.com.zhshzh.system.user.service.FileHeadPortraitService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户头像信息controller
 *
 * @author WBT
 * @since 2020/03/11
 */
@Api(tags = "头像信息API")
@RestController
@RequestMapping("/portraits")
public class FileHeadPortraitController {
    private FileHeadPortraitService fileHeadPortraitService;

    @Autowired
    FileHeadPortraitController(FileHeadPortraitService fileHeadPortraitService) {
        this.fileHeadPortraitService = fileHeadPortraitService;
    }

    /**
     * 保存头像信息
     *
     * @param multipartFile
     * @param request
     * @return
     */
    @PostMapping
    public HttpResult<?> savePortrait(@RequestParam("headPortrait") MultipartFile multipartFile, HttpServletRequest request) {
        return fileHeadPortraitService.savePortrait(multipartFile, RedisUtil.getUserInfoId(request));
    }

    /**
     * 获取头像信息
     *
     * @param request
     * @return
     */
    @GetMapping
    public HttpResult<FileHeadPortraitOutDTO> getPortrait(HttpServletRequest request) {
        return fileHeadPortraitService.getPortrait(RedisUtil.getUserInfoId(request));
    }
}
