package cn.com.zhshzh.system.generator.dao;

import cn.com.zhshzh.system.generator.po.ColumnsPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 数据库表的列信息mapper
 *
 * @author WBT
 * @since 2019/12/15
 */
@Mapper
public interface ColumnsMapper {

    /**
     * 根据数据库名和表名查询该表中所有的列信息
     *
     * @param tableSchema 数据库名
     * @param tableName   表名
     * @return 列信息
     */
    List<ColumnsPO> listAllColumns(@NotBlank @Param("tableSchema") String tableSchema, @NotBlank @Param("tableName") String tableName);
}
