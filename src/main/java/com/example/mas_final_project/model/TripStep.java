package com.example.mas_final_project.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TripStep {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message="name is mandatory")
    @Size(min = 4, max = 255)
    private String name;

    @NotBlank(message = "description is mandatory")
    @Size(min = 4, max = 255)
    private String description;

    @Min(1)
    private int step;


    //TRIPSTEP MAY HAVE ONLY 1 TRIP
    //COMPOSITION
    @ManyToOne(optional = false)
    @JoinColumn(name ="owner_id",nullable = false,updatable = false)
    private TourismPackage owner;
}
