package com.example.mas_final_project.services;



import com.example.mas_final_project.model.Client;
import com.example.mas_final_project.model.TourismPackage;
import com.example.mas_final_project.repository.TourismPackageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TourismPackageService {

    @Autowired
    private final TourismPackageRepository tpr;


    /**
     * return lsit of clients which are enrolled on a specifeid trip
     * @param id if of a selected trip
     * @return null if there are no clients found, or returns list of clients
     */
    public List<Client> getClientsFromTrip(Long id)
    {
        List<Client> clients = tpr.getClientsByTripId(id);
        if(clients.isEmpty())
        {
            return null;
        }

        return clients;
    }

    /**
     * returns trip based on trips name qulified association
     * @param name name of a trip (they are unique)
     * @return null if not found, trip is found
     */
    public TourismPackage getTripByName(String name)
    {
        Optional<TourismPackage> otp = tpr.findTourismPackageByName(name);

        if(otp.isEmpty())
        {
            return null;
        }
        return otp.get();
    }

    /**
     * here we choose period of time upon which there will be returned list of trips
     * happening in that period
     * @param from date from
     * @param to date to
     * @return list of trips based on period
     */
    public List<TourismPackage> getTripsByDates(LocalDate from, LocalDate to)
    {
       List<TourismPackage> trips = tpr.findTourismPackageByDateFromBetweenAndDateToBetween(from,to,from,to);
       if(trips== null || trips.isEmpty())
       {
           return null;
       }
       return trips;
    }
}
