
package com.unobank.servicehub.platform.commonlib.annotations;


import com.unobank.servicehub.platform.commonlib.validators.EmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;


@Target( { FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EmailValidator.class)
public @interface EmailValidation {
    public String message() default "Please provide valid email address";

    public Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default { };
}
