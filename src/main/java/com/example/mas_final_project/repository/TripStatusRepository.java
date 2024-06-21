package com.example.mas_final_project.repository;


import com.example.mas_final_project.model.TripStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface TripStatusRepository extends CrudRepository<TripStatus,Long> {


    /**
     * return a tripStatus object by id of it
     * @param id
     * @return
     */
    Optional<TripStatus> getById(Long id);

}
