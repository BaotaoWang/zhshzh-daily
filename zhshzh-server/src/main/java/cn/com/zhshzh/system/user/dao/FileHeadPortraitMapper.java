package cn.com.zhshzh.system.user.dao;

import cn.com.zhshzh.system.user.po.FileHeadPortraitPO;
import cn.com.zhshzh.core.model.DeleteBatchLogicalModel;
import cn.com.zhshzh.core.model.WhereConditions;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * file_head_portrait
 * 用户头像Mapper
 *
 * @author Generator
 * @since 2020/03/15
 */
@Mapper
public interface FileHeadPortraitMapper {
    /**
     * 新增用户头像
     *
     * @param fileHeadPortraitPO 用户头像
     */
    void insertFileHeadPortrait(FileHeadPortraitPO fileHeadPortraitPO);

    /**
     * 批量新增用户头像
     *
     * @param fileHeadPortraitPOList 用户头像List
     */
    void insertFileHeadPortraitBatch(@Param("fileHeadPortraitPOList") List<FileHeadPortraitPO> fileHeadPortraitPOList);

    /**
     * 根据id逻辑删除用户头像
     *
     * @param headPortraitId 主键id
     * @param updateBy 用户id
     */
    void deleteByIdLogical(@Param("headPortraitId") Long headPortraitId, @Param("updateBy") Long updateBy);

    /**
     * 批量逻辑删除用户头像
     *
     * @param deleteBatchLogicalModel 批量逻辑删除的模型对象
     */
    void deleteBatchLogical(DeleteBatchLogicalModel deleteBatchLogicalModel);

    /**
     * 根据id物理删除用户头像
     *
     * @param headPortraitId 主键id
     */
    void deleteByIdPhysical(@Param("headPortraitId") Long headPortraitId);

    /**
     * 批量物理删除用户头像
     *
     * @param fileHeadPortraits 主键id的数组
     */
    void deleteBatchPhysical(@Param("fileHeadPortraits") String[] fileHeadPortraits);

    /**
     * 修改用户头像
     *
     * @param fileHeadPortraitPO 用户头像
     */
    void updateFileHeadPortrait(FileHeadPortraitPO fileHeadPortraitPO);

    /**
     * 批量修改用户头像
     *
     * @param fileHeadPortraitPOList 用户头像List
     */
    void updateFileHeadPortraitBatch(@Param("fileHeadPortraitPOList") List<FileHeadPortraitPO> fileHeadPortraitPOList);

    /**
     * 根据id查询用户头像
     *
     * @param headPortraitId 主键id
     * @return 用户头像
     */
    FileHeadPortraitPO getFileHeadPortrait(@Param("headPortraitId") Long headPortraitId);

    /**
     * 条件查询用户头像
     *
     * @param whereConditions 查询条件
     * @return 用户头像list
     */
    List<FileHeadPortraitPO> listFileHeadPortraits(WhereConditions whereConditions);

    /**
     * 查询所有的用户头像
     *
     * @return 用户头像list
     */
    List<FileHeadPortraitPO> listAllFileHeadPortraits();

    /**
     * 条件查询用户头像条数
     *
     * @param whereConditions 查询条件
     * @return 条数
     */
    Integer countFileHeadPortraits(WhereConditions whereConditions);

    // 以上代码由代码生成器自动生成，原则上不允许任何修改, 如需自定义，请在下面补充
}
