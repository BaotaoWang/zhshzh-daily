package cn.com.zhshzh.system.generator.service.impl;

import cn.com.zhshzh.core.constant.HttpResultEnum;
import cn.com.zhshzh.core.util.FileUtil;
import cn.com.zhshzh.core.util.JsonResultUtil;
import cn.com.zhshzh.system.generator.dao.ColumnsMapper;
import cn.com.zhshzh.system.generator.dao.TablesMapper;
import cn.com.zhshzh.system.generator.dto.CodeGenerationDTO;
import cn.com.zhshzh.system.generator.model.GeneratorMappingsModel;
import cn.com.zhshzh.system.generator.model.MappingModel;
import cn.com.zhshzh.system.generator.po.ColumnsPO;
import cn.com.zhshzh.system.generator.po.TablesPO;
import cn.com.zhshzh.system.generator.service.GeneratorService;
import cn.com.zhshzh.system.generator.util.GenerateMapperFileUtil;
import cn.com.zhshzh.system.generator.util.GeneratorUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 代码生成器impl
 *
 * @author WBT
 * @since 2019/10/15
 */
@Service
public class GeneratorServiceImpl implements GeneratorService {
    private static final Logger logger = LogManager.getLogger(GeneratorServiceImpl.class);

    // 数据库名
    private static final String TABLE_SCHEMA = "daily";

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
    public JsonResultUtil generator(CodeGenerationDTO codeGenerationDTO) {
        // po文件的路径
        String poFilePath = codeGenerationDTO.getPoFilePath();
        // dao文件的路径
        String daoFilePath = codeGenerationDTO.getDaoFilePath();
        // mapper文件的路径
        String mapperFilePath = codeGenerationDTO.getMapperFilePath();
        // 数据库名
        String tableSchema = codeGenerationDTO.getTableSchema();
        // 要生成代码的表名
        String tableName = codeGenerationDTO.getTableName();
        // 表的信息
        TablesPO tablesPO = tablesMapper.getTable(tableSchema, tableName);
        // 表的备注
        String tableComment = tablesPO.getTableComment();
        // 列名的信息
        List<ColumnsPO> columnsPOList = columnsMapper.listAllColumns(tableSchema, tableName);
        Map<String, MappingModel> map = GeneratorUtil.getMappingModel();

        try {
            // 生成mapper.xml文件
            GenerateMapperFileUtil.generateMapperFile(poFilePath, daoFilePath, mapperFilePath, tableName,
                    tableComment, map, columnsPOList);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new JsonResultUtil(HttpResultEnum.GENERATOR_ERROR, e.getMessage());
        }
        return new JsonResultUtil();
    }
}
