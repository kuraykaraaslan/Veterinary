/*
 * This class represents the controller for the appointments.
 * It handles the requests for the appointments.
 * 
 * It has the following methods:
 * - getAllAppointments: This method handles the request for getting all the appointments.
 * - getAppointmentById: This method handles the request for getting an appointment by its id.
 * - addAppointment: This method handles the request for adding a new appointment.
 * - updateAppointment: This method handles the request for updating an appointment.
 * - deleteAppointment: This method handles the request for deleting an appointment.
 * - findByAnimalIdAndDateBetween: This method handles the request for getting all the appointments for a specific animal between two dates.
 * - findByDoctorIdAndDateBetween: This method handles the request for getting all the appointments for a specific doctor between two dates.
 * 
 * It has the following routes:
 * - GET /api/appointments
 * - GET /api/appointments/{id}
 * - POST /api/appointments
 * - PUT /api/appointments/{id}
 * - DELETE /api/appointments/{id}
 * - GET /api/appointments/findByAnimalIdAndDateBetween
 * - GET /api/appointments/findByDoctorIdAndDateBetween
 */
package com.veterinary.management.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.veterinary.management.models.Appointment;
import com.veterinary.management.requests.AppointmentRequest;
import com.veterinary.management.services.AppointmentService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    /*
     * This method handles the request for getting all the appointments.
     * @return a list of all the appointments.
     */
    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    /*
     * This method handles the request for getting an appointment by its id.
     * @param id the id of the appointment.
     * @return the appointment with the given id.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id) {
        return ResponseEntity.ok(appointmentService.getAppointmentById(id));
    }

    /*
     * This method handles the request for adding a new appointment.
     * @param appointment the request for adding a new appointment.
     * @return the added appointment.
     */
    @PostMapping
    public ResponseEntity<Appointment> addAppointment(@RequestBody AppointmentRequest appointmentRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.addAppointment(appointmentRequest));
    }

    /*
     * This method handles the request for updating an appointment.
     * @param id the id of the appointment.
     * @param appointmentRequest the request for updating an appointment.
     * @return the updated appointment.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id, @RequestBody AppointmentRequest appointmentRequest) {
        return ResponseEntity.ok(appointmentService.updateAppointment(id, appointmentRequest));
    }

    /*
     * This method handles the request for deleting an appointment.
     * @param id the id of the appointment.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /*
     * This method handles the request for getting all the appointments for a specific doctor between two dates.
     * Değerlendirme Formu: 20
     */
    @GetMapping("/searchByDoctorAndDateRange")
    public ResponseEntity<List<Appointment>> findByDoctorIdAndDateRange (@RequestParam Long id, @RequestParam LocalDate startDate, @RequestParam LocalDate endDate){
        List<Appointment> appointmentListSearchByDoctorAndDateRange = appointmentService.findByDoctorIdAndDateRange(id, startDate, endDate);
        return ResponseEntity.ok().body(appointmentListSearchByDoctorAndDateRange);
    }

    /*
     * This method handles the request for getting all the appointments for a specific animal between two dates.
     * Değerlendirme Formu: 19
     */
    @GetMapping("/searchByAnimalAndDateRange")
    public ResponseEntity<List<Appointment>> findByAnimalIdAndDateRange (@RequestParam Long id,@RequestParam LocalDate startDate,@RequestParam LocalDate endDate){
        List<Appointment> appointmentListSearchByAnimalAndDateRange = appointmentService.findByAnimalIdAndDateRange(id, startDate, endDate);
        return ResponseEntity.ok().body(appointmentListSearchByAnimalAndDateRange);
    }
}