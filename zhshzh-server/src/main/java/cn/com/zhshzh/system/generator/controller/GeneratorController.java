package cn.com.zhshzh.system.generator.controller;

import cn.com.zhshzh.core.util.JsonResultUtil;
import cn.com.zhshzh.system.generator.dto.CodeGenerationDTO;
import cn.com.zhshzh.system.generator.service.GeneratorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "代码生成器服务", tags = {"代码生成器接口"})
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
     *
     * @param codeGenerationDTO 生成代码的相关参数
     * @return JsonResultUtil
     */
    @ApiOperation(value = "生成代码", notes = "注意问题点")
    @PostMapping("/codeGeneration")
    public JsonResultUtil codeGeneration(@RequestBody CodeGenerationDTO codeGenerationDTO) {
        return generatorService.generator(codeGenerationDTO);
    }
}
