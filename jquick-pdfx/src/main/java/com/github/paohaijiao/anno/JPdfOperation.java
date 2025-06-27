package com.github.paohaijiao.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JPdfOperation {

    String name() default "";

    String description() default "";

    boolean requiresSource() default false;

    boolean producesResult() default true;
}
