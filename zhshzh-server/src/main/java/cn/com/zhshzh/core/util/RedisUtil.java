package cn.com.zhshzh.core.util;

import cn.com.zhshzh.core.constant.RedisKeyConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;

/**
 * redis工具类
 *
 * @author WBT
 * @since 2020/03/10
 */
@Component
public class RedisUtil {
    private static final Logger logger = LogManager.getLogger(RedisUtil.class);
    private static StringRedisTemplate stringRedisTemplate;

    @Autowired
    private void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        RedisUtil.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * 通过在request中获取的用户名来获取redis中存的用户id
     *
     * @param request
     * @return
     */
    public static long getUserInfoId(HttpServletRequest request) {
        // 从request中获取用户的登录名
        String username = request.getRemoteUser();
        if (StringUtils.isEmpty(username)) {
            throw new RuntimeException("请求数据中无用户信息");
        }
        // 根据用户名从redis中获取用户id
        String userIdKey = RedisKeyConstants.USERID_PREFIX + username + RedisKeyConstants.USERID_SUFFIX;
        String userInfoId = stringRedisTemplate.opsForValue().get(userIdKey);
        if (StringUtils.isEmpty(userInfoId)) {
            throw new RuntimeException("redis中无该用户id");
        }
        return Long.parseLong(userInfoId);
    }

    /**
     * 将用户id利用反射set到对象中
     *
     * @param request
     * @param object
     */
    public static void copyUserInfoId(HttpServletRequest request, Object object) {
        long userInfoId = RedisUtil.getUserInfoId(request);
        try {
            Field field = object.getClass().getDeclaredField("userInfoId");
            field.setAccessible(true);
            field.set(object, userInfoId);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
