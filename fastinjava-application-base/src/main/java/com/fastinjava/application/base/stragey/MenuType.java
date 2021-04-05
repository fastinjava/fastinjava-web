package com.fastinjava.application.base.stragey;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MenuType {

    String value();

}
