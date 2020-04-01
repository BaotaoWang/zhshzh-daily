package cn.com.zhshzh.system.generator.util;

import cn.com.zhshzh.core.util.DailyException;
import cn.com.zhshzh.core.util.DateFormatUtil;
import cn.com.zhshzh.core.util.FileUtil;
import cn.com.zhshzh.system.generator.model.GeneratorStringModel;

import java.time.LocalDate;
import java.util.Set;

/**
 * 生成dto文件的工具类
 *
 * @author WBT
 * @since 2020/03/29
 */
public class GenerateDtoFileUtil {

    /**
     * 生成dto.java文件
     *
     * @param generatorStringModel 解析数据库表及字段的模型对象
     * @throws DailyException 异常
     */
    public static void generateDtoFile(GeneratorStringModel generatorStringModel) throws DailyException {
        // dto文件的绝对路径
        String dtoFileAbsolutePath = generatorStringModel.getDtoFileAbsolutePath();
        // dto文件的包名
        String dtoPackageName = generatorStringModel.getDtoPackageName();
        // 数据库表名
        String tableName = generatorStringModel.getTableName();
        // 数据库表的注释
        String tableComment = generatorStringModel.getTableComment();
        // pojo对象中需要引的包
        Set<String> importPackages = generatorStringModel.getImportPackages();
        // // pojo对象中需要额外引的包
        Set<String> otherImportPackages = generatorStringModel.getOtherImportPackages();
        // dto中的成员变量
        String dtoMemberVariables = generatorStringModel.getDtoMemberVariables();
        // inDto中的成员变量
        String inDtoMemberVariables = generatorStringModel.getInDtoMemberVariables();
        // outDto中的成员变量
        String outDtoMemberVariables = generatorStringModel.getOutDtoMemberVariables();
        // 首字母大写的驼峰格式表名
        String upperCamelCaseTableName = generatorStringModel.getUpperCamelCaseTableName();
        // dto文件名
        String dtoFileName = upperCamelCaseTableName + "DTO.java";
        // inDto文件名
        String inDtoFileName = upperCamelCaseTableName + "InDTO.java";
        // outDto文件名
        String outDtoFileName = upperCamelCaseTableName + "OutDTO.java";

        // 拼接dto文件
        StringBuilder dtoBuilder = new StringBuilder();
        // 生成dto文件
        generateDto(dtoPackageName, tableComment, upperCamelCaseTableName, importPackages, dtoMemberVariables, dtoBuilder);
        // 将dto字符串文本写到本地文件
        FileUtil.convertTextToLocalFile(dtoFileAbsolutePath, dtoFileName, dtoBuilder.toString());

        // 拼接inDto文件
        StringBuilder inDtoBuilder = new StringBuilder();
        // 生成inDto文件
        generateInDto(dtoPackageName, tableComment, upperCamelCaseTableName, importPackages, otherImportPackages, inDtoMemberVariables, inDtoBuilder);
        // 将inDto字符串文本写到本地文件
        FileUtil.convertTextToLocalFile(dtoFileAbsolutePath, inDtoFileName, inDtoBuilder.toString());

        // 拼接outDto文件
        StringBuilder outDtoBuilder = new StringBuilder();
        // 生成outDto文件
        generateOutDto(dtoPackageName, tableComment, upperCamelCaseTableName, importPackages, outDtoMemberVariables, outDtoBuilder);
        // 将outDto字符串文本写到本地文件
        FileUtil.convertTextToLocalFile(dtoFileAbsolutePath, outDtoFileName, outDtoBuilder.toString());
    }

