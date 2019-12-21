package cn.com.zhshzh.system.generator.util;

import cn.com.zhshzh.core.util.DailyException;
import cn.com.zhshzh.core.util.FileUtil;
import cn.com.zhshzh.core.util.StringUtil;
import cn.com.zhshzh.system.generator.model.MappingModel;
import cn.com.zhshzh.system.generator.po.ColumnsPO;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 生成Mybatis的mapper.xml文件的工具类
 *
 * @author WBT
 * @since 2019/12/16
 */
public class GenerateMapperFileUtil {

    /**
     * 生成Mybatis的mapper.xml文件
     *
     * @param poFilePath     po文件的路径
     * @param daoFilePath    dao文件的路径
     * @param mapperFilePath mapper文件的路径
     * @param tableName      数据库表的名称
     * @param tableComment   数据库表的备注
     * @param map            代码生成器配置的map
     * @param columnsPOList  数据库表的所有列
     * @throws DailyException 异常
     */
    public static void generateMapperFile(String poFilePath, String daoFilePath, String mapperFilePath, String tableName,
                                          String tableComment, Map<String, MappingModel> map,
                                          List<ColumnsPO> columnsPOList) throws DailyException {
        poFilePath = GeneratorUtil.changeFilePath(poFilePath, ".");
        daoFilePath = GeneratorUtil.changeFilePath(daoFilePath, ".");
        mapperFilePath = GeneratorUtil.changeFilePath(mapperFilePath, File.separator) + File.separator;
        // mapper文件的绝对路径
        String filePath = GeneratorUtil.getResourcesPath() + mapperFilePath;
        // mapper文件名
        String fileName = StringUtil.underlineToCamelCase(tableName, false) + "Mapper.xml";

        // 拼接mapper.xml文件
        StringBuilder builder = new StringBuilder();
        // 生成mapper.xml的头部
        GenerateMapperFileUtil.generateHeader(daoFilePath, tableName, builder);
        // 生成mapper.xml的ResultMap
        GenerateMapperFileUtil.generateResultMap(poFilePath, tableName, map, columnsPOList, builder);
        // 生成mapper.xml的Base_Column_List
        GenerateMapperFileUtil.generateBaseColumnList(columnsPOList, builder);
        // 生成mapper.xml的插入SQL
        GenerateMapperFileUtil.generateInsertSql(poFilePath, tableName, tableComment, map, columnsPOList, builder);
        // 生成mapper.xml的批量SQL
        GenerateMapperFileUtil.generateInsertBatchSql(tableName, tableComment, map, columnsPOList, builder);
        // 生成mapper.xml的根据id逻辑删除SQL
        GenerateMapperFileUtil.generateDeleteLogicalSql(poFilePath, tableName, tableComment, map, columnsPOList, builder);
        // 生成mapper.xml的根据批量逻辑删除SQL
        GenerateMapperFileUtil.generateDeleteBatchLogicalSql(tableName, tableComment, map, columnsPOList, builder);
        // 生成mapper.xml的根据id物理删除SQL
        GenerateMapperFileUtil.generateDeletePhysicalSql(tableName, tableComment, map, columnsPOList, builder);
        // 生成mapper.xml的根据批量物理删除SQL
        GenerateMapperFileUtil.generateDeleteBatchPhysicalSql(tableName, tableComment, map, columnsPOList, builder);
        // 生成mapper.xml的修改SQL
        GenerateMapperFileUtil.generateUpdateSql(poFilePath, tableName, tableComment, map, columnsPOList, builder);
        // 生成mapper.xml的批量修改SQL
        GenerateMapperFileUtil.generateUpdateBatchSql(tableName, tableComment, map, columnsPOList, builder);
        // 根据id查
        // 条件查询
        // 生成mapper.xml的查询总条数SQL
        GenerateMapperFileUtil.generateSelectCountSql(tableName, tableComment, builder);
        // 生成mapper.xml的尾部
        GenerateMapperFileUtil.generateFooter(builder);

        // 将字符串文本写到本地文件
        FileUtil.convertTextToLocalFile(filePath, fileName, builder.toString());
    }

