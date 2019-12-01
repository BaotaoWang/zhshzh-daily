package cn.com.zhshzh.core.util;

import cn.com.zhshzh.core.constant.HttpResultEnum;
import lombok.Data;

/**
 * 定义返回调用方的 JSON 格式规范
 *
 * @author WBT
 * @since 2019/11/30
 */
@Data
public class JsonResultUtil {
    private int code;
    private String message;
    private Object data;

    /**
     * 定义返回成功消息的json对象（无返回数据）
     */
    public JsonResultUtil() {
        this.code = HttpResultEnum.SUCCESS.getCode();
        this.message = HttpResultEnum.SUCCESS.getMessage();
    }

    /**
     * 自定义返回消息的json对象（无返回数据）
     *
     * @param httpResultEnum 返回消息的枚举
     */
    public JsonResultUtil(HttpResultEnum httpResultEnum) {
        this.code = httpResultEnum.getCode();
        this.message = httpResultEnum.getMessage();
    }

    /**
     * 定义返回成功消息的json对象（有返回数据）
     *
     * @param data 返回的数据
     */
    public JsonResultUtil(Object data) {
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
    public JsonResultUtil(HttpResultEnum httpResultEnum, Object data) {
        this.code = httpResultEnum.getCode();
        this.message = httpResultEnum.getMessage();
        this.data = data;
    }
}
