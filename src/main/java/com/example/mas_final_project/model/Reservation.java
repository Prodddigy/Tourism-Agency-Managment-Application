package com.example.mas_final_project.model;


import com.example.mas_final_project.constraint.ReservationDatesSwapped;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ReservationDatesSwapped
//@Table(uniqueConstraints = {
//        @UniqueConstraint(columnNames = {"tourismPackage_id","accommodation_id"})
//})
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //TOURISM PACAKGE ONYL 1
    @ManyToOne
    @JoinColumn(name = "tourismPackage_id",nullable = false)
    @NotNull
    private TourismPackage tourismPackage;

    //ACCOMMODATION ONLY 1
    @ManyToOne
    @JoinColumn(name = "accommodation_id",nullable = false)
    @NotNull
    private Accommodation accommodation;

    @DateTimeFormat
    @FutureOrPresent
    @NotNull
    private LocalDate dateFrom;

    @DateTimeFormat
    @FutureOrPresent
    @NotNull
    private LocalDate dateTo;
}
