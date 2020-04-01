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
     * dto文件的绝对路径
     */
    private String dtoFileAbsolutePath;

    /**
     * service文件的绝对路径
     */
    private String serviceFileAbsolutePath;

    /**
     * serviceImpl文件的绝对路径
     */
    private String serviceImplFileAbsolutePath;

    /**
     * controller文件的绝对路径
     */
    private String controllerFileAbsolutePath;

    /**
     * controller中的requestMapping值
     */
    private String requestMapping;

    /**
     * po文件的包名
     */
    private String poPackageName;

    /**
     * dao文件的包名
     */
    private String daoPackageName;

    /**
     * dto文件的包名
     */
    private String dtoPackageName;

    /**
     * service文件的包名
     */
    private String servicePackageName;

    /**
     * serviceImpl文件的包名
     */
    private String serviceImplPackageName;

    /**
     * controller文件的包名
     */
    private String controllerPackageName;

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
     * pojo类中要引的包
     */
    private Set<String> importPackages;

    /**
     * inDto中额外要引的包
     */
    private Set<String> otherImportPackages;

    /**
     * po中遍历的成员变量字符串
     */
    private String memberVariables;

    /**
     * dto中遍历的成员变量字符串
     */
    private String dtoMemberVariables;

    /**
     * inDto中遍历的成员变量字符串
     */
    private String inDtoMemberVariables;

    /**
     * outDto中遍历的成员变量字符串
     */
    private String outDtoMemberVariables;
}
