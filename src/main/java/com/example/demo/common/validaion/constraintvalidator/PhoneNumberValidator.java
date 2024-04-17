package com.example.demo.common.validaion.constraintvalidator;

import com.example.demo.common.validaion.constraint.PhoneNumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber,String> {

    String c;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (c.equals("china")) {
           return value.length() == 11;
        }
        return false;
    }

    @Override
    public void initialize(PhoneNumber constraintAnnotation) {
        String country = constraintAnnotation.country();
        c = country;
    }
}
