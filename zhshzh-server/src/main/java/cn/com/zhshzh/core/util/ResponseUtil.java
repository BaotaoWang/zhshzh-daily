package cn.com.zhshzh.core.util;

import cn.com.zhshzh.core.constant.HttpResultEnum;
import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * response响应消息的工具类
 *
 * @author WBT
 * @since 2019/11/30
 */
public class ResponseUtil {
    private static final Logger logger = LogManager.getLogger(ResponseUtil.class);

    /**
     * 响应成功的消息（无响应数据）
     *
     * @param response 响应消息对象
     */
    public static void writeMessage(HttpServletResponse response) {
        JsonResultUtil jsonResultUtil = new JsonResultUtil();
        ResponseUtil.printWriter(response, jsonResultUtil);
    }

    /**
     * 响应成功的消息（有响应数据）
     *
     * @param response 响应消息的对象
     * @param data     响应数据
     */
    public static void writeMessage(HttpServletResponse response, Object data) {
        JsonResultUtil jsonResultUtil = new JsonResultUtil(data);
        ResponseUtil.printWriter(response, jsonResultUtil);
    }

    /**
     * 响应自定义的消息（无响应数据）
     *
     * @param response       响应消息的对象
     * @param httpResultEnum 响应消息的枚举
     */
    public static void writeMessage(HttpServletResponse response, HttpResultEnum httpResultEnum) {
        JsonResultUtil jsonResultUtil = new JsonResultUtil(httpResultEnum);
        ResponseUtil.printWriter(response, jsonResultUtil);
    }

    /**
     * 响应自定义的消息（有响应数据）
     *
     * @param response       响应消息的对象
     * @param httpResultEnum 响应消息的枚举
     * @param data           响应数据
     */
    public static void writeMessage(HttpServletResponse response, HttpResultEnum httpResultEnum, Object data) {
        JsonResultUtil jsonResultUtil = new JsonResultUtil(httpResultEnum, data);
        ResponseUtil.printWriter(response, jsonResultUtil);
    }

    /**
     * 向response对象中写自定义消息
     *
     * @param response       响应消息的对象
     * @param jsonResultUtil 返回的json数据
     */
    private static void printWriter(HttpServletResponse response, JsonResultUtil jsonResultUtil) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            // 获取字符流
            out = response.getWriter();
            // 向字符流中写数据
            out.write(JSON.toJSONString(jsonResultUtil));
        } catch (IOException e) {
            logger.error(e);
        } finally {
            if (null != out) {
                out.flush();
                out.close();
            }
        }
    }
}
