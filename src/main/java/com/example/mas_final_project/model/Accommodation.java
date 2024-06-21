package com.example.mas_final_project.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Bag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Accommodation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message="name is mandatory")
    @Size(min = 4, max = 255)
    private String name;

  //  @NotBlank(message = "address is mandatory")
    @Embedded
    @NotNull
    private Address address;

    @NotBlank(message = "type is mandatory")
    @Size(min = 4, max = 255)
    private String type;

    @Nullable
    @Size(max=255)
    private String accessibility;

    //ACCOMMODATION MAY HAVE MANY TRIP RESERVATIONS
    @OneToMany(mappedBy = "accommodation", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    @Bag
    private List<Reservation> reservationSet = new ArrayList<>();

}
