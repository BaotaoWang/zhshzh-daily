package cn.com.zhshzh.system.generator.model;


import lombok.Data;

/**
 * 数据库表字段类型与java字段类型的映射对象
 *
 * @author WBT
 * @since 2019/12/15
 */
@Data
public class MappingModel {
    /**
     * 数据库表字段的实际类型
     */
    private String columnType;

    /**
     * java对象中成员变量的类型
     */
    private String fieldType;

    /**
     * mapper文件中的jdbcType
     */
    private String jdbcType;

    /**
     * mapper文件中的javaType
     */
    private String javaType;
}

