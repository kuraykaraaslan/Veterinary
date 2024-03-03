/*
 * This class is used to handle the requests for the Veterinarian
 * It is annotated with the @RestController annotation to indicate that it is a controller
 * The VeterinarianController class is a parameterized class as it is annotated with the @RestController annotation
 * 
 * Routes:
 * 
 * GET /api/doctors
 * GET /api/doctors/{id}
 * POST /api/doctors
 * PUT /api/doctors/{id}
 * DELETE /api/doctors/{id}
 */


package com.veterinary.management.controllers;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.veterinary.management.request.DoctorRequestDto;
import com.veterinary.management.entity.Doctor;
import com.veterinary.management.service.DoctorService;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {

    public final DoctorService doctorService;

    @GetMapping
    public List<Doctor> findAllDoctors (){
        return doctorService.findAllDoctors();
    }

    @GetMapping("/{id}")
    public Doctor findDoctorById (@PathVariable Long id){
        return doctorService.findDoctorById(id);
    }

    @PostMapping
    public Doctor createDoctor (@RequestBody DoctorRequestDto doctorRequestDto){
        return doctorService.createDoctor(doctorRequestDto);
    }

    @PutMapping("/{id}")
    public Doctor updateDoctor (@PathVariable Long id, @RequestBody DoctorRequestDto doctorRequestDto){
        return doctorService.updateDoctor(id,doctorRequestDto);
    }

    @DeleteMapping("/{id}")
    public String deleteDoctor(@PathVariable Long id){
        return doctorService.deleteDoctor(id);
    }
}
