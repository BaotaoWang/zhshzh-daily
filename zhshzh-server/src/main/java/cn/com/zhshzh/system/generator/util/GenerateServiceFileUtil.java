package cn.com.zhshzh.system.generator.util;

import cn.com.zhshzh.core.util.DailyException;
import cn.com.zhshzh.core.util.DateFormatUtil;
import cn.com.zhshzh.core.util.FileUtil;
import cn.com.zhshzh.system.generator.model.GeneratorStringModel;

import java.time.LocalDate;

/**
 * 生成service.java文件的工具类
 *
 * @author WBT
 * @since 2020/03/31
 */
public class GenerateServiceFileUtil {

    /**
     * 生成service.java文件
     *
     * @param generatorStringModel 解析数据库表及字段的模型对象
     * @throws DailyException 异常
     */
    public static void generateServiceFile(GeneratorStringModel generatorStringModel) throws DailyException {
        // po文件的包名
        String poPackageName = generatorStringModel.getPoPackageName();
        // dao文件的包名
        String daoPackageName = generatorStringModel.getDaoPackageName();
        // dto文件的包名
        String dtoPackageName = generatorStringModel.getDtoPackageName();
        // service文件的包名
        String servicePackageName = generatorStringModel.getServicePackageName();
        // serviceImpl文件的包名
        String serviceImplPackageName = generatorStringModel.getServiceImplPackageName();
        // service.java文件的绝对路径
        String serviceFileAbsolutePath = generatorStringModel.getServiceFileAbsolutePath();
        // serviceImpl.java文件的绝对路径
        String serviceImplFileAbsolutePath = generatorStringModel.getServiceImplFileAbsolutePath();
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
        // service文件名
        String serviceFileName = upperCamelCaseTableName + "Service.java";
        // serviceImpl文件名
        String serviceImplFileName = upperCamelCaseTableName + "ServiceImpl.java";

        // 拼接service.java文件
        StringBuilder serviceBuilder = new StringBuilder();
        // 拼接serviceImpl.java文件
        StringBuilder serviceImplBuilder = new StringBuilder();
        // 生成头部
        generateServiceHeader(servicePackageName, dtoPackageName, tableComment, upperCamelCaseTableName, serviceBuilder);
        generateServiceImplHeader(serviceImplPackageName, daoPackageName, dtoPackageName, poPackageName, servicePackageName, tableComment,
                upperCamelCaseTableName, lowerCamelCaseTableName, serviceImplBuilder);
        // 生成根据id查询方法
        generateGetMethod(upperCamelCaseTableName, lowerCamelCaseTableName, lowerCamelCasePrimaryKey, tableComment, serviceBuilder, serviceImplBuilder);
        // 生成分页条件查询方法
        generateListMethod(upperCamelCaseTableName, lowerCamelCaseTableName, tableComment, serviceBuilder, serviceImplBuilder);
        // 生成新增的方法
        generateInsertMethod(upperCamelCaseTableName, lowerCamelCaseTableName, tableComment, serviceBuilder, serviceImplBuilder);
        // 生成更新的方法
        generateUpdateMethod(upperCamelCaseTableName, lowerCamelCaseTableName, tableComment, lowerCamelCasePrimaryKey, serviceBuilder, serviceImplBuilder);
        // 生成逻辑删除的方法
        generateDeleteMethod(upperCamelCaseTableName, lowerCamelCaseTableName, tableComment, lowerCamelCasePrimaryKey, serviceBuilder, serviceImplBuilder);
        // 生成尾部
        generateServiceFooter(serviceBuilder, serviceImplBuilder);

        // 将service.java的字符串文本写到本地文件
        FileUtil.convertTextToLocalFile(serviceFileAbsolutePath, serviceFileName, serviceBuilder.toString());
        // 将serviceImpl.java的字符串文本写到本地文件
        FileUtil.convertTextToLocalFile(serviceImplFileAbsolutePath, serviceImplFileName, serviceImplBuilder.toString());
    }

