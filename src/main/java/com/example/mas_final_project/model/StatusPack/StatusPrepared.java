package com.example.mas_final_project.model.StatusPack;


import com.example.mas_final_project.model.TripStatus;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class StatusPrepared extends TripStatus {
    @PositiveOrZero
    private int maxParticipants;
    @PositiveOrZero
    private int minParticipants;
}
