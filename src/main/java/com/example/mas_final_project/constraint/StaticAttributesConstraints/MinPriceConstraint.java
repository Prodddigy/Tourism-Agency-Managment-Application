package com.example.mas_final_project.constraint.StaticAttributesConstraints;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = StaticMinPriceConstraint.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MinPriceConstraint {

    String message() default "price must be higher or equal to the minPrice" +
            "\nor minPrice has not been set";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
