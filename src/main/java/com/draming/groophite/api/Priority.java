package com.draming.groophite.api;

import com.draming.groophite.modsCompat.Groophite_Expose;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Groophite_Expose
@Retention(RetentionPolicy.SOURCE)
@Target(
        {ElementType.LOCAL_VARIABLE,
                ElementType.FIELD,
                ElementType.CONSTRUCTOR,
                ElementType.METHOD,
                ElementType.PACKAGE,
                ElementType.TYPE
        }
        )
@interface Priority {
    int value() default -1;
}
