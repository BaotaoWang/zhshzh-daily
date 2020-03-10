package cn.com.zhshzh.core.model;

import cn.com.zhshzh.core.constant.HttpResultEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 定义API返回数据规范
 *
 * @author WBT
 * @since 2019/11/30
 */
@Data
@ApiModel(description = "API返回数据模型")
public class HttpResult<T> {

    @ApiModelProperty(value = "响应状态码", dataType = "int")
    private int code;

    @ApiModelProperty(value = "响应消息", dataType = "String")
    private String message;

    @ApiModelProperty(value = "响应数据")
    private T data;

    /**
     * 定义返回成功消息的json对象（无返回数据）
     */
    public HttpResult() {
        this.code = HttpResultEnum.SUCCESS.getCode();
        this.message = HttpResultEnum.SUCCESS.getMessage();
    }

    /**
     * 自定义返回消息的json对象（无返回数据）
     *
     * @param httpResultEnum 返回消息的枚举
     */
    public HttpResult(HttpResultEnum httpResultEnum) {
        this.code = httpResultEnum.getCode();
        this.message = httpResultEnum.getMessage();
    }

    /**
     * 定义返回成功消息的json对象（有返回数据）
     *
     * @param data 返回的数据
     */
    public HttpResult(T data) {
        this.code = HttpResultEnum.SUCCESS.getCode();
        this.message = HttpResultEnum.SUCCESS.getMessage();
        this.data = data;
    }

    /**
     * 自定义返回消息的json对象（有返回数据）
     *
     * @param httpResultEnum 返回消息的枚举
     * @param data           返回的数据
     */
    public HttpResult(HttpResultEnum httpResultEnum, T data) {
        this.code = httpResultEnum.getCode();
        this.message = httpResultEnum.getMessage();
        this.data = data;
    }

    /**
     * 返回成功的信息提示
     * @param <T>
     * @return
     */
    public static <T> HttpResult<T> success() {
        return new HttpResult<>();
    }

    /**
     * 返回成功的请求数据
     * @param data
     * @param <T>
     * @return
     */
    public static <T> HttpResult<T> success(T data) {
        return new HttpResult<>(data);
    }

    /**
     * 返回失败的信息提示
     * @param httpResultEnum
     * @param <T>
     * @return
     */
    public static <T> HttpResult<T> error(HttpResultEnum httpResultEnum) {
        return new HttpResult<>(httpResultEnum);
    }
}
