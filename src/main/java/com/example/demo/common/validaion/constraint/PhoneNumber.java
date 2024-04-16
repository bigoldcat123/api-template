package com.example.demo.common.validaion.constraint;


import com.example.demo.common.validaion.constraintvalidator.CustomValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = CustomValidator. class)
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
public @interface PhoneNumber {
    String message() default "不是一个合法的手机号";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String country() default "china";
}
