package com.example.demo.common.validaion.constraint;


import com.example.demo.common.validaion.constraintvalidator.PhoneNumberValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = PhoneNumberValidator. class)// 修改此处即可
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
public @interface PhoneNumber {
    String message() default "不是一个合法的手机号";//错误信息
    Class<?>[] groups() default {};//无关
    Class<? extends Payload>[] payload() default {};//无关
    String country() default "china";// 默认值
}
