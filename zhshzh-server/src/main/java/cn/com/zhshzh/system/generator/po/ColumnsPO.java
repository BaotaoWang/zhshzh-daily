package cn.com.zhshzh.system.generator.po;

import lombok.Data;

/**
 * 数据库表的列信息的实体对象
 */
@Data
public class ColumnsPO {
    private String tableCatalog;
    private String tableSchema;
    private String tableName;
    private String columnName;
    private Integer ordinalPosition;
    private String columnDefault;
    private String isNullable;
    private String dataType;
    private Long characterMaximumLength;
    private Long characterOctetLength;
    private Long numericPrecision;
    private Long numericScale;
    private Integer datetimePrecision;
    private String characterSetName;
    private String collationName;
    private String columnType;
    private String columnKey;
    private String extra;
    private String privileges;
    private String columnComment;
    private String generationExpression;
    private Integer srsId;

    public ColumnsPO() {
        super();
    }

}
