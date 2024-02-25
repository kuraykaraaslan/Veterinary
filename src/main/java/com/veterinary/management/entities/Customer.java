/*
 * This class is used to create a customer object
 * Attributes:
 * id: Long - the id of the customer
 * name: String - the name of the customer
 * surname: String - the surname of the customer
 * dateOfBirth: LocalDate - the date of birth of the customer
 * phoneNumber: String
 * email: String
 * address: String
 * animals: List<Animal> - the animals that the customer owns
 * appointments: List<Appointment> - the appointments that the customer has
 */

package com.veterinary.management.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
public class Customer {
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
    private List<Animal> animals;

    public List<Appointment> getAppointments() {
        List<Animal> animals = getAnimals();
        List<Appointment> appointments = new ArrayList<>();
        for (Animal animal : animals) {
            appointments.addAll(animal.getAppointments());
        }
        return appointments;
    }
}
        