package cn.com.zhshzh.system.generator.model;

import lombok.Data;

import java.util.List;

/**
 * 映射代码生成器配置文件的对象
 *
 * @author WBT
 * @since 2019/12/15
 */
@Data
public class GeneratorMappingsModel {
    /**
     * 名称
     */
    private String name;

    /**
     * 版本
     */
    private String version;

    /**
     * 描述
     */
    private String description;

    /**
     * 映射对象
     */
    private List<MappingModel> mappings;
}
