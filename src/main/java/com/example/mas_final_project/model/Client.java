package com.example.mas_final_project.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class Client extends Person{

    private boolean premium;

    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY)//zmień na lazy!!!
    @JoinTable( name ="trip_joined",joinColumns = @JoinColumn(name = "client_id"),
    inverseJoinColumns = @JoinColumn(name="tourismpackage_id"))
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Set<TourismPackage> joinedTrips = new HashSet<>();

    public void addTrip(TourismPackage tp)
    {
        this.joinedTrips.add(tp);

        tp.getClients().add(this);
    }

    @Override
    public int giveRandomBonus() {
        int result = 0;
        if(isPremium())
        {
            result +=100;
        }
        if(Objects.equals(getDob(), LocalDate.now()))
        {
            result += 20;
        }
        return result;
    }
}
