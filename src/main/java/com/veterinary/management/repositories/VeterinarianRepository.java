/*
 * This interface is used to perform CRUD operations on the Veterinarian table in the database
 * It extends the JpaRepository interface
 * 
 * Attributes:
 * id: Long - the id of the Veterinarian
 * name: String - the name of the Veterinarian
 * surname: String - the surname of the Veterinarian
 * dateOfBirth: LocalDate - the date of birth of the vet
 * phoneNumber: String
 * email: String
 * address: String
 */

package com.veterinary.management.repositories;

import com.veterinary.management.entities.Veterinarian;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeterinarianRepository extends JpaRepository<Veterinarian, Long> {

    Veterinarian findVeterinarianByName(String name);

    Veterinarian findVeterinarianById(Long id);

    Veterinarian findVeterinarianByEmail(String email);

    Veterinarian findVeterinarianByPhoneNumber(String phoneNumber);

}

