/*
 * This class is used to create an appointment object
 * Attributes:
 * id: Long - the id of the appointment
 * startDate: LocalDate - the start date of the appointment
 * endDate: LocalDate - the end date of the appointment
 * time: LocalTime - the time of the appointment
 * veterinarian: Veterinarian - the veterinarian that will take care of the animal
 * animal: Animal - the animal that will be taken care of
 * customer: Customer - the customer that owns the animal
 * description: String - the description of the appointment
 * status: String - the status of the appointment
 * price: Double - the price of the appointment
 */

package com.veterinary.management.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Table(name = "appointment")
@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "date")
    private LocalDateTime date;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "animal_id")
    private Animal animal;
}
