package com.example.mas_final_project.constraint.StaticAttributesConstraints;

import com.example.mas_final_project.model.TourismPackage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StaticMinPriceConstraint implements ConstraintValidator<MinPriceConstraint, TourismPackage> {


    @Override
    public void initialize(MinPriceConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(TourismPackage value, ConstraintValidatorContext context) {

        if(value.getMinPrice()==0)
        {
            return false;
        }

        if(value.getMinPrice()<= value.getPrice())
        {
            return true;
        }

        return false;
    }
}
