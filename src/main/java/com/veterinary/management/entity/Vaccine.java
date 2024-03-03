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

package com.veterinary.management.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Table(name = "vaccine")
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "protection_start_date")
    private LocalDate protectionStartDate;

    @Column(name = "protection_finish_date")
    private LocalDate protectionFinishDate;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "animal_id")
    private Animal animal;


}
