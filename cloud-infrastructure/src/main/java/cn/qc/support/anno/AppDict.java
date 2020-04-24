package cn.qc.support.anno;

import java.lang.annotation.*;

/**
 * @author linjs
 * @ClassName AppDict.java
 * @Description TODO
 * @createTime 2020年04月20日
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
@Documented
public @interface AppDict {
    String value() default "";
}
