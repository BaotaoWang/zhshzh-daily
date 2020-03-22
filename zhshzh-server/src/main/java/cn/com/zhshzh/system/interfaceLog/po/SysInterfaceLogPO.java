package cn.com.zhshzh.system.interfaceLog.po;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * sys_interface_log
 * 系统接口日志PO
 *
 * @author Generator
 * @since 2020/03/22
 */
@Data
public class SysInterfaceLogPO implements Serializable {

    /**
     * interface_log_id
     * 接口日志id
     */
    private Long interfaceLogId;

    /**
     * request_url
     * 请求url
     */
    private String requestUrl;

    /**
     * request_type
     * 请求方式
     */
    private String requestType;

    /**
     * request_data
     * 请求数据
     */
    private String requestData;

    /**
     * response_data
     * 响应数据
     */
    private String responseData;

    /**
     * process_time
     * 请求数据处理时长（ms）
     */
    private Long processTime;

    /**
     * class_name
     * 请求类名
     */
    private String className;

    /**
     * method_name
     * 请求方法名
     */
    private String methodName;

    /**
     * user_id
     * 请求用户id
     */
    private Long userId;

    /**
     * user_name
     * 请求用户账号
     */
    private String userName;

    /**
     * principal
     * 用户权限信息
     */
    private String principal;

    /**
     * client_ip
     * 客户端ip
     */
    private String clientIp;

    /**
     * server_ip
     * 服务端ip
     */
    private String serverIp;

    /**
     * server_port
     * 服务端端口号
     */
    private Integer serverPort;

    /**
     * request_exception
     * 请求异常
     */
    private String requestException;

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
    private String createBy;

    /**
     * update_by
     * 修改人
     */
    private String updateBy;

    /**
     * 无参构造方法
     */
    public SysInterfaceLogPO() {
        super();
    }

    // 以上代码由代码生成器自动生成，原则上不允许任何修改, 如需自定义，请在下面补充
}