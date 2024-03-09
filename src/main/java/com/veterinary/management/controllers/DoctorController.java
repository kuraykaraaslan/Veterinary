/*
 * This class is the controller for the doctor entity.
 * It handles the requests for the doctor entity.
 * 
 * It has the following methods:
 * - getAllDoctors: This method handles the request for getting all the doctors.
 * - getDoctorById: This method handles the request for getting a doctor by its id.
 * - addDoctor: This method handles the request for adding a new doctor.
 * - updateDoctor: This method handles the request for updating a doctor.
 * - deleteDoctor: This method handles the request for deleting a doctor.
 * 
 * It has the following routes:
 * - GET /api/doctors
 * - GET /api/doctors/{id}
 * - POST /api/doctors
 * - PUT /api/doctors/{id}
 * - DELETE /api/doctors/{id}
 */
package com.veterinary.management.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.veterinary.management.models.Doctor;
import com.veterinary.management.requests.DoctorRequest ;
import com.veterinary.management.services.DoctorService;
import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    /*
     * This method handles the request for getting all the doctors.
     */
    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    /*
     * This method handles the request for getting a doctor by its id.
     * @param id the id of the doctor.
     * @return the doctor with the given id.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }

    /*
     * This method handles the request for adding a new doctor.
     * @param doctor the request for adding a new doctor.
     * @return the added doctor.
     */
    @PostMapping
    public ResponseEntity<Doctor> addDoctor(@RequestBody DoctorRequest doctor) {
        return new ResponseEntity<>(doctorService.addDoctor(doctor), HttpStatus.CREATED);
    }

    /*
     * This method handles the request for updating a doctor.
     * @param id the id of the doctor.
     * @param doctor the request for updating a doctor.
     * @return the updated doctor.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id, @RequestBody DoctorRequest doctor) {
        return ResponseEntity.ok(doctorService.updateDoctor(id, doctor));
    }

    /*
     * This method handles the request for deleting a doctor by its id.
     * @param id the id of the doctor.
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteById(id);
        return new ResponseEntity<>("Doctor with id " + id + " has been deleted", HttpStatus.OK);
    }
}