    /**
     * 生成mapper.xml的头部
     *
     * @param daoFilePath dao文件的路径
     * @param tableName   数据库表名
     * @param builder     拼接的mapper.xml文件文本
     */
    private static void generateHeader(String daoFilePath, String tableName, StringBuilder builder) {
        // dao文件名
        String daoFileName = StringUtil.underlineToCamelCase(tableName, false) + "Mapper";
        // 映射dao文件的namespace
        String namespace = GeneratorUtil.changeFilePath(daoFilePath, ".") + "." + daoFileName;
        // 拼接mapper.xml的头部
        builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>").append("\r\n");
        builder.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >").append("\r\n");
        builder.append("<mapper namespace=\"").append(namespace).append("\" >").append("\r\n");
    }

    /**
     * 生成mapper.xml的ResultMap
     *
     * @param poFilePath    po文件的路径
     * @param tableName     数据库表名
     * @param map           代码生成器配置的map
     * @param columnsPOList 数据库表的所有列
     * @param builder       拼接的mapper.xml文件文本
     */
    private static void generateResultMap(String poFilePath, String tableName, Map<String, MappingModel> map,
                                          List<ColumnsPO> columnsPOList, StringBuilder builder) {
        // po文件名
        String poFileName = StringUtil.underlineToCamelCase(tableName, false) + "PO";
        // po文件路径
        String type = GeneratorUtil.changeFilePath(poFilePath, ".") + "." + poFileName;
        builder.append("  <resultMap id=\"BaseResultMap\" type=\"").append(type).append("\" >").append("\r\n");
        StringBuilder resultMapBuilder = new StringBuilder();
        // 遍历数据库的每一个字段
        columnsPOList.forEach(columnsPO -> {
            // 数据库字段名称
            String columnName = columnsPO.getColumnName();
            // 字段类型
            String dataType = columnsPO.getDataType();
            // java字段名称（下划线转驼峰）
            String property = StringUtil.underlineToCamelCase(columnName, true);
            // java字段的boolean类型一般对应数据库字段的bit类型，如果数据库字段是bit类型，并且以is开头，则将is删除，并将首字母小写
            // 比如数据库字段类型是bit，字段是is_delete这种格式，直接转驼峰是isDelete，参考阿里Java开发手册，将其改为delete
            if ("bit".equals(dataType.toLowerCase()) && property.startsWith("is")) {
                property = property.substring(2, 3).toLowerCase() + property.substring(3);
            }
            MappingModel mappingModel = map.get(dataType.toUpperCase());
            // 拼接每一个<result/>
            if ("PRI".equals(columnsPO.getColumnKey())) {
                // 主键ID的处理
                StringBuilder priBuilder = new StringBuilder();
                priBuilder.append("    <id column=\"").append(columnName).append("\" jdbcType=\"")
                        .append(mappingModel.getJdbcType()).append("\" property=\"").append(property)
                        .append("\" javaType=\"").append(mappingModel.getJavaType()).append("\" />").append("\r\n");
                resultMapBuilder.insert(0, priBuilder);
            } else {
                // 非主键字段的处理
                resultMapBuilder.append("    <result column=\"").append(columnName).append("\" jdbcType=\"")
                        .append(mappingModel.getJdbcType()).append("\" property=\"").append(property)
                        .append("\" javaType=\"").append(mappingModel.getJavaType()).append("\" />").append("\r\n");
            }
        });
        builder.append(resultMapBuilder);
        builder.append("  </resultMap>").append("\r\n");
    }

