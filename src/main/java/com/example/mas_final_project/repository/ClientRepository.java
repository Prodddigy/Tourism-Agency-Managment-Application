package com.example.mas_final_project.repository;


import com.example.mas_final_project.model.Client;
import com.example.mas_final_project.model.TourismPackage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface ClientRepository extends CrudRepository<Client,Long> {
    List<Client> findAll();

   // List<Client> findClientByJoinedTrips(Set<TourismPackage> joinedTrips);


    @Query("select tp from TourismPackage tp join tp.clients clnt where clnt.id = :id")
    public List<TourismPackage> getTripsByClientId(@Param("id")Long id);

}