    /**
     * 生成dto文件
     *
     * @param dtoPackageName          dto文件的包名
     * @param tableComment            数据库表的注释
     * @param upperCamelCaseTableName 首字母大写的驼峰格式表名
     * @param importPackages          dto对象中需要引的包
     * @param dtoMemberVariables      拼接后dto对象的成员变量
     * @param dtoBuilder              拼接的dto文件文本
     */
    private static void generateDto(String dtoPackageName, String tableComment, String upperCamelCaseTableName,
                                    Set<String> importPackages, String dtoMemberVariables, StringBuilder dtoBuilder) {
        // 当前日期
        LocalDate localDate = LocalDate.now();
        String nowDateStr = DateFormatUtil.getBackslashDateString(localDate);

        // 拼接dto.java的头部
        dtoBuilder.append("package ").append(dtoPackageName).append(";").append("\r\n");
        dtoBuilder.append("\r\n");
        // 拼接引包
        dtoBuilder.append("import io.swagger.annotations.ApiModel;").append("\r\n");
        dtoBuilder.append("import io.swagger.annotations.ApiModelProperty;").append("\r\n");
        dtoBuilder.append("import lombok.Data;").append("\r\n");
        dtoBuilder.append("\r\n");
        dtoBuilder.append("import java.io.Serializable;").append("\r\n");
        // 遍历拼接需要额外引的包
        importPackages.forEach(importPackage -> dtoBuilder.append(importPackage).append("\r\n"));
        dtoBuilder.append("\r\n");
        // 生成注释
        dtoBuilder.append("/**").append("\r\n");
        dtoBuilder.append(" * ").append(tableComment).append("模型(条件)").append("\r\n");
        dtoBuilder.append(" *").append("\r\n");
        dtoBuilder.append(" * @author Generator").append("\r\n");
        dtoBuilder.append(" * @since ").append(nowDateStr).append("\r\n");
        dtoBuilder.append(" */").append("\r\n");
        dtoBuilder.append("@Data").append("\r\n");
        dtoBuilder.append("@ApiModel(description = \"").append(tableComment).append("模型(条件)\")").append("\r\n");
        dtoBuilder.append("public class ").append(upperCamelCaseTableName).append("DTO implements Serializable {").append("\r\n");
        dtoBuilder.append(dtoMemberVariables).append("\r\n");
        dtoBuilder.append("    @ApiModelProperty(value = \"页码\", dataType = \"Integer\", required = true)").append("\r\n");
        dtoBuilder.append("    private Integer pageNum; // 页码").append("\r\n");
        dtoBuilder.append("\r\n");
        dtoBuilder.append("    @ApiModelProperty(value = \"每页条数\", dataType = \"Integer\", required = true)").append("\r\n");
        dtoBuilder.append("    private Integer pageSize; // 每页条数").append("\r\n");
        dtoBuilder.append("\r\n");
        dtoBuilder.append("    /**").append("\r\n");
        dtoBuilder.append("     * 无参构造方法").append("\r\n");
        dtoBuilder.append("     */").append("\r\n");
        dtoBuilder.append("    public ").append(upperCamelCaseTableName).append("DTO() {").append("\r\n");
        dtoBuilder.append("        super();").append("\r\n");
        dtoBuilder.append("    }").append("\r\n");
        dtoBuilder.append("\r\n");
        dtoBuilder.append("}");
    }

    /**
     * 生成inDto文件
     *
     * @param dtoPackageName          inDto文件的包名
     * @param tableComment            数据库表的注释
     * @param upperCamelCaseTableName 首字母大写的驼峰格式表名
     * @param importPackages          inDto对象中需要引的包
     * @param otherImportPackages     inDto对象中需要引的包
     * @param inDtoMemberVariables    拼接后inDto对象的成员变量
     * @param inDtoBuilder            拼接的inDto文件文本
     */
    private static void generateInDto(String dtoPackageName, String tableComment, String upperCamelCaseTableName, Set<String> importPackages,
                                      Set<String> otherImportPackages, String inDtoMemberVariables, StringBuilder inDtoBuilder) {
        // 当前日期
        LocalDate localDate = LocalDate.now();
        String nowDateStr = DateFormatUtil.getBackslashDateString(localDate);

        // 拼接inDto.java的头部
        inDtoBuilder.append("package ").append(dtoPackageName).append(";").append("\r\n");
        inDtoBuilder.append("\r\n");
        // 拼接引包
        inDtoBuilder.append("import io.swagger.annotations.ApiModel;").append("\r\n");
        inDtoBuilder.append("import io.swagger.annotations.ApiModelProperty;").append("\r\n");
        inDtoBuilder.append("import lombok.Data;").append("\r\n");
        inDtoBuilder.append("\r\n");
        inDtoBuilder.append("import java.io.Serializable;").append("\r\n");
        // 遍历拼接需要额外引的包
        importPackages.forEach(importPackage -> inDtoBuilder.append(importPackage).append("\r\n"));
        otherImportPackages.forEach(otherImportPackage -> inDtoBuilder.append(otherImportPackage).append("\r\n"));
        inDtoBuilder.append("\r\n");
        // 生成注释
        inDtoBuilder.append("/**").append("\r\n");
        inDtoBuilder.append(" * ").append(tableComment).append("模型(新增/修改)").append("\r\n");
        inDtoBuilder.append(" *").append("\r\n");
        inDtoBuilder.append(" * @author Generator").append("\r\n");
        inDtoBuilder.append(" * @since ").append(nowDateStr).append("\r\n");
        inDtoBuilder.append(" */").append("\r\n");
        inDtoBuilder.append("@Data").append("\r\n");
        inDtoBuilder.append("@ApiModel(description = \"").append(tableComment).append("模型(新增/修改)\")").append("\r\n");
        inDtoBuilder.append("public class ").append(upperCamelCaseTableName).append("InDTO implements Serializable {").append("\r\n");
        inDtoBuilder.append(inDtoMemberVariables).append("\r\n");
        inDtoBuilder.append("    /**").append("\r\n");
        inDtoBuilder.append("     * 无参构造方法").append("\r\n");
        inDtoBuilder.append("     */").append("\r\n");
        inDtoBuilder.append("    public ").append(upperCamelCaseTableName).append("InDTO() {").append("\r\n");
        inDtoBuilder.append("        super();").append("\r\n");
        inDtoBuilder.append("    }").append("\r\n");
        inDtoBuilder.append("\r\n");
        inDtoBuilder.append("}");
    }

