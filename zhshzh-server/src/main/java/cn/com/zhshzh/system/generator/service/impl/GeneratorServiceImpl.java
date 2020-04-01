package cn.com.zhshzh.system.generator.service.impl;

import cn.com.zhshzh.core.constant.HttpResultEnum;
import cn.com.zhshzh.core.model.HttpResult;
import cn.com.zhshzh.core.model.SuggestionModel;
import cn.com.zhshzh.system.generator.dao.ColumnsMapper;
import cn.com.zhshzh.system.generator.dao.TablesMapper;
import cn.com.zhshzh.system.generator.dto.CodeGenerationInDTO;
import cn.com.zhshzh.system.generator.model.GeneratorStringModel;
import cn.com.zhshzh.system.generator.po.ColumnsPO;
import cn.com.zhshzh.system.generator.po.TablesPO;
import cn.com.zhshzh.system.generator.service.GeneratorService;
import cn.com.zhshzh.system.generator.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 代码生成器impl
 *
 * @author WBT
 * @since 2019/10/15
 */
@Service
public class GeneratorServiceImpl implements GeneratorService {
    private static final Logger logger = LogManager.getLogger(GeneratorServiceImpl.class);

    private TablesMapper tablesMapper;
    private ColumnsMapper columnsMapper;

    @Autowired
    private GeneratorServiceImpl(TablesMapper tablesMapper, ColumnsMapper columnsMapper) {
        this.tablesMapper = tablesMapper;
        this.columnsMapper = columnsMapper;
    }

    /**
     * 生成代码
     *
     * @param codeGenerationInDTO 生成代码的相关参数
     * @return 生成代码后的结果
     */
    @Override
    public HttpResult<?> generator(CodeGenerationInDTO codeGenerationInDTO) {
        // 数据库名
        String tableSchema = codeGenerationInDTO.getTableSchema();
        if (StringUtils.isEmpty(tableSchema)) {
            tableSchema = GeneratorUtil.TABLE_SCHEMA;
        }
        // 要生成代码的表名
        String tableName = codeGenerationInDTO.getTableName();
        // 数据库表的信息
        TablesPO tablesPO = tablesMapper.getTable(tableSchema, tableName);
        if (tablesPO == null) {
            return HttpResult.error(HttpResultEnum.TABLE_NOT_EXISTS);
        }
        // 数据库表中列名的信息
        List<ColumnsPO> columnsPOList = columnsMapper.listAllColumns(tableSchema, tableName);

        try {
            // 解析数据库表及列的信息
            GeneratorStringModel generatorStringModel = GeneratorUtil.handleStringBuilders(codeGenerationInDTO, tablesPO, columnsPOList);
            if (codeGenerationInDTO.getGeneratePoFile()) {
                // 生成PO.java文件
                GeneratePoFileUtil.generatePoFile(generatorStringModel);
            }
            if (codeGenerationInDTO.getGenerateDaoFile()) {
                // 生成mapper.java文件
                GenerateDaoFileUtil.generateDaoFile(generatorStringModel);
            }
            if (codeGenerationInDTO.getGenerateMapperFile()) {
                // 生成mapper.xml文件
                GenerateMapperFileUtil.generateMapperFile(generatorStringModel);
            }
            if (codeGenerationInDTO.getGenerateDtoFile()) {
                // 生成dto.java文件
                GenerateDtoFileUtil.generateDtoFile(generatorStringModel);
            }
            if (codeGenerationInDTO.getGenerateServiceFile()) {
                // 生成service.java文件
                GenerateServiceFileUtil.generateServiceFile(generatorStringModel);
            }
            if (codeGenerationInDTO.getGenerateControllerFile()) {
                // 生成controller.java文件
                GenerateControllerFileUtil.generateControllerFile(generatorStringModel);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.error(HttpResultEnum.GENERATOR_ERROR);
        }
        return HttpResult.success();
    }

    /**
     * 查询数据库中所有的表名
     *
     * @param database
     * @return
     */
    @Override
    public HttpResult<List<SuggestionModel>> listAllTables(String database) {
        // 查询数据库中所有表的集合
        List<TablesPO> tablesPOList = tablesMapper.listAllTables(StringUtils.isEmpty(database) ? GeneratorUtil.TABLE_SCHEMA : database);
        // 取出表名放入新的集合
        List<SuggestionModel> tableNameList = tablesPOList.stream().map(tablesPO -> {
            SuggestionModel suggestionModel = new SuggestionModel();
            suggestionModel.setValue(tablesPO.getTableName());
            return suggestionModel;
        }).collect(Collectors.toList());
        return HttpResult.success(tableNameList);
    }

}