    /**
     * 生成mapper.xml的Base_Column_List
     *
     * @param columnsPOList 数据库表的所有列
     * @param builder       拼接的mapper.xml文件文本
     */
    private static void generateBaseColumnList(List<ColumnsPO> columnsPOList, StringBuilder builder) {
        builder.append("\r\n");
        builder.append("  <sql id=\"Base_Column_List\" >").append("\r\n");
        builder.append("    ");
        // 生成Base_Column_List的同时格式化代码，保证每行不超过120个字符（包括空格）
        // rowLength为每行的字符数，初始值为4，代表前面有4个空格
        int rowLength = 4;
        int listSize = columnsPOList.size();
        // 遍历所有的字段
        for (int i = 0; i < listSize; i++) {
            ColumnsPO columnsPO = columnsPOList.get(i);
            // 数据库字段名
            String columnName = columnsPO.getColumnName();
            // 该字段长度
            int columnLength = columnName.length();
            if (i < (listSize - 1)) {
                if ((rowLength + columnLength + 1) > 120) {
                    // 如果当前行长度+该字段长度+1（逗号长度）>120则将前一个空格删除，并换行，然后将rowLength初始化为4
                    builder.delete(builder.length() - 1, builder.length());
                    builder.append("\r\n").append("    ").append(columnName).append(", ");
                    rowLength = 4;
                } else {
                    builder.append(columnName).append(", ");
                    rowLength += columnLength + 2;
                }
            } else {
                // 最后一个字段的处理
                if ((rowLength + columnLength) > 120) {
                    // 如果当前行长度+该字段长度>120则将前一个空格删除，并换行，然后将rowLength初始化为4
                    builder.delete(builder.length() - 1, builder.length());
                    builder.append("\r\n").append("    ").append(columnName).append("\r\n");
                    rowLength = 4;
                } else {
                    builder.append(columnName).append("\r\n");
                }
            }
        }
        builder.append("  </sql>").append("\r\n");
    }

    /**
     * 生成mapper.xml的插入SQL
     *
     * @param poFilePath    po文件的路径
     * @param tableName     数据库表名
     * @param tableComment  数据库表的备注
     * @param map           代码生成器配置的map
     * @param columnsPOList 数据库表的所有列
     * @param builder       拼接的mapper.xml文件文本
     */
    private static void generateInsertSql(String poFilePath, String tableName, String tableComment, Map<String, MappingModel> map,
                                          List<ColumnsPO> columnsPOList, StringBuilder builder) {
        // 将数据库表名转为首字母大写的驼峰格式
        String camelCaseTableName = StringUtil.underlineToCamelCase(tableName, false);
        // 将前台传的po文件路径转为正确格式的包名
        String poFilePackage = GeneratorUtil.changeFilePath(poFilePath, ".");
        builder.append("\r\n");
        // 生成sql的注释
        if (!StringUtils.isEmpty(tableComment) && tableComment.endsWith("表")) {
            builder.append("  <!-- 新增").append(tableComment, 0, tableComment.length() - 1).append(" -->").append("\r\n");
        }
        // 拼接insertSQL
        builder.append("  <insert id=\"insert").append(camelCaseTableName).append("\" parameterType=\"").append(poFilePackage)
                .append(".").append(camelCaseTableName).append("PO\" >").append("\r\n");
        builder.append("    INSERT INTO ").append(tableName).append("\r\n");
        builder.append("    <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\" >").append("\r\n");
        StringBuilder tempBuilder = new StringBuilder();
        // 主键
        String primaryKey = "";
        MappingModel priMappingModel = new MappingModel();
        for (ColumnsPO columnsPO : columnsPOList) {
            // 数据库字段名
            String columnName = columnsPO.getColumnName();
            // 字段名转为首字母小写的驼峰格式
            String camelCaseColumnName = StringUtil.underlineToCamelCase(columnName, true);
            // 字段类型
            String dataType = columnsPO.getDataType();
            MappingModel mappingModel = map.get(dataType.toUpperCase());
            builder.append("      <if test=\"").append(camelCaseColumnName).append(" != null\" > ").append(columnName)
                    .append(", </if>").append("\r\n");
            tempBuilder.append("      <if test=\"").append(camelCaseColumnName).append(" != null\" > #{")
                    .append(camelCaseColumnName).append(", jdbcType=").append(mappingModel.getJdbcType())
                    .append("}, </if>").append("\r\n");
            // 主键的处理
            if ("PRI".equals(columnsPO.getColumnKey())) {
                priMappingModel = mappingModel;
                primaryKey = camelCaseColumnName;
            }
        }
        builder.append("    </trim>").append("\r\n");
        builder.append("    <trim prefix=\"VALUES (\" suffix=\")\" suffixOverrides=\",\" >").append("\r\n");
        builder.append(tempBuilder);
        builder.append("    </trim>").append("\r\n");
        // 返回新增数据的主键
        builder.append("    <selectKey resultType=\"").append(priMappingModel.getJavaType())
                .append("\" order=\"AFTER\" keyProperty=\"").append(primaryKey).append("\" >").append("\r\n");
        builder.append("      SELECT LAST_INSERT_ID()").append("\r\n");
        builder.append("    </selectKey>").append("\r\n");
        builder.append("  </insert>").append("\r\n");
    }

