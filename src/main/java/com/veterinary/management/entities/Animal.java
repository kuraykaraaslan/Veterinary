/*
* This class is used to represent the Animal entity.
* Attributes:
* id: Long - the id of the animal
* name: String - the name of the animal
* species: String - the species of the animal
* dateOfBirth: LocalDate - the date of birth of the animal
* gender: String
* color: String
* weight: Double
* chipNumber: String - the chip number of the animal
* customer: Customer - the customer that owns the animal
* appointments: List<Appointment> - the appointments that the animal has
* Vaccinations: List<Vaccination> - the vaccinations that the animal has  
*/

package com.veterinary.management.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "animals")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String species;
    private LocalDate dateOfBirth;
    private String gender;
    private String color;
    private Double weight;
    private String chipNumber;

    @ManyToOne
    @JsonIgnore
    private Customer customer;

    @OneToMany
    @JsonIgnore
    private List<Vaccination> vaccinations;

    @OneToMany
    @JsonIgnore
    private List<Appointment> appointments;


}