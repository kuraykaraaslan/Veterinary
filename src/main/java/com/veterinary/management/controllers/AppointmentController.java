package com.veterinary.management.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.veterinary.management.models.Appointment;
import com.veterinary.management.requests.AppointmentRequest;
import com.veterinary.management.services.AppointmentService;

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
     * This method handles the request for getting all the appointments for a specific animal between two dates.
     */
    @GetMapping("/findByAnimalIdAndDateBetween")
    public ResponseEntity<List<Appointment>> findByAnimalIdAndDateBetween(@RequestParam Long animalId, @RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate) {
        return ResponseEntity.ok(appointmentService.findByAnimalIdAndDateBetween(animalId, startDate, endDate));
    }

    /*
     * This method handles the request for getting all the appointments for a specific doctor between two dates.
     */
    @GetMapping("/findByDoctorIdAndDateBetween")
    public ResponseEntity<List<Appointment>> findByDoctorIdAndDateBetween(@RequestParam Long doctorId, @RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate) {
        return ResponseEntity.ok(appointmentService.findByDoctorIdAndDateBetween(doctorId, startDate, endDate));
    }
}