/*
 * This class is used to store the working days of a vetenerian
 * Attributes:
 * id: Long - the id of the working day
 * vetenerian: Vetenerian - the vetenerian that works on the day
 * startDate: LocalDate - the start date of the working day
 * endDate: LocalDate - the end date of the working day
*/

package com.veterinary.management.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "available_date")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AvailableDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "available_date")
    private LocalDate availableDate;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

}
