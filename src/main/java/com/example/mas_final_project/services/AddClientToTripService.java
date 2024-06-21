package com.example.mas_final_project.services;

import com.example.mas_final_project.model.Client;
import com.example.mas_final_project.model.Person;
import com.example.mas_final_project.model.StatusPack.StatusPrepared;
import com.example.mas_final_project.model.TourismPackage;
import com.example.mas_final_project.repository.ClientRepository;
import com.example.mas_final_project.repository.TourismPackageRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddClientToTripService  {

    @Autowired
    private final TourismPackageRepository tpr;
    @Autowired
    private final ClientRepository cr;

    public AddClientToTripService(TourismPackageRepository tpr, ClientRepository cr) {
        this.tpr = tpr;
        this.cr = cr;
    }

    /**
     * return Observable list of all clients in the DB
     * @return
     */
    public ObservableList<Client> getClients()
    {
      return  FXCollections.observableArrayList(cr.findAll());
    }

    /**
     * return all trips that are of StatusPrepared whcih means that they can add clients
     * @return observable list of trips with StatusPrepared
     */
    public ObservableList<TourismPackage> getPreparedTrips()
    {
        List<TourismPackage> trips = tpr.findAll();
        if(trips.isEmpty())
        {
            return null;
        }
        List<TourismPackage> preparedTrips = new ArrayList<>();

        for(TourismPackage tp : trips)
        {
            if (tp.getTripStatus() instanceof StatusPrepared)
            {
                preparedTrips.add(tp);
            }
        }
//        System.out.println(trips.get(0).getName());
//        System.out.println(preparedTrips.size()+"size prepared");
        return FXCollections.observableArrayList(preparedTrips);
    }

    /**
     * performs a insertion of client into a trip
     * @param clientID desired client's id
     * @param tripID desired trip's id
     * @return 1 if insertion went ok, 0  if either of them were not found in DB
     */
    @Transactional
    public int addClientToTrip(Long clientID, Long tripID)
    {
        Optional<Client> client = cr.findById(clientID);
        Optional<TourismPackage> trip = tpr.findById(tripID);
        if(client.isEmpty() || trip.isEmpty())
        {
            return 0;
        }
        //insert into client_trip where
        client.get().addTrip(trip.get());
        cr.save(client.get());
        tpr.save(trip.get());
        return 1;
    }
}
