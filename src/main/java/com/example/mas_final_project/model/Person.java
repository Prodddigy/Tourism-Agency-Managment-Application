package com.example.mas_final_project.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message="forename is mandatory")
    @Size(min = 4, max = 255)
    private String forename;

    @NotBlank(message="surname is mandatory")
    @Size(min = 4, max = 255)
    private String surname;

  //  @NotBlank(message = "dob is mandatory")
    @DateTimeFormat
    private LocalDate dob;
//
    @Embedded
    private Address address;

    @Transient
    public int getAge()
    {
        return Period.between(dob,LocalDate.now()).getYears();
    }

    public abstract int giveRandomBonus();

}
