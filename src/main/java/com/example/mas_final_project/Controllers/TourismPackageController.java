package com.example.mas_final_project.Controllers;

import com.example.mas_final_project.model.TourismPackage;
import com.example.mas_final_project.services.TourismPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class TourismPackageController {


    @Autowired
    private TourismPackageService tourismPackageService;


    public TourismPackage getTripByName(String name)
    {
        TourismPackage tp = tourismPackageService.getTripByName(name);

        return tp;
    }

    public List<TourismPackage> getTripsByDates(LocalDate from,LocalDate to)
    {
        List<TourismPackage> trips = tourismPackageService.getTripsByDates(from ,to);

        return trips;
    }
}
