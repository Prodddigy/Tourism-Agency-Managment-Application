package com.example.mas_final_project.repository;


import com.example.mas_final_project.model.Reservation;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<Reservation,Long> {
}