    /**
     * 生成mapper.xml的批量插入SQL
     *
     * @param tableName     数据库表名
     * @param tableComment  数据库表的备注
     * @param map           代码生成器配置的map
     * @param columnsPOList 数据库表的所有列
     * @param builder       拼接的mapper.xml文件文本
     */
    private static void generateInsertBatchSql(String tableName, String tableComment, Map<String, MappingModel> map,
                                               List<ColumnsPO> columnsPOList, StringBuilder builder) {
        // 将数据库表名转为首字母小写的驼峰格式
        String lowerCaseTableName = StringUtil.underlineToCamelCase(tableName, true);
        // 将数据库表名转为首字母大写的驼峰格式
        String upperCaseTableName = StringUtil.underlineToCamelCase(tableName, false);
        builder.append("\r\n");
        // 生成sql的注释
        if (!StringUtils.isEmpty(tableComment) && tableComment.endsWith("表")) {
            builder.append("  <!-- 批量新增").append(tableComment, 0, tableComment.length() - 1).append(" -->").append("\r\n");
        }
        // 拼接insertBatchSQL
        builder.append("  <insert id=\"insert").append(upperCaseTableName)
                .append("Batch\" parameterType=\"java.util.List\" >").append("\r\n");
        builder.append("    INSERT INTO ").append(tableName).append(" (").append("\r\n");
        builder.append("      <include refid=\"Base_Column_List\" />").append("\r\n");
        builder.append("    )").append("\r\n");
        builder.append("    VALUES").append("\r\n");
        builder.append("    <foreach collection=\"").append(lowerCaseTableName)
                .append("List\" item=\"item\" index=\"index\" separator=\",\" >").append("\r\n");
        builder.append("      <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\" >").append("\r\n");
        for (ColumnsPO columnsPO : columnsPOList) {
            // 数据库字段名
            String columnName = columnsPO.getColumnName();
            // 字段名转为首字母小写的驼峰格式
            String camelCaseColumnName = StringUtil.underlineToCamelCase(columnName, true);
            // 字段类型
            String dataType = columnsPO.getDataType();
            MappingModel mappingModel = map.get(dataType.toUpperCase());
            // 对是否删除、创建时间、更新时间特殊处理
            switch (columnName) {
                case "is_delete":
                    builder.append("        0,").append("\r\n");
                    break;
                case "create_time":
                case "update_time":
                    builder.append("        CURRENT_TIMESTAMP,").append("\r\n");
                    break;
                default:
                    builder.append("        #{item.").append(camelCaseColumnName).append(", jdbcType=").append(mappingModel.getJdbcType())
                            .append("},").append("\r\n");
            }
        }
        builder.append("      </trim>").append("\r\n");
        builder.append("    </foreach>").append("\r\n");
        builder.append("  </insert>").append("\r\n");
    }

