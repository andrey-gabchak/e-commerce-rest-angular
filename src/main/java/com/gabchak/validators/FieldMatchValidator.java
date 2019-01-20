package com.gabchak.validators;

import com.gabchak.validators.annotations.FieldMatch;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

    private String field;
    private String fieldMatch;

    @Override
    public void initialize(FieldMatch fieldMatch) {
        this.field = fieldMatch.field();
        this.fieldMatch = fieldMatch.fieldMatch();
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        BeanWrapperImpl wrapper = new BeanWrapperImpl(obj);
        boolean result = false;
        String field1 = (String) wrapper.getPropertyValue(field);
        String field2 = (String) wrapper.getPropertyValue(fieldMatch);

        if (field1 != null && field2 != null) {
            result = field1.equals(field2);
        }

        return result;
    }
}
