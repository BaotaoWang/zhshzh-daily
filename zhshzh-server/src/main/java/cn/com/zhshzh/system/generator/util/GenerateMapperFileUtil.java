package cn.com.zhshzh.system.generator.util;

import cn.com.zhshzh.core.util.DailyException;
import cn.com.zhshzh.core.util.FileUtil;
import cn.com.zhshzh.system.generator.model.GeneratorStringModel;

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
     * @param generatorStringModel 解析数据库表及字段的模型对象
     * @throws DailyException 异常
     */
    public static void generateMapperFile(GeneratorStringModel generatorStringModel) throws DailyException {
        // po文件的包名
        String poPackageName = generatorStringModel.getPoPackageName();
        // dao文件的包名
        String daoPackageName = generatorStringModel.getDaoPackageName();
        // mapper.xml文件的绝对路径
        String mapperFileAbsolutePath = generatorStringModel.getMapperFileAbsolutePath();
        // 数据库表名
        String tableName = generatorStringModel.getTableName();
        // 数据库表的注释
        String tableComment = generatorStringModel.getTableComment();
        // 首字母大写的驼峰格式数据库表名
        String upperCamelCaseTableName = generatorStringModel.getUpperCamelCaseTableName();
        // 首字母小写的驼峰格式数据库表名
        String lowerCamelCaseTableName = generatorStringModel.getLowerCamelCaseTableName();
        // 主键id
        String primaryKey = generatorStringModel.getPrimaryKey();
        // 首字母小写的驼峰格式主键
        String lowerCamelCasePrimaryKey = generatorStringModel.getLowerCamelCasePrimaryKey();
        // 主键的jdbcType
        String priJdbcType = generatorStringModel.getPriJdbcType();
        // 主键的javaType
        String priJavaType = generatorStringModel.getPriJavaType();
        // po文件类型
        String poFileType = poPackageName + "." + upperCamelCaseTableName + "PO";
        // mapper文件名
        String fileName = upperCamelCaseTableName + "Mapper.xml";

        // 拼接mapper.xml文件
        StringBuilder builder = new StringBuilder();
        // 生成mapper.xml的头部
        generateHeader(daoPackageName, upperCamelCaseTableName, builder);
        // 生成mapper.xml的ResultMap
        generateResultMap(poFileType, generatorStringModel.getResultMap(), builder);
        // 生成mapper.xml的Base_Column_List
        generateBaseColumnList(generatorStringModel.getBaseColumnList(), builder);
        // 生成mapper.xml的Where_Clause
        generateWhereClause(builder);
        // 生成mapper.xml的插入SQL
        generateInsertSql(tableName, tableComment, lowerCamelCaseTableName, poFileType, primaryKey,
                priJavaType, generatorStringModel.getInsertColumnSql(), generatorStringModel.getInsertValueSql(), builder);
        // 生成mapper.xml的批量插入SQL
        generateInsertBatchSql(tableName, tableComment, upperCamelCaseTableName,
                lowerCamelCaseTableName, generatorStringModel.getInsertBatchSql(), builder);
        // 生成mapper.xml的根据id逻辑删除SQL
        generateDeleteLogicalSql(tableName, tableComment, primaryKey, priJdbcType,
                lowerCamelCasePrimaryKey, poFileType, builder);
        // 生成mapper.xml的根据批量逻辑删除SQL
        generateDeleteBatchLogicalSql(tableName, tableComment, primaryKey, priJdbcType,
                lowerCamelCaseTableName, builder);
        // 生成mapper.xml的根据id物理删除SQL
        generateDeletePhysicalSql(tableName, tableComment, primaryKey, priJdbcType, priJavaType,
                lowerCamelCasePrimaryKey, builder);
        // 生成mapper.xml的根据批量物理删除SQL
        generateDeleteBatchPhysicalSql(tableName, tableComment, primaryKey, priJdbcType,
                lowerCamelCaseTableName, builder);
        // 生成mapper.xml的修改SQL
        generateUpdateSql(tableName, tableComment, upperCamelCaseTableName, poFileType, primaryKey,
                priJdbcType, lowerCamelCasePrimaryKey, generatorStringModel.getUpdateSql(), builder);
        // 生成mapper.xml的批量修改SQL
        generateUpdateBatchSql(tableName, tableComment, upperCamelCaseTableName,
                lowerCamelCaseTableName, primaryKey, priJdbcType, lowerCamelCasePrimaryKey,
                generatorStringModel.getUpdateBatchSql(), builder);
        // 生成mapper.xml的根据id查询的SQL
        generateSelectSql(tableName, tableComment, upperCamelCaseTableName, primaryKey,
                priJdbcType, priJavaType, lowerCamelCasePrimaryKey, builder);
        // 生成mapper.xml的条件查询的SQL
        generateSelectByConditionsSql(tableName, tableComment, upperCamelCaseTableName, builder);
        // 生成mapper.xml的条件查询条数SQL
        generateSelectCountSql(tableName, tableComment, upperCamelCaseTableName, builder);
        // 生成mapper.xml的尾部
        generateFooter(builder);

        // 将字符串文本写到本地文件
        FileUtil.convertTextToLocalFile(mapperFileAbsolutePath, fileName, builder.toString());
    }

    /**
     * 生成mapper.xml的头部
     *
     * @param daoPackageName          dao文件的路径
     * @param upperCamelCaseTableName 首字母大写的驼峰格式数据库表名
     * @param builder                 拼接的mapper.xml文件文本
     */
    private static void generateHeader(String daoPackageName, String upperCamelCaseTableName, StringBuilder builder) {
        // dao文件名
        String daoFileName = upperCamelCaseTableName + "Mapper";
        // 映射dao文件的namespace
        String namespace = daoPackageName + "." + daoFileName;
        // 拼接mapper.xml的头部
        builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>").append("\r\n");
        builder.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >").append("\r\n");
        builder.append("<mapper namespace=\"").append(namespace).append("\" >").append("\r\n");
    }

    /**
     * 生成mapper.xml的ResultMap
     *
     * @param poFileType po文件类型
     * @param resultMap  遍历后的resultMap字符串
     * @param builder    拼接的mapper.xml文件文本
     */
    private static void generateResultMap(String poFileType, String resultMap, StringBuilder builder) {
        builder.append("  <resultMap id=\"BaseResultMap\" type=\"").append(poFileType).append("\" >").append("\r\n");
        builder.append(resultMap);
        builder.append("  </resultMap>").append("\r\n");
    }

    /**
     * 生成mapper.xml的Base_Column_List
     *
     * @param baseColumnList 遍历后的Base_Column_List字符串
     * @param builder        拼接的mapper.xml文件文本
     */
    private static void generateBaseColumnList(String baseColumnList, StringBuilder builder) {
        builder.append("\r\n");
        builder.append("  <sql id=\"Base_Column_List\" >").append("\r\n");
        builder.append("    ");
        builder.append(baseColumnList);
        builder.append("  </sql>").append("\r\n");
    }

    /**
     * 生成mapper.xml的Where_Clause
     *
     * @param builder 拼接的mapper.xml文件文本
     */
    private static void generateWhereClause(StringBuilder builder) {
        builder.append("\r\n");
        builder.append("  <!-- 查询条件的拼装 -->").append("\r\n");
        builder.append("  <sql id=\"Where_Clause\" >").append("\r\n");
        builder.append("    <where>").append("\r\n");
        builder.append("      is_delelte = 0 AND").append("\r\n");
        builder.append("      <if test=\"conditionList != null and conditionList.size() != 0\">").append("\r\n");
        builder.append("        <foreach collection=\"conditionList\" item=\"item\" index=\"index\" separator=\"AND\" >").append("\r\n");
        builder.append("          <choose>").append("\r\n");
        builder.append("            <when test=\"item.whereConditionEnum.condition == 'LIKE'\" >").append("\r\n");
        builder.append("              ${item.columnName} ${item.whereConditionEnum.condition} CONCAT(CONCAT('%', #{item.value}), %)").append("\r\n");
        builder.append("            </when>").append("\r\n");
        builder.append("            <when test=\"item.whereConditionEnum.condition == 'NOT LIKE'\" >").append("\r\n");
        builder.append("              ${item.columnName} ${item.whereConditionEnum.condition} CONCAT(CONCAT('%', #{item.value}), %)").append("\r\n");
        builder.append("            </when>").append("\r\n");
        builder.append("            <when test=\"item.whereConditionEnum.condition == 'BETWEEN'\" >").append("\r\n");
        builder.append("              <if test=\"item.startValue != null and item.startValue != ''\" >").append("\r\n");
        builder.append("                ${item.columnName} &gt;= #{item.startValue}").append("\r\n");
        builder.append("              </if>").append("\r\n");
        builder.append("              <if  test=\"item.endValue != null and item.endValue != ''\" >").append("\r\n");
        builder.append("                ${item.columnName} &lt;= #{item.endValue}").append("\r\n");
        builder.append("              </if>").append("\r\n");
        builder.append("              <if  test=\"(item.startValue == null or item.startValue == '') and (item.endValue == null or item.endValue == '')\" >").append("\r\n");
        builder.append("                1 = 1").append("\r\n");
        builder.append("              </if>").append("\r\n");
        builder.append("            </when>").append("\r\n");
        builder.append("            <when test=\"item.whereConditionEnum.condition == 'IN'\" >").append("\r\n");
        builder.append("              ${item.columnName} ${item.whereConditionEnum.condition}").append("\r\n");
        builder.append("              <foreach collection=\"item.values\" item=\"item\" index=\"index\" separator=\",\" >").append("\r\n");
        builder.append("                #{item}").append("\r\n");
        builder.append("              </foreach>").append("\r\n");
        builder.append("            </when>").append("\r\n");
        builder.append("            <when test=\"item.whereConditionEnum.condition == 'NOT IN'\" >").append("\r\n");
        builder.append("              ${item.columnName} ${item.whereConditionEnum.condition}").append("\r\n");
        builder.append("              <foreach collection=\"item.values\" item=\"item\" index=\"index\" separator=\",\" >").append("\r\n");
        builder.append("                #{item}").append("\r\n");
        builder.append("              </foreach>").append("\r\n");
        builder.append("            </when>").append("\r\n");
        builder.append("            <otherwise>").append("\r\n");
        builder.append("              ${item.columnName} ${item.whereConditionEnum.condition} #{item.value}").append("\r\n");
        builder.append("            </otherwise>").append("\r\n");
        builder.append("          </choose>").append("\r\n");
        builder.append("        </foreach>").append("\r\n");
        builder.append("      </if>").append("\r\n");
        builder.append("    </where>").append("\r\n");
        builder.append("  </sql>").append("\r\n");
    }

    /**
     * 生成mapper.xml的插入SQL
     *
     * @param tableName          数据库表名
     * @param tableComment       数据库表的备注
     * @param camelCaseTableName 首字母小写的驼峰格式数据库表名
     * @param poFileType         po文件类型
     * @param primaryKey         主键id
     * @param priJavaType        主键的javaType
     * @param insertColumnSql    遍历后的insertColumnSql字符串
     * @param insertValueSql     遍历后的insertColumnSql字符串
     * @param builder            拼接的mapper.xml文件文本
     */
    private static void generateInsertSql(String tableName, String tableComment, String camelCaseTableName,
                                          String poFileType, String primaryKey, String priJavaType,
                                          String insertColumnSql, String insertValueSql, StringBuilder builder) {
        builder.append("\r\n");
        // 生成sql的注释
        builder.append("  <!-- 新增").append(tableComment).append(" -->").append("\r\n");
        // 拼接insertSQL
        builder.append("  <insert id=\"insert").append(camelCaseTableName).append("\" parameterType=\"").append(poFileType)
                .append("\" >").append("\r\n");
        builder.append("    INSERT INTO ").append(tableName).append("\r\n");
        builder.append("    <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\" >").append("\r\n");
        builder.append(insertColumnSql);
        builder.append("    </trim>").append("\r\n");
        builder.append("    <trim prefix=\"VALUES (\" suffix=\")\" suffixOverrides=\",\" >").append("\r\n");
        builder.append(insertValueSql);
        builder.append("    </trim>").append("\r\n");
        // 返回新增数据的主键
        builder.append("    <selectKey resultType=\"").append(priJavaType)
                .append("\" order=\"AFTER\" keyProperty=\"").append(primaryKey).append("\" >").append("\r\n");
        builder.append("      SELECT LAST_INSERT_ID()").append("\r\n");
        builder.append("    </selectKey>").append("\r\n");
        builder.append("  </insert>").append("\r\n");
    }

    /**
     * 生成mapper.xml的批量插入SQL
     *
     * @param tableName               数据库表名
     * @param tableComment            数据库表的备注
     * @param upperCamelCaseTableName 首字母大写的驼峰格式数据库表名
     * @param lowerCamelCaseTableName 首字母小写的驼峰格式数据库表名
     * @param insertBatchSql          遍历后的insertBatchSql字符串
     * @param builder                 拼接的mapper.xml文件文本
     */
    private static void generateInsertBatchSql(String tableName, String tableComment, String upperCamelCaseTableName,
                                               String lowerCamelCaseTableName, String insertBatchSql, StringBuilder builder) {
        builder.append("\r\n");
        // 生成sql的注释
        builder.append("  <!-- 批量新增").append(tableComment).append(" -->").append("\r\n");
        // 拼接insertBatchSQL
        builder.append("  <insert id=\"insert").append(upperCamelCaseTableName)
                .append("Batch\" parameterType=\"java.util.List\" >").append("\r\n");
        builder.append("    INSERT INTO ").append(tableName).append(" (").append("\r\n");
        builder.append("      <include refid=\"Base_Column_List\" />").append("\r\n");
        builder.append("    )").append("\r\n");
        builder.append("    VALUES").append("\r\n");
        builder.append("    <foreach collection=\"").append(lowerCamelCaseTableName)
                .append("List\" item=\"item\" index=\"index\" separator=\",\" >").append("\r\n");
        builder.append("      <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\" >").append("\r\n");
        builder.append(insertBatchSql);
        builder.append("      </trim>").append("\r\n");
        builder.append("    </foreach>").append("\r\n");
        builder.append("  </insert>").append("\r\n");
    }

    /**
     * 生成mapper.xml的根据id逻辑删除SQL
     *
     * @param tableName                数据库表名
     * @param tableComment             数据库表的备注
     * @param primaryKey               主键
     * @param priJdbcType              主键的jdbcType
     * @param lowerCamelCasePrimaryKey 首字母小写的驼峰格式的主键
     * @param poFileType               po文件类型
     * @param builder                  拼接的mapper.xml文件文本
     */
    private static void generateDeleteLogicalSql(String tableName, String tableComment, String primaryKey,
                                                 String priJdbcType, String lowerCamelCasePrimaryKey,
                                                 String poFileType, StringBuilder builder) {
        builder.append("\r\n");
        // 生成sql的注释
        builder.append("  <!-- 根据id逻辑删除").append(tableComment).append(" -->").append("\r\n");
        // 拼接update语句
        builder.append("  <update id=\"deleteByIdLogical\" parameterType=\"").append(poFileType).append("\" >").append("\r\n");
        builder.append("    UPDATE ").append(tableName).append("\r\n");
        builder.append("    SET is_delete = 1,").append("\r\n");
        builder.append("    update_by = #{updateBy, jdbcType=BIGINT}").append("\r\n");
        builder.append("    WHERE ").append(primaryKey).append(" = #{").append(lowerCamelCasePrimaryKey).append(", jdbcType=")
                .append(priJdbcType).append("}").append("\r\n");
        builder.append("  </update>").append("\r\n");
    }

    /**
     * 生成mapper.xml的根据批量逻辑删除SQL
     *
     * @param tableName               数据库表名
     * @param tableComment            数据库表的备注
     * @param primaryKey              主键
     * @param priJdbcType             主键的jdbcType
     * @param lowerCamelCaseTableName 首字母小写的驼峰格式的数据库表名
     * @param builder                 拼接的mapper.xml文件文本
     */
    private static void generateDeleteBatchLogicalSql(String tableName, String tableComment, String primaryKey,
                                                      String priJdbcType, String lowerCamelCaseTableName,
                                                      StringBuilder builder) {
        builder.append("\r\n");
        // 生成sql的注释
        builder.append("  <!-- 批量逻辑删除").append(tableComment).append(" -->").append("\r\n");
        // 拼接update语句
        builder.append("  <update id=\"deleteBatchLogical\" parameterType=\"java.util.Map\" >").append("\r\n");
        builder.append("    UPDATE ").append(tableName).append("\r\n");
        builder.append("    SET is_delete = 1,").append("\r\n");
        builder.append("    update_by = #{updateBy, jdbcType=BIGINT}").append("\r\n");
        builder.append("    WHERE ").append(primaryKey).append(" IN").append("\r\n");
        builder.append("    <foreach collection=\"").append(lowerCamelCaseTableName)
                .append("s\" item=\"item\" index=\"index\" open=\"(\" close=\")\" separator=\",\" >").append("\r\n");
        builder.append("      #{item, jdbcType=").append(priJdbcType).append("}").append("\r\n");
        builder.append("    </foreach>").append("\r\n");
        builder.append("  </update>").append("\r\n");
    }

    /**
     * 生成mapper.xml的根据id物理删除SQL
     *
     * @param tableName                数据库表名
     * @param tableComment             数据库表的备注
     * @param primaryKey               主键
     * @param priJdbcType              主键的jdbcType
     * @param priJavaType              主键的javaType
     * @param lowerCamelCasePrimaryKey 首字母小写的驼峰格式的主键
     * @param builder                  拼接的mapper.xml文件文本
     */
    private static void generateDeletePhysicalSql(String tableName, String tableComment, String primaryKey,
                                                  String priJdbcType, String priJavaType, String lowerCamelCasePrimaryKey,
                                                  StringBuilder builder) {
        builder.append("\r\n");
        // 生成sql的注释
        builder.append("  <!-- 根据id物理删除").append(tableComment).append(" -->").append("\r\n");
        // 拼接delete语句
        builder.append("  <delete id=\"deleteByIdPhysical\" parameterType=\"").append(priJavaType).append("\" >").append("\r\n");
        builder.append("    DELETE FROM ").append(tableName).append("\r\n");
        builder.append("    WHERE ").append(primaryKey).append(" = #{").append(lowerCamelCasePrimaryKey).append(", jdbcType=")
                .append(priJdbcType).append("}").append("\r\n");
        builder.append("  </delete>").append("\r\n");
    }

    /**
     * 生成mapper.xml的批量物理删除SQL
     *
     * @param tableName               数据库表名
     * @param tableComment            数据库表的备注
     * @param primaryKey              主键
     * @param priJdbcType             主键的jdbcType
     * @param lowerCamelCaseTableName 首字母小写的驼峰格式的数据库表名
     * @param builder                 拼接的mapper.xml文件文本
     */
    private static void generateDeleteBatchPhysicalSql(String tableName, String tableComment, String primaryKey,
                                                       String priJdbcType, String lowerCamelCaseTableName,
                                                       StringBuilder builder) {
        builder.append("\r\n");
        // 生成sql的注释
        builder.append("  <!-- 批量物理删除").append(tableComment).append(" -->").append("\r\n");
        // 拼接delete语句
        builder.append("  <delete id=\"deleteBatchPhysical\" >").append("\r\n");
        builder.append("    DELETE FROM ").append(tableName).append("\r\n");
        builder.append("    WHERE ").append(primaryKey).append(" IN").append("\r\n");
        builder.append("    <foreach collection=\"").append(lowerCamelCaseTableName)
                .append("s\" item=\"item\" index=\"index\" open=\"(\" close=\")\" separator=\",\" >").append("\r\n");
        builder.append("      #{item, jdbcType=").append(priJdbcType).append("}").append("\r\n");
        builder.append("    </foreach>").append("\r\n");
        builder.append("  </delete>").append("\r\n");
    }

    /**
     * 生成mapper.xml的修改SQL
     *
     * @param tableName                数据库表名
     * @param tableComment             数据库表的备注
     * @param upperCamelCaseTableName  首字母大写的驼峰格式数据库表名
     * @param poFileType               po文件类型
     * @param primaryKey               主键id
     * @param priJdbcType              主键的jdbcType
     * @param lowerCamelCasePrimaryKey 首字母小写的驼峰格式主键
     * @param updateSql                遍历后的updateSql字符串
     * @param builder                  拼接的mapper.xml文件文本
     */
    private static void generateUpdateSql(String tableName, String tableComment, String upperCamelCaseTableName,
                                          String poFileType, String primaryKey, String priJdbcType,
                                          String lowerCamelCasePrimaryKey, String updateSql, StringBuilder builder) {
        builder.append("\r\n");
        // 生成sql的注释
        builder.append("  <!-- 根据id修改").append(tableComment).append(" -->").append("\r\n");
        // 拼接updateSQL
        builder.append("  <update id=\"update").append(upperCamelCaseTableName).append("\" parameterType=\"")
                .append(poFileType).append("\" >").append("\r\n");
        builder.append("    UPDATE ").append(tableName).append("\r\n");
        builder.append("    <set>").append("\r\n");
        builder.append(updateSql);
        builder.append("    </set>").append("\r\n");
        builder.append("    WHERE ").append(primaryKey).append(" = #{").append(lowerCamelCasePrimaryKey).append(", jdbcType=")
                .append(priJdbcType).append("}").append("\r\n");
        builder.append("  </update>").append("\r\n");
    }

    /**
     * 生成mapper.xml的批量修改SQL
     *
     * @param tableName                数据库表名
     * @param tableComment             数据库表的备注
     * @param upperCamelCaseTableName  首字母大写的驼峰格式数据库表名
     * @param lowerCamelCaseTableName  首字母大写的驼峰格式数据库表名
     * @param primaryKey               主键id
     * @param priJdbcType              主键的jdbcType
     * @param lowerCamelCasePrimaryKey 首字母小写的驼峰格式主键
     * @param updateBatchSql           遍历后的updateBatchSql字符串
     * @param builder                  拼接的mapper.xml文件文本
     */
    private static void generateUpdateBatchSql(String tableName, String tableComment, String upperCamelCaseTableName,
                                               String lowerCamelCaseTableName, String primaryKey, String priJdbcType,
                                               String lowerCamelCasePrimaryKey, String updateBatchSql, StringBuilder builder) {
        builder.append("\r\n");
        // 生成sql的注释
        builder.append("  <!-- 批量修改").append(tableComment).append(" -->").append("\r\n");
        // 拼接updateBatchSQL
        builder.append("  <update id=\"update").append(upperCamelCaseTableName)
                .append("Batch\" parameterType=\"java.util.List\" >").append("\r\n");
        builder.append("    <foreach collection=\"").append(lowerCamelCaseTableName)
                .append("List\" item=\"item\" index=\"index\" separator=\";\" >").append("\r\n");
        builder.append("      UPDATE ").append(tableName).append("\r\n");
        builder.append("      <set>").append("\r\n");
        builder.append(updateBatchSql);
        builder.append("      </set>").append("\r\n");
        builder.append("      WHERE ").append(primaryKey).append(" = #{").append(lowerCamelCasePrimaryKey).append(", jdbcType=")
                .append(priJdbcType).append("}").append("\r\n");
        builder.append("    </foreach>").append("\r\n");
        builder.append("  </update>").append("\r\n");
    }

    /**
     * 生成mapper.xml的根据id查询的SQL
     *
     * @param tableName                数据库表名
     * @param tableComment             数据库表的备注
     * @param upperCamelCaseTableName  首字母大写的驼峰格式数据库表名
     * @param priJavaType              主键的javaType
     * @param primaryKey               主键id
     * @param priJdbcType              主键的jdbcType
     * @param lowerCamelCasePrimaryKey 首字母小写的驼峰格式主键
     * @param builder                  拼接的mapper.xml文件文本
     */
    private static void generateSelectSql(String tableName, String tableComment, String upperCamelCaseTableName,
                                          String primaryKey, String priJdbcType, String priJavaType,
                                          String lowerCamelCasePrimaryKey, StringBuilder builder) {
        builder.append("\r\n");
        // 生成sql的注释
        builder.append("  <!-- 根据id查询").append(tableComment).append(" -->").append("\r\n");
        // 拼接selectSQL
        builder.append("  <select id=\"get").append(upperCamelCaseTableName).append("\" parameterType=\"")
                .append(priJavaType).append("\" resultMap=\"BaseResultMap\" >").append("\r\n");
        builder.append("    SELECT").append("\r\n");
        builder.append("    <include refid=\"Base_Column_List\" />").append("\r\n");
        builder.append("    FROM ").append(tableName).append("\r\n");
        builder.append("    WHERE ").append(primaryKey).append(" = #{").append(lowerCamelCasePrimaryKey).append(", jdbcType=")
                .append(priJdbcType).append("}").append("\r\n");
        builder.append("  </select>").append("\r\n");
    }

    /**
     * 生成mapper.xml的条件查询的SQL
     *
     * @param tableName               数据库表名
     * @param tableComment            数据库表的备注
     * @param upperCamelCaseTableName 首字母大写的驼峰格式数据库表名
     * @param builder                 拼接的mapper.xml文件文本
     */
    private static void generateSelectByConditionsSql(String tableName, String tableComment, String upperCamelCaseTableName,
                                                      StringBuilder builder) {
        builder.append("\r\n");
        // 生成sql的注释
        builder.append("  <!-- 条件查询").append(tableComment).append(" -->").append("\r\n");
        // 拼接selectSQL
        builder.append("  <select id=\"list").append(upperCamelCaseTableName)
                .append("s\" parameterType=\"cn.com.zhshzh.core.model.WhereConditions")
                .append("\" resultMap=\"BaseResultMap\" >").append("\r\n");
        builder.append("    SELECT").append("\r\n");
        builder.append("    <include refid=\"Base_Column_List\" />").append("\r\n");
        builder.append("    FROM ").append(tableName).append("\r\n");
        builder.append("    <include refid=\"Where_Clause\" />").append("\r\n");
        builder.append("    <if test=\"orderByList != null and orderByList.size() != 0\">").append("\r\n");
        builder.append("      ORDER BY").append("\r\n");
        builder.append("      <foreach collection=\"orderByList\" item=\"item\" index=\"index\" separator=\",\" >").append("\r\n");
        builder.append("        ${item.sort} ${item.orderByEnum.order}").append("\r\n");
        builder.append("      </foreach>").append("\r\n");
        builder.append("    </if>").append("\r\n");
        builder.append("  </select>").append("\r\n");
    }

    /**
     * 生成mapper.xml的条件查询条数SQL
     *
     * @param tableName               数据库表名
     * @param tableComment            数据库表的备注
     * @param upperCamelCaseTableName 首字母大写的驼峰格式数据库表名
     * @param builder                 拼接的mapper.xml文件文本
     */
    private static void generateSelectCountSql(String tableName, String tableComment, String upperCamelCaseTableName,
                                               StringBuilder builder) {
        builder.append("\r\n");
        // 生成sql的注释
        builder.append("  <!-- 条件查询").append(tableComment).append("条数 -->").append("\r\n");
        // 拼接selectCountSQL
        builder.append("  <select id=\"count").append(upperCamelCaseTableName)
                .append("s\"  parameterType=\"cn.com.zhshzh.core.model.WhereConditions\" resultType=\"java.long.Integer\" >").append("\r\n");
        builder.append("    SELECT COUNT(*) FROM ").append(tableName).append("\r\n");
        builder.append("    <include refid=\"Where_Clause\" />").append("\r\n");
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
