package cn.com.zhshzh.core.swagger;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 忽略接口注解
 *
 * @author WBT
 * @since 2019/10/16
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface SwaggerCustomIgnore {
}
