package cn.com.zhshzh.system.generator.controller;

import cn.com.zhshzh.core.util.JsonResultUtil;
import cn.com.zhshzh.system.generator.dto.CodeGenerationDTO;
import cn.com.zhshzh.system.generator.service.GeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 代码生成器controller
 *
 * @author WBT
 * @since 2019/12/15
 */
@RestController
@RequestMapping("/generator")
public class GeneratorController {
    private GeneratorService generatorService;

    @Autowired
    private GeneratorController(GeneratorService generatorService) {
        this.generatorService = generatorService;
    }

    /**
     * 代码生成器
     * spring boot版本: 2.1.9.RELEASE
     * mysql版本: 8.0
     * jdk版本: 1.8
     * mybatis版本: 3.5
     *
     * @param codeGenerationDTO 生成代码的相关参数
     * @return JsonResultUtil 返回到前台的信息
     */
    @PostMapping("/codeGeneration")
    public JsonResultUtil codeGeneration(@RequestBody CodeGenerationDTO codeGenerationDTO) {
        return generatorService.generator(codeGenerationDTO);
    }
}