    /**
     * 生成service.java的头部
     *
     * @param servicePackageName      service文件的路径
     * @param dtoPackageName          dto文件类型
     * @param tableComment            数据库表的注释
     * @param upperCamelCaseTableName 首字母大写的驼峰格式数据库表名
     * @param serviceBuilder          拼接的mapper.java文件文本
     */
    private static void generateServiceHeader(String servicePackageName, String dtoPackageName, String tableComment,
                                              String upperCamelCaseTableName, StringBuilder serviceBuilder) {
        // 当前日期
        LocalDate localDate = LocalDate.now();
        String nowDateStr = DateFormatUtil.getBackslashDateString(localDate);

        // 拼接service.java的头部
        serviceBuilder.append("package ").append(servicePackageName).append(";").append("\r\n");
        serviceBuilder.append("\r\n");
        // 拼接引包
        serviceBuilder.append("import cn.com.zhshzh.core.model.HttpResult;").append("\r\n");
        serviceBuilder.append("import cn.com.zhshzh.core.model.PageResult;").append("\r\n");
        serviceBuilder.append("import ").append(dtoPackageName).append(".").append(upperCamelCaseTableName).append("DTO;").append("\r\n");
        serviceBuilder.append("import ").append(dtoPackageName).append(".").append(upperCamelCaseTableName).append("InDTO;").append("\r\n");
        serviceBuilder.append("import ").append(dtoPackageName).append(".").append(upperCamelCaseTableName).append("OutDTO;").append("\r\n");
        serviceBuilder.append("\r\n");
        serviceBuilder.append("import java.util.List;").append("\r\n");
        serviceBuilder.append("\r\n");
        // 生成注释
        serviceBuilder.append("/**").append("\r\n");
        serviceBuilder.append(" * ").append(tableComment).append("Service").append("\r\n");
        serviceBuilder.append(" *").append("\r\n");
        serviceBuilder.append(" * @author Generator").append("\r\n");
        serviceBuilder.append(" * @since ").append(nowDateStr).append("\r\n");
        serviceBuilder.append(" */").append("\r\n");
        serviceBuilder.append("public interface ").append(upperCamelCaseTableName).append("Service {").append("\r\n");
    }

    /**
     * 生成serviceImpl.java的头部
     *
     * @param serviceImplPackageName
     * @param daoPackageName
     * @param dtoPackageName
     * @param poPackageName
     * @param servicePackageName
     * @param tableComment
     * @param upperCamelCaseTableName
     * @param lowerCamelCaseTableName
     * @param serviceImplBuilder
     */
    private static void generateServiceImplHeader(String serviceImplPackageName, String daoPackageName, String dtoPackageName,
                                                  String poPackageName, String servicePackageName, String tableComment,
                                                  String upperCamelCaseTableName, String lowerCamelCaseTableName, StringBuilder serviceImplBuilder) {
        // 当前日期
        LocalDate localDate = LocalDate.now();
        String nowDateStr = DateFormatUtil.getBackslashDateString(localDate);

        // 拼接serviceImpl.java的头部
        serviceImplBuilder.append("package ").append(serviceImplPackageName).append(";").append("\r\n");
        serviceImplBuilder.append("\r\n");
        // 拼接引包
        serviceImplBuilder.append("import cn.com.zhshzh.core.constant.HttpResultEnum;").append("\r\n");
        serviceImplBuilder.append("import cn.com.zhshzh.core.model.*;").append("\r\n");
        serviceImplBuilder.append("import cn.com.zhshzh.core.util.ConvertBeanUtil;").append("\r\n");
        serviceImplBuilder.append("import ").append(daoPackageName).append(".").append(upperCamelCaseTableName).append("Mapper;").append("\r\n");
        serviceImplBuilder.append("import ").append(dtoPackageName).append(".").append(upperCamelCaseTableName).append("DTO;").append("\r\n");
        serviceImplBuilder.append("import ").append(dtoPackageName).append(".").append(upperCamelCaseTableName).append("InDTO;").append("\r\n");
        serviceImplBuilder.append("import ").append(dtoPackageName).append(".").append(upperCamelCaseTableName).append("OutDTO;").append("\r\n");
        serviceImplBuilder.append("import ").append(poPackageName).append(".").append(upperCamelCaseTableName).append("PO;").append("\r\n");
        serviceImplBuilder.append("import ").append(servicePackageName).append(".").append(upperCamelCaseTableName).append("Service;").append("\r\n");
        serviceImplBuilder.append("import com.github.pagehelper.PageHelper;").append("\r\n");
        serviceImplBuilder.append("import org.apache.logging.log4j.LogManager;").append("\r\n");
        serviceImplBuilder.append("import org.apache.logging.log4j.Logger;").append("\r\n");
        serviceImplBuilder.append("import org.springframework.beans.BeanUtils;").append("\r\n");
        serviceImplBuilder.append("import org.springframework.beans.factory.annotation.Autowired;").append("\r\n");
        serviceImplBuilder.append("import org.springframework.stereotype.Service;").append("\r\n");
        serviceImplBuilder.append("import org.springframework.util.ObjectUtils;").append("\r\n");
        serviceImplBuilder.append("\r\n");
        serviceImplBuilder.append("import java.util.ArrayList;").append("\r\n");
        serviceImplBuilder.append("import java.util.List;").append("\r\n");
        serviceImplBuilder.append("import java.util.stream.Collectors;").append("\r\n");
        serviceImplBuilder.append("\r\n");
        // 生成注释
        serviceImplBuilder.append("/**").append("\r\n");
        serviceImplBuilder.append(" * ").append(tableComment).append("Service").append("\r\n");
        serviceImplBuilder.append(" *").append("\r\n");
        serviceImplBuilder.append(" * @author Generator").append("\r\n");
        serviceImplBuilder.append(" * @since ").append(nowDateStr).append("\r\n");
        serviceImplBuilder.append(" */").append("\r\n");
        serviceImplBuilder.append("@Service").append("\r\n");
        serviceImplBuilder.append("public class ").append(upperCamelCaseTableName).append("ServiceImpl implements ")
                .append(upperCamelCaseTableName).append("Service {").append("\r\n");
        serviceImplBuilder.append("    private static final Logger logger = LogManager.getLogger(")
                .append(upperCamelCaseTableName).append("ServiceImpl.class);").append("\r\n");
        serviceImplBuilder.append("\r\n");
        serviceImplBuilder.append("    private ").append(upperCamelCaseTableName).append("Mapper ").append(lowerCamelCaseTableName)
                .append("Mapper;").append("\r\n");
        serviceImplBuilder.append("\r\n");
        serviceImplBuilder.append("    @Autowired").append("\r\n");
        serviceImplBuilder.append("    void set").append(upperCamelCaseTableName).append("Mapper(")
                .append(upperCamelCaseTableName).append("Mapper ").append(lowerCamelCaseTableName).append("Mapper) {").append("\r\n");
        serviceImplBuilder.append("        this.").append(lowerCamelCaseTableName).append("Mapper = ")
                .append(lowerCamelCaseTableName).append("Mapper;").append("\r\n");
        serviceImplBuilder.append("    }").append("\r\n");
        serviceImplBuilder.append("\r\n");
    }

