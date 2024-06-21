package com.example.mas_final_project.model.StatusPack;


import com.example.mas_final_project.model.TripStatus;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class StatusReady extends TripStatus {

    @Positive
    private double totalGrossIncome;
}
