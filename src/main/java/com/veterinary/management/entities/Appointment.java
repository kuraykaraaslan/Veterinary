/*
 * This class is used to create an appointment object
 * Attributes:
 * id: Long - the id of the appointment
 * date: LocalDate - the date of the appointment
 * time: LocalTime - the time of the appointment
 * vetenerian: Vetenerian - the vetenerian that will take care of the animal
 * animal: Animal - the animal that will be taken care of
 * customer: Customer - the customer that owns the animal
 * description: String - the description of the appointment
 * status: String - the status of the appointment
 * price: Double - the price of the appointment
 */

package com.veterinary.management.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalTime;

import java.util.List;

@Table(name = "appointments")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private LocalDate date;

    @Column
    private LocalTime time;

    @ManyToOne
    @JsonIgnore
    private Veterinarian veterinarian;

    @ManyToOne
    @JsonIgnore
    private Animal animal;

    @Column
    private String description;

    @Column
    private String status;
    @Column
    private Double price;

    @OneToMany
    private List<Vaccination> vaccinations;

    public Customer getCustomer() {
        return animal.getCustomer();
    }

}