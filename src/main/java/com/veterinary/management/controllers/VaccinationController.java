/*
 * This class is responsible for handling the requests related to the vaccination of the animals.
 * It is a parameterized class as it is annotated with the @RestController annotation.
 * 
 */

package com.veterinary.management.controllers;

import com.veterinary.management.entities.Appointment;
import com.veterinary.management.entities.Vaccination;
import com.veterinary.management.entities.Veterinarian;
import com.veterinary.management.services.VaccinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.List;

@RestController
@RequestMapping(path = "api/vaccinations")
public class VaccinationController {

    private final VaccinationService vaccinationService;

    @Autowired
    public VaccinationController(VaccinationService vaccinationService) {
        this.vaccinationService = vaccinationService;
    }

    /*
     * This method is used to get all the vaccinations
     */
    @GetMapping
    public ResponseEntity<?> getVaccinations() {
        try {
            List<Vaccination> vaccinations = vaccinationService.getVaccinations();
            return new ResponseEntity<>(vaccinations, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * This method is used to add a new vaccination
     */
    @PostMapping
    public ResponseEntity<?> addNewVaccination(@RequestBody Vaccination vaccination) {
        try {
            vaccinationService.addNewVaccination(vaccination);
            return new ResponseEntity<>(vaccination, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * This method is used to delete a vaccination
     */
    @DeleteMapping(path = "{vaccinationId}")
    public ResponseEntity<?> deleteVaccination(@PathVariable("vaccinationId") Long vaccinationId) {
        try {
            vaccinationService.deleteVaccination(vaccinationId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * This method is used to update a vaccination
     */
    @PutMapping(path = "{vaccinationId}")
    public ResponseEntity<?> updateVaccination(@PathVariable("vaccinationId") Long vaccinationId, @RequestBody Vaccination vaccination) {
        try {
            vaccinationService.updateVaccination(vaccination);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * This method is used to get a vaccination by its id
     */
    @GetMapping(path = "{vaccinationId}")
    public ResponseEntity<?> getVaccinationById(@PathVariable("vaccinationId") Long vaccinationId) {
        try {
            Vaccination vaccination = vaccinationService.getVaccinationById(vaccinationId);
            return new ResponseEntity<>(vaccination, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * This method is used to get the animals that have been vaccinated with a specific vaccination
     */
    @GetMapping(path = "{vaccinationId}/animals")
    public ResponseEntity<?> getAnimalsVaccinatedWithVaccination(@PathVariable("vaccinationId") Long vaccinationId) {
        try {
            //Check if the vaccination exists
            Vaccination vaccination = vaccinationService.getVaccinationById(vaccinationId);
            if (vaccination == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(vaccination.getAnimal(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * This method is used to get the appointment that have been made for a specific vaccination
     */
    @GetMapping(path = "{vaccinationId}/appointment")
    public ResponseEntity<?> getAppointmentsForVaccination(@PathVariable("vaccinationId") Long vaccinationId) {
        try {
            //Check if the vaccination exists
            Vaccination vaccination = vaccinationService.getVaccinationById(vaccinationId);
            if (vaccination == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(vaccination.getAppointment(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * This method is used to get the veterinarian that have administered a specific vaccination
     */

    @GetMapping(path = "{vaccinationId}/veterinarian")
    public ResponseEntity<?> getVeterinarianForVaccination(@PathVariable("vaccinationId") Long vaccinationId) {
        try {
            //Check if the vaccination exists
            Vaccination vaccination = vaccinationService.getVaccinationById(vaccinationId);
            if (vaccination == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            
            //get the appointment for the vaccination
            Appointment appointment = vaccination.getAppointment();
            if (appointment == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            //get the veterinarian for the appointment
            Veterinarian veterinarian = appointment.getVeterinarian();
            if (veterinarian == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(veterinarian, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
