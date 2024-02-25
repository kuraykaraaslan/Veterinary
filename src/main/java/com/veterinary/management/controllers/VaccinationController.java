/*
 * This class is responsible for handling the requests related to the vaccination of the animals.
 * It is a parameterized class as it is annotated with the @RestController annotation.
 * 
 * Routes:
 * /api/vaccinations - GET: get all the vaccinations, Returns ResponseEntity<List<Vaccination>>
 * /api/vaccinations - POST: add a new vaccination, Returns ResponseEntity<Vaccination>
 * /api/vaccinations/{vaccinationId} - DELETE: delete a vaccination, Returns ResponseEntity<?>
 * /api/vaccinations/{vaccinationId} - PUT: update a vaccination, Returns ResponseEntity<?>
 * /api/vaccinations/{vaccinationId} - GET: get a vaccination by its id, Returns ResponseEntity<Vaccination>
 * /api/vaccinations/{vaccinationId}/animals - GET: get the animals that have been vaccinated with a specific vaccination, Returns ResponseEntity<List<Animal>>
 * /api/vaccinations/{vaccinationId}/appointment - GET: get the appointment that have been made for a specific vaccination, Returns ResponseEntity<Appointment>
 * /api/vaccinations/{vaccinationId}/veterinarian - GET: get the veterinarian that have administered a specific vaccination, Returns ResponseEntity<Veterinarian>
 * 
 * Search:
 * /api/vaccinations/search - GET: search for a vaccination by its date range, Returns ResponseEntity<List<Vaccination>>
 */

package com.veterinary.management.controllers;

import com.veterinary.management.entities.Animal;
import com.veterinary.management.entities.Appointment;
import com.veterinary.management.entities.Vaccination;
import com.veterinary.management.entities.Veterinarian;
import com.veterinary.management.services.VaccinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
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
            // Check if any appointment exists for the vaccination
            if (vaccination.getAppointment() != null) {
                Long appointmentId = vaccination.getAppointment().getId();
                if (appointmentId != null) {
                    return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
                }
            }

            // Get the animal for the vaccination, if it exists
            if (vaccination.getAnimal() != null) {
                Long animalId = vaccination.getAnimal().getId();
                if (animalId != null) {
                    return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
                }
            }

            // Get animal for the vaccination, if it exists
            Animal animal = vaccination.getAnimal();

            // Get All the vaccinations for the animal
            List<Vaccination> vaccinationsForAnimal = vaccinationService.getVaccinationsForAnimal(animal);

            // Remove the vaccinations that has not same type as the new vaccination
            vaccinationsForAnimal.removeIf(v -> !v.getType().equals(vaccination.getType()));

            // If there is any vaccination that has not expired, return not acceptable
            for (Vaccination v : vaccinationsForAnimal) {
                if (v.getExpirationDate().isAfter(LocalDate.now())) {
                    return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
                }
            }

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
    public ResponseEntity<?> updateVaccination(@PathVariable("vaccinationId") Long vaccinationId,
            @RequestBody Vaccination vaccination) {
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
     * This method is used to get the animals that have been vaccinated with a
     * specific vaccination
     */
    @GetMapping(path = "{vaccinationId}/animals")
    public ResponseEntity<?> getAnimalsVaccinatedWithVaccination(@PathVariable("vaccinationId") Long vaccinationId) {
        try {
            // Check if the vaccination exists
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
     * This method is used to get the appointment that have been made for a specific
     * vaccination
     */
    @GetMapping(path = "{vaccinationId}/appointment")
    public ResponseEntity<?> getAppointmentsForVaccination(@PathVariable("vaccinationId") Long vaccinationId) {
        try {
            // Check if the vaccination exists
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
     * This method is used to get the veterinarian that have administered a specific
     * vaccination
     */

    @GetMapping(path = "{vaccinationId}/veterinarian")
    public ResponseEntity<?> getVeterinarianForVaccination(@PathVariable("vaccinationId") Long vaccinationId) {
        try {
            // Check if the vaccination exists
            Vaccination vaccination = vaccinationService.getVaccinationById(vaccinationId);
            if (vaccination == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            // get the appointment for the vaccination
            Appointment appointment = vaccination.getAppointment();
            if (appointment == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            // get the veterinarian for the appointment
            Veterinarian veterinarian = appointment.getVeterinarian();
            if (veterinarian == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(veterinarian, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * This method is used to search for a vaccination by its date range
     */
    @GetMapping(path = "search")
    public ResponseEntity<?> searchVaccinationByDateRange(@RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        try {
            List<Vaccination> vaccinations = vaccinationService.searchVaccinationsByDateRange(startDate, endDate);
            return new ResponseEntity<>(vaccinations, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
