package com.openclassrooms.chatop.constraints.password;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class ValidPasswordConstraint implements ConstraintValidator<ValidPassword, String> {
    @Override
    public void initialize(ValidPassword constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return (s.length() >= 8 &&  s.matches(".*[0-9].*") && s.matches(".*[A-Z].*"));
    }
}
