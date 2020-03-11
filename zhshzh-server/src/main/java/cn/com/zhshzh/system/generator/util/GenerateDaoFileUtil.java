package cn.com.zhshzh.system.generator.util;

import cn.com.zhshzh.core.util.DailyException;
import cn.com.zhshzh.core.util.DateFormatUtil;
import cn.com.zhshzh.core.util.FileUtil;
import cn.com.zhshzh.system.generator.model.GeneratorStringModel;

import java.util.Date;

/**
 * 生成Mybatis的mapper.java文件的工具类
 *
 * @author WBT
 * @since 2019/12/22
 */
public class GenerateDaoFileUtil {

    /**
     * 生成Mybatis的mapper.java文件
     *
     * @param generatorStringModel 解析数据库表及字段的模型对象
     * @throws DailyException 异常
     */
    public static void generateDaoFile(GeneratorStringModel generatorStringModel) throws DailyException {
        // po文件的包名
        String poPackageName = generatorStringModel.getPoPackageName();
        // dao文件的包名
        String daoPackageName = generatorStringModel.getDaoPackageName();
        // mapper.java文件的绝对路径
        String daoFileAbsolutePath = generatorStringModel.getDaoFileAbsolutePath();
        // 数据库表名
        String tableName = generatorStringModel.getTableName();
        // 数据库表的注释
        String tableComment = generatorStringModel.getTableComment();
        // 首字母大写的驼峰格式数据库表名
        String upperCamelCaseTableName = generatorStringModel.getUpperCamelCaseTableName();
        // 首字母小写的驼峰格式数据库表名
        String lowerCamelCaseTableName = generatorStringModel.getLowerCamelCaseTableName();
        // 首字母小写的驼峰格式po名
        String lowerCamelCasePoName = generatorStringModel.getLowerCamelCaseTableName() + "PO";
        // 首字母小写的驼峰格式主键
        String lowerCamelCasePrimaryKey = generatorStringModel.getLowerCamelCasePrimaryKey();
        // 主键的fieldType
        String priFieldType = generatorStringModel.getPriFieldType();
        // po文件类型
        String poFileType = poPackageName + "." + upperCamelCaseTableName + "PO";
        // mapper文件名
        String fileName = upperCamelCaseTableName + "Mapper.java";

        // 拼接mapper.xml文件
        StringBuilder builder = new StringBuilder();
        // 生成mapper.java的头部
        generateHeader(daoPackageName, poFileType, tableName, tableComment, upperCamelCaseTableName, builder);
        // 生成mapper.java的插入方法
        generateInsertMethod(upperCamelCaseTableName, lowerCamelCasePoName, tableComment, builder);
        // 生成mapper.java的批量插入方法
        generateInsertBatchMethod(upperCamelCaseTableName, lowerCamelCasePoName, tableComment, builder);
        // 生成mapper.java的根据id逻辑删除方法
        generateDeleteByIdLogicalMethod(tableComment, lowerCamelCasePrimaryKey, priFieldType, builder);
        // 生成mapper.java的批量逻辑删除方法
        generateDeleteBatchLogicalMethod(lowerCamelCasePrimaryKey, tableComment, builder);
        // 生成mapper.java的根据id物理删除方法
        generateDeleteByIdPhysicalMethod(lowerCamelCasePrimaryKey, priFieldType, tableComment, builder);
        // 生成mapper.java的批量物理删除方法
        generateDeleteBatchPhysicalMethod(lowerCamelCaseTableName, tableComment, builder);
        // 生成mapper.java的修改方法
        generateUpdateMethod(upperCamelCaseTableName, lowerCamelCasePoName, tableComment, builder);
        // 生成mapper.java的批量修改方法
        generateUpdateBatchMethod(upperCamelCaseTableName, lowerCamelCasePoName, tableComment, builder);
        // 生成mapper.java的根据id查询的方法
        generateGetPoMethod(upperCamelCaseTableName, lowerCamelCasePrimaryKey, priFieldType, tableComment, builder);
        // 生成mapper.java的条件查询的方法
        generateListPosMethod(upperCamelCaseTableName, tableComment, builder);
        // 生成mapper.java的listAllPos方法
        generateListAllPosMethod(upperCamelCaseTableName, tableComment, builder);
        // 生成mapper.java的条件查询条数方法
        generateCountMethod(upperCamelCaseTableName, tableComment, builder);
        // 生成mapper.java的尾部
        generateFooter(builder);

        // 将字符串文本写到本地文件
        FileUtil.convertTextToLocalFile(daoFileAbsolutePath, fileName, builder.toString());
    }

