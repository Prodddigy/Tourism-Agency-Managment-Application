package com.example.mas_final_project.constraint.StaticAttributesConstraints;

import com.example.mas_final_project.model.TourismPackage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StaticPremiumDiscountConstraint implements ConstraintValidator<PremiumDiscountConstraint, TourismPackage> {
    @Override
    public void initialize(PremiumDiscountConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(TourismPackage value, ConstraintValidatorContext context) {
        if(value.getPremiumDiscount() >0)
        {
            return true;
        }
        return false;
    }
}
