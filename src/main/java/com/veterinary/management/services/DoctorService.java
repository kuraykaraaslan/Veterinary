package com.veterinary.management.services;

import java.util.List;
import org.springframework.stereotype.Service;

import com.veterinary.management.repositories.DoctorRepository;
import com.veterinary.management.requests.DoctorRequest;
import com.veterinary.management.models.Doctor;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    /*
     * This method deletes a doctor by id
     */
    public void deleteById(Long id) {
        if (id == null) {
            return;
        }
        doctorRepository.deleteById(id);
    }

    /*
     * This method returns all doctors
     * @return List<Doctor>
     * 
     */
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    /*
     * This method returns a doctor by id
     * @param Long id
     * @return Doctor
     * 
     */
    public Doctor getDoctorById(Long id) {
        if (id == null) {
            return null;
        }
        return doctorRepository.findById(id).orElse(null);
    }

    /*
     * This method adds a doctor
     * @param DoctorRequest doctorRequest
     * @return Doctor
     * 
     */
    public Doctor addDoctor(DoctorRequest doctorRequest) {
        Doctor doctor = new Doctor();
        doctor.setName(doctorRequest.getName());
        doctor.setEmail(doctorRequest.getEmail());
        doctor.setPhone(doctorRequest.getPhone());
        doctor.setAddress(doctorRequest.getAddress());
        doctor.setCity(doctorRequest.getCity());
        return doctorRepository.save(doctor);
    }

    /*
     * This method updates a doctor
     * @param Long id
     * @param DoctorRequest doctorRequest
     * @return Doctor
     * 
     */
    public Doctor updateDoctor(Long id, DoctorRequest doctorRequest) {
        if (id == null) {
            return null;
        }
        Doctor doctor = doctorRepository.findById(id).orElse(null);
        if (doctor != null) {
            doctor.setName(doctorRequest.getName());
            doctor.setEmail(doctorRequest.getEmail());
            doctor.setPhone(doctorRequest.getPhone());
            doctor.setAddress(doctorRequest.getAddress());
            doctor.setCity(doctorRequest.getCity());
            return doctorRepository.save(doctor);
        }
        return null;
    }

    /*
     * This method returns a doctor by name and email
     * @param String name
     * @param String email
     * @return Doctor
     * 
     */
    public Doctor getDoctorByNameAndEmail(String name, String email) {
        return doctorRepository.findByNameAndEmail(name, email).orElse(null);
    }
}

