package cn.com.zhshzh.system.generator.util;

import cn.com.zhshzh.core.util.DailyException;
import cn.com.zhshzh.core.util.FileUtil;
import cn.com.zhshzh.core.util.StringUtil;
import cn.com.zhshzh.system.generator.dto.CodeGenerationDTO;
import cn.com.zhshzh.system.generator.model.GeneratorMappingsModel;
import cn.com.zhshzh.system.generator.model.GeneratorStringModel;
import cn.com.zhshzh.system.generator.model.MappingModel;
import cn.com.zhshzh.system.generator.po.ColumnsPO;
import cn.com.zhshzh.system.generator.po.TablesPO;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.*;
import java.util.regex.Matcher;

/**
 * 代码生成器工具类
 *
 * @author WBT
 * @since 2019/12/16
 */
public class GeneratorUtil {
    // 项目路径
    private static final String PROJECT_PATH = "user.dir";
    // java文件路径
    private static final String JAVA_PATH = "/src/main/java/";
    // 配置文件路径
    private static final String RESOURCES_PATH = "/src/main/resources/";
    // 代码生成器配置文件
    private static final String GENERATOR_MAPPING_JSON = "generatorMapping.json";
    // 数据库名
    public static final String TABLE_SCHEMA = "daily";
    // 是否删除的标识
    private static final String IS_DELETED = "is_deleted";
    // 创建时间
    private static final String CREATE_TIME = "create_time";
    // 修改时间
    private static final String UPDATE_TIME = "update_time";
    // 创建人
    private static final String CREATE_BY = "create_by";
    // 修改人
    private static final String UPDATE_BY = "update_by";

