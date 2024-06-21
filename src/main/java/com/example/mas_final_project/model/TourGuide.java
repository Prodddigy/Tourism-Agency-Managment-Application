package com.example.mas_final_project.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class TourGuide extends Person {


    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "expertTopic", joinColumns = @JoinColumn(name = "tourGuide_id"))
    @Builder.Default
    private List<String> expertTopics = new ArrayList<>();

    @Nullable
    private String experienceLevel;


    //TOUR GUIDE MAY HAVE MANY TRIPS
    @OneToMany(mappedBy = "guides",fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<TourismPackage> trips = new HashSet<>();

    @Override
    public int giveRandomBonus() {
        int result= 10* expertTopics.size();
        if(experienceLevel !=null)
        {
            result +=50;
        }
        return result;
    }
}
