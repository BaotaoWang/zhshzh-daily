package cn.com.zhshzh.core.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 分页数据模型
 *
 * @author WBT
 * @since 2020/03/19
 */
@Data
@ApiModel(description = "分页数据模型")
public class PageResult<T> {
    @ApiModelProperty(value = "页码", dataType = "Integer")
    private int pageNum;

    @ApiModelProperty(value = "每页条数", dataType = "Integer")
    private int pageSize;

    @ApiModelProperty(value = "总页数", dataType = "Integer")
    private int pages;

    @ApiModelProperty(value = "数据总数", dataType = "Long")
    private long total;

    @ApiModelProperty(value = "返回数据条数", dataType = "Integer")
    private int size;

    @ApiModelProperty(value = "结果集", dataType = "Object")
    private List<T> rows;

    public PageResult(List<T> rows) {
        this.rows = rows;
    }
}
