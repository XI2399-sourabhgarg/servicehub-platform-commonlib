
package com.unobank.servicehub.platform.commonlib.validators;

import com.unobank.servicehub.platform.commonlib.annotations.DatePattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateValidator implements ConstraintValidator<DatePattern, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Pattern pattern = Pattern.compile("^(\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2})$");
        if(Objects.isNull(value)){
            return false;
        }else {
            Matcher matcher = pattern.matcher(value);
            try {
                return matcher.matches();
            } catch (Exception e) {
                return false;
            }
        }
    }
}


