/*
 * This interface is used to perform CRUD operations on the Customer entity
 * It extends the JpaRepository interface
 * 
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

package com.veterinary.management.repositories;

import com.veterinary.management.entities.Customer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findCustomerByName(String name);

    Customer findCustomerById(Long id);

    Customer findCustomerByEmail(String email);

    Customer findCustomerByPhoneNumber(String phoneNumber);

    List<Customer> findCustomersByName(String name);

}

