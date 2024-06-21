package com.example.mas_final_project.repository;

import com.example.mas_final_project.model.TourGuide;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TourGuideRepository extends CrudRepository<TourGuide,Long> {


    @Query("from TourGuide as t left join fetch t.trips where t.id = :id")
    public Optional<TourGuide> findById(@Param("id")Long id);



    @Query("from TourGuide where experienceLevel is not Null")
    List<TourGuide> findByExperienceLevel();
}
