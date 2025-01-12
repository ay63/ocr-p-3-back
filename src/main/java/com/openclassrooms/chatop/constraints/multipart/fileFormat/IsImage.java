package com.openclassrooms.chatop.constraints.multipart.fileFormat;


import com.nimbusds.jose.Payload;
import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = IsImageConstraints.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsImage {

    String message() default "Only png, jpg and jpeg are allowed has type file";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] extensions() default {"png", "jpeg", "jpg"};
}
