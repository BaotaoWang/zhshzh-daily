package cn.com.zhshzh.system.generator.service;

import cn.com.zhshzh.core.model.HttpResult;
import cn.com.zhshzh.core.model.SuggestionModel;
import cn.com.zhshzh.system.generator.dto.CodeGenerationDTO;

import java.util.List;

/**
 * 代码生成器service
 *
 * @author WBT
 * @since 2019/12/15
 */
public interface GeneratorService {
    /**
     * 生成代码
     *
     * @param codeGenerationDTO 生成代码的相关参数
     * @return 代码生成结果
     */
    HttpResult<?> generator(CodeGenerationDTO codeGenerationDTO);

    /**
     * 查询数据库中所有的表名
     *
     * @param database
     * @return
     */
    HttpResult<List<SuggestionModel>> listAllTables(String database);
}