    /**
     * 生成mapper.xml的根据id逻辑删除SQL
     *
     * @param poFilePath    po文件的路径
     * @param tableName     数据库表名
     * @param tableComment  数据库表的备注
     * @param map           代码生成器配置的map
     * @param columnsPOList 数据库表的所有列
     * @param builder       拼接的mapper.xml文件文本
     */
    private static void generateDeleteLogicalSql(String poFilePath, String tableName, String tableComment, Map<String, MappingModel> map,
                                                 List<ColumnsPO> columnsPOList, StringBuilder builder) {
        // 将数据库表名转为首字母大写的驼峰格式
        String camelCaseTableName = StringUtil.underlineToCamelCase(tableName, false);
        // 将前台传的po文件路径转为正确格式的包名
        String poFilePackage = GeneratorUtil.changeFilePath(poFilePath, ".");
        // 主键类型
        String priJdbcType = "";
        String primaryKey = "";
        for (ColumnsPO columnsPO : columnsPOList) {
            // 主键的处理
            if ("PRI".equals(columnsPO.getColumnKey())) {
                MappingModel mappingModel = map.get(columnsPO.getDataType().toUpperCase());
                priJdbcType = mappingModel.getJdbcType();
                primaryKey = columnsPO.getColumnName();
            }
        }
        // 将主键转为首字母小写的驼峰格式
        String camelCasePrimaryKey = StringUtil.underlineToCamelCase(primaryKey, true);
        builder.append("\r\n");
        // 生成sql的注释
        if (!StringUtils.isEmpty(tableComment) && tableComment.endsWith("表")) {
            builder.append("  <!-- 根据id逻辑删除").append(tableComment, 0, tableComment.length() - 1).append(" -->").append("\r\n");
        }
        // 拼接update语句
        builder.append("  <update id=\"deleteByIdLogical\" parameterType=\"").append(poFilePackage)
                .append(".").append(camelCaseTableName).append("PO\" >").append("\r\n");
        builder.append("    UPDATE ").append(tableName).append("\r\n");
        builder.append("    SET is_delete = 1,").append("\r\n");
        builder.append("    update_by = #{updateBy, jdbcType=BIGINT}").append("\r\n");
        builder.append("    WHERE ").append(primaryKey).append(" = #{").append(camelCasePrimaryKey).append(", jdbcType=")
                .append(priJdbcType).append("}").append("\r\n");
        builder.append("  </update>").append("\r\n");
    }

    /**
     * 生成mapper.xml的根据批量逻辑删除SQL
     *
     * @param tableName     数据库表名
     * @param tableComment  数据库表的备注
     * @param map           代码生成器配置的map
     * @param columnsPOList 数据库表的所有列
     * @param builder       拼接的mapper.xml文件文本
     */
    private static void generateDeleteBatchLogicalSql(String tableName, String tableComment, Map<String, MappingModel> map,
                                                      List<ColumnsPO> columnsPOList, StringBuilder builder) {
        // 主键类型
        String priJdbcType = "";
        String primaryKey = "";
        for (ColumnsPO columnsPO : columnsPOList) {
            // 主键的处理
            if ("PRI".equals(columnsPO.getColumnKey())) {
                MappingModel mappingModel = map.get(columnsPO.getDataType().toUpperCase());
                priJdbcType = mappingModel.getJdbcType();
                primaryKey = columnsPO.getColumnName();
            }
        }
        // 将主键转为首字母小写的驼峰格式
        String camelCasePrimaryKey = StringUtil.underlineToCamelCase(primaryKey, true);
        builder.append("\r\n");
        // 生成sql的注释
        if (!StringUtils.isEmpty(tableComment) && tableComment.endsWith("表")) {
            builder.append("  <!-- 批量逻辑删除").append(tableComment, 0, tableComment.length() - 1).append(" -->").append("\r\n");
        }
        // 拼接update语句
        builder.append("  <update id=\"deleteBatchLogical\" parameterType=\"java.util.Map\" >").append("\r\n");
        builder.append("    UPDATE ").append(tableName).append("\r\n");
        builder.append("    SET is_delete = 1,").append("\r\n");
        builder.append("    update_by = #{updateBy, jdbcType=BIGINT}").append("\r\n");
        builder.append("    WHERE ").append(primaryKey).append(" IN").append("\r\n");
        builder.append("    <foreach collection=\"").append(camelCasePrimaryKey)
                .append("s\" item=\"item\" index=\"index\" open=\"(\" close=\")\" separator=\",\" >").append("\r\n");
        builder.append("      #{item, jdbcType=").append(priJdbcType).append("}").append("\r\n");
        builder.append("    </foreach>").append("\r\n");
        builder.append("  </update>").append("\r\n");
    }

