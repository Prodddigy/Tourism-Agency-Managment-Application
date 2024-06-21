package com.example.mas_final_project.constraint.OverlappingConst;


import com.example.mas_final_project.model.TourismPackage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PilgrimageMandatoryAttributes implements ConstraintValidator<PilgrimageConstraint, TourismPackage> {
    @Override
    public void initialize(PilgrimageConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(TourismPackage tourismPackage, ConstraintValidatorContext constraintValidatorContext) {
        if(tourismPackage.getTripTypes().contains(TourismPackage.TripType.Pilgrimage))
        {
            if( tourismPackage.getReligion() != null && !tourismPackage.getReligion().isEmpty() )
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
