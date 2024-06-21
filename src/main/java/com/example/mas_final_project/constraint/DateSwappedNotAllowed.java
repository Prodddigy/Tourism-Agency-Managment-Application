package com.example.mas_final_project.constraint;


import com.example.mas_final_project.model.TourismPackage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateSwappedNotAllowed implements ConstraintValidator<DatesSwapped, TourismPackage> {


    @Override
    public void initialize(DatesSwapped constraintAnnotation) {

    }

    @Override
    public boolean isValid(TourismPackage tp, ConstraintValidatorContext constraintValidatorContext) {
        return tp.getDateFrom().isBefore(tp.getDateTo())|| tp.getDateFrom().equals(tp.getDateTo());
    }
}
