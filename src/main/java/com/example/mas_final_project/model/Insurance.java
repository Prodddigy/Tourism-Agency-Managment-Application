package com.example.mas_final_project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Insurance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

   // @Column(name ="companyName")
    @NotBlank(message = "companyName is mandatory")
    @Size(min = 4, max = 255)
    private String companyName;

//    @NotBlank(message = "price is mandatory")
    @Min(50)
   // @Column(name="price")
    private double price;

    @NotBlank(message = "description is mandatory")
    @Size(min = 4, max = 255)
 //   @Column(name="description")
    private String description;

    @NotBlank(message = "type is mandatory")
    @Size(min = 4, max = 255)
   // @Column(name="type")
    private String type;

    public void setPrice(double price)
    {
        if(price >this.price)
        {
            this.price = price;
        }
    }

    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable( name ="trips",joinColumns = @JoinColumn(name = "insurance_id"),
            inverseJoinColumns = @JoinColumn(name="tourismpackage_id"))
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Set<TourismPackage> trips = new HashSet<>();


}