    /**
     * 生成mapper.java的头部
     *
     * @param daoPackageName          dao文件的路径
     * @param poFileType              po文件类型
     * @param tableName               数据库表名
     * @param tableComment            数据库表的注释
     * @param upperCamelCaseTableName 首字母大写的驼峰格式数据库表名
     * @param builder                 拼接的mapper.java文件文本
     */
    private static void generateHeader(String daoPackageName, String poFileType, String tableName, String tableComment,
                                       String upperCamelCaseTableName, StringBuilder builder) {
        // 当前日期
        Date nowDate = new Date();
        String nowDateStr = DateFormatUtil.getBackslashDateString(nowDate);

        // 拼接mapper.xml的头部
        builder.append("package ").append(daoPackageName).append(";").append("\r\n");
        builder.append("\r\n");
        // 拼接引包
        builder.append("import ").append(poFileType).append(";").append("\r\n");
        builder.append("import cn.com.zhshzh.core.model.DeleteBatchLogicalModel;").append("\r\n");
        builder.append("import cn.com.zhshzh.core.model.WhereConditions;").append("\r\n");
        builder.append("import org.apache.ibatis.annotations.Mapper;").append("\r\n");
        builder.append("import org.apache.ibatis.annotations.Param;").append("\r\n");
        builder.append("\r\n");
        builder.append("import java.util.List;").append("\r\n");
        builder.append("\r\n");
        // 生成注释
        builder.append("/**").append("\r\n");
        builder.append(" * ").append(tableName).append("\r\n");
        builder.append(" * ").append(tableComment).append("Mapper").append("\r\n");
        builder.append(" *").append("\r\n");
        builder.append(" * @author Generator").append("\r\n");
        builder.append(" * @since ").append(nowDateStr).append("\r\n");
        builder.append(" */").append("\r\n");
        builder.append("@Mapper").append("\r\n");
        builder.append("public interface ").append(upperCamelCaseTableName).append("Mapper {").append("\r\n");
    }

    /**
     * 生成mapper.java的insert方法
     *
     * @param upperCamelCaseTableName 首字母大写的驼峰格式po名
     * @param lowerCamelCasePoName    首字母小写的驼峰格式po名
     * @param tableComment            数据库表的注释
     * @param builder                 拼接的mapper.java文件文本
     */
    private static void generateInsertMethod(String upperCamelCaseTableName, String lowerCamelCasePoName,
                                             String tableComment, StringBuilder builder) {
        builder.append("    /**").append("\r\n");
        builder.append("     * 新增").append(tableComment).append("\r\n");
        builder.append("     *").append("\r\n");
        builder.append("     * @param ").append(lowerCamelCasePoName).append(" ").append(tableComment).append("\r\n");
        builder.append("     */").append("\r\n");
        builder.append("    void insert").append(upperCamelCaseTableName).append("(").append(upperCamelCaseTableName)
                .append("PO ").append(lowerCamelCasePoName).append(");").append("\r\n");
    }

