
package com.unobank.servicehub.platform.commonlib.validators;

import com.unobank.servicehub.platform.commonlib.annotations.EmailValidation;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author Shreyas Kekre
 */

public class EmailValidator implements ConstraintValidator<EmailValidation, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Pattern pattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        if(Objects.isNull(value) || StringUtils.isEmpty(value)){
            return true;
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