    /**
     * 生成mapper.xml的根据id物理删除SQL
     *
     * @param tableName     数据库表名
     * @param tableComment  数据库表的备注
     * @param map           代码生成器配置的map
     * @param columnsPOList 数据库表的所有列
     * @param builder       拼接的mapper.xml文件文本
     */
    private static void generateDeletePhysicalSql(String tableName, String tableComment, Map<String, MappingModel> map,
                                                  List<ColumnsPO> columnsPOList, StringBuilder builder) {
        // 主键类型
        String priJdbcType = "";
        String priJavaType = "";
        String primaryKey = "";
        for (ColumnsPO columnsPO : columnsPOList) {
            // 主键的处理
            if ("PRI".equals(columnsPO.getColumnKey())) {
                MappingModel mappingModel = map.get(columnsPO.getDataType().toUpperCase());
                priJdbcType = mappingModel.getJdbcType();
                priJavaType = mappingModel.getJavaType();
                primaryKey = columnsPO.getColumnName();
            }
        }
        // 将主键转为首字母小写的驼峰格式
        String camelCasePrimaryKey = StringUtil.underlineToCamelCase(primaryKey, true);
        builder.append("\r\n");
        // 生成sql的注释
        if (!StringUtils.isEmpty(tableComment) && tableComment.endsWith("表")) {
            builder.append("  <!-- 根据id物理删除").append(tableComment, 0, tableComment.length() - 1).append(" -->").append("\r\n");
        }
        // 拼接delete语句
        builder.append("  <delete id=\"deleteByIdPhysical\" parameterType=\"").append(priJavaType).append("\" >").append("\r\n");
        builder.append("    DELETE FROM ").append(tableName).append("\r\n");
        builder.append("    WHERE ").append(primaryKey).append(" = #{").append(camelCasePrimaryKey).append(", jdbcType=")
                .append(priJdbcType).append("}").append("\r\n");
        builder.append("  </delete>").append("\r\n");
    }

    /**
     * 生成mapper.xml的批量物理删除SQL
     *
     * @param tableName     数据库表名
     * @param tableComment  数据库表的备注
     * @param map           代码生成器配置的map
     * @param columnsPOList 数据库表的所有列
     * @param builder       拼接的mapper.xml文件文本
     */
    private static void generateDeleteBatchPhysicalSql(String tableName, String tableComment, Map<String, MappingModel> map,
                                                       List<ColumnsPO> columnsPOList, StringBuilder builder) {
        // 主键类型
        String priJdbcType = "";
        String primaryKey = "";
        String camelCasePrimaryKey = "";
        for (ColumnsPO columnsPO : columnsPOList) {
            // 主键的处理
            if ("PRI".equals(columnsPO.getColumnKey())) {
                MappingModel mappingModel = map.get(columnsPO.getDataType().toUpperCase());
                priJdbcType = mappingModel.getJdbcType();
                primaryKey = columnsPO.getColumnName();
                camelCasePrimaryKey = StringUtil.underlineToCamelCase(primaryKey, true);
            }
        }
        builder.append("\r\n");
        // 生成sql的注释
        if (!StringUtils.isEmpty(tableComment) && tableComment.endsWith("表")) {
            builder.append("  <!-- 批量物理删除").append(tableComment, 0, tableComment.length() - 1).append(" -->").append("\r\n");
        }
        // 拼接delete语句
        builder.append("  <delete id=\"deleteBatchPhysical\" >").append("\r\n");
        builder.append("    DELETE FROM ").append(tableName).append("\r\n");
        builder.append("    WHERE ").append(primaryKey).append(" IN").append("\r\n");
        builder.append("    <foreach collection=\"").append(camelCasePrimaryKey)
                .append("s\" item=\"item\" index=\"index\" open=\"(\" close=\")\" separator=\",\" >").append("\r\n");
        builder.append("      #{item, jdbcType=").append(priJdbcType).append("}").append("\r\n");
        builder.append("    </foreach>").append("\r\n");
        builder.append("  </delete>").append("\r\n");
    }

