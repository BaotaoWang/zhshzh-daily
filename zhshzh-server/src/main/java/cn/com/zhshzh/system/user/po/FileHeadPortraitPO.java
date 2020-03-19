package cn.com.zhshzh.system.user.po;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * file_head_portrait
 * 用户头像PO
 *
 * @author Generator
 * @since 2020/03/19
 */
@Data
public class FileHeadPortraitPO implements Serializable {

    /**
     * head_portrait_id
     * 头像id
     */
    private Long headPortraitId;

    /**
     * user_info_id
     * 用户id
     */
    private Long userInfoId;

    /**
     * version
     * 版本号
     */
    private Integer version;

    /**
     * image_name
     * 图片名称
     */
    private String imageName;

    /**
     * image_path
     * 图片存放路径
     */
    private String imagePath;

    /**
     * image_size
     * 图片大小（单位：byte）
     */
    private Long imageSize;

    /**
     * image_type
     * 图片格式
     */
    private String imageType;

    /**
     * is_deleted
     * 是否已删除（0：false-未删除； 1：true-已删除）
     */
    private Boolean deleted;

    /**
     * create_time
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * update_time
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * create_by
     * 创建人
     */
    private Long createBy;

    /**
     * update_by
     * 修改人
     */
    private Long updateBy;

    /**
     * 无参构造方法
     */
    public FileHeadPortraitPO() {
        super();
    }

    // 以上代码由代码生成器自动生成，原则上不允许任何修改, 如需自定义，请在下面补充
}