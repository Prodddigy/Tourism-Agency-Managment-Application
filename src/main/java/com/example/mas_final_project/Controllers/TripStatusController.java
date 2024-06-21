package com.example.mas_final_project.Controllers;


import com.example.mas_final_project.model.StatusPack.StatusFinished;
import com.example.mas_final_project.model.StatusPack.StatusPrepared;
import com.example.mas_final_project.model.StatusPack.StatusReady;
import com.example.mas_final_project.model.TourismPackage;
import com.example.mas_final_project.services.TripStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TripStatusController {


    @Autowired
    private TripStatusService tss;

    public StatusPrepared changeToPrepared(TourismPackage tp, int min, int max)
    {

        StatusPrepared sp = tss.changeToPrepared(tp,min,max);

        return sp;

    }

    public int deleteStatusInPreparation(TourismPackage tp)
    {
        int result = tss.deleteStatus(tp);
        return result;
    }

    public int changeToStatusCancelled(TourismPackage tp, String description)
    {
        int result = tss.changeToCancelled(tp,description);

        return result;
    }
    public int changeToStatusReady(TourismPackage tp)
    {
        StatusReady sr = tss.changeToReady(tp);

        if(sr == null)
        {
            return 0;
        }
        return 1;
    }

    public int changeToStatusFinished(TourismPackage tp,String descReport)
    {
        StatusFinished sr = tss.changeToFinished(tp,descReport);

        if(sr == null)
        {
            return 0;
        }

        return 1;


    }
}
