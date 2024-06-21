package com.example.mas_final_project.Controllers;

import com.example.mas_final_project.model.TourGuide;
import com.example.mas_final_project.services.TourGuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TourGuideController {

    @Autowired
    private final TourGuideService tgs;


    public TourGuideController(TourGuideService tgs) {
        this.tgs = tgs;
    }


    public int giveRandomBonus(Long id)
    {
        int bonus = tgs.giveRandomBonus(id);

        return bonus;
    }

    public List<TourGuide> showExperiencedGuides()
    {
        List<TourGuide> guides = tgs.showExperiencedGuides();

        return guides;
    }
}
