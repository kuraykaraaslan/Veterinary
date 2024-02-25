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
}