/*
 * This class is responsible for handling the requests related to the vaccination of the animals.
 * It is a parameterized class as it is annotated with the @RestController annotation.
 * 
 * Routes:
 * GET /api/vaccines
 * GET /api/vaccines/{id}
 * GET /api/vaccines/searchByVaccinationRange
 * GET /api/vaccines/searchByAnimal
 * POST /api/vaccines
 * PUT /api/vaccines/{id}
 * DELETE /api/vaccines/{id}
 */

package com.veterinary.management.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.veterinary.management.request.VaccineWithoutCustomerRequestDto;
import com.veterinary.management.entity.Vaccine;
import com.veterinary.management.service.VaccineService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/vaccines")
@RequiredArgsConstructor
public class VaccineController {

    private final VaccineService vaccineService;

    @GetMapping
    public ResponseEntity<List<Vaccine>> findAllVaccines(){
        List<Vaccine> vaccineList = vaccineService.findAllVaccines();

        return ResponseEntity.ok().body(vaccineList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vaccine> findVaccineById (@PathVariable Long id){
        Vaccine vaccine = vaccineService.findVaccineById(id);
        if (vaccine != null){
            return ResponseEntity.ok().body(vaccine);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/searchByVaccinationRange")
    public ResponseEntity<List<Vaccine>> findAnimalsByVaccineProtectionFinishDateRange (@RequestParam LocalDate startDate, @RequestParam LocalDate endDate ){
        List<Vaccine> vaccineListSearchByVaccineProtectionFinishDateRange = vaccineService.findAnimalsByVaccineProtectionFinishDateRange(startDate, endDate);
        return ResponseEntity.ok().body(vaccineListSearchByVaccineProtectionFinishDateRange);
    }
    @GetMapping("/searchByAnimal")
    public ResponseEntity<List<Vaccine>> findVaccinesByAnimal (@RequestParam Long id){
        List<Vaccine> vaccineListSearchByAnimal = vaccineService.findVaccinesByAnimal(id);
        return ResponseEntity.ok().body(vaccineListSearchByAnimal);
    }

    @PostMapping
    public ResponseEntity<Vaccine> createVaccine (@RequestBody VaccineWithoutCustomerRequestDto vaccineWithoutCustomerRequestDto){
        Vaccine createdVaccine = vaccineService.createVaccine(vaccineWithoutCustomerRequestDto);
        if (createdVaccine != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(createdVaccine);
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vaccine> updateVaccine (@PathVariable Long id, @RequestBody VaccineWithoutCustomerRequestDto vaccineWithoutCustomerRequestDto){
        Vaccine updatedVaccine = vaccineService.updateVaccine(id,vaccineWithoutCustomerRequestDto);
        if (updatedVaccine != null){
            return ResponseEntity.status(HttpStatus.OK).body(updatedVaccine);
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public String deleteVaccine(@PathVariable Long id){
        return vaccineService.deleteVaccine(id);
    }

}
