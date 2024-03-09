/*
 * This class is the controller for the vaccines.
 * It handles the requests for the vaccines.
 * 
 * It has the following methods:
 * - getAllVaccines: This method handles the request for getting all the vaccines.
 * - getVaccineById: This method handles the request for getting a vaccine by its id.
 * - addVaccine: This method handles the request for adding a new vaccine.
 * - updateVaccine: This method handles the request for updating a vaccine.
 * - deleteVaccine: This method handles the request for deleting a vaccine.
 * - findVaccinesByAnimalIdAndApplicationDateBetween: This method handles the request for getting all the vaccines of an animal between two dates.
 * 
 * It has the following routes:
 * - GET /api/vaccines
 * - GET /api/vaccines/{id}
 * - POST /api/vaccines
 * - PUT /api/vaccines/{id}
 * - DELETE /api/vaccines/{id}
 * - GET /api/vaccines/findVaccinesByAnimalIdAndApplicationDateBetween
 */
package com.veterinary.management.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.veterinary.management.models.Vaccine;
import com.veterinary.management.requests.VaccineRequest;
import com.veterinary.management.services.VaccineService;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
     * This method handles the request for getting all the vaccines of between two dates.
     * Değerlendirme Formu: 23
     */
    @GetMapping("/searchByVaccinationRange")
    public ResponseEntity<List<Vaccine>> findVaccinesByApplicationDateBetween(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {

        List<Vaccine> vaccineListSearchByVaccinationRange = vaccineService.findVaccinesByApplicationDateBetween(startDate, endDate);
        return ResponseEntity.ok().body(vaccineListSearchByVaccinationRange);
    }
}