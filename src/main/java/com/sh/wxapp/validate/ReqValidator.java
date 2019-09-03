package com.sh.wxapp.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * ReqValidator
 *
 * @author xuqie
 * @version 1.0.0
 **/

public class ReqValidator implements ConstraintValidator<ReqValide,Object> {
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return false;
    }
}