    /**
     * 生成mapper.java的insertBatch方法
     *
     * @param upperCamelCaseTableName 首字母大写的驼峰格式po名
     * @param lowerCamelCasePoName    首字母小写的驼峰格式po名
     * @param tableComment            数据库表的注释
     * @param builder                 拼接的mapper.java文件文本
     */
    private static void generateInsertBatchMethod(String upperCamelCaseTableName, String lowerCamelCasePoName,
                                                  String tableComment, StringBuilder builder) {
        builder.append("\r\n");
        builder.append("    /**").append("\r\n");
        builder.append("     * 批量新增").append(tableComment).append("\r\n");
        builder.append("     *").append("\r\n");
        builder.append("     * @param ").append(lowerCamelCasePoName).append("List ").append(tableComment)
                .append("List").append("\r\n");
        builder.append("     */").append("\r\n");
        builder.append("    void insert").append(upperCamelCaseTableName).append("Batch(").append("@Param(\"")
                .append(lowerCamelCasePoName).append("List\") ").append("List<").append(upperCamelCaseTableName)
                .append("PO> ").append(lowerCamelCasePoName).append("List);").append("\r\n");
    }

    /**
     * 生成mapper.java的deleteByIdLogical方法
     *
     * @param tableComment             数据库表的注释
     * @param lowerCamelCasePrimaryKey 首字母小写的驼峰格式主键
     * @param priFieldType             主键的fieldType
     * @param builder                  拼接的mapper.java文件文本
     */
    private static void generateDeleteByIdLogicalMethod(String tableComment, String lowerCamelCasePrimaryKey,
                                                        String priFieldType, StringBuilder builder) {
        builder.append("\r\n");
        builder.append("    /**").append("\r\n");
        builder.append("     * 根据id逻辑删除").append(tableComment).append("\r\n");
        builder.append("     *").append("\r\n");
        builder.append("     * @param ").append(lowerCamelCasePrimaryKey).append(" 主键id").append("\r\n");
        builder.append("     * @param userInfoId 用户id").append("\r\n");
        builder.append("     */").append("\r\n");
        builder.append("    void deleteByIdLogical(").append("@Param(\"").append(lowerCamelCasePrimaryKey)
                .append("\") ").append(priFieldType).append(" ").append(lowerCamelCasePrimaryKey)
                .append(", @Param(\"userInfoId\") Long userInfoId").append(");").append("\r\n");
    }

    /**
     * 生成mapper.java的deleteBatchLogical方法
     *
     * @param lowerCamelCasePrimaryKey 首字母小写的驼峰格式主键
     * @param tableComment             数据库表的注释
     * @param builder                  拼接的mapper.java文件文本
     */
    private static void generateDeleteBatchLogicalMethod(String lowerCamelCasePrimaryKey, String tableComment,
                                                         StringBuilder builder) {
        builder.append("\r\n");
        builder.append("    /**").append("\r\n");
        builder.append("     * 批量逻辑删除").append(tableComment).append("\r\n");
        builder.append("     *").append("\r\n");
        builder.append("     * @param deleteBatchLogicalModel 批量逻辑删除的模型对象").append("\r\n");
        builder.append("     */").append("\r\n");
        builder.append("    void deleteBatchLogical(DeleteBatchLogicalModel deleteBatchLogicalModel);").append("\r\n");
    }

    /**
     * 生成mapper.java的deleteByIdPhysical方法
     *
     * @param lowerCamelCasePrimaryKey 首字母小写的驼峰格式主键
     * @param priFieldType             主键的fieldType
     * @param tableComment             数据库表的注释
     * @param builder                  拼接的mapper.java文件文本
     */
    private static void generateDeleteByIdPhysicalMethod(String lowerCamelCasePrimaryKey, String priFieldType,
                                                         String tableComment, StringBuilder builder) {
        builder.append("\r\n");
        builder.append("    /**").append("\r\n");
        builder.append("     * 根据id物理删除").append(tableComment).append("\r\n");
        builder.append("     *").append("\r\n");
        builder.append("     * @param ").append(lowerCamelCasePrimaryKey).append(" 主键id").append("\r\n");
        builder.append("     */").append("\r\n");
        builder.append("    void deleteByIdPhysical(").append("@Param(\"").append(lowerCamelCasePrimaryKey)
                .append("\") ").append(priFieldType).append(" ").append(lowerCamelCasePrimaryKey).append(");").append("\r\n");
    }

