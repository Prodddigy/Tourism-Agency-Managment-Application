package com.example.mas_final_project.services;



import com.example.mas_final_project.model.Client;
import com.example.mas_final_project.model.StatusPack.StatusPrepared;
import com.example.mas_final_project.model.TourismPackage;
import com.example.mas_final_project.repository.ClientRepository;
import com.example.mas_final_project.repository.TourismPackageRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class ClientService {

    private final ClientRepository cr;
    private final TourismPackageRepository tpr;


    /**
     * return all trips of specified client
     * @param id id of a selected client
     * @return observable list of trips or null if no trips were found
     */
    public ObservableList<TourismPackage> getClientTrips(Long id)
    {
        List<TourismPackage> trips = cr.getTripsByClientId(id);
        if(trips.isEmpty())
        {
            return null;
        }
        return FXCollections.observableArrayList(trips);
    }

    /**
     * this method return a bonus for the client that can be used for discounts or other bussinesss
     * @param id id of the client
     * @return int that can be money dicount
     */
    public int giveRandomBonus(Long id)
    {
        int bonus=0;
        Optional<Client> client = cr.findById(id);
        if(client.isEmpty())
        {
            return bonus;
        }

        bonus +=client.get().giveRandomBonus();
        bonus += cr.getTripsByClientId(client.get().getId()).size();

        return bonus;
    }
}
