package cn.com.zhshzh.system.generator.model;

import lombok.Data;

import java.util.Set;

/**
 * 处理拼接代码生成器字符串的模型对象
 *
 * @author WBT
 * @since 2019/12/21
 */
@Data
public class GeneratorStringModel {
    /**
     * po文件的绝对路径
     */
    private String poFileAbsolutePath;

    /**
     * dao文件的绝对路径
     */
    private String daoFileAbsolutePath;

    /**
     * mapper文件的绝对路径
     */
    private String mapperFileAbsolutePath;

    /**
     * po文件的包名
     */
    private String poPackageName;

    /**
     * dao文件的包名
     */
    private String daoPackageName;

    /**
     * 数据库表名
     */
    private String tableName;

    /**
     * 数据库表的注释信息
     */
    private String tableComment;

    /**
     * 首字母小写的驼峰格式数据库表名
     */
    private String lowerCamelCaseTableName;

    /**
     * 首字母大写的驼峰格式数据库表名
     */
    private String upperCamelCaseTableName;

    /**
     * 主键id
     */
    private String primaryKey;

    /**
     * 首字母小写的驼峰格式主键
     */
    private String lowerCamelCasePrimaryKey;

    /**
     * 主键的jdbcType
     */
    private String priJdbcType;

    /**
     * 主键的javaType
     */
    private String priJavaType;

    /**
     * 主键的fieldType
     */
    private String priFieldType;

    /**
     * mapper.xml中遍历的resultMap字符串
     */
    private String resultMap;

    /**
     * mapper.xml中遍历的baseColumnList字符串
     */
    private String baseColumnList;

    /**
     * mapper.xml中遍历的insertColumnSql字符串
     */
    private String insertColumnSql;

    /**
     * mapper.xml中遍历的insertValueSql字符串
     */
    private String insertValueSql;

    /**
     * mapper.xml中遍历的insertBatchSql字符串
     */
    private String insertBatchSql;

    /**
     * mapper.xml中遍历的updateSql字符串
     */
    private String updateSql;

    /**
     * mapper.xml中遍历的updateBatchSql字符串
     */
    private String updateBatchSql;

    /**
     * po中要引的包
     */
    private Set<String> importPackages;

    /**
     * po中遍历的成员变量字符串
     */
    private String memberVariables;
}