    /**
     * 解析数据库表及列的信息
     * 遍历数据库表的所有列，并处理代码生成器字符串
     *
     * @param codeGenerationDTO 生成代码的相关参数
     * @param tablesPO          数据库表的信息
     * @param columnsPOList     数据库表中列名的信息
     * @return 拼接代码生成器字符串的模型对象
     * @throws DailyException 异常
     */
    public static GeneratorStringModel handleStringBuilders(CodeGenerationDTO codeGenerationDTO, TablesPO tablesPO,
                                                            List<ColumnsPO> columnsPOList) throws DailyException {
        GeneratorStringModel generatorStringModel = new GeneratorStringModel();
        // po文件的路径
        String poFilePath = codeGenerationDTO.getPoFilePath();
        // po文件的绝对路径
        String poFileAbsolutePath = GeneratorUtil.getJavaPath() + GeneratorUtil.changeFilePath(poFilePath, File.separator)
                + File.separator;
        generatorStringModel.setPoFileAbsolutePath(poFileAbsolutePath);
        generatorStringModel.setPoPackageName(GeneratorUtil.changeFilePath(poFilePath, "."));
        // dao文件的路径
        String daoFilePath = codeGenerationDTO.getDaoFilePath();
        // dao文件的绝对路径
        String daoFileAbsolutePath = GeneratorUtil.getJavaPath() + GeneratorUtil.changeFilePath(daoFilePath, File.separator)
                + File.separator;
        generatorStringModel.setDaoFileAbsolutePath(daoFileAbsolutePath);
        generatorStringModel.setDaoPackageName(GeneratorUtil.changeFilePath(daoFilePath, "."));
        // mapper文件的路径
        String mapperFilePath = codeGenerationDTO.getMapperFilePath();
        // mapper文件的绝对路径
        String MapperFileAbsolutePath = GeneratorUtil.getResourcesPath() + GeneratorUtil.changeFilePath(mapperFilePath, File.separator)
                + File.separator;
        generatorStringModel.setMapperFileAbsolutePath(MapperFileAbsolutePath);

        // 数据库名
        String tableSchema = codeGenerationDTO.getTableSchema();
        // 数据库表名
        String tableName = tablesPO.getTableName();
        if (StringUtils.isEmpty(tableSchema) || TABLE_SCHEMA.equals(tableSchema)) {
            generatorStringModel.setTableName(tableName);
        } else {
            generatorStringModel.setTableName(tableSchema + "." + tableName);
        }
        generatorStringModel.setLowerCamelCaseTableName(StringUtil.underlineToCamelCase(tableName, true));
        generatorStringModel.setUpperCamelCaseTableName(StringUtil.underlineToCamelCase(tableName, false));
        // 数据库表的注释
        String tableComment = tablesPO.getTableComment();
        if (StringUtils.isEmpty(tableComment)) {
            generatorStringModel.setTableComment("");
        } else {
            if (tableComment.endsWith("表")) {
                tableComment = tableComment.substring(0, tableComment.length() - 1);
            }
            generatorStringModel.setTableComment(tableComment);
        }

        // po对象中需要额外引的包
        Set<String> importPackages = new TreeSet<>();
        // 将代码生成器数据类型配置文件转为Map
        Map<String, MappingModel> map = GeneratorUtil.getMappingModel();
        // 遍历后的resultMap
        StringBuilder resultMapBuilder = new StringBuilder();
        // 遍历后的Base_Column_List
        StringBuilder baseColumnListBuilder = new StringBuilder();
        // 遍历后的insertColumnSql
        StringBuilder insertColumnSqlBuilder = new StringBuilder();
        // 遍历后的insertValueSql
        StringBuilder insertValueSqlBuilder = new StringBuilder();
        // 遍历后的insertBatchSql
        StringBuilder insertBatchSqlBuilder = new StringBuilder();
        // 遍历后的updateSql
        StringBuilder updateSqlBuilder = new StringBuilder();
        // 遍历后的updateBatchSql
        StringBuilder updateBatchSqlBuilder = new StringBuilder();
        // 遍历后的成员变量
        StringBuilder memberVariablesBuilder = new StringBuilder();

        // 生成Base_Column_List的同时格式化代码，保证每行不超过120个字符（包括空格）
        // rowLength为每行的字符数，初始值为4，代表前面有4个空格
        int rowLength = 4;
        int listSize = columnsPOList.size();
        for (int i = 0; i < listSize; i++) {
            // 数据库表的列对象
            ColumnsPO columnsPO = columnsPOList.get(i);
            // 数据库表字段名
            String columnName = columnsPO.getColumnName();
            // 数据库表字段注释
            String columnComment = columnsPO.getColumnComment();
            // 数据库表字段数据类型
            String dataType = columnsPO.getDataType();
            // java字段名称（下划线转驼峰）
            String camelCaseColumnName = StringUtil.underlineToCamelCase(columnName, true);
            // java字段的boolean类型一般对应数据库字段的bit类型，如果数据库字段是bit类型，并且以is开头，则将is删除，并将首字母小写
            // 比如数据库字段类型是bit，字段是is_deleted这种格式，直接转驼峰是isDeleted，参考阿里Java开发手册，将其改为deleted
            if ("bit".equals(dataType.toLowerCase()) && camelCaseColumnName.startsWith("is")) {
                camelCaseColumnName = camelCaseColumnName.substring(2, 3).toLowerCase() + camelCaseColumnName.substring(3);
            }
            // 数据库表字段键类型
            String columnKey = columnsPO.getColumnKey();
            // 数据库表字段数据类型与java数据类型的映射对象
            MappingModel mappingModel = map.get(dataType.toUpperCase());
            if (mappingModel == null) {
                throw new DailyException("未找到columnType为:" + dataType + "的映射配置，请在generatorMapping.json中进行配置");
            }
            // mapper.xml中的javaType
            String javaType = mappingModel.getJavaType();
            // mapper.xml中的jdbcType
            String jdbcType = mappingModel.getJdbcType();
            // java对象成员变量的数据类型
            String fieldType = mappingModel.getFieldType();
            // 是否是主键
            boolean isPrimaryKey = false;
            // 数据库表主键的处理
            if (!StringUtils.isEmpty(columnKey) && "PRI".equals(columnKey.toUpperCase())) {
                generatorStringModel.setPrimaryKey(columnName);
                generatorStringModel.setLowerCamelCasePrimaryKey(StringUtil.underlineToCamelCase(columnName, true));
                generatorStringModel.setPriJavaType(javaType);
                generatorStringModel.setPriJdbcType(jdbcType);
                generatorStringModel.setPriFieldType(fieldType);
                isPrimaryKey = true;
            }

            // 如果javaType不在java.lang包下，需要额外引包
            // 目前只是针对java.math.BigDecimal和java.util.Date
            if (!javaType.startsWith("java.lang") && !"_byte[]".equals(javaType)) {
                importPackages.add("import " + javaType + ";");
            }

            // 迭代拼接mapper.xml中的resultMap
            iterateResultMap(columnName, camelCaseColumnName, jdbcType, javaType, isPrimaryKey, resultMapBuilder);
            // 迭代拼接mapper.xml中的Base_Column_List
            rowLength = iterateBaseColumnList(i, listSize, rowLength, columnName, baseColumnListBuilder);
            // 迭代拼接mapper.xml中的insertSql
            iterateInsertSql(columnName, camelCaseColumnName, jdbcType, insertColumnSqlBuilder, insertValueSqlBuilder);
            // 迭代拼接mapper.xml中的insertBatchSql
            iterateInsertBatchSql(columnName, camelCaseColumnName, jdbcType, insertBatchSqlBuilder);
            // 迭代拼接mapper.xml中的updateSql
            iterateUpdateSql(columnName, camelCaseColumnName, jdbcType, updateSqlBuilder);
            // 迭代拼接mapper.xml中的updateBatchSql
            iterateUpdateBatchSql(columnName, camelCaseColumnName, jdbcType, updateBatchSqlBuilder);
            // 迭代拼接PO.java中的成员变量
            iterateMemberVariables(columnName, columnComment, camelCaseColumnName, fieldType, memberVariablesBuilder);
        }
        generatorStringModel.setImportPackages(importPackages);
        generatorStringModel.setResultMap(resultMapBuilder.toString());
        generatorStringModel.setBaseColumnList(baseColumnListBuilder.toString());
        generatorStringModel.setInsertColumnSql(insertColumnSqlBuilder.toString());
        generatorStringModel.setInsertValueSql(insertValueSqlBuilder.toString());
        generatorStringModel.setInsertBatchSql(insertBatchSqlBuilder.toString());
        generatorStringModel.setUpdateSql(updateSqlBuilder.toString());
        generatorStringModel.setUpdateBatchSql(updateBatchSqlBuilder.toString());
        generatorStringModel.setMemberVariables(memberVariablesBuilder.toString());
        return generatorStringModel;
    }

