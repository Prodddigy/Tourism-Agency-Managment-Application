package com.example.mas_final_project.repository;

import com.example.mas_final_project.model.StatusPack.StatusPrepared;
import com.example.mas_final_project.model.TourismPackage;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StatusPreparedRepository extends CrudRepository<StatusPrepared,Long> {


    Optional<StatusPrepared> findStatusPreparedByOwner(TourismPackage owner);
}
