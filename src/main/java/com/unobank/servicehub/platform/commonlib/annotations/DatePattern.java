package com.unobank.servicehub.platform.commonlib.annotations;


import com.unobank.servicehub.platform.commonlib.validators.DateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author tanay sen
 */

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = DateValidator.class)
@Documented
public @interface DatePattern {

    String message() default "{yearMonth.invalid}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
