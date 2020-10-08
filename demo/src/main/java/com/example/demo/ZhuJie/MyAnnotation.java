package com.example.demo.ZhuJie;

import java.lang.annotation.*;

/**
 * Created by 24345 on 2020/8/5.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MyAnnotation {
    String name() default "";
}