    /**
     * 将文件路径格式化为指定格式
     * 比如将路径"cn/com/zhshzh"转化为包名"cn.com.zhshzh"
     * 或者将包名"cn.com.zhshzh"转化为路径"cn/com/zhshzh"
     *
     * @param filePath 文件路径
     * @param symbol   分割符
     * @return 格式化后的文件路径
     */
    static String changeFilePath(String filePath, String symbol) {
        if (StringUtils.isEmpty(filePath)) {
            return filePath;
        }
        if (filePath.contains(".")) {
            filePath = filePath.replaceAll("\\.", Matcher.quoteReplacement(symbol));
        }
        if (filePath.contains("/")) {
            filePath = filePath.replaceAll("/", Matcher.quoteReplacement(symbol));
        }
        if (filePath.contains("\\")) {
            filePath = filePath.replaceAll("\\\\", Matcher.quoteReplacement(symbol));
        }
        if (filePath.startsWith(symbol) && filePath.length() > 1) {
            filePath = filePath.substring(1);
        }
        if (filePath.endsWith(symbol) && filePath.length() > 1) {
            filePath = filePath.substring(0, filePath.length() - 1);
        }
        return filePath;
    }

    /**
     * 获取本项目java文件目录的绝对路径
     *
     * @return java文件目录的绝对路径
     */
    private static String getJavaPath() {
        // 项目目录的绝对路径
        String projectPath = System.getProperty(PROJECT_PATH);
        return projectPath + JAVA_PATH;
    }

    /**
     * 获取本项目resources目录的绝对路径
     *
     * @return resources目录的绝对路径
     */
    private static String getResourcesPath() {
        // 项目目录的绝对路径
        String projectPath = System.getProperty(PROJECT_PATH);
        return projectPath + RESOURCES_PATH;
    }

    /**
     * 将代码生成器数据类型配置文件转为Map
     * Map的key为columnType，value为MappingModel对象
     *
     * @return map
     */
    private static Map<String, MappingModel> getMappingModel() {
        // 读取代码生成器的配置文件，并转换为GeneratorMappingModel对象
        GeneratorMappingsModel generatorMappingsModel = FileUtil.readLocalJsonFile(GENERATOR_MAPPING_JSON, GeneratorMappingsModel.class);
        // 断言generatorMappingsModel不为null
        assert generatorMappingsModel != null;
        List<MappingModel> mappingModelLists = generatorMappingsModel.getMappings();
        Map<String, MappingModel> map = new HashMap<>();
        // 将代码生成器数据类型配置文件转为Map
        mappingModelLists.forEach(mappingModel -> map.put(mappingModel.getColumnType(), mappingModel));
        return map;
    }

    /**
     * 迭代拼接mapper.xml中的resultMap
     *
     * @param columnName          数据库表字段名
     * @param camelCaseColumnName java字段名称
     * @param jdbcType            jdbcType
     * @param javaType            javaType
     * @param isPrimaryKey        是否是主键
     * @param resultMapBuilder    拼接的字符串
     */
    private static void iterateResultMap(String columnName, String camelCaseColumnName, String jdbcType,
                                         String javaType, boolean isPrimaryKey, StringBuilder resultMapBuilder) {
        StringBuilder tempBuilder = new StringBuilder();
        tempBuilder.append(columnName).append("\" jdbcType=\"")
                .append(jdbcType).append("\" property=\"").append(camelCaseColumnName)
                .append("\" javaType=\"").append(javaType).append("\" />").append("\r\n");
        if (isPrimaryKey) {
            resultMapBuilder.insert(0, tempBuilder).insert(0, "    <id column=\"");
        } else {
            resultMapBuilder.append("    <result column=\"").append(tempBuilder);
        }
    }

    /**
     * 迭代拼接mapper.xml中的Base_Column_List
     *
     * @param rowNumber             当前遍历的下标
     * @param listSize              数据库表字段的个数
     * @param rowLength             当前行的长度
     * @param columnName            数据库表字段名
     * @param baseColumnListBuilder 拼接的字符串
     * @return 当前行的长度
     */
    private static int iterateBaseColumnList(int rowNumber, int listSize, int rowLength, String columnName,
                                             StringBuilder baseColumnListBuilder) {
        // 该字段长度
        int columnLength = columnName.length();
        if (rowNumber < (listSize - 1)) {
            if ((rowLength + columnLength + 1) > 120) {
                // 如果当前行长度+该字段长度+1（逗号长度）>120则将前一个空格删除，并换行，然后将rowLength初始化为4
                baseColumnListBuilder.delete(baseColumnListBuilder.length() - 1, baseColumnListBuilder.length());
                baseColumnListBuilder.append("\r\n").append("    ").append(columnName).append(", ");
                rowLength = 4;
            } else {
                baseColumnListBuilder.append(columnName).append(", ");
                rowLength += columnLength + 2;
            }
        } else {
            // 最后一个字段的处理
            if ((rowLength + columnLength) > 120) {
                // 如果当前行长度+该字段长度>120则将前一个空格删除，并换行，然后将rowLength初始化为4
                baseColumnListBuilder.delete(baseColumnListBuilder.length() - 1, baseColumnListBuilder.length());
                baseColumnListBuilder.append("\r\n").append("    ").append(columnName).append("\r\n");
                rowLength = 4;
            } else {
                baseColumnListBuilder.append(columnName).append("\r\n");
            }
        }
        return rowLength;
    }

    /**
     * 迭代拼接mapper.xml中的insertSql
     *
     * @param columnName             数据库表字段名
     * @param camelCaseColumnName    java字段名称
     * @param jdbcType               jdbcType
     * @param insertColumnSqlBuilder 拼接的字符串
     * @param insertValueSqlBuilder  拼接的字符串
     */
    private static void iterateInsertSql(String columnName, String camelCaseColumnName, String jdbcType,
                                         StringBuilder insertColumnSqlBuilder, StringBuilder insertValueSqlBuilder) {
        insertColumnSqlBuilder.append("      <if test=\"").append(camelCaseColumnName).append(" != null\" > ").append(columnName)
                .append(", </if>").append("\r\n");
        insertValueSqlBuilder.append("      <if test=\"").append(camelCaseColumnName).append(" != null\" > #{")
                .append(camelCaseColumnName).append(", jdbcType=").append(jdbcType)
                .append("}, </if>").append("\r\n");
    }

    /**
     * 迭代拼接mapper.xml中的insertBatchSql
     *
     * @param columnName            数据库表字段名
     * @param camelCaseColumnName   java字段名称
     * @param jdbcType              jdbcType
     * @param insertBatchSqlBuilder 拼接的字符串
     */
    private static void iterateInsertBatchSql(String columnName, String camelCaseColumnName, String jdbcType,
                                              StringBuilder insertBatchSqlBuilder) {
        // 对是否删除、创建时间、更新时间特殊处理
        switch (columnName) {
            case IS_DELETED:
                insertBatchSqlBuilder.append("        0,").append("\r\n");
                break;
            case CREATE_TIME:
            case UPDATE_TIME:
                insertBatchSqlBuilder.append("        CURRENT_TIMESTAMP,").append("\r\n");
                break;
            default:
                insertBatchSqlBuilder.append("        #{item.").append(camelCaseColumnName).append(", jdbcType=")
                        .append(jdbcType).append("},").append("\r\n");
        }
    }

    /**
     * 迭代拼接mapper.xml中的updateSql
     *
     * @param columnName          数据库表字段名
     * @param camelCaseColumnName java字段名称
     * @param jdbcType            jdbcType
     * @param updateSqlBuilder    拼接的字符串
     */
    private static void iterateUpdateSql(String columnName, String camelCaseColumnName, String jdbcType,
                                         StringBuilder updateSqlBuilder) {
        // 更新数据时，不更新是否删除字段和创建时间
        if (IS_DELETED.equals(columnName) || CREATE_TIME.equals(columnName)
                || UPDATE_TIME.equals(columnName) || CREATE_BY.equals(columnName)) {
            return;
        }
        if (UPDATE_BY.equals(columnName)) {
            // 修改人必须更新
            updateSqlBuilder.append("      ").append(columnName)
                    .append(" = #{").append(camelCaseColumnName).append(", jdbcType=").append(jdbcType)
                    .append("}").append("\r\n");
        } else {
            updateSqlBuilder.append("      <if test=\"").append(camelCaseColumnName).append(" != null\" > ").append(columnName)
                    .append(" = #{").append(camelCaseColumnName).append(", jdbcType=").append(jdbcType)
                    .append("}, </if>").append("\r\n");
        }
    }

    /**
     * 迭代拼接mapper.xml中的updateBatchSql
     *
     * @param columnName            数据库表字段名
     * @param camelCaseColumnName   java字段名称
     * @param jdbcType              jdbcType
     * @param updateBatchSqlBuilder 拼接的字符串
     */
    private static void iterateUpdateBatchSql(String columnName, String camelCaseColumnName, String jdbcType,
                                              StringBuilder updateBatchSqlBuilder) {
        // 更新数据时，不更新是否删除字段和创建时间
        if (IS_DELETED.equals(columnName) || CREATE_TIME.equals(columnName)
                || UPDATE_TIME.equals(columnName) || CREATE_BY.equals(columnName)) {
            return;
        }
        if (UPDATE_BY.equals(columnName)) {
            // 修改人必须更新
            updateBatchSqlBuilder.append("        ").append(columnName)
                    .append(" = #{item.").append(camelCaseColumnName).append(", jdbcType=").append(jdbcType)
                    .append("}").append("\r\n");
        } else {
            updateBatchSqlBuilder.append("        <if test=\"item.").append(camelCaseColumnName)
                    .append(" != null\" > ").append(columnName).append(" = #{item.").append(camelCaseColumnName)
                    .append(", jdbcType=").append(jdbcType).append("}, </if>").append("\r\n");
        }
    }

    /**
     * 迭代拼接mapper.xml中的updateBatchSql
     *
     * @param columnName             数据库表字段名
     * @param columnComment          数据库表字段注释
     * @param camelCaseColumnName    java字段名称
     * @param fieldType              fieldType
     * @param memberVariablesBuilder 拼接的字符串
     */
    private static void iterateMemberVariables(String columnName, String columnComment, String camelCaseColumnName,
                                               String fieldType, StringBuilder memberVariablesBuilder) {
        memberVariablesBuilder.append("\r\n");
        memberVariablesBuilder.append("    /**").append("\r\n");
        memberVariablesBuilder.append("     * ").append(columnName).append("\r\n");
        memberVariablesBuilder.append("     * ").append(columnComment).append("\r\n");
        memberVariablesBuilder.append("     */").append("\r\n");
        memberVariablesBuilder.append("    private ").append(fieldType).append(" ").append(camelCaseColumnName)
                .append(";").append("\r\n");
    }
}