    /**
     * 生成mapper.xml的修改SQL
     *
     * @param poFilePath    po文件的路径
     * @param tableName     数据库表名
     * @param tableComment  数据库表的备注
     * @param map           代码生成器配置的map
     * @param columnsPOList 数据库表的所有列
     * @param builder       拼接的mapper.xml文件文本
     */
    private static void generateUpdateSql(String poFilePath, String tableName, String tableComment, Map<String, MappingModel> map,
                                          List<ColumnsPO> columnsPOList, StringBuilder builder) {
        // 将数据库表名转为首字母大写的驼峰格式
        String camelCaseTableName = StringUtil.underlineToCamelCase(tableName, false);
        // 将前台传的po文件路径转为正确格式的包名
        String poFilePackage = GeneratorUtil.changeFilePath(poFilePath, ".");
        builder.append("\r\n");
        // 生成sql的注释
        if (!StringUtils.isEmpty(tableComment) && tableComment.endsWith("表")) {
            builder.append("  <!-- 修改").append(tableComment, 0, tableComment.length() - 1).append(" -->").append("\r\n");
        }
        // 拼接updateSQL
        builder.append("  <update id=\"update").append(camelCaseTableName).append("\" parameterType=\"").append(poFilePackage)
                .append(".").append(camelCaseTableName).append("PO\" >").append("\r\n");
        builder.append("    UPDATE ").append(tableName).append("\r\n");
        builder.append("    <set>").append("\r\n");
        // 主键
        String primaryKey = "";
        String priJdbcType = "";
        String camelCasePrimaryKey = "";
        for (ColumnsPO columnsPO : columnsPOList) {
            // 数据库字段名
            String columnName = columnsPO.getColumnName();
            // 更新数据时，不更新是否删除字段和创建时间
            if ("is_delete".equals(columnName) || "create_time".equals(columnName)
                    || "update_time".equals(columnName) || "create_by".equals(columnName)) {
                continue;
            }
            // 字段名转为首字母小写的驼峰格式
            String camelCaseColumnName = StringUtil.underlineToCamelCase(columnName, true);
            // 字段类型
            String dataType = columnsPO.getDataType();
            MappingModel mappingModel = map.get(dataType.toUpperCase());
            if ("update_by".equals(columnName)) {
                // 修改人必须更新
                builder.append("      ").append(columnName)
                        .append(" = #{").append(camelCaseColumnName).append(", jdbcType=").append(mappingModel.getJdbcType())
                        .append("}").append("\r\n");
            } else {
                builder.append("      <if test=\"").append(camelCaseColumnName).append(" != null\" > ").append(columnName)
                        .append(" = #{").append(camelCaseColumnName).append(", jdbcType=").append(mappingModel.getJdbcType())
                        .append("}, </if>").append("\r\n");
            }
            // 主键的处理
            if ("PRI".equals(columnsPO.getColumnKey())) {
                primaryKey = columnName;
                priJdbcType = mappingModel.getJdbcType();
                camelCasePrimaryKey = camelCaseColumnName;
            }
        }
        builder.append("    </set>").append("\r\n");
        builder.append("    WHERE ").append(primaryKey).append(" = #{").append(camelCasePrimaryKey).append(", jdbcType=")
                .append(priJdbcType).append("}").append("\r\n");
        builder.append("  </update>").append("\r\n");
    }