    /**
     * 生成mapper.java的deleteBatchPhysical方法
     *
     * @param lowerCamelCaseTableName 首字母小写的驼峰格式数据库表名
     * @param tableComment            数据库表的注释
     * @param builder                 拼接的mapper.java文件文本
     */
    private static void generateDeleteBatchPhysicalMethod(String lowerCamelCaseTableName, String tableComment,
                                                          StringBuilder builder) {
        builder.append("\r\n");
        builder.append("    /**").append("\r\n");
        builder.append("     * 批量物理删除").append(tableComment).append("\r\n");
        builder.append("     *").append("\r\n");
        builder.append("     * @param ").append(lowerCamelCaseTableName).append("s 主键id的数组").append("\r\n");
        builder.append("     */").append("\r\n");
        builder.append("    void deleteBatchPhysical(@Param(\"").append(lowerCamelCaseTableName)
                .append("s\") String[] ").append(lowerCamelCaseTableName).append("s);").append("\r\n");
    }

    /**
     * 生成mapper.java的update方法
     *
     * @param upperCamelCaseTableName 首字母大写的驼峰格式po名
     * @param lowerCamelCasePoName    首字母小写的驼峰格式po名
     * @param tableComment            数据库表的注释
     * @param builder                 拼接的mapper.java文件文本
     */
    private static void generateUpdateMethod(String upperCamelCaseTableName, String lowerCamelCasePoName,
                                             String tableComment, StringBuilder builder) {
        builder.append("\r\n");
        builder.append("    /**").append("\r\n");
        builder.append("     * 修改").append(tableComment).append("\r\n");
        builder.append("     *").append("\r\n");
        builder.append("     * @param ").append(lowerCamelCasePoName).append(" ").append(tableComment).append("\r\n");
        builder.append("     */").append("\r\n");
        builder.append("    void update").append(upperCamelCaseTableName).append("(").append(upperCamelCaseTableName)
                .append("PO ").append(lowerCamelCasePoName).append(");").append("\r\n");
    }

    /**
     * 生成mapper.java的updateBatch方法
     *
     * @param upperCamelCaseTableName 首字母大写的驼峰格式po名
     * @param lowerCamelCasePoName    首字母小写的驼峰格式po名
     * @param tableComment            数据库表的注释
     * @param builder                 拼接的mapper.java文件文本
     */
    private static void generateUpdateBatchMethod(String upperCamelCaseTableName, String lowerCamelCasePoName,
                                                  String tableComment, StringBuilder builder) {
        builder.append("\r\n");
        builder.append("    /**").append("\r\n");
        builder.append("     * 批量修改").append(tableComment).append("\r\n");
        builder.append("     *").append("\r\n");
        builder.append("     * @param ").append(lowerCamelCasePoName).append("List ").append(tableComment)
                .append("List").append("\r\n");
        builder.append("     */").append("\r\n");
        builder.append("    void update").append(upperCamelCaseTableName).append("Batch(").append("@Param(\"")
                .append(lowerCamelCasePoName).append("List\") ").append("List<").append(upperCamelCaseTableName)
                .append("PO> ").append(lowerCamelCasePoName).append("List);").append("\r\n");
    }

