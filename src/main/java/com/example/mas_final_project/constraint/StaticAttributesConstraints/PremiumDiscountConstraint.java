package com.example.mas_final_project.constraint.StaticAttributesConstraints;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.hibernate.Internal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = StaticPremiumDiscountConstraint.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PremiumDiscountConstraint {

    String message() default "premiumDiscount must be positive and must be set";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
