package com.openclassrooms.chatop.validators.multipart;


import com.nimbusds.jose.Payload;
import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NotEmptyMultipartFileConstraint.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotEmptyMultipartFile {

    String message() default "File is required";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
