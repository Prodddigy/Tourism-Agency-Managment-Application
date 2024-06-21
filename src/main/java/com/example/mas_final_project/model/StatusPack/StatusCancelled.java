package com.example.mas_final_project.model.StatusPack;


import com.example.mas_final_project.model.TripStatus;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class StatusCancelled extends TripStatus {

    @Size(max=255)
    private String reasonDesc;
}
