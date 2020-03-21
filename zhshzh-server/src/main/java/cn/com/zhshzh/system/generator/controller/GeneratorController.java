package cn.com.zhshzh.system.generator.controller;

import cn.com.zhshzh.core.constant.HttpResultEnum;
import cn.com.zhshzh.core.model.HttpResult;
import cn.com.zhshzh.core.model.SuggestionModel;
import cn.com.zhshzh.system.generator.dto.CodeGenerationInDTO;
import cn.com.zhshzh.system.generator.service.GeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 代码生成器controller
 *
 * @author WBT
 * @since 2019/12/15
 */
@RestController
@RequestMapping("/generator/admin")
public class GeneratorController {
    @Autowired
    private GeneratorService generatorService;

    /**
     * 代码生成器
     * spring boot版本: 2.1.9.RELEASE
     * mysql版本: 8.0
     * jdk版本: 1.8
     * mybatis版本: 3.5
     *
     * @param request           request
     * @param codeGenerationInDTO 生成代码的相关参数
     * @return JsonResultUtil 返回到前台的信息
     */
    @PostMapping
    public HttpResult<?> codeGeneration(@RequestBody CodeGenerationInDTO codeGenerationInDTO, HttpServletRequest request) {
        // 请求端的ip地址
        String remoteAddr = request.getRemoteAddr();
        // 服务端的ip地址
        String serverName = request.getServerName();
        // 为避免恶意在非已服务端生成代码，只允许在本地生成代码
        if ("0:0:0:0:0:0:0:1".equals(remoteAddr) || "127.0.0.1".equals(remoteAddr) || "localhost".equals(remoteAddr)
                || serverName.equals(remoteAddr)) {
            return generatorService.generator(codeGenerationInDTO);
        } else {
            return HttpResult.error(HttpResultEnum.GENERATOR_NOT_ALLOW);
        }
    }

    /**
     * 查询数据库中所有的表名
     *
     * @param database
     * @return
     */
    @GetMapping
    public HttpResult<List<SuggestionModel>> listAllTables(@RequestParam(value = "database", required = false) String database) {
        return generatorService.listAllTables(database);
    }
}
