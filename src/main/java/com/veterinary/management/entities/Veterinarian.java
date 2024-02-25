/*
* This class is used to represent the
* Veterinarian entity.
* Attributes:
* id: Long - the id of the Veterinarian
* name: String - the name of the Veterinarian
* surname: String - the surname of the Veterinarian
* dateOfBirth: LocalDate - the date of birth of the vet
* phoneNumber: String
* email: String
* address: String
*/

package com.veterinary.management.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "vetenerians")
@Getter
@Setter
@NoArgsConstructor
public class Veterinarian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String email;
    private String address;

    @OneToMany
    @JsonIgnore
    private List<Appointment> appointments;

    @OneToMany
    @JsonIgnore
    private List<WorkingDay> workingDays;

    /*
     * This method is used to check if the vet is working on a specific date
     */
    public boolean isWorking(LocalDate date) {
        for (WorkingDay workingDay : workingDays) {
            LocalDate startDate = workingDay.getStartDate();
            LocalDate endDate = workingDay.getEndDate();

            if (date.isAfter(startDate) && date.isBefore(endDate)) {
                return true;
            }
        }
        return false;
    }
}

