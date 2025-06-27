package com.github.paohaijiao.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface JPdfToolAnnotation {

    String name() default "";

    String description() default "";

    String version() default "1.0";

}
