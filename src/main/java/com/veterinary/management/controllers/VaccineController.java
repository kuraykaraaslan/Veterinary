package com.veterinary.management.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.veterinary.management.models.Vaccine;
import com.veterinary.management.requests.VaccineRequest;
import com.veterinary.management.services.VaccineService;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/vaccines")
@RequiredArgsConstructor
public class VaccineController {

    private final VaccineService vaccineService;

    /*
     * This method handles the request for getting all the vaccines.
     */
    @GetMapping
    public ResponseEntity<List<Vaccine>> getAllVaccines() {
        return new ResponseEntity<>(vaccineService.getAllVaccines(), HttpStatus.OK);
    }

    /*
     * This method handles the request for getting a vaccine by its id.
     * @param id the id of the vaccine.
     * @return the vaccine with the given id.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Vaccine> getVaccineById(@PathVariable Long id) {
        return new ResponseEntity<>(vaccineService.getVaccineById(id), HttpStatus.OK);
    }

    /*
     * This method handles the request for adding a new vaccine.
     * @param vaccine the request for adding a new vaccine.
     * @return the added vaccine.
     */
    @PostMapping
    public ResponseEntity<Vaccine> addVaccine(@RequestBody VaccineRequest vaccine) {
        return new ResponseEntity<>(vaccineService.addVaccine(vaccine), HttpStatus.CREATED);
    }

    /*
     * This method handles the request for updating a vaccine.
     * @param id the id of the vaccine.
     * @param vaccine the request for updating a vaccine.
     * @return the updated vaccine.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Vaccine> updateVaccine(@PathVariable Long id, @RequestBody VaccineRequest vaccine) {
        return new ResponseEntity<>(vaccineService.updateVaccine(id, vaccine), HttpStatus.OK);
    }

    /*
     * This method handles the request for deleting a vaccine.
     * @param id the id of the vaccine.
     * @return the deleted vaccine.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVaccine(@PathVariable Long id) {
        vaccineService.deleteVaccine(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    
    /*
     * This method handles the request for getting all the vaccines of an animal between two dates.
     */
    @GetMapping("/findVaccinesByAnimalIdAndApplicationDateBetween")
    public ResponseEntity<List<Vaccine>> findVaccinesByAnimalIdAndApplicationDateBetween(@RequestParam Long animalId, @RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return new ResponseEntity<>(vaccineService.findVaccinesByAnimalIdAndApplicationDateBetween(animalId, startDate, endDate), HttpStatus.OK);
    }
}