/*
 * This class is used to create a vaccine object
 * Attributes:
 * id: Long - the id of the vaccine
 * name: String - the name of the vaccine
 * type: String - the type of the vaccine
 * description: String - the description of the vaccine
 * price: Double - the price of the vaccine
 * expirationDate: LocalDate - the expiration date of the vaccine
 * quantity: Integer - the quantity of the vaccine
 * animals: List<Animal> - the animals that have been vaccinated with the vaccine
 * appointments: List<Appointment> - the appointments that have been made for the vaccine
 */

package com.veterinary.management.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "vaccinations")
@Getter
@Setter
@NoArgsConstructor
public class Vaccination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private String description;
    private Double price;
    private LocalDate expirationDate;
    private Integer quantity;

    @OneToOne
    @JsonIgnore
    private Animal animal;

    @OneToOne
    @JsonIgnore
    private Appointment appointment;

}