package com.example.mas_final_project.constraint.OverlappingConst;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PilgrimageMandatoryAttributes.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PilgrimageConstraint {

    String message() default "relligion can't be null or empty and can be sent only for Pilgrimage";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
