/*
 * This class is used to create a vaccine object
 * Attributes:
 * id: Long - the id of the vaccine
 * name: String - the name of the vaccine
 * code: String - the code of the vaccine
 *  protectionStartDate: LocalDate - the start date of the protection
 * protectionFinishDate: LocalDate - the finish date of the protection
 * animal: Animal - the animal that will receive the vaccine
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "animal_id")
    private Animal animal;

}
