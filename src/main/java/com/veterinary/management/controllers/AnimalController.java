/*
 * This class is responsible for handling the requests related to the animals.
 * It is a parameterized class as it is annotated with the @RestController annotation.
 * 
 * Routes:
 * GET /api/animals
 * GET /api/animals/{id}
 * GET /api/animals/searchByName
 * GET /api/animals/searchByCustomer
 * POST /api/animals
 * PUT /api/animals/{id}
 * DELETE /api/animals/{id}
 */


package com.veterinary.management.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.veterinary.management.request.AnimalRequestDto;
import com.veterinary.management.entity.Animal;
import com.veterinary.management.service.AnimalService;

import java.util.List;

@RestController
@RequestMapping("/api/animals")
@RequiredArgsConstructor
public class AnimalController {

    public final AnimalService animalService;

    @GetMapping
    public ResponseEntity<List<Animal>> findAllAnimals(){
        List<Animal> animalList = animalService.findAllAnimals();
        return ResponseEntity.ok().body(animalList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> findAnimalById (@PathVariable Long id){
        Animal animal = animalService.findAnimalById(id);
        if (animal != null){
            return ResponseEntity.ok().body(animal);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/searchByName")
    public ResponseEntity<List<Animal>> findAnimalsByName (@RequestParam String name){
        List<Animal> animalListSearchByName = animalService.findAnimalsByName(name);
        return ResponseEntity.ok().body(animalListSearchByName);
    }

    @GetMapping("/searchByCustomer")
    public ResponseEntity<List<Animal>> findAnimalsByCustomer (@RequestParam Long id){
        List<Animal> animalListSearchByCustomer = animalService.findAnimalsByCustomer(id);
        return ResponseEntity.ok().body(animalListSearchByCustomer);
    }

    @PostMapping
    public ResponseEntity<Animal> createAnimal (@RequestBody AnimalRequestDto animalRequestDto){
        Animal createdAnimal = animalService.createAnimal(animalRequestDto);
        if (createdAnimal != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAnimal);
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Animal> updateAnimal (@PathVariable Long id, @RequestBody AnimalRequestDto animalRequestDto){
        Animal updatedAnimal = animalService.updateAnimal(id,animalRequestDto);
        if (updatedAnimal != null){
            return ResponseEntity.status(HttpStatus.OK).body(updatedAnimal);
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public String deleteAnimal(@PathVariable Long id){
        return animalService.deleteAnimal(id);
    }

}
