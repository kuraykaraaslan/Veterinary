/*
 * This class is responsible for handling the requests related to the working days of the clinic.
 * It is a parameterized class as it is annotated with the @RestController annotation.
 * 
 * Routes:
 * /api/working_days - GET: get all the working days, Returns ResponseEntity<List<WorkingDay>>
 * /api/working_days - POST: add a new working day, Returns ResponseEntity<WorkingDay>
 * /api/working_days/{workingDayId} - DELETE: delete a working day, Returns ResponseEntity<?>
 * /api/working_days/{workingDayId} - PUT: update a working day, Returns ResponseEntity<WorkingDay>
 * /api/working_days/{workingDayId} - GET: get a working day by its id, Returns ResponseEntity<WorkingDay>
 * /api/working_days/{workingDayId}/veterinarian - GET: get the veterinarian of a working day, Returns ResponseEntity<Veterinarian>
 * 
 */

package com.veterinary.management.controllers;

import com.veterinary.management.entities.Veterinarian;
import com.veterinary.management.entities.WorkingDay;
import com.veterinary.management.services.WorkingDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping(path = "api/working_days")
public class WorkingDaysController {

    private final WorkingDayService workingDayService;

    @Autowired
    public WorkingDaysController(WorkingDayService workingDayService) {
        this.workingDayService = workingDayService;
    }

    /*
     * This method is used to get all the working days
     */
    @GetMapping
    public ResponseEntity<?> getWorkingDays() {
        try {
            List<WorkingDay> workingDays = workingDayService.getWorkingDays();
            return new ResponseEntity<>(workingDays, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * This method is used to add a new working day
     */
    @PostMapping
    public ResponseEntity<?> addNewWorkingDay(@RequestBody WorkingDay workingDay) {
        try {
            workingDayService.addNewWorkingDay(workingDay);
            return new ResponseEntity<>(workingDay, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * This method is used to delete a working day
     */
    @DeleteMapping(path = "{workingDayId}")
    public ResponseEntity<?> deleteWorkingDay(@PathVariable("workingDayId") Long workingDayId) {
        try {
            workingDayService.deleteWorkingDay(workingDayId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * This method is used to update a working day
     */
    @PutMapping(path = "{workingDayId}")
    public ResponseEntity<?> updateWorkingDay(@PathVariable("workingDayId") Long workingDayId, @RequestBody WorkingDay workingDay) {
        try {
            workingDayService.updateWorkingDay(workingDayId, workingDay);
            return new ResponseEntity<>(workingDay, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * This method is used to get a working day by its id
     */
    @GetMapping(path = "{workingDayId}")
    public ResponseEntity<?> getWorkingDayById(@PathVariable("workingDayId") Long workingDayId) {
        try {
            WorkingDay workingDay = workingDayService.getWorkingDayById(workingDayId);
            return new ResponseEntity<>(workingDay, HttpStatus.OK);
        } catch (Exception e
        ) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * This method is used to get the veterinarian of a working day
     */
    @GetMapping(path = "{workingDayId}/veterinarian")
    public ResponseEntity<?> getVeterinarianOfWorkingDay(@PathVariable("workingDayId") Long workingDayId) {
        try {
            WorkingDay workingDay = workingDayService.getWorkingDayById(workingDayId);
            Veterinarian veterinarian = workingDay.getVeterinarian();
            return new ResponseEntity<>(veterinarian, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
