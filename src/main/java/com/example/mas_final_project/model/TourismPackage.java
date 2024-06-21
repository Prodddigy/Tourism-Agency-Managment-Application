package com.example.mas_final_project.model;



import com.example.mas_final_project.constraint.DatesSwapped;
import com.example.mas_final_project.constraint.StaticAttributesConstraints.MinPriceConstraint;
import com.example.mas_final_project.constraint.OverlappingConst.CampConstraint;
import com.example.mas_final_project.constraint.OverlappingConst.MountainHikeConstraint;
import com.example.mas_final_project.constraint.OverlappingConst.PilgrimageConstraint;
import com.example.mas_final_project.constraint.StaticAttributesConstraints.PremiumDiscountConstraint;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.Bag;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@DatesSwapped
@PilgrimageConstraint
@MountainHikeConstraint
@CampConstraint
@ToString
@MinPriceConstraint
@PremiumDiscountConstraint
public class TourismPackage {

    public enum Board {
        BB,HB,FB,AI,OV,SC,PP
    }
    public enum TripType
    {
        Camp,Pilgrimage,Mountain_Hike
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message="name is mandatory")
    @Size(min = 4, max = 255)
    @Column(unique = true)
    private String name;

    @NotBlank(message = "description is mandatory")
    @Size(min = 4, max = 255)
    private String description;

    //POTRZEBNA VALIDACJA DAT!!!
    @DateTimeFormat
    @FutureOrPresent
    @NotNull
    private LocalDate dateFrom;

    @DateTimeFormat
    @FutureOrPresent
    @NotNull
    private LocalDate dateTo;

    @Enumerated(EnumType.STRING)
    private Board board;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "transportType", joinColumns = @JoinColumn(name = "tourismPackage_id"))
    @Builder.Default
    @Size(min = 1, max = 2)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<String> transportTypes = new ArrayList<>();



    //STATIC ATTRIBUTE

    private static int minPrice=0;
    private static int premiumDiscount=0;

    public static void setMinPrice(int minPrice) {
        if(minPrice >0) {
            TourismPackage.minPrice = minPrice;
        }
    }
    public static void setPremiumDiscount(int discount) {
        if(discount >0) {
            TourismPackage.premiumDiscount = discount;
        }
    }

    public static int getPremiumDiscount()
    {
        return premiumDiscount;
    }

    public static int getMinPrice()
    {
        return minPrice;
    }

    @Positive
    private double price;

    @ElementCollection(targetClass = TripType.class,fetch = FetchType.EAGER)
    @JoinTable(name="tpTypes",joinColumns = @JoinColumn(name = "tourismPackage_id"))
   // @CollectionTable(name= "tripTypes",joinColumns = @JoinColumn(name ="tourismPackage_id"))
    @Size(min=1)
    @Enumerated(EnumType.STRING)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<TripType> tripTypes ;


    //CAMP
    @Nullable
    private Integer ageFrom;
    @Nullable
    private Integer ageTo;


    //PILGRIMAGE
    @Nullable
    private String religion;
    @Transient
    public Integer getPrayingBeads()
    {
        if(this.getTripTypes().contains(TripType.Pilgrimage))
        {
            Integer beads =0;
            for(Client c : this.getClients())
            {
                if(c.getAge()>=18)
                {
                    beads += 1;
                }
            }
           return beads;
        }else {
            return null;
        }
    }

    //Mountain Hike
    @Nullable
    private String equipmentReq;

    //TOURISMPACKAGE MAY HAVE 1 OR 0 TOURGUIDE
    @ManyToOne
    @JoinColumn(name = "tourGuide_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private TourGuide guides;


    //TOURISMPACKAGE MAY HAVE MANY ACCOMODATION RESERVATIONS
    //ASSOCIATION WITH ATTRIBUTE
    @OneToMany(mappedBy = "tourismPackage",cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    @Bag
    private List<Reservation> reservations = new ArrayList<>();

    //TOURISMPACAKAGE MAY HAVE MANY STEPS COMPOSITION
    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    @OrderBy("step ASC")
    private List<TripStep> steps = new ArrayList<>();


    @ManyToMany(mappedBy = "joinedTrips",fetch = FetchType.LAZY)//zmień
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private Set<Client> clients = new HashSet<>();

    @OneToOne(mappedBy = "owner",cascade = CascadeType.REMOVE,orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    //@Builder.Default
    private TripStatus tripStatus;

    @ManyToMany(mappedBy = "trips",fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private Set<Insurance> insurances = new HashSet<>();

}
