package com.example.mas_final_project.constraint.OverlappingConst;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CampMandatoryAttributes.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CampConstraint {

    String message() default "ageTo and ageFrom can't be null and ageFrom must be above or equal to 8!!! \n" +
            "and ageTo must be greater or equal to ageFrom and can be set only for Camps";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
