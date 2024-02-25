/*
 * This interface is used to perform CRUD operations on the vaccine table in the database
 * It extends the JpaRepository interface
 * The VaccineRepository interface is a parameterized interface as it extends the JpaRepository interface
 *
 * Attributes:
 * id: Long - the id of the vaccine
 * name: String - the name of the vaccine
 * description: String - the description of the vaccine
 * price: Double - the price of the vaccine
 * expirationDate: LocalDate - the expiration date of the vaccine
 * quantity: Integer - the quantity of the vaccine
 * animals: List<Animal> - the animals that have been vaccinated with the vaccine
 * appointments: List<Appointment> - the appointments that have been made for the vaccine
 */

package com.veterinary.management.repositories;

import com.veterinary.management.entities.Vaccination;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccinationRepository extends JpaRepository<Vaccination, Long> {

}
