/*
 * This class is used to handle the appointment related requests
 * It is annotated with the @RestController annotation to indicate that it is a controller
 * The AppointmentController class is a parameterized class as it is annotated with the @RestController annotation
 * 
 * Routes:
 * /api/appointments - GET: get all the appointments, Returns ResponseEntity<List<Appointment>>
 * /api/appointments - POST: add a new appointment, Returns ResponseEntity<Appointment>
 * /api/appointments/{appointmentId} - DELETE: delete an appointment, Returns ResponseEntity<?>
 * /api/appointments/{appointmentId} - PUT: update an appointment, Returns ResponseEntity<Appointment>
 * /api/appointments/{appointmentId} - GET: get an appointment by its id, Returns ResponseEntity<Appointment>
 * /api/appointments/{appointmentId}/animal - GET: get the animal of an appointment, Returns ResponseEntity<Animal>
 * /api/appointments/{appointmentId}/customer - GET: get the customer of an appointment, Returns ResponseEntity<Customer>
 * /api/appointments/{appointmentId}/veterinarian - GET: get the veterinarian of an appointment, Returns ResponseEntity<Veterinarian>
 * 
 * Search:
 * /api/appointments/search - GET: search for an appointment by its date range
 * /api/appointments/search/animal - GET: search for an appointment by its date range and the animal
 * /api/appointments/search/veterinarian - GET: search for an appointment by its date range and the veterinarian
 */

package com.veterinary.management.controllers;

import com.veterinary.management.entities.Appointment;
import com.veterinary.management.entities.Animal;
import com.veterinary.management.entities.Customer;
import com.veterinary.management.entities.Veterinarian;
import com.veterinary.management.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }
    
    /*
     * This method is used to get all the appointments
     */
    @GetMapping
    public ResponseEntity<?> getAppointments() {
        try {
            List<Appointment> appointments = appointmentService.getAppointments();
            return new ResponseEntity<>(appointments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * This method is used to add a new appointment
     */
    @PostMapping
    public ResponseEntity<?> addNewAppointment(@RequestBody Appointment appointment) {
        try {

            //Get the animal, customer and veterinarian of the appointment
            Animal animal = appointment.getAnimal();
            Customer customer = appointment.getCustomer();
            Veterinarian veterinarian = appointment.getVeterinarian();

            //Check if the animal, customer and veterinarian exist
            if (animal == null || customer == null || veterinarian == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }


            //Check if the veterinarian is available
            if (veterinarian.isWorking(appointment.getStartDate())) {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }

            // get all the appointments of the veterinarian
            List<Appointment> appointments = appointment.getVeterinarian().getAppointments();

            //Check if any of the appointments of the veterinarian overlap with the new appointment
            for (Appointment appointmentExisting : appointments) {
                //if the new appointment starts before the existing appointment ends, and the new appointment ends after the existing appointment starts
                if (appointment.getStartDate().isBefore(appointmentExisting.getEndDate()) && appointment.getEndDate().isAfter(appointmentExisting.getStartDate())) {
                    return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
                }

                //if the new appointment starts before the existing appointment starts, and the new appointment ends before the existing appointment ends
                if (appointment.getStartDate().isBefore(appointmentExisting.getStartDate()) && appointment.getEndDate().isBefore(appointmentExisting.getEndDate())) {
                    return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
                }

                //if the new appointment starts after the existing appointment starts, and the new appointment ends after the existing appointment ends
                if (appointment.getStartDate().isAfter(appointmentExisting.getStartDate()) && appointment.getEndDate().isAfter(appointmentExisting.getEndDate())) {
                    return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
                }

                //if the new appointment starts after the existing appointment starts, and the new appointment ends before the existing appointment ends
                if (appointment.getStartDate().isAfter(appointmentExisting.getStartDate()) && appointment.getEndDate().isBefore(appointmentExisting.getEndDate())) {
                    return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
                }
            }
                           
            appointmentService.addNewAppointment(appointment);
            return new ResponseEntity<>(appointment, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * This method is used to delete an appointment
     */
    @DeleteMapping(path = "{appointmentId}")
    public ResponseEntity<?> deleteAppointment(@PathVariable("appointmentId") Long appointmentId) {
        try {
            //Check if the appointment exists
            Appointment appointment = appointmentService.getAppointmentById(appointmentId);
            if (appointment == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            appointmentService.deleteAppointment(appointmentId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * This method is used to update an appointment
     */
    @PutMapping(path = "{appointmentId}")
    public ResponseEntity<?> updateAppointment(@PathVariable("appointmentId") Long appointmentId, @RequestBody Appointment appointment) {
        try {
            //Check if the appointment exists
            Appointment appointmentById = appointmentService.getAppointmentById(appointmentId);
            if (appointmentById == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            //Check if the veterinarian is available
            if (appointment.getVeterinarian().isWorking(appointment.getStartDate())) {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }

            // get all the appointments of the veterinarian
            List<Appointment> appointments = appointment.getVeterinarian().getAppointments();

            //Check if any of the appointments of the veterinarian overlap with the new appointment
            for (Appointment appointmentExisting : appointments) {
                // if the appointment is the same as the existing appointment, skip it
                if (appointment.getId().equals(appointmentExisting.getId())) {
                    continue;
                }
                //if the new appointment starts before the existing appointment ends, and the new appointment ends after the existing appointment starts
                if (appointment.getStartDate().isBefore(appointmentExisting.getEndDate()) && appointment.getEndDate().isAfter(appointmentExisting.getStartDate())) {
                    return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
                }

                //if the new appointment starts before the existing appointment starts, and the new appointment ends before the existing appointment ends
                if (appointment.getStartDate().isBefore(appointmentExisting.getStartDate()) && appointment.getEndDate().isBefore(appointmentExisting.getEndDate())) {
                    return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
                }

                //if the new appointment starts after the existing appointment starts, and the new appointment ends after the existing appointment ends
                if (appointment.getStartDate().isAfter(appointmentExisting.getStartDate()) && appointment.getEndDate().isAfter(appointmentExisting.getEndDate())) {
                    return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
                }

                //if the new appointment starts after the existing appointment starts, and the new appointment ends before the existing appointment ends
                if (appointment.getStartDate().isAfter(appointmentExisting.getStartDate()) && appointment.getEndDate().isBefore(appointmentExisting.getEndDate())) {
                    return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
                }
            }
            
            appointmentService.updateAppointment(appointment);
            return new ResponseEntity<>(appointment, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * This method is used to get an appointment by its id
     */
    @GetMapping(path = "{appointmentId}")
    public ResponseEntity<?> getAppointmentById(@PathVariable("appointmentId") Long appointmentId) {
        try {
            //Check if the appointment exists
            Appointment appointment = appointmentService.getAppointmentById(appointmentId);
            if (appointment == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(appointment, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * This method is used to get the animal of an appointment
     */
    @GetMapping(path = "{appointmentId}/animal")
    public ResponseEntity<?> getAnimalOfAppointment(@PathVariable("appointmentId") Long appointmentId) {
        try {
            //Check if the appointment exists
            Appointment appointment = appointmentService.getAppointmentById(appointmentId);
            if (appointment == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            Animal animal = appointment.getAnimal();
            return new ResponseEntity<>(animal, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * This method is used to get the customer of an appointment
     */
    @GetMapping(path = "{appointmentId}/customer")
    public ResponseEntity<?> getCustomerOfAppointment(@PathVariable("appointmentId") Long appointmentId) {
        try {
            //Check if the appointment exists
            Appointment appointment = appointmentService.getAppointmentById(appointmentId);
            if (appointment == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            Customer customer = appointment.getCustomer();
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * This method is used to get the veterinarian of an appointment
     */
    @GetMapping(path = "{appointmentId}/veterinarian")
    public ResponseEntity<?> getVeterinarianOfAppointment(@PathVariable("appointmentId") Long appointmentId) {
        try {
            //Check if the appointment exists
            Appointment appointment = appointmentService.getAppointmentById(appointmentId);
            if (appointment == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            Veterinarian veterinarian = appointment.getVeterinarian();
            return new ResponseEntity<>(veterinarian, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * This method is used to finds for an appointment by its start date range
     */
    @GetMapping(path = "search")
    public ResponseEntity<?> findAppointmentsByStartDateRange(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        try {
            // Get all the appointments
            List<Appointment> appointments = appointmentService.getAppointments();
            // Create a list to store the appointments that are within the date range
            List<Appointment> appointmentsInRange = new ArrayList<Appointment>();

            // Loop through the appointments
            for (Appointment appointment : appointments) {
                // If the appointment is within the date range, add it to the list
                if (appointment.getStartDate().isAfter(startDate) && appointment.getEndDate().isBefore(endDate)) {
                    appointmentsInRange.add(appointment);
                }
            }

            return new ResponseEntity<>(appointmentsInRange, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
      
    }


    /*
     * This method is used to finds for an appointment by its date range, and the animal
     */
    @GetMapping(path = "search/animal")
    public ResponseEntity<?> findAppointmentsByDateRangeAndAnimal(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate, @RequestParam Long animalId) {
        try {
            // Get all the appointments
            List<Appointment> appointments = appointmentService.getAppointments();
            // Create a list to store the appointments that are within the date range and have the animal
            List<Appointment> appointmentsInRange = new ArrayList<Appointment>();

            // Loop through the appointments
            for (Appointment appointment : appointments) {
                // If the appointment is within the date range, and has the animal, add it to the list
                if (appointment.getStartDate().isAfter(startDate) && appointment.getEndDate().isBefore(endDate) && appointment.getAnimal().getId().equals(animalId)) {
            
                    if (appointment.getAnimal().getId().equals(animalId)) {
                        appointmentsInRange.add(appointment);
                    }
                }
            }

            return new ResponseEntity<>(appointmentsInRange, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * This method is used to find for an appointment by its date range, and the veterinarian
     */
    @GetMapping(path = "search/veterinarian")
    public ResponseEntity<?> findAppointmentsByDateRangeAndVeterinarian(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate, @RequestParam Long veterinarianId) {
        try {
            // Get all the appointments
            List<Appointment> appointments = appointmentService.getAppointments();
            // Create a list to store the appointments that are within the date range and have the veterinarian
            List<Appointment> appointmentsInRange = new ArrayList<Appointment>();

            // Loop through the appointments
            for (Appointment appointment : appointments) {
                // If the appointment is within the date range, and has the veterinarian, add it to the list
                if (appointment.getStartDate().isAfter(startDate) && appointment.getEndDate().isBefore(endDate) && appointment.getVeterinarian().getId().equals(veterinarianId)) {
                    appointmentsInRange.add(appointment);
                }
            }

            return new ResponseEntity<>(appointmentsInRange, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}