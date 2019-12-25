package cn.com.zhshzh.core.model;

import lombok.Data;

/**
 * 批量逻辑删除的模型对象
 *
 * @author WBT
 * @since 2019.12.25
 */

@Data
public class DeleteBatchLogicalModel {

    /**
     * 修改人
     */
    private Long updateBy;

    /**
     * 要删除数据的id的数组
     */
    private String[] deleteIds;

    public DeleteBatchLogicalModel(Long updateBy, String[] deleteIds) {
        this.updateBy = updateBy;
        this.deleteIds = deleteIds;
    }
}
