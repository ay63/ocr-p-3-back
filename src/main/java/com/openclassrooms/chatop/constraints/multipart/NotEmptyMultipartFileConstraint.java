package com.openclassrooms.chatop.validators.multipart;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

public class NotEmptyMultipartFileConstraint implements ConstraintValidator<NotEmptyMultipartFile, MultipartFile> {
    @Override
    public void initialize(NotEmptyMultipartFile constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {
        return multipartFile != null && !multipartFile.isEmpty();
    }
}