    /**
     * 生成service的根据id查询的方法
     *
     * @param upperCamelCaseTableName  首字母大写的驼峰格式表名
     * @param lowerCamelCaseTableName  首字母小写的驼峰格式表名
     * @param lowerCamelCasePrimaryKey 首字母小写的驼峰主键
     * @param tableComment             数据库表的注释
     * @param serviceBuilder           拼接的service.java文件文本
     * @param serviceImplBuilder       拼接的serviceImpl.java文件文本
     */
    private static void generateGetMethod(String upperCamelCaseTableName, String lowerCamelCaseTableName, String lowerCamelCasePrimaryKey,
                                          String tableComment, StringBuilder serviceBuilder, StringBuilder serviceImplBuilder) {
        StringBuilder tempBuilder = new StringBuilder();
        tempBuilder.append("    /**").append("\r\n");
        tempBuilder.append("     * 根据id查询").append(tableComment).append("\r\n");
        tempBuilder.append("     *").append("\r\n");
        tempBuilder.append("     * @param ").append(lowerCamelCasePrimaryKey).append("\r\n");
        tempBuilder.append("     * @return").append("\r\n");
        tempBuilder.append("     */").append("\r\n");
        serviceBuilder.append(tempBuilder);
        serviceBuilder.append("   ");
        serviceImplBuilder.append(tempBuilder);
        serviceImplBuilder.append("    @Override").append("\r\n");
        serviceImplBuilder.append("    public");
        tempBuilder = new StringBuilder();
        tempBuilder.append(" HttpResult<").append(upperCamelCaseTableName).append("OutDTO> get")
                .append(upperCamelCaseTableName).append("(long ").append(lowerCamelCasePrimaryKey).append(")");
        serviceBuilder.append(tempBuilder).append(";").append("\r\n");
        serviceBuilder.append("\r\n");
        serviceImplBuilder.append(tempBuilder).append(" {").append("\r\n");
        serviceImplBuilder.append("        ").append(upperCamelCaseTableName).append("PO ").append(lowerCamelCaseTableName)
                .append("PO = ").append(lowerCamelCaseTableName).append("Mapper.get").append(upperCamelCaseTableName)
                .append("(").append(lowerCamelCasePrimaryKey).append(");").append("\r\n");
        serviceImplBuilder.append("        // 如果查询结果为空，直接返回").append("\r\n");
        serviceImplBuilder.append("        if (ObjectUtils.isEmpty(").append(lowerCamelCaseTableName).append("PO)) {").append("\r\n");
        serviceImplBuilder.append("            return HttpResult.error(HttpResultEnum.EMPTY_DATA);").append("\r\n");
        serviceImplBuilder.append("        }").append("\r\n");
        serviceImplBuilder.append("        ").append(upperCamelCaseTableName).append("OutDTO ").append(lowerCamelCaseTableName)
                .append("OutDTO = new ").append(upperCamelCaseTableName).append("OutDTO();").append("\r\n");
        serviceImplBuilder.append("        // po对象转为dto对象").append("\r\n");
        serviceImplBuilder.append("        BeanUtils.copyProperties(").append(lowerCamelCaseTableName).append("PO, ")
                .append(lowerCamelCaseTableName).append("OutDTO);").append("\r\n");
        serviceImplBuilder.append("        return HttpResult.success(").append(lowerCamelCaseTableName).append("OutDTO);").append("\r\n");
        serviceImplBuilder.append("    }").append("\r\n");
        serviceImplBuilder.append("\r\n");
    }

    /**
     * 生成service的条件查询的方法
     *
     * @param upperCamelCaseTableName 首字母大写的驼峰格式表名
     * @param lowerCamelCaseTableName 首字母小写的驼峰格式表名
     * @param tableComment            数据库表的注释
     * @param serviceBuilder          拼接的service.java文件文本
     * @param serviceImplBuilder      拼接的serviceImpl.java文件文本
     */
    private static void generateListMethod(String upperCamelCaseTableName, String lowerCamelCaseTableName, String tableComment,
                                           StringBuilder serviceBuilder, StringBuilder serviceImplBuilder) {
        StringBuilder tempBuilder = new StringBuilder();
        tempBuilder.append("    /**").append("\r\n");
        tempBuilder.append("     * 条件查询").append(tableComment).append("\r\n");
        tempBuilder.append("     *").append("\r\n");
        tempBuilder.append("     * @param ").append(lowerCamelCaseTableName).append("DTO").append("\r\n");
        tempBuilder.append("     * @return").append("\r\n");
        tempBuilder.append("     */").append("\r\n");
        serviceBuilder.append(tempBuilder);
        serviceBuilder.append("   ");
        serviceImplBuilder.append(tempBuilder);
        serviceImplBuilder.append("    @Override").append("\r\n");
        serviceImplBuilder.append("    public");
        tempBuilder = new StringBuilder();
        tempBuilder.append(" HttpResult<PageResult<").append(upperCamelCaseTableName).append("OutDTO>> list").append(upperCamelCaseTableName)
                .append("s(").append(upperCamelCaseTableName).append("DTO ").append(lowerCamelCaseTableName).append("DTO)");
        serviceBuilder.append(tempBuilder).append(";").append("\r\n");
        serviceBuilder.append("\r\n");
        serviceImplBuilder.append(tempBuilder).append(" {").append("\r\n");
        serviceImplBuilder.append("        // 拼接查询条件").append("\r\n");
        serviceImplBuilder.append("        List<ConditionModel> conditionModelList = new ArrayList<>();").append("\r\n");
        serviceImplBuilder.append("        ").append("\r\n");
        serviceImplBuilder.append("        // 设置要排序的字段及顺序").append("\r\n");
        serviceImplBuilder.append("        List<OrderByModel> orderByList = new ArrayList<>();").append("\r\n");
        serviceImplBuilder.append("        ").append("\r\n");
        serviceImplBuilder.append("        // 用PageHelper插件进行分页").append("\r\n");
        serviceImplBuilder.append("        PageHelper.startPage(").append(lowerCamelCaseTableName).append("DTO.getPageNum(), ")
                .append(lowerCamelCaseTableName).append("DTO.getPageSize());").append("\r\n");
        serviceImplBuilder.append("        // 分页条件查询").append("\r\n");
        serviceImplBuilder.append("        List<").append(upperCamelCaseTableName).append("PO> ").append(lowerCamelCaseTableName)
                .append("POList = ").append(lowerCamelCaseTableName).append("Mapper.list").append(upperCamelCaseTableName)
                .append("s(new WhereConditions(conditionModelList, orderByList));").append("\r\n");
        serviceImplBuilder.append("        // 将分页数据返回给前台").append("\r\n");
        serviceImplBuilder.append("        return HttpResult.success(ConvertBeanUtil.getPageResult(").append(lowerCamelCaseTableName)
                .append("POList, ").append(upperCamelCaseTableName).append("OutDTO.class));").append("\r\n");
        serviceImplBuilder.append("    }").append("\r\n");
        serviceImplBuilder.append("\r\n");
    }

    /**
     * 生成service的新增的方法
     *
     * @param upperCamelCaseTableName 首字母大写的驼峰格式表名
     * @param lowerCamelCaseTableName 首字母小写的驼峰格式表名
     * @param tableComment            数据库表的注释
     * @param serviceBuilder          拼接的service.java文件文本
     * @param serviceImplBuilder      拼接的serviceImpl.java文件文本
     */
    private static void generateInsertMethod(String upperCamelCaseTableName, String lowerCamelCaseTableName, String tableComment,
                                             StringBuilder serviceBuilder, StringBuilder serviceImplBuilder) {
        StringBuilder tempBuilder = new StringBuilder();
        tempBuilder.append("    /**").append("\r\n");
        tempBuilder.append("     * 新增").append(tableComment).append("\r\n");
        tempBuilder.append("     *").append("\r\n");
        tempBuilder.append("     * @param ").append(lowerCamelCaseTableName).append("InDTOList").append("\r\n");
        tempBuilder.append("     * @param userInfoId").append("\r\n");
        tempBuilder.append("     * @return").append("\r\n");
        tempBuilder.append("     */").append("\r\n");
        serviceBuilder.append(tempBuilder);
        serviceBuilder.append("   ");
        serviceImplBuilder.append(tempBuilder);
        serviceImplBuilder.append("    @Override").append("\r\n");
        serviceImplBuilder.append("    public");
        tempBuilder = new StringBuilder();
        tempBuilder.append(" HttpResult<?> insert").append(upperCamelCaseTableName).append("(List<").append(upperCamelCaseTableName)
                .append("InDTO> ").append(lowerCamelCaseTableName).append("InDTOList, long userInfoId)");
        serviceBuilder.append(tempBuilder).append(";").append("\r\n");
        serviceBuilder.append("\r\n");
        serviceImplBuilder.append(tempBuilder).append(" {").append("\r\n");
        serviceImplBuilder.append("        // 利用lambda表达式将dto转为po").append("\r\n");
        serviceImplBuilder.append("        List<").append(upperCamelCaseTableName).append("PO> ").append(lowerCamelCaseTableName)
                .append("POList = ").append(lowerCamelCaseTableName).append("InDTOList.stream().map(")
                .append(lowerCamelCaseTableName).append("InDTO -> {").append("\r\n");
        serviceImplBuilder.append("            ").append(upperCamelCaseTableName).append("PO ").append(lowerCamelCaseTableName)
                .append("PO = new ").append(upperCamelCaseTableName).append("PO();").append("\r\n");
        serviceImplBuilder.append("            BeanUtils.copyProperties(").append(lowerCamelCaseTableName).append("InDTO, ")
                .append(lowerCamelCaseTableName).append("PO);").append("\r\n");
        serviceImplBuilder.append("            ").append(lowerCamelCaseTableName).append("PO.setCreateBy(userInfoId);").append("\r\n");
        serviceImplBuilder.append("            ").append(lowerCamelCaseTableName).append("PO.setUpdateBy(userInfoId);").append("\r\n");
        serviceImplBuilder.append("            return ").append(lowerCamelCaseTableName).append("PO;").append("\r\n");
        serviceImplBuilder.append("        }).collect(Collectors.toList());").append("\r\n");
        serviceImplBuilder.append("        // 批量新增").append(tableComment).append("\r\n");
        serviceImplBuilder.append("        ").append(lowerCamelCaseTableName).append("Mapper.insert").append(upperCamelCaseTableName)
                .append("Batch(").append(lowerCamelCaseTableName).append("POList);").append("\r\n");
        serviceImplBuilder.append("        return HttpResult.success();").append("\r\n");
        serviceImplBuilder.append("    }").append("\r\n");
        serviceImplBuilder.append("\r\n");
    }

    /**
     * 生成service的更新的方法
     *
     * @param upperCamelCaseTableName  首字母大写的驼峰格式表名
     * @param lowerCamelCaseTableName  首字母小写的驼峰格式表名
     * @param tableComment             数据库表的注释
     * @param lowerCamelCasePrimaryKey 首字母小写的驼峰格式主键
     * @param serviceBuilder           拼接的service.java文件文本
     * @param serviceImplBuilder       拼接的serviceImpl.java文件文本
     */
    private static void generateUpdateMethod(String upperCamelCaseTableName, String lowerCamelCaseTableName, String tableComment,
                                             String lowerCamelCasePrimaryKey, StringBuilder serviceBuilder, StringBuilder serviceImplBuilder) {
        // 首字母大写的驼峰格式主键
        String upperCamelCasePrimaryKey = lowerCamelCasePrimaryKey.substring(0, 1).toUpperCase() + lowerCamelCasePrimaryKey.substring(1);
        String userInfoId = "userInfoId";
        if (userInfoId.equals(lowerCamelCasePrimaryKey)) {
            userInfoId = "updateBy";
        }
        StringBuilder tempBuilder = new StringBuilder();
        tempBuilder.append("    /**").append("\r\n");
        tempBuilder.append("     * 更新").append(tableComment).append("\r\n");
        tempBuilder.append("     *").append("\r\n");
        tempBuilder.append("     * @param ").append(lowerCamelCasePrimaryKey).append("\r\n");
        tempBuilder.append("     * @param ").append(lowerCamelCaseTableName).append("InDTO").append("\r\n");
        tempBuilder.append("     * @param ").append(userInfoId).append("\r\n");
        tempBuilder.append("     * @return").append("\r\n");
        tempBuilder.append("     */").append("\r\n");
        serviceBuilder.append(tempBuilder);
        serviceBuilder.append("   ");
        serviceImplBuilder.append(tempBuilder);
        serviceImplBuilder.append("    @Override").append("\r\n");
        serviceImplBuilder.append("    public");
        tempBuilder = new StringBuilder();
        tempBuilder.append(" HttpResult<?> update").append(upperCamelCaseTableName).append("(long ").append(lowerCamelCasePrimaryKey)
                .append(", ").append(upperCamelCaseTableName).append("InDTO ").append(lowerCamelCaseTableName)
                .append("InDTO, long ").append(userInfoId).append(")");
        serviceBuilder.append(tempBuilder).append(";").append("\r\n");
        serviceBuilder.append("\r\n");
        serviceImplBuilder.append(tempBuilder).append(" {").append("\r\n");
        serviceImplBuilder.append("        // 先根据id查询").append(tableComment).append("\r\n");
        serviceImplBuilder.append("        ").append(upperCamelCaseTableName).append("PO ").append(lowerCamelCaseTableName)
                .append("PO = ").append(lowerCamelCaseTableName).append("Mapper.get").append(upperCamelCaseTableName)
                .append("(").append(lowerCamelCasePrimaryKey).append(");").append("\r\n");
        serviceImplBuilder.append("        // 如果查询结果为空，直接返回").append("\r\n");
        serviceImplBuilder.append("        if (ObjectUtils.isEmpty(").append(lowerCamelCaseTableName).append("PO)) {").append("\r\n");
        serviceImplBuilder.append("            return HttpResult.error(HttpResultEnum.EMPTY_DATA);").append("\r\n");
        serviceImplBuilder.append("        }").append("\r\n");
        serviceImplBuilder.append("        ").append(lowerCamelCaseTableName).append("PO = new ").append(upperCamelCaseTableName)
                .append("PO();").append("\r\n");
        serviceImplBuilder.append("        // 将dto转为po").append("\r\n");
        serviceImplBuilder.append("        BeanUtils.copyProperties(").append(lowerCamelCaseTableName).append("InDTO, ")
                .append(lowerCamelCaseTableName).append("PO);").append("\r\n");
        serviceImplBuilder.append("        ").append(lowerCamelCaseTableName).append("PO.set").append(upperCamelCasePrimaryKey)
                .append("(").append(lowerCamelCasePrimaryKey).append(");").append("\r\n");
        serviceImplBuilder.append("        ").append(lowerCamelCaseTableName).append("PO.setUpdateBy(").append(userInfoId)
                .append(");").append("\r\n");
        serviceImplBuilder.append("        // 更新").append(tableComment).append("\r\n");
        serviceImplBuilder.append("        ").append(lowerCamelCaseTableName).append("Mapper.update").append(upperCamelCaseTableName)
                .append("(").append(lowerCamelCaseTableName).append("PO);").append("\r\n");
        serviceImplBuilder.append("        return HttpResult.success();").append("\r\n");
        serviceImplBuilder.append("    }").append("\r\n");
        serviceImplBuilder.append("\r\n");
    }

