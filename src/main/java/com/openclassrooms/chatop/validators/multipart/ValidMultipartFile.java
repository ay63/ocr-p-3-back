package com.openclassrooms.chatop.validators.multipart;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MultipartValidator.class)
public @interface ValidMultipartFile {
    String message() default "File cant not be empty";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
