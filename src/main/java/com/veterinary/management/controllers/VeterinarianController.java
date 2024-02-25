/*
 * This class is used to handle the requests for the Veterinarian
 * It is annotated with the @RestController annotation to indicate that it is a controller
 * The VeterinarianController class is a parameterized class as it is annotated with the @RestController annotation
 * 
 * Routes:
 * /api/Veterinarians - GET: get all the Veterinarians, Returns ResponseEntity<List<Veterinarian>>
 * /api/Veterinarians - POST: add a new Veterinarian, Returns ResponseEntity<Veterinarian>
 * /api/Veterinarians/{VeterinarianId} - DELETE: delete a Veterinarian, Returns ResponseEntity<?>
 * /api/Veterinarians/{VeterinarianId} - PUT: update a Veterinarian, Returns ResponseEntity<Veterinarian>
 * /api/Veterinarians/{VeterinarianId} - GET: get a Veterinarian by its id, Returns ResponseEntity<Veterinarian>
 * /api/Veterinarians/{VeterinarianId}/appointments - GET: get all the appointments of a Veterinarian, Returns ResponseEntity<List<Appointment>>
 * /api/Veterinarians/{VeterinarianId}/workingDays - GET: get all the working days of a Veterinarian, Returns ResponseEntity<List<WorkingDay>>
 */

package com.veterinary.management.controllers;

import com.veterinary.management.entities.Appointment;
import com.veterinary.management.entities.Veterinarian;
import com.veterinary.management.entities.WorkingDay;
import com.veterinary.management.services.VeterinarianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping(path = "api/Veterinarians")
public class VeterinarianController {

    private final VeterinarianService VeterinarianService;

    @Autowired
    public VeterinarianController(VeterinarianService VeterinarianService) {
        this.VeterinarianService = VeterinarianService;
    }

    /*
     * This method is used to get all the Veterinarians
     */
    @GetMapping
    public ResponseEntity<?> getVeterinarians() {
        try {
            List<Veterinarian> Veterinarians = VeterinarianService.getVeterinarians();
            return new ResponseEntity<>(Veterinarians, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * This method is used to add a new Veterinarian
     */
    @PostMapping
    public ResponseEntity<?> addNewVeterinarian(@RequestBody Veterinarian Veterinarian) {
        try {
            VeterinarianService.addNewVeterinarian(Veterinarian);
            return new ResponseEntity<>(Veterinarian, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * This method is used to delete a Veterinarian
     */
    @DeleteMapping(path = "{VeterinarianId}")
    public ResponseEntity<?> deleteVeterinarian(@PathVariable("VeterinarianId") Long VeterinarianId) {
        try {
            VeterinarianService.deleteVeterinarian(VeterinarianId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * This method is used to update a Veterinarian
     */
    @PutMapping(path = "{VeterinarianId}")
    public ResponseEntity<?> updateVeterinarian(@PathVariable("VeterinarianId") Long VeterinarianId, @RequestBody Veterinarian Veterinarian) {
        try {
            VeterinarianService.updateVeterinarian(Veterinarian);
            return new ResponseEntity<>(Veterinarian, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * This method is used to get a Veterinarian by its id
     */
    @GetMapping(path = "{VeterinarianId}")
    public ResponseEntity<?> getVeterinarianById(@PathVariable("VeterinarianId") Long VeterinarianId) {
        try {
            Veterinarian Veterinarian = VeterinarianService.getVeterinarianById(VeterinarianId);
            if (Veterinarian == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(Veterinarian, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * This method is used to get all the appointments of a Veterinarian
     */
    @GetMapping(path = "{VeterinarianId}/appointments")
    public ResponseEntity<?> getAppointmentsOfVeterinarian(@PathVariable("VeterinarianId") Long VeterinarianId) {
        try {
             //Check if the Veterinarian exists
            Veterinarian Veterinarian = VeterinarianService.getVeterinarianById(VeterinarianId);
            if (Veterinarian == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            List<Appointment> appointments = Veterinarian.getAppointments();
            return new ResponseEntity<>(appointments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * This method is used to get all the working days of a Veterinarian
     */
    @GetMapping(path = "{VeterinarianId}/working_days")
    public ResponseEntity<?> getWorkingDaysOfVeterinarian(@PathVariable("VeterinarianId") Long VeterinarianId) {
        try {
             //Check if the Veterinarian exists
            Veterinarian Veterinarian = VeterinarianService.getVeterinarianById(VeterinarianId);
            if (Veterinarian == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            List<WorkingDay> workingDays = Veterinarian.getWorkingDays();
            return new ResponseEntity<>(workingDays, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}