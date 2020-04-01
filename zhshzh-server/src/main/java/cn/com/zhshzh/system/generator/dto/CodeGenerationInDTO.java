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
     * 是否生成PO文件
     */
    private Boolean generatePoFile;

    /**
     * dao文件的路径
     */
    private String daoFilePath;

    /**
     * 是否生成dao文件
     */
    private Boolean generateDaoFile;

    /**
     * mapper文件的路径
     */
    private String mapperFilePath;

    /**
     * 是否生成mapper文件
     */
    private Boolean generateMapperFile;

    /**
     * DTO文件的路径
     */
    private String dtoFilePath;

    /**
     * 是否生成DTO文件
     */
    private Boolean generateDtoFile;

    /**
     * service文件的路径
     */
    private String serviceFilePath;

    /**
     * 是否生成service文件
     */
    private Boolean generateServiceFile;

    /**
     * controller文件的路径
     */
    private String controllerFilePath;

    /**
     * 是否生成controller文件
     */
    private Boolean generateControllerFile;

    /**
     * 请求资源名称
     */
    private String requestMapping;
}
