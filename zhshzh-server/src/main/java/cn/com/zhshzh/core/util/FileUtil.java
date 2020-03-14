package cn.com.zhshzh.core.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.Base64Utils;

import java.io.*;

/**
 * 操作文件的工具类
 *
 * @author WBT
 * @since 2019/12/15
 */
public class FileUtil {
    private static final Logger logger = LogManager.getLogger(FileUtil.class);

    /**
     * 读取本地的json文件，并转化为java对象
     *
     * @param filePath 文件路径
     * @param clazz    要映射的class
     * @param <T>      泛型
     * @return 映射后的对象
     */
    public static <T> T readLocalJsonFile(String filePath, Class<T> clazz) {
        // 读取项目中resources目录下的配置文件
        ClassPathResource resource = new ClassPathResource(filePath);
        FileReader fileReader = null;
        Reader reader = null;
        try {
            File file = resource.getFile();
            fileReader = new FileReader(file);
            reader = new InputStreamReader(new FileInputStream(file));
            int ch;
            StringBuilder builder = new StringBuilder();
            while ((ch = reader.read()) != -1) {
                builder.append((char) ch);
            }
            return JSONObject.parseObject(builder.toString(), clazz);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
        return null;
    }

    /**
     * 将字符串输出到本地文件
     *
     * @param filePath 文件路径
     * @param fileName 文件名称
     * @param text     字符串文本
     * @throws DailyException 异常
     */
    public static void convertTextToLocalFile(String filePath, String fileName, String text) throws DailyException {
        File directory = new File(filePath);
        boolean isMkdir;
        // 如果目标目录不存在，则新建目录
        if (!directory.exists()) {
            isMkdir = directory.mkdirs();
        } else {
            isMkdir = true;
        }
        if (!isMkdir) {
            throw new DailyException("创建目录:" + filePath + "时发生错误！");
        }

        BufferedOutputStream outputStream = null;
        try {
            File file = new File(filePath + fileName);
            boolean isCreateNewFile;
            // 如果文件不存在则创建文件
            if (!file.exists()) {
                isCreateNewFile = file.createNewFile();
            } else {
                isCreateNewFile = true;
            }
            if (!isCreateNewFile) {
                throw new DailyException("创建文件:" + fileName + "时发生错误！");
            }
            outputStream = new BufferedOutputStream(new FileOutputStream(file));
            // 将字符串写到文件
            outputStream.write(text.getBytes());
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }

    /**
     * 将文件流转为字符串
     *
     * @param inputStream
     * @return
     */
    public static String streamToBase64(InputStream inputStream) throws IOException {
        // 得到图片的二进制数据，以二进制封装得到数据，具有通用性
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        // 每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        // 使用一个输入流从buffer里把数据读取出来
        while ((len = inputStream.read(buffer)) != -1) {
            // 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        // 关闭输入流
        inputStream.close();
        byte[] data = outStream.toByteArray();
        // 对字节数组Base64编码
        return Base64Utils.encodeToString(data);
    }
}
