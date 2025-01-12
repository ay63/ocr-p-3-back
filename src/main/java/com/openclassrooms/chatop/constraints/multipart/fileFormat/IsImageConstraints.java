package com.openclassrooms.chatop.constraints.multipart.fileFormat;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

public class IsImageConstraints implements ConstraintValidator<IsImage, MultipartFile> {

    private String[] extensions;

    @Override
    public void initialize(IsImage constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);

        this.extensions = constraintAnnotation.extensions();
    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {

        String originalFilename = multipartFile.getOriginalFilename();
        if (originalFilename == null || originalFilename.isEmpty()) {
            return false;
        }

        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
        return Arrays.asList(extensions).contains(fileExtension);
    }
}