    /**
     * 生成mapper.java的getPo方法
     *
     * @param upperCamelCaseTableName  首字母大写的驼峰格式数据库表名
     * @param lowerCamelCasePrimaryKey 首字母小写的驼峰格式主键
     * @param priFieldType             主键的fieldType
     * @param tableComment             数据库表的注释
     * @param builder                  拼接的mapper.java文件文本
     */
    private static void generateGetPoMethod(String upperCamelCaseTableName, String lowerCamelCasePrimaryKey,
                                            String priFieldType, String tableComment, StringBuilder builder) {
        builder.append("\r\n");
        builder.append("    /**").append("\r\n");
        builder.append("     * 根据id查询").append(tableComment).append("\r\n");
        builder.append("     *").append("\r\n");
        builder.append("     * @param ").append(lowerCamelCasePrimaryKey).append(" 主键id").append("\r\n");
        builder.append("     * @return ").append(tableComment).append("\r\n");
        builder.append("     */").append("\r\n");
        builder.append("    ").append(upperCamelCaseTableName).append("PO get").append(upperCamelCaseTableName)
                .append("(@Param(\"").append(lowerCamelCasePrimaryKey).append("\") ").append(priFieldType).append(" ")
                .append(lowerCamelCasePrimaryKey).append(");").append("\r\n");
    }

    /**
     * 生成mapper.java的listPos方法
     *
     * @param upperCamelCaseTableName 首字母大写的驼峰格式数据库表名
     * @param tableComment            数据库表的注释
     * @param builder                 拼接的mapper.java文件文本
     */
    private static void generateListPosMethod(String upperCamelCaseTableName, String tableComment, StringBuilder builder) {
        builder.append("\r\n");
        builder.append("    /**").append("\r\n");
        builder.append("     * 条件查询").append(tableComment).append("\r\n");
        builder.append("     *").append("\r\n");
        builder.append("     * @param whereConditions 查询条件").append("\r\n");
        builder.append("     * @return ").append(tableComment).append("list").append("\r\n");
        builder.append("     */").append("\r\n");
        builder.append("    List<").append(upperCamelCaseTableName).append("PO> list").append(upperCamelCaseTableName)
                .append("s(WhereConditions whereConditions);").append("\r\n");
    }

    /**
     * 生成mapper.java的listAllPos方法
     *
     * @param upperCamelCaseTableName 首字母大写的驼峰格式数据库表名
     * @param tableComment            数据库表的注释
     * @param builder                 拼接的mapper.java文件文本
     */
    private static void generateListAllPosMethod(String upperCamelCaseTableName, String tableComment, StringBuilder builder) {
        builder.append("\r\n");
        builder.append("    /**").append("\r\n");
        builder.append("     * 查询所有的").append(tableComment).append("\r\n");
        builder.append("     *").append("\r\n");
        builder.append("     * @return ").append(tableComment).append("list").append("\r\n");
        builder.append("     */").append("\r\n");
        builder.append("    List<").append(upperCamelCaseTableName).append("PO> listAll").append(upperCamelCaseTableName)
                .append("s();").append("\r\n");
    }

    /**
     * 生成mapper.java的count方法
     *
     * @param upperCamelCaseTableName 首字母大写的驼峰格式数据库表名
     * @param tableComment            数据库表的注释
     * @param builder                 拼接的mapper.java文件文本
     */
    private static void generateCountMethod(String upperCamelCaseTableName, String tableComment, StringBuilder builder) {
        builder.append("\r\n");
        builder.append("    /**").append("\r\n");
        builder.append("     * 条件查询").append(tableComment).append("条数").append("\r\n");
        builder.append("     *").append("\r\n");
        builder.append("     * @param whereConditions 查询条件").append("\r\n");
        builder.append("     * @return 条数").append("\r\n");
        builder.append("     */").append("\r\n");
        builder.append("    Integer count").append(upperCamelCaseTableName)
                .append("s(WhereConditions whereConditions);").append("\r\n");
    }

    /**
     * 生成mapper.java的尾部
     *
     * @param builder 拼接的mapper.java文件文本
     */
    private static void generateFooter(StringBuilder builder) {
        builder.append("\r\n");
        builder.append("    // 以上代码由代码生成器自动生成，原则上不允许任何修改, 如需自定义，请在下面补充").append("\r\n");
        builder.append("}").append("\r\n");
    }
}
