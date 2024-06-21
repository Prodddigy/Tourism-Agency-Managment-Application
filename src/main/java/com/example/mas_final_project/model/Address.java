package com.example.mas_final_project.model;


import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
//@ToString()
public class Address {
// może puki co bez tego?
//@Id
//@GeneratedValue(strategy = GenerationType.AUTO)
//private Long id;


    // @Column(name ="companyName")
    @NotBlank(message = "street is mandatory")
    @Size(min = 4, max = 255)
    private String street;

    @NotBlank(message = "zip is mandatory")
    @Size(min = 6,max = 6)
    private String zip;

    @NotBlank(message = "city is mandatory")
    @Size(min = 4,max = 255)
    private String city;

    @Override
    public String toString()
    {
        return getStreet()+", "+getZip()+", "+getCity();
    }
}