    /**
     * 生成service的删除的方法
     *
     * @param upperCamelCaseTableName  首字母大写的驼峰格式表名
     * @param lowerCamelCaseTableName  首字母小写的驼峰格式表名
     * @param tableComment             数据库表的注释
     * @param lowerCamelCasePrimaryKey 首字母小写的驼峰格式主键
     * @param serviceBuilder           拼接的service.java文件文本
     * @param serviceImplBuilder       拼接的serviceImpl.java文件文本
     */
    private static void generateDeleteMethod(String upperCamelCaseTableName, String lowerCamelCaseTableName,String tableComment,
                                             String lowerCamelCasePrimaryKey, StringBuilder serviceBuilder, StringBuilder serviceImplBuilder) {
        String userInfoId = "userInfoId";
        if (userInfoId.equals(lowerCamelCasePrimaryKey)) {
            userInfoId = "updateBy";
        }
        StringBuilder tempBuilder = new StringBuilder();
        tempBuilder.append("    /**").append("\r\n");
        tempBuilder.append("     * 删除").append(tableComment).append("\r\n");
        tempBuilder.append("     *").append("\r\n");
        tempBuilder.append("     * @param ").append(lowerCamelCasePrimaryKey).append("\r\n");
        tempBuilder.append("     * @param ").append(userInfoId).append("\r\n");
        tempBuilder.append("     * @return").append("\r\n");
        tempBuilder.append("     */").append("\r\n");
        serviceBuilder.append(tempBuilder);
        serviceBuilder.append("   ");
        serviceImplBuilder.append(tempBuilder);
        serviceImplBuilder.append("    @Override").append("\r\n");
        serviceImplBuilder.append("    public");
        tempBuilder = new StringBuilder();
        tempBuilder.append(" HttpResult<?> delete").append(upperCamelCaseTableName).append("(long ").append(lowerCamelCasePrimaryKey)
                .append(", long ").append(userInfoId).append(")");
        serviceBuilder.append(tempBuilder).append(";").append("\r\n");
        serviceImplBuilder.append(tempBuilder).append(" {").append("\r\n");
        serviceImplBuilder.append("        ").append(lowerCamelCaseTableName).append("Mapper.deleteByIdLogical(")
                .append(lowerCamelCasePrimaryKey).append(", ").append(userInfoId).append(");").append("\r\n");
        serviceImplBuilder.append("        return HttpResult.success();").append("\r\n");
        serviceImplBuilder.append("    }").append("\r\n");
    }

    /**
     * 生成service.java的尾部
     *
     * @param serviceBuilder     拼接的service.java文件文本
     * @param serviceImplBuilder 拼接的serviceImpl.java文件文本
     */
    private static void generateServiceFooter(StringBuilder serviceBuilder, StringBuilder serviceImplBuilder) {
        serviceBuilder.append("}").append("\r\n");
        serviceImplBuilder.append("}").append("\r\n");
    }
}
