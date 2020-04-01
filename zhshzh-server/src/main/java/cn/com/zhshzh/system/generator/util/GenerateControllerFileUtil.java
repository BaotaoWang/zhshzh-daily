package cn.com.zhshzh.system.generator.util;

import cn.com.zhshzh.core.util.DailyException;
import cn.com.zhshzh.core.util.DateFormatUtil;
import cn.com.zhshzh.core.util.FileUtil;
import cn.com.zhshzh.system.generator.model.GeneratorStringModel;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

/**
 * 生成controller.java文件的工具类
 *
 * @author WBT
 * @since 2020/04/01
 */
public class GenerateControllerFileUtil {

    /**
     * 生成controller.java文件
     *
     * @param generatorStringModel 解析数据库表及字段的模型对象
     * @throws DailyException 异常
     */
    public static void generateControllerFile(GeneratorStringModel generatorStringModel) throws DailyException {
        // dto文件的包名
        String dtoPackageName = generatorStringModel.getDtoPackageName();
        // service文件的包名
        String servicePackageName = generatorStringModel.getServicePackageName();
        // controller文件的包名
        String controllerPackageName = generatorStringModel.getControllerPackageName();
        // controller.java文件的绝对路径
        String controllerFileAbsolutePath = generatorStringModel.getControllerFileAbsolutePath();
        // 数据库表名
        String tableName = generatorStringModel.getTableName();
        // 数据库表的注释
        String tableComment = generatorStringModel.getTableComment();
        // 首字母大写的驼峰格式数据库表名
        String upperCamelCaseTableName = generatorStringModel.getUpperCamelCaseTableName();
        // 首字母小写的驼峰格式数据库表名
        String lowerCamelCaseTableName = generatorStringModel.getLowerCamelCaseTableName();
        // 首字母小写的驼峰格式主键
        String lowerCamelCasePrimaryKey = generatorStringModel.getLowerCamelCasePrimaryKey();
        // controller中的requestMapping
        String requestMapping = generatorStringModel.getRequestMapping();
        if (!StringUtils.isEmpty(requestMapping) && !requestMapping.startsWith("/")) {
            requestMapping = "/" + requestMapping;
        }
        // controller文件名
        String controllerFileName = upperCamelCaseTableName + "Controller.java";

        // 拼接controller.java文件
        StringBuilder builder = new StringBuilder();
        // 生成头部
        generateControllerHeader(controllerPackageName, servicePackageName, dtoPackageName, tableComment, upperCamelCaseTableName,
                lowerCamelCaseTableName, requestMapping, builder);
        // 生成根据id查询方法
        generateGetMethod(upperCamelCaseTableName, lowerCamelCaseTableName, tableComment, builder);
        // 生成分页条件查询方法
        generateListMethod(upperCamelCaseTableName, lowerCamelCaseTableName, tableComment, builder);
        // 生成新增的方法
        generateInsertMethod(upperCamelCaseTableName, lowerCamelCaseTableName, tableComment, builder);
        // 生成更新的方法
        generateUpdateMethod(upperCamelCaseTableName, lowerCamelCaseTableName, tableComment, lowerCamelCasePrimaryKey, builder);
        // 生成逻辑删除的方法
        generateDeleteMethod(upperCamelCaseTableName, lowerCamelCaseTableName, tableComment, lowerCamelCasePrimaryKey, builder);
        // 生成尾部
        generateControllerFooter(builder);

        // 将controller.java的字符串文本写到本地文件
        FileUtil.convertTextToLocalFile(controllerFileAbsolutePath, controllerFileName, builder.toString());
    }

    /**
     * 生成controller.java的头部
     *
     * @param controllerPackageName   controller文件的路径
     * @param servicePackageName      service文件的路径
     * @param dtoPackageName          dto文件类型
     * @param tableComment            数据库表的注释
     * @param upperCamelCaseTableName 首字母大写的驼峰格式数据库表名
     * @param lowerCamelCaseTableName 首字母小写的驼峰格式数据库表名
     * @param requestMapping          controller中的requestMapping
     * @param builder                 拼接的mapper.java文件文本
     */
    private static void generateControllerHeader(String controllerPackageName, String servicePackageName, String dtoPackageName,
                                                 String tableComment, String upperCamelCaseTableName, String lowerCamelCaseTableName,
                                                 String requestMapping, StringBuilder builder) {
        // 当前日期
        LocalDate localDate = LocalDate.now();
        String nowDateStr = DateFormatUtil.getBackslashDateString(localDate);

        // 拼接service.java的头部
        builder.append("package ").append(controllerPackageName).append(";").append("\r\n");
        builder.append("\r\n");
        // 拼接引包
        builder.append("import cn.com.zhshzh.core.model.HttpResult;").append("\r\n");
        builder.append("import cn.com.zhshzh.core.model.PageResult;").append("\r\n");
        builder.append("import cn.com.zhshzh.core.util.RedisUtil;").append("\r\n");
        builder.append("import ").append(dtoPackageName).append(".").append(upperCamelCaseTableName).append("DTO;").append("\r\n");
        builder.append("import ").append(dtoPackageName).append(".").append(upperCamelCaseTableName).append("InDTO;").append("\r\n");
        builder.append("import ").append(dtoPackageName).append(".").append(upperCamelCaseTableName).append("OutDTO;").append("\r\n");
        builder.append("import ").append(servicePackageName).append(".").append(upperCamelCaseTableName).append("Service;").append("\r\n");
        builder.append("import io.swagger.annotations.Api;").append("\r\n");
        builder.append("import io.swagger.annotations.ApiImplicitParam;").append("\r\n");
        builder.append("import io.swagger.annotations.ApiImplicitParams;").append("\r\n");
        builder.append("import io.swagger.annotations.ApiOperation;").append("\r\n");
        builder.append("import org.springframework.beans.factory.annotation.Autowired;").append("\r\n");
        builder.append("import org.springframework.validation.BindingResult;").append("\r\n");
        builder.append("import org.springframework.web.bind.annotation.*;").append("\r\n");
        builder.append("\r\n");
        builder.append("import javax.servlet.http.HttpServletRequest;").append("\r\n");
        builder.append("import javax.validation.Valid;").append("\r\n");
        builder.append("import java.util.List;").append("\r\n");
        builder.append("\r\n");
        // 生成注释
        builder.append("/**").append("\r\n");
        builder.append(" * ").append(tableComment).append("Controller").append("\r\n");
        builder.append(" *").append("\r\n");
        builder.append(" * @author Generator").append("\r\n");
        builder.append(" * @since ").append(nowDateStr).append("\r\n");
        builder.append(" */").append("\r\n");
        builder.append("@Api(tags = \"").append(tableComment).append("API\")").append("\r\n");
        builder.append("@RestController").append("\r\n");
        builder.append("@RequestMapping(\"").append(requestMapping).append("\")").append("\r\n");
        builder.append("public class ").append(upperCamelCaseTableName).append("Controller {").append("\r\n");
        builder.append("    private ").append(upperCamelCaseTableName).append("Service ").append(lowerCamelCaseTableName)
                .append("Service;").append("\r\n");
        builder.append("\r\n");
        builder.append("    @Autowired").append("\r\n");
        builder.append("    void set").append(upperCamelCaseTableName).append("Service(").append(upperCamelCaseTableName)
                .append("Service ").append(lowerCamelCaseTableName).append("Service) {").append("\r\n");
        builder.append("        this.").append(lowerCamelCaseTableName).append("Service = ").append(lowerCamelCaseTableName)
                .append("Service;").append("\r\n");
        builder.append("    }").append("\r\n");
        builder.append("\r\n");
    }

    /**
     * 生成controller的根据id查询的方法
     *
     * @param upperCamelCaseTableName 首字母大写的驼峰格式表名
     * @param lowerCamelCaseTableName 首字母小写的驼峰格式表名
     * @param tableComment            数据库表的注释
     * @param builder                 拼接的controller.java文件文本
     */
    private static void generateGetMethod(String upperCamelCaseTableName, String lowerCamelCaseTableName,
                                          String tableComment, StringBuilder builder) {
        builder.append("    /**").append("\r\n");
        builder.append("     * 根据id查询").append(tableComment).append("\r\n");
        builder.append("     *").append("\r\n");
        builder.append("     * @param id").append("\r\n");
        builder.append("     * @return").append("\r\n");
        builder.append("     */").append("\r\n");
        builder.append("    @GetMapping(\"/{id}\")").append("\r\n");
        builder.append("    @ApiOperation(value = \"根据id查询").append(tableComment).append("\")").append("\r\n");
        builder.append("    @ApiImplicitParams({").append("\r\n");
        builder.append("            @ApiImplicitParam(name = \"id\", value = \"").append(tableComment)
                .append("id\", required = true, paramType = \"path\", dataType = \"Long\", example = \"0\")").append("\r\n");
        builder.append("    })").append("\r\n");
        builder.append("    public HttpResult<").append(upperCamelCaseTableName).append("OutDTO> get")
                .append(upperCamelCaseTableName).append("(@PathVariable(\"id\") long id) {").append("\r\n");
        builder.append("        return ").append(lowerCamelCaseTableName).append("Service.get")
                .append(upperCamelCaseTableName).append("(id);").append("\r\n");
        builder.append("    }").append("\r\n");
        builder.append("\r\n");
    }

    /**
     * 生成controller的条件查询的方法
     *
     * @param upperCamelCaseTableName 首字母大写的驼峰格式表名
     * @param lowerCamelCaseTableName 首字母小写的驼峰格式表名
     * @param tableComment            数据库表的注释
     * @param builder                 拼接的controller.java文件文本
     */
    private static void generateListMethod(String upperCamelCaseTableName, String lowerCamelCaseTableName, String tableComment,
                                           StringBuilder builder) {
        builder.append("    /**").append("\r\n");
        builder.append("     * 条件查询").append(tableComment).append("\r\n");
        builder.append("     *").append("\r\n");
        builder.append("     * @param ").append(lowerCamelCaseTableName).append("DTO").append("\r\n");
        builder.append("     * @return").append("\r\n");
        builder.append("     */").append("\r\n");
        builder.append("    @GetMapping").append("\r\n");
        builder.append("    @ApiOperation(value = \"条件查询").append(tableComment).append("\")").append("\r\n");
        builder.append("    public HttpResult<PageResult<").append(upperCamelCaseTableName).append("OutDTO>> list")
                .append(upperCamelCaseTableName).append("s(").append(upperCamelCaseTableName).append("DTO ")
                .append(lowerCamelCaseTableName).append("DTO) {").append("\r\n");
        builder.append("        return ").append(lowerCamelCaseTableName).append("Service.list").append(upperCamelCaseTableName)
                .append("s(").append(lowerCamelCaseTableName).append("DTO);").append("\r\n");
        builder.append("    }").append("\r\n");
        builder.append("\r\n");
    }

    /**
     * 生成controller的新增的方法
     *
     * @param upperCamelCaseTableName 首字母大写的驼峰格式表名
     * @param lowerCamelCaseTableName 首字母小写的驼峰格式表名
     * @param tableComment            数据库表的注释
     * @param builder                 拼接的controller.java文件文本
     */
    private static void generateInsertMethod(String upperCamelCaseTableName, String lowerCamelCaseTableName, String tableComment,
                                             StringBuilder builder) {
        builder.append("    /**").append("\r\n");
        builder.append("     * 新增").append(tableComment).append("\r\n");
        builder.append("     *").append("\r\n");
        builder.append("     * @param ").append(lowerCamelCaseTableName).append("InDTOList").append("\r\n");
        builder.append("     * @param bindingResult").append("\r\n");
        builder.append("     * @param request").append("\r\n");
        builder.append("     * @return").append("\r\n");
        builder.append("     */").append("\r\n");
        builder.append("    @PostMapping").append("\r\n");
        builder.append("    @ApiOperation(value = \"新增").append(tableComment).append("\")").append("\r\n");
        builder.append("    public HttpResult<?> insert").append(upperCamelCaseTableName).append("(@RequestBody @Valid List<")
                .append(upperCamelCaseTableName).append("InDTO> ").append(lowerCamelCaseTableName)
                .append("InDTOList,").append("\r\n");
        builder.append("                                           BindingResult bindingResult, HttpServletRequest request) {").append("\r\n");
        builder.append("        // 字段校验不成功，直接返回前台错误信息").append("\r\n");
        builder.append("        if (bindingResult.hasErrors()) {").append("\r\n");
        builder.append("            return HttpResult.error(bindingResult.getAllErrors().get(0).getDefaultMessage());").append("\r\n");
        builder.append("        }").append("\r\n");
        builder.append("        return ").append(lowerCamelCaseTableName).append("Service.insert").append(upperCamelCaseTableName)
                .append("(").append(lowerCamelCaseTableName).append("InDTOList, RedisUtil.getUserInfoId(request));").append("\r\n");
        builder.append("    }").append("\r\n");
        builder.append("\r\n");
    }

    /**
     * 生成controller的更新的方法
     *
     * @param upperCamelCaseTableName  首字母大写的驼峰格式表名
     * @param lowerCamelCaseTableName  首字母小写的驼峰格式表名
     * @param tableComment             数据库表的注释
     * @param lowerCamelCasePrimaryKey 首字母小写的驼峰格式主键
     * @param builder                  拼接的controller.java文件文本
     */
    private static void generateUpdateMethod(String upperCamelCaseTableName, String lowerCamelCaseTableName, String tableComment,
                                             String lowerCamelCasePrimaryKey, StringBuilder builder) {
        builder.append("    /**").append("\r\n");
        builder.append("     * 更新").append(tableComment).append("\r\n");
        builder.append("     *").append("\r\n");
        builder.append("     * @param ").append(lowerCamelCasePrimaryKey).append("\r\n");
        builder.append("     * @param ").append(lowerCamelCaseTableName).append("InDTO").append("\r\n");
        builder.append("     * @param bindingResult").append("\r\n");
        builder.append("     * @param request").append("\r\n");
        builder.append("     * @return").append("\r\n");
        builder.append("     */").append("\r\n");
        builder.append("    @PutMapping(\"/{id}\")").append("\r\n");
        builder.append("    @ApiOperation(value = \"更新").append(tableComment).append("\")").append("\r\n");
        builder.append("    @ApiImplicitParams({").append("\r\n");
        builder.append("            @ApiImplicitParam(name = \"id\", value = \"").append(tableComment)
                .append("id\", required = true, paramType = \"path\", dataType = \"Long\", example = \"0\")").append("\r\n");
        builder.append("    })").append("\r\n");
        builder.append("    public HttpResult<?> update").append(upperCamelCaseTableName).append("(@PathVariable(\"id\") long ")
                .append(lowerCamelCasePrimaryKey).append(", @RequestBody @Valid ").append(upperCamelCaseTableName).append("InDTO ")
                .append(lowerCamelCaseTableName).append("InDTO,").append("\r\n");
        builder.append("                                          BindingResult bindingResult, HttpServletRequest request) {").append("\r\n");
        builder.append("        // 字段校验不成功，直接返回前台错误信息").append("\r\n");
        builder.append("        if (bindingResult.hasErrors()) {").append("\r\n");
        builder.append("            return HttpResult.error(bindingResult.getAllErrors().get(0).getDefaultMessage());").append("\r\n");
        builder.append("        }").append("\r\n");
        builder.append("        return ").append(lowerCamelCaseTableName).append("Service.update").append(upperCamelCaseTableName)
                .append("(").append(lowerCamelCasePrimaryKey).append(", ").append(lowerCamelCaseTableName)
                .append("InDTO, RedisUtil.getUserInfoId(request));").append("\r\n");
        builder.append("    }").append("\r\n");
        builder.append("\r\n");
    }

    /**
     * 生成controller的删除的方法
     *
     * @param upperCamelCaseTableName  首字母大写的驼峰格式表名
     * @param lowerCamelCaseTableName  首字母小写的驼峰格式表名
     * @param tableComment             数据库表的注释
     * @param lowerCamelCasePrimaryKey 首字母小写的驼峰格式主键
     * @param builder                  拼接的controller.java文件文本
     */
    private static void generateDeleteMethod(String upperCamelCaseTableName, String lowerCamelCaseTableName, String tableComment,
                                             String lowerCamelCasePrimaryKey, StringBuilder builder) {
        builder.append("    /**").append("\r\n");
        builder.append("     * 删除").append(tableComment).append("\r\n");
        builder.append("     *").append("\r\n");
        builder.append("     * @param ").append(lowerCamelCasePrimaryKey).append("\r\n");
        builder.append("     * @param request").append("\r\n");
        builder.append("     * @return").append("\r\n");
        builder.append("     */").append("\r\n");
        builder.append("    @DeleteMapping(\"/{id}\")").append("\r\n");
        builder.append("    @ApiOperation(value = \"删除").append(tableComment).append("\")").append("\r\n");
        builder.append("    @ApiImplicitParams({").append("\r\n");
        builder.append("            @ApiImplicitParam(name = \"id\", value = \"").append(tableComment)
                .append("id\", required = true, paramType = \"path\", dataType = \"Long\", example = \"0\")").append("\r\n");
        builder.append("    })").append("\r\n");
        builder.append("    public HttpResult<?> delete").append(upperCamelCaseTableName).append("(@PathVariable(\"id\") long ")
                .append(lowerCamelCasePrimaryKey).append(", HttpServletRequest request) {").append("\r\n");
        builder.append("        return ").append(lowerCamelCaseTableName).append("Service.delete").append(upperCamelCaseTableName)
                .append("(").append(lowerCamelCasePrimaryKey).append(", RedisUtil.getUserInfoId(request));").append("\r\n");
        builder.append("    }").append("\r\n");
    }

    /**
     * 生成controller.java的尾部
     *
     * @param builder 拼接的controller.java文件文本
     */
    private static void generateControllerFooter(StringBuilder builder) {
        builder.append("}").append("\r\n");
    }
}
