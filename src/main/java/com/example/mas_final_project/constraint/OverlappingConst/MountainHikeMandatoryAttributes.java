package com.example.mas_final_project.constraint.OverlappingConst;


import com.example.mas_final_project.model.TourismPackage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MountainHikeMandatoryAttributes implements ConstraintValidator<MountainHikeConstraint, TourismPackage> {


    @Override
    public void initialize(MountainHikeConstraint constraintAnnotation) {
        //ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(TourismPackage tourismPackage, ConstraintValidatorContext constraintValidatorContext) {
        if(tourismPackage.getTripTypes().contains(TourismPackage.TripType.Mountain_Hike)
                && tourismPackage.getEquipmentReq() != null)
        {

            if(tourismPackage.getEquipmentReq().isEmpty())
            {
                return false;
            }
            else
            {
                return true;
            }

        }
        else
        {
            return true;
        }


    }
}
