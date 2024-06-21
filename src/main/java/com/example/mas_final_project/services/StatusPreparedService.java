package com.example.mas_final_project.services;


import com.example.mas_final_project.model.StatusPack.StatusPrepared;
import com.example.mas_final_project.model.TourismPackage;
import com.example.mas_final_project.repository.StatusPreparedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StatusPreparedService {

    @Autowired
    private StatusPreparedRepository spr;


    /**
     * returns Object of StatusPrepared based on specified trip
     * @param tp chosen trip for which a status is searched
     * @return null if status is not present, or object of StatusPrepared
     */
    public StatusPrepared getStatusPrepared(TourismPackage tp)
    {
        Optional<StatusPrepared> statusPrepared = spr.findStatusPreparedByOwner(tp);

        if(!statusPrepared.isPresent())
        {
            return null;
        }

        return statusPrepared.get();

    }
}
