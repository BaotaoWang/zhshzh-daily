package cn.com.zhshzh.system.generator.dao;

import cn.com.zhshzh.system.generator.po.TablesPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 数据库表信息mapper
 *
 * @author WBT
 * @since 2019/12/15
 */
@Mapper
public interface TablesMapper {
    /**
     * 根据数据库名和表名获取表信息
     *
     * @param tableSchema 数据库名
     * @param tableName   表名
     * @return 表信息
     */
    TablesPO getTable(@NotBlank @Param("tableSchema") String tableSchema, @NotBlank @Param("tableName") String tableName);

    /**
     * 根据数据库名查询该数据库下所有的表信息
     *
     * @param tableSchema 数据库名
     * @return 表信息
     */
    List<TablesPO> listAllTables(@NotBlank String tableSchema);
}
