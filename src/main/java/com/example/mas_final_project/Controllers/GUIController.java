package com.example.mas_final_project.Controllers;



import com.example.mas_final_project.model.Client;
import com.example.mas_final_project.model.StatusPack.StatusPrepared;
import com.example.mas_final_project.model.TourismPackage;
import com.example.mas_final_project.services.AddClientToTripService;
import com.example.mas_final_project.services.ClientService;
import com.example.mas_final_project.services.StatusPreparedService;
import com.example.mas_final_project.services.TourismPackageService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GUIController {

    @Autowired
    private AddClientToTripService addService;

    @Autowired
    private TourismPackageService tourismPackageService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private StatusPreparedService statusPreparedService;

    public ObservableList<Client> getAllClients()
    {
        return addService.getClients();
    }

    public ObservableList<TourismPackage> getPreparedTrips()
    {
       ObservableList<TourismPackage> tps =  addService.getPreparedTrips();
         return tps;
    }

    public int addClientToTrip(Long clientID,Long tripID)
    {
        return addService.addClientToTrip(clientID, tripID);
    }


    /**
     * returns a list of clients based on a trip
     * @param id id of a trip
     * @return Observable list of clients or null if no clients enrolled or if the trip doesnt exist
     */
    public ObservableList<Client> getClientsFromTrip(Long id)
    {
        List<Client> cs = tourismPackageService.getClientsFromTrip(id);
        ObservableList<Client> clients =null;
        if(cs !=null) {

            clients = FXCollections.observableArrayList(cs);
            if(clients.isEmpty())
            {
                return null;
            }
        }
        return clients;
    }

    /**
     * return collection of trips based on trip id
     * @param id id of a trip
     * @return return observable list of clients or null otheriwse
     */
    public ObservableList<TourismPackage> getClientTrips(Long id)
    {
        ObservableList<TourismPackage> trips = clientService.getClientTrips(id);

        if(trips == null)
        {
            return null;
        }

        return trips;
    }


    /**
     * return number of max participants based on a trip and its status
     * @param tp trip which status we want to check
     * @return -1 if there is no status or trip is non-existent and so on
     * otherise return number of max participants*/
    public Integer getMaxParticipants(TourismPackage tp)
    {
        StatusPrepared statusP =statusPreparedService.getStatusPrepared(tp);
        if(statusP ==null)
        {
            return -1;
        }
        return statusP.getMaxParticipants();
    }
}
