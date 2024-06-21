package com.example.mas_final_project.constraint;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ReservationDateSwappedNotAllowed.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ReservationDatesSwapped {


    String message() default "dateTo and dateFrom cannot be swapped";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
