/*
 * This class is used to store the working days of a vetenerian
 * Attributes:
 * id: Long - the id of the working day
 * vetenerian: Vetenerian - the vetenerian that works on the day
 * day: String - the day of the week
*/

package com.veterinary.management.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;


@Entity
@Table(name = "working_days")
@Getter
@Setter
@NoArgsConstructor
public class WorkingDay {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDate day;
    
    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Veterinarian veterinarian;
}
