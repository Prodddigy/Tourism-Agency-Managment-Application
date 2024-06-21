package com.example.mas_final_project.services;

import com.example.mas_final_project.model.TourGuide;
import com.example.mas_final_project.repository.TourGuideRepository;
import com.example.mas_final_project.repository.TourismPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TourGuideService {


    @Autowired
    private final TourGuideRepository tgr;

    @Autowired
    private final TourismPackageRepository tpr;

    public TourGuideService(TourGuideRepository tgr, TourismPackageRepository tpr) {
        this.tgr = tgr;
        this.tpr = tpr;
    }


    /**
     * this bonus returns a bonus to payslip for the worker(Tour guide)
     * based on number of trips guiding and other stuff
     * @param id id of the tourguide
     * @return int presumably money
     */
    public int giveRandomBonus(Long id)
    {
        int bonus =0;
        Optional<TourGuide> tg = tgr.findById(id);

        if(tg.isEmpty())
        {
            return 0;
        }
        bonus += tg.get().giveRandomBonus();
        bonus *= tpr.countTourismPackageByTourGuide(id);

        return bonus;
    }

    /**
     * return all guides that have non null experience
     *
     * @return list of experienced guides
     */
    public List<TourGuide> showExperiencedGuides()
    {
        List<TourGuide> guides = tgr.findByExperienceLevel();
        if(guides == null ||guides.isEmpty())
        {
            return null;
        }
        return guides;
    }
}
