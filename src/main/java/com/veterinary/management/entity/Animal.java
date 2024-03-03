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

package com.veterinary.management.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Table(name = "animal")
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "species")
    private String species;

    @Column(name = "breed")
    private String breed;

    @Column(name = "gender")
    private String gender;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "colour")
    private String colour;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "Customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "animal",  cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Vaccine> vaccines;

    @OneToMany(mappedBy = "animal")
    @JsonIgnore
    List<Appointment> appointments;
}
