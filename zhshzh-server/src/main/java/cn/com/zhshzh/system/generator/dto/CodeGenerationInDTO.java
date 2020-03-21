package cn.com.zhshzh.system.generator.dto;

import lombok.Data;

/**
 * 代码生成器的数据传输对象
 *
 * @author WBT
 * @since 2019/12/15
 */
@Data
public class CodeGenerationInDTO {
    /**
     * 数据库名
     */
    private String tableSchema;

    /**
     * 表名
     */
    private String tableName;

    /**
     * PO文件的路径
     */
    private String poFilePath;

    /**
     * dao文件的路径
     */
    private String daoFilePath;

    /**
     * mapper文件的路径
     */
    private String mapperFilePath;
}
