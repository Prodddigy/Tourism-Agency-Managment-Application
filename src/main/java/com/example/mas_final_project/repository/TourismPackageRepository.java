package com.example.mas_final_project.repository;


import com.example.mas_final_project.model.Client;
import com.example.mas_final_project.model.TourismPackage;
import com.example.mas_final_project.model.TripStatus;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TourismPackageRepository extends CrudRepository<TourismPackage,Long> {


    /**
     * return tourism package based on id
     * @param id must not be {@literal null}.
     * @return
     */
    @Query("from TourismPackage as t where t.id = :id")
    public Optional<TourismPackage> findById(@Param("id")Long id);


    /**
     * return list of clients of a specified trip
     * @param id id of a trip
     * @return
     */
    @Query("select clnt from Client clnt join clnt.joinedTrips tp where tp.id = :id")
    public List<Client> getClientsByTripId(@Param("id")Long id);


    /**
     *
     * @param id
     * @return
     */
    @Query("select count(trps) from TourismPackage tp  join tp.clients trps where tp.id = :id ")
    int findClientsByTourismPackage(@Param("id")Long id);



    List<TourismPackage> findAll();

    Optional<TourismPackage> findTourismPackageByName( String name);


    @Query("SELECT count(*) from TourismPackage where guides.id =:id")
    int countTourismPackageByTourGuide(@Param("id")Long id);

    List<TourismPackage> findTourismPackageByDateFromBetweenAndDateToBetween(@FutureOrPresent @NotNull LocalDate dateFrom, @FutureOrPresent @NotNull LocalDate dateFrom2, @FutureOrPresent @NotNull LocalDate dateTo, @FutureOrPresent @NotNull LocalDate dateTo2);

}
