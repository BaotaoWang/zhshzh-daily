package cn.com.zhshzh.system.generator.po;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 数据库表信息的实体对象
 */
@Data
public class TablesPO {
    private String tableCatalog;
    private String tableSchema;
    private String tableName;
    private String tableType;
    private String engine;
    private Integer version;
    private String rowFormat;
    private Long tableRows;
    private Long avgRowLength;
    private Long dataLength;
    private Long maxDataLength;
    private Long indexLength;
    private Long dataFree;
    private Long autoIncrement;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private LocalDateTime checkTime;
    private String tableCollation;
    private Long checksum;
    private String createOptions;
    private String tableComment;

    public TablesPO() {
        super();
    }

}