    /**
     * 生成mapper.xml的批量修改SQL
     *
     * @param tableName     数据库表名
     * @param tableComment  数据库表的备注
     * @param map           代码生成器配置的map
     * @param columnsPOList 数据库表的所有列
     * @param builder       拼接的mapper.xml文件文本
     */
    private static void generateUpdateBatchSql(String tableName, String tableComment, Map<String, MappingModel> map,
                                               List<ColumnsPO> columnsPOList, StringBuilder builder) {
        // 将数据库表名转为首字母小写的驼峰格式
        String lowerCaseTableName = StringUtil.underlineToCamelCase(tableName, true);
        // 将数据库表名转为首字母大写的驼峰格式
        String upperCaseTableName = StringUtil.underlineToCamelCase(tableName, false);
        builder.append("\r\n");
        // 生成sql的注释
        if (!StringUtils.isEmpty(tableComment) && tableComment.endsWith("表")) {
            builder.append("  <!-- 批量修改").append(tableComment, 0, tableComment.length() - 1).append(" -->").append("\r\n");
        }
        // 拼接updateBatchSQL
        builder.append("  <update id=\"update").append(upperCaseTableName)
                .append("Batch\" parameterType=\"java.util.List\" >").append("\r\n");
        builder.append("    <foreach collection=\"").append(lowerCaseTableName)
                .append("List\" item=\"item\" index=\"index\" separator=\";\" >").append("\r\n");
        builder.append("      UPDATE ").append(tableName).append("\r\n");
        builder.append("      <set>").append("\r\n");
        // 主键
        String primaryKey = "";
        String priJdbcType = "";
        String camelCasePrimaryKey = "";
        for (ColumnsPO columnsPO : columnsPOList) {
            // 数据库字段名
            String columnName = columnsPO.getColumnName();
            // 更新数据时，不更新是否删除字段和创建时间
            if ("is_delete".equals(columnName) || "create_time".equals(columnName)
                    || "update_time".equals(columnName) || "create_by".equals(columnName)) {
                continue;
            }
            // 字段名转为首字母小写的驼峰格式
            String camelCaseColumnName = StringUtil.underlineToCamelCase(columnName, true);
            // 字段类型
            String dataType = columnsPO.getDataType();
            MappingModel mappingModel = map.get(dataType.toUpperCase());
            if ("update_by".equals(columnName)) {
                // 修改人必须更新
                builder.append("        ").append(columnName)
                        .append(" = #{item.").append(camelCaseColumnName).append(", jdbcType=").append(mappingModel.getJdbcType())
                        .append("}").append("\r\n");
            } else {
                builder.append("        <if test=\"item.").append(camelCaseColumnName).append(" != null\" > ").append(columnName)
                        .append(" = #{item.").append(camelCaseColumnName).append(", jdbcType=").append(mappingModel.getJdbcType())
                        .append("}, </if>").append("\r\n");
            }
            // 主键的处理
            if ("PRI".equals(columnsPO.getColumnKey())) {
                primaryKey = columnName;
                priJdbcType = mappingModel.getJdbcType();
                camelCasePrimaryKey = camelCaseColumnName;
            }
        }
        builder.append("      </set>").append("\r\n");
        builder.append("      WHERE ").append(primaryKey).append(" = #{").append(camelCasePrimaryKey).append(", jdbcType=")
                .append(priJdbcType).append("}").append("\r\n");
        builder.append("    </foreach>").append("\r\n");
        builder.append("  </update>").append("\r\n");
    }

    /**
     * 生成mapper.xml的查询总条数SQL
     *
     * @param tableName    数据库表名
     * @param tableComment 数据库表的备注
     * @param builder      拼接的mapper.xml文件文本
     */
    private static void generateSelectCountSql(String tableName, String tableComment, StringBuilder builder) {
        // 将数据库表名转为首字母大写的驼峰格式
        String upperCaseTableName = StringUtil.underlineToCamelCase(tableName, false);
        builder.append("\r\n");
        // 生成sql的注释
        builder.append("  <!-- 查询").append(tableComment).append("总条数 -->").append("\r\n");
        // 拼接selectCountSQL
        builder.append("  <select id=\"count").append(upperCaseTableName)
                .append("s\" resultType=\"java.long.Integer\" >").append("\r\n");
        builder.append("    SELECT COUNT(*) FROM ").append(tableName).append("\r\n");
        builder.append("    WHERE is_delete = 0").append("\r\n");
        builder.append("  </select>").append("\r\n");
    }

    /**
     * 生成mapper.xml的尾部
     *
     * @param builder 拼接的mapper.xml文件文本
     */
    private static void generateFooter(StringBuilder builder) {
        builder.append("\r\n");
        builder.append("  <!-- 以上代码由代码生成器自动生成，原则上不允许任何修改, 如需自定义，请在下面补充 -->")
                .append("\r\n");
        builder.append("</mapper>");
    }
}
