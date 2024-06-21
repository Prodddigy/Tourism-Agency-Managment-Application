package com.example.mas_final_project.constraint.OverlappingConst;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = MountainHikeMandatoryAttributes.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MountainHikeConstraint {


    String message() default "equipment can be null but can be set only for Mountain Hike and if set\n" +
            "it must not be empty";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
