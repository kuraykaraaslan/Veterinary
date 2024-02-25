/*
 * This class is used to perform CRUD operations on the Animal table in the database
 * It extends the JpaRepository interface
 * The AnimalRepository interface is a parameterized interface as it extends the JpaRepository interface
 * The first parameter is the entity type that the repository is managing (Animal)
 * The second parameter is the type of the id field of the entity (Long)
 * The AnimalRepository interface is annotated with the @Repository annotation
 * 
 * Attributes:
 * id: Long - the id of the animal
 * name: String - the name of the animal
 * species: String - the species of the animal
 * dateOfBirth: LocalDate - the date of birth of the animal
 * gender: String
 * color: String
 * weight: Double
 * chipNumber: String - the chip number of the animal
 * customer: Customer - the customer that owns the animal
 */

package com.veterinary.management.repositories;

import com.veterinary.management.entities.Animal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    List<Animal> findByName(String name);

    List<Animal> findBySpecies(String species);

    Animal findByChipNumber(String chipNumber);

    
}