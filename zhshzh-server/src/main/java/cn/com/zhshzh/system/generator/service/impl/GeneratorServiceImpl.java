package cn.com.zhshzh.system.generator.service.impl;

import cn.com.zhshzh.core.constant.HttpResultEnum;
import cn.com.zhshzh.core.model.HttpResult;
import cn.com.zhshzh.system.generator.dao.ColumnsMapper;
import cn.com.zhshzh.system.generator.dao.TablesMapper;
import cn.com.zhshzh.system.generator.dto.CodeGenerationDTO;
import cn.com.zhshzh.system.generator.model.GeneratorStringModel;
import cn.com.zhshzh.system.generator.po.ColumnsPO;
import cn.com.zhshzh.system.generator.po.TablesPO;
import cn.com.zhshzh.system.generator.service.GeneratorService;
import cn.com.zhshzh.system.generator.util.GenerateDaoFileUtil;
import cn.com.zhshzh.system.generator.util.GenerateMapperFileUtil;
import cn.com.zhshzh.system.generator.util.GeneratePoFileUtil;
import cn.com.zhshzh.system.generator.util.GeneratorUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

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
     * @param codeGenerationDTO 生成代码的相关参数
     * @return 生成代码后的结果
     */
    @Override
    public HttpResult<CodeGenerationDTO> generator(CodeGenerationDTO codeGenerationDTO) {
        // 数据库名
        String tableSchema = codeGenerationDTO.getTableSchema();
        if (StringUtils.isEmpty(tableSchema)) {
            tableSchema = GeneratorUtil.TABLE_SCHEMA;
        }
        // 要生成代码的表名
        String tableName = codeGenerationDTO.getTableName();
        // 数据库表的信息
        TablesPO tablesPO = tablesMapper.getTable(tableSchema, tableName);
        if (tablesPO == null) {
            return HttpResult.error(HttpResultEnum.TABLE_NOT_EXISTS);
        }
        // 数据库表中列名的信息
        List<ColumnsPO> columnsPOList = columnsMapper.listAllColumns(tableSchema, tableName);

        try {
            // 解析数据库表及列的信息
            GeneratorStringModel generatorStringModel = GeneratorUtil.handleStringBuilders(codeGenerationDTO, tablesPO, columnsPOList);
            // 生成PO.java文件
            GeneratePoFileUtil.generatePoFile(generatorStringModel);
            // 生成mapper.java文件
            GenerateDaoFileUtil.generateDaoFile(generatorStringModel);
            // 生成mapper.xml文件
            GenerateMapperFileUtil.generateMapperFile(generatorStringModel);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.error(HttpResultEnum.GENERATOR_ERROR);
        }
        return HttpResult.success();
    }
}
