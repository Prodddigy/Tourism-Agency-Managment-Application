package com.example.mas_final_project.constraint;

import com.example.mas_final_project.model.Reservation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ReservationDateSwappedNotAllowed implements ConstraintValidator<ReservationDatesSwapped, Reservation> {
    @Override
    public void initialize(ReservationDatesSwapped constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Reservation value, ConstraintValidatorContext context) {
        return value.getDateFrom().isBefore(value.getDateTo())|| value.getDateFrom().equals(value.getDateTo());
    }
}
