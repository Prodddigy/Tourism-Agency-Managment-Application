package com.example.mas_final_project.constraint.OverlappingConst;


import com.example.mas_final_project.model.TourismPackage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CampMandatoryAttributes implements ConstraintValidator<CampConstraint,TourismPackage> {
    @Override
    public void initialize(CampConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(TourismPackage tourismPackage, ConstraintValidatorContext constraintValidatorContext) {
        if(tourismPackage.getTripTypes().contains(TourismPackage.TripType.Camp))
        {
            if(tourismPackage.getAgeFrom() != null && tourismPackage.getAgeTo() != null &&
            tourismPackage.getAgeFrom()>=8 && tourismPackage.getAgeFrom()<= tourismPackage.getAgeTo())
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return true;
        }
    }
}
