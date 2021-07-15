package com.test.mark.zhang.test.other.project.cascade.session;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author by mark
 * @Classname PhoneValidator
 * @Description TODO
 * @Date 2021/7/14 1:33 下午
 */
public class PhoneValidator implements ConstraintValidator<Phone,String> {
    @Override
    public void initialize(Phone constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        //todo
        return false;
    }
}
