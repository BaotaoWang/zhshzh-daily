package cn.com.zhshzh.system.generator.util;

import cn.com.zhshzh.core.util.DailyException;
import cn.com.zhshzh.core.util.DateFormatUtil;
import cn.com.zhshzh.core.util.FileUtil;
import cn.com.zhshzh.system.generator.model.GeneratorStringModel;

import java.util.Date;
import java.util.Set;

/**
 * 生成Mybatis的po文件的工具类
 *
 * @author WBT
 * @since 2019/12/22
 */
public class GeneratePoFileUtil {

    /**
     * 生成Mybatis的mapper.java文件
     *
     * @param generatorStringModel 解析数据库表及字段的模型对象
     * @throws DailyException 异常
     */
    public static void generatePoFile(GeneratorStringModel generatorStringModel) throws DailyException {
        // po文件的绝对路径
        String poFileAbsolutePath = generatorStringModel.getPoFileAbsolutePath();
        // po文件的包名
        String poPackageName = generatorStringModel.getPoPackageName();
        // 数据库表名
        String tableName = generatorStringModel.getTableName();
        // 数据库表的注释
        String tableComment = generatorStringModel.getTableComment();
        // po对象中需要额外引的包
        Set<String> importPackages = generatorStringModel.getImportPackages();
        // 成员变量
        String memberVariables = generatorStringModel.getMemberVariables();
        // 首字母大写的驼峰格式po名
        String upperCamelCasePoName = generatorStringModel.getUpperCamelCaseTableName() + "PO";
        // po文件名
        String poFileName = upperCamelCasePoName + ".java";

        // 拼接po文件
        StringBuilder builder = new StringBuilder();
        // 生成po文件
        generatePo(poPackageName, tableName, tableComment, upperCamelCasePoName, importPackages, memberVariables, builder);

        // 将字符串文本写到本地文件
        FileUtil.convertTextToLocalFile(poFileAbsolutePath, poFileName, builder.toString());
    }

    /**
     * 生成po文件
     *
     * @param poPackageName        po文件的包名
     * @param tableName            数据库表名
     * @param tableComment         数据库表的注释
     * @param upperCamelCasePoName 首字母大写的驼峰格式po名
     * @param importPackages       po对象中需要额外引的包
     * @param memberVariables      拼接后po对象的成员变量
     * @param builder              拼接的po文件文本
     */
    private static void generatePo(String poPackageName, String tableName, String tableComment, String upperCamelCasePoName,
                                   Set<String> importPackages, String memberVariables, StringBuilder builder) {
        // 当前日期
        Date nowDate = new Date();
        String nowDateStr = DateFormatUtil.getBackslashDateString(nowDate);

        // 拼接mapper.xml的头部
        builder.append("package ").append(poPackageName).append(";").append("\r\n");
        builder.append("\r\n");
        // 拼接引包
        builder.append("import lombok.Data;").append("\r\n");
        builder.append("\r\n");
        builder.append("import java.io.Serializable;").append("\r\n");
        // 遍历拼接需要额外引的包
        importPackages.forEach(importPackage -> builder.append(importPackage).append("\r\n"));
        builder.append("\r\n");
        // 生成注释
        builder.append("/**").append("\r\n");
        builder.append(" * ").append(tableName).append("\r\n");
        builder.append(" * ").append(tableComment).append("PO").append("\r\n");
        builder.append(" *").append("\r\n");
        builder.append(" * @author Generator").append("\r\n");
        builder.append(" * @since ").append(nowDateStr).append("\r\n");
        builder.append(" */").append("\r\n");
        builder.append("@Data").append("\r\n");
        builder.append("public class ").append(upperCamelCasePoName).append(" implements Serializable {").append("\r\n");
        builder.append(memberVariables).append("\r\n");
        builder.append("    /**").append("\r\n");
        builder.append("     * 无参构造方法").append("\r\n");
        builder.append("     */").append("\r\n");
        builder.append("    public ").append(upperCamelCasePoName).append("() {").append("\r\n");
        builder.append("        super();").append("\r\n");
        builder.append("    }").append("\r\n");
        builder.append("\r\n");
        builder.append("    // 以上代码由代码生成器自动生成，原则上不允许任何修改, 如需自定义，请在下面补充").append("\r\n");
        builder.append("}");
    }
}
