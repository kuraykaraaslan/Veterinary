package com.veterinary.management.controllers;


import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.veterinary.management.models.Animal;
import com.veterinary.management.models.Vaccine;
import com.veterinary.management.services.AnimalService;
import com.veterinary.management.requests.AnimalRequest;

@RestController
@RequestMapping("/api/animals")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService animalService;

    /*
     *  This method handles the request for getting all the animals.
     */
    @GetMapping
    public ResponseEntity<List<Animal>> getAllAnimals() {
        return ResponseEntity.ok(animalService.getAllAnimals());
    }

    /*
     *  This method handles the request for getting an animal by its id.
     *  @param id the id of the animal.
     *  @return the animal with the given id.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Animal> getAnimalById(@PathVariable Long id) {
        return ResponseEntity.ok(animalService.getAnimalById(id));
    }

    /*
     *  This method handles the request for adding a new animal.
     *  @param animal the request for adding a new animal.
     *  @return the added animal.
     */
    @PostMapping
    public ResponseEntity<Animal> addAnimal(@RequestBody AnimalRequest animalRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(animalService.addAnimal(animalRequest));
    }

    /*
     *  This method handles the request for updating an animal.
     *  @param id the id of the animal.
     *  @param animalRequest the request for updating an animal.
     *  @return the updated animal.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Animal> updateAnimal(@PathVariable Long id, @RequestBody AnimalRequest animalRequest) {
        return ResponseEntity.ok(animalService.updateAnimal(id, animalRequest));
    }

    /*
     *  This method handles the request for deleting an animal.
     *  @param id the id of the animal.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable Long id) {
        animalService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /*
     * This method handles the request for Fİnding animals by name
     */
    @GetMapping("/findByName")
    public ResponseEntity<List<Animal>> findByName(@RequestParam String name) {
        return ResponseEntity.ok(animalService.findByName(name));
    }

    /*
     * This method handles the request for getting all the vaccines of an animal.
     */
    @GetMapping("/{id}/vaccines")
    public ResponseEntity<List<Vaccine>> getVaccinesByAnimalId(@PathVariable Long id) {
        Animal animal = animalService.getAnimalById(id);

        if (animal == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(animal.getVaccines());
    }
}


