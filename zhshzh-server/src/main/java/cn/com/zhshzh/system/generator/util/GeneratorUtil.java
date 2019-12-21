package cn.com.zhshzh.system.generator.util;

import cn.com.zhshzh.core.util.FileUtil;
import cn.com.zhshzh.system.generator.model.GeneratorMappingsModel;
import cn.com.zhshzh.system.generator.model.MappingModel;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    static String getJavaPath() {
        // 项目目录的绝对路径
        String projectPath = System.getProperty(PROJECT_PATH);
        return projectPath + JAVA_PATH;
    }

    /**
     * 获取本项目resources目录的绝对路径
     *
     * @return resources目录的绝对路径
     */
    static String getResourcesPath() {
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
    public static Map<String, MappingModel> getMappingModel() {
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
}
