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

package com.veterinary.management.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "customer")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @Column (name = "name")
    private String name;

    @Column (name = "phone")
    private String phone;

    @Column (name = "email")
    private String email;

    @Column (name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Animal> animals ;

}
