package com.test.mark.zhang.test.other.project.cascade.session;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author by mark
 * @Classname Phone
 * @Description TODO
 * @Date 2021/7/14 1:27 下午
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = {PhoneValidator.class})
public @interface Phone {

    String pattern() default "";
    String message() default "手机格式不正确";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payLoad() default {};
}
