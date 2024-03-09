/*
 * This class represents the controller for the appointments.
 * It handles the requests for the appointments.
 * 
 * It has the following methods:
 * - getAllAvailableDates: This method handles the request for getting all the available dates.
 * - getAvailableDateById: This method handles the request for getting an available date by its id.
 * - addAvailableDate: This method handles the request for adding a new available date.
 * - updateAvailableDate: This method handles the request for updating an available date.
 * - deleteAvailableDate: This method handles the request for deleting an available date.
 * 
 * It has the following routes:
 * - GET /api/dates
 * - GET /api/dates/{id}
 * - POST /api/dates
 * - PUT /api/dates/{id}
 * - DELETE /api/dates/{id}
 */
package com.veterinary.management.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.veterinary.management.models.AvailableDate;
import com.veterinary.management.requests.AvailableDateRequest;
import com.veterinary.management.services.AvailableDateService;
import java.util.List;

@RestController
@RequestMapping("/api/dates")
@RequiredArgsConstructor
public class AvailableDateController {

    private final AvailableDateService availableDateService;

    /*
     * This method handles the request for getting all the available dates.
     */
    @GetMapping
    public ResponseEntity<List<AvailableDate>> getAllAvailableDates() {
        return ResponseEntity.ok(availableDateService.getAllAvailableDates());
    }

    /*
     * This method handles the request for getting an available date by its id.
     * @param id the id of the available date.
     * @return the available date with the given id.
     */
    @GetMapping("/{id}")
    public ResponseEntity<AvailableDate> getAvailableDateById(@PathVariable Long id) {
        return ResponseEntity.ok(availableDateService.getAvailableDateById(id));
    }

    /*
     * This method handles the request for adding a new available date.
     * @param availableDateRequest the request for adding a new available date.
     * @return the added available date.
     */
    @PostMapping
    public ResponseEntity<AvailableDate> addAvailableDate(@RequestBody AvailableDateRequest availableDateRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(availableDateService.addAvailableDate(availableDateRequest));
    }

    /*
     * This method handles the request for updating an available date.
     * @param id the id of the available date.
     * @param availableDateRequest the request for updating an available date.
     * @return the updated available date.
     */
    @PutMapping("/{id}")
    public ResponseEntity<AvailableDate> updateAvailableDate(@PathVariable Long id, @RequestBody AvailableDateRequest availableDateRequest) {
        return ResponseEntity.ok(availableDateService.updateAvailableDate(id, availableDateRequest));
    }

    /*
     * This method handles the request for deleting an available date.
     * @param id the id of the available date.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvailableDate(@PathVariable Long id) {
        availableDateService.deleteAvailableDate(id);
        return ResponseEntity.noContent().build();
    }
}
