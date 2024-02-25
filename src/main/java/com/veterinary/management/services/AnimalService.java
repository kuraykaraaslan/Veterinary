/*
 * This class is used to provide the service for the Animal entity
 * It is annotated with the @Service annotation
 * The AnimalService class is a parameterized class as it is annotated with the @Service annotation
 * The AnimalService class is used to provide the service for the Animal entity
 * 
 * Methods:
 * getAnimals: List<Animal> - this method is used to get all the animals
 * addNewAnimal: void - this method is used to add a new animal
 * deleteAnimal: void - this method is used to delete an animal
 * updateAnimal: void - this method is used to update an animal
 * getAnimalById: Animal - this method is used to get an animal by its id
 * getAnimalByName: Animal - this method is used to get an animal by its name
 * getAnimalBySpecies: Animal - this method is used to get an animal by its species
 * getAnimalByChipNumber: Animal - this method is used to get an animal by its chip number
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
package com.veterinary.management.services;

import com.veterinary.management.entities.Animal;
import com.veterinary.management.repositories.AnimalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {
    
    @Autowired
    private AnimalRepository animalRepository;

    public Animal getAnimalById(Long animalId) {
        return animalRepository.findById(animalId).orElse(null);
    }

    public void updateAnimal(Animal animal) {
        animalRepository.save(animal);
    }

    public void deleteAnimal(Long animalId) {
        animalRepository.deleteById(animalId);
    }

    public void addNewAnimal(Animal animal) {
        animalRepository.save(animal);
    }

    public List<Animal> getAnimals() {
        return animalRepository.findAll();
    }
}