/*
 * This class is responsible for handling the requests related to the animals.
 * It is a parameterized class as it is annotated with the @RestController annotation.
 * 
 * Routes:
 * /api/animals - GET: get all the animals, Returns ResponseEntity<List<Animal>>
 * /api/animals - POST: add a new animal, Returns ResponseEntity<Animal>
 * /api/animals/{animalId} - DELETE: delete an animal, Returns ResponseEntity<?>
 * /api/animals/{animalId} - PUT: update an animal, Returns ResponseEntity<Animal>
 * /api/animals/{animalId} - GET: get an animal by its id, Returns ResponseEntity<Animal>
 * /api/animals/{animalId}/appointments - GET: get all the appointments of an animal, Returns ResponseEntity<List<Appointment>>
 * /api/animals/{animalId}/customer - GET: get the customer of an animal, Returns ResponseEntity<Customer>
 * /api/animals/{animalId}/vaccinations - GET: get the vaccinations of an animal, Returns ResponseEntity<List<Vaccination>>
 */


package com.veterinary.management.controllers;

import com.veterinary.management.entities.Animal;
import com.veterinary.management.services.AnimalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping(path = "api/animals")
public class AnimalController {

    private final AnimalService animalService;

    @Autowired
    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    /*
     * This method is used to get all the animals
     */
    @GetMapping
    public ResponseEntity<?> getAnimals() {
        try {
            List<Animal> animals = animalService.getAnimals();
            return new ResponseEntity<>(animals, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * This method is used to add a new animal
     */

    @PostMapping
    public ResponseEntity<?> addNewAnimal(@RequestBody Animal animal) {
        try {
            animalService.addNewAnimal(animal);
            return new ResponseEntity<>(animal, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * This method is used to delete an animal
     */
    @DeleteMapping(path = "{animalId}")
    public ResponseEntity<?> deleteAnimal(@PathVariable("animalId") Long animalId) {
        try {
            animalService.deleteAnimal(animalId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * This method is used to update an animal
     */
    @PutMapping(path = "{animalId}")
    public ResponseEntity<?> updateAnimal(@PathVariable("animalId") Long animalId, @RequestBody Animal animal) {
        try {
            //Check if the animal exists
            Animal animalById = animalService.getAnimalById(animalId);
            if (animalById == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            animalService.updateAnimal(animal);          
            return new ResponseEntity<>(animal, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * This method is used to get an animal by its id
     */
    @GetMapping(path = "{animalId}")
    public ResponseEntity<?> getAnimalById(@PathVariable("animalId") Long animalId) {
        try {
            //Check if the animal exists
            Animal animal = animalService.getAnimalById(animalId);
            if (animal == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(animal, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * This method is used to get all the appointments of an animal
     */
    @GetMapping(path = "{animalId}/appointments")
    public ResponseEntity<?> getAppointmentsOfAnimal(@PathVariable("animalId") Long animalId) {
        try {
            //Check if the animal exists
            Animal animal = animalService.getAnimalById(animalId);
            if (animal == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(animal.getAppointments(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * This method is used to get the customer of an animal
     */
    @GetMapping(path = "{animalId}/customer")
    public ResponseEntity<?> getCustomerOfAnimal(@PathVariable("animalId") Long animalId) {
        try {
            //Check if the animal exists
            Animal animal = animalService.getAnimalById(animalId);
            if (animal == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(animal.getCustomer(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * This method is used to get the vaccinations of an animal
     */
    @GetMapping(path = "{animalId}/vaccinations")
    public ResponseEntity<?> getVaccinationsOfAnimal(@PathVariable("animalId") Long animalId) {
        try {
            //Check if the animal exists
            Animal animal = animalService.getAnimalById(animalId);
            if (animal == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(animal.getVaccinations(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