    /**
     * 生成po文件
     *
     * @param dtoPackageName          outDto文件的包名
     * @param tableComment            数据库表的注释
     * @param upperCamelCaseTableName 首字母大写的驼峰格式表名
     * @param importPackages          outDto对象中需要引的包
     * @param outDtoMemberVariables   拼接后outDto对象的成员变量
     * @param outDtoBuilder           拼接的outDto文件文本
     */
    private static void generateOutDto(String dtoPackageName, String tableComment, String upperCamelCaseTableName,
                                       Set<String> importPackages, String outDtoMemberVariables, StringBuilder outDtoBuilder) {
        // 当前日期
        LocalDate localDate = LocalDate.now();
        String nowDateStr = DateFormatUtil.getBackslashDateString(localDate);

        // 拼接dto.java的头部
        outDtoBuilder.append("package ").append(dtoPackageName).append(";").append("\r\n");
        outDtoBuilder.append("\r\n");
        // 拼接引包
        outDtoBuilder.append("import io.swagger.annotations.ApiModel;").append("\r\n");
        outDtoBuilder.append("import io.swagger.annotations.ApiModelProperty;").append("\r\n");
        outDtoBuilder.append("import lombok.Data;").append("\r\n");
        outDtoBuilder.append("\r\n");
        outDtoBuilder.append("import java.io.Serializable;").append("\r\n");
        // 遍历拼接需要额外引的包
        importPackages.forEach(importPackage -> outDtoBuilder.append(importPackage).append("\r\n"));
        outDtoBuilder.append("\r\n");
        // 生成注释
        outDtoBuilder.append("/**").append("\r\n");
        outDtoBuilder.append(" * ").append(tableComment).append("模型(结果)").append("\r\n");
        outDtoBuilder.append(" *").append("\r\n");
        outDtoBuilder.append(" * @author Generator").append("\r\n");
        outDtoBuilder.append(" * @since ").append(nowDateStr).append("\r\n");
        outDtoBuilder.append(" */").append("\r\n");
        outDtoBuilder.append("@Data").append("\r\n");
        outDtoBuilder.append("@ApiModel(description = \"").append(tableComment).append("模型(结果)\")").append("\r\n");
        outDtoBuilder.append("public class ").append(upperCamelCaseTableName).append("OutDTO implements Serializable {").append("\r\n");
        outDtoBuilder.append(outDtoMemberVariables).append("\r\n");
        outDtoBuilder.append("    /**").append("\r\n");
        outDtoBuilder.append("     * 无参构造方法").append("\r\n");
        outDtoBuilder.append("     */").append("\r\n");
        outDtoBuilder.append("    public ").append(upperCamelCaseTableName).append("OutDTO() {").append("\r\n");
        outDtoBuilder.append("        super();").append("\r\n");
        outDtoBuilder.append("    }").append("\r\n");
        outDtoBuilder.append("\r\n");
        outDtoBuilder.append("}");
    }
}
