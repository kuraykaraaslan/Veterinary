/*
 * This service class is used to manage the doctor operations.
 * Attributes:
 * doctorRepository: DoctorRepository - the repository that manages the doctor operations
 * modelMapper: ModelMapper - the model mapper that is used to map the doctor request dto to doctor
 * Methods:
 * findAllDoctors: List<Doctor> - this method is used to find all the doctors
 * findDoctorById: Doctor - this method is used to find a doctor by its id
 * createDoctor: Doctor - this method is used to create a doctor
 * updateDoctor: Doctor - this method is used to update a doctor
 * deleteDoctor: String - this method is used to delete a doctor
 * 
 */
package com.veterinary.management.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.veterinary.management.request.DoctorRequestDto;
import com.veterinary.management.entity.Doctor;
import com.veterinary.management.repository.DoctorRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;

    public List<Doctor> findAllDoctors (){
        return doctorRepository.findAll();
    }

    public Doctor findDoctorById (Long id){
        return doctorRepository.findById(id).orElseThrow(() -> new RuntimeException("id:" + id + "Doctor could not found!!!"));
    }

    public Doctor createDoctor(DoctorRequestDto doctorRequestDto){
        Optional<Doctor> existDoctorWithSameSpecs = doctorRepository.findByNameAndEmail(doctorRequestDto.getName(), doctorRequestDto.getEmail());

        if (existDoctorWithSameSpecs.isPresent()){
            throw new RuntimeException("The doctor has already been saved.");
        }
        Doctor newDoctor = modelMapper.map(doctorRequestDto, Doctor.class);
        return doctorRepository.save(newDoctor);
    }

    public Doctor updateDoctor (Long id, DoctorRequestDto doctorRequestDto){
        Optional<Doctor> doctorFromDb = doctorRepository.findById(id);
        Optional<Doctor> existOtherDoctorFromRequest = doctorRepository.findByNameAndEmail(doctorRequestDto.getName(), doctorRequestDto.getEmail());

        if (doctorFromDb.isEmpty()){
            throw new RuntimeException("id:" + id + "Doctor could not found!!!");
        }

        if (existOtherDoctorFromRequest.isPresent() && !existOtherDoctorFromRequest.get().getId().equals(id)){
            throw new RuntimeException("This doctor has already been registered. That's why this request causes duplicate data");
        }

        Doctor updatedDoctor = doctorFromDb.get();
        modelMapper.map(doctorRequestDto, updatedDoctor); // DoctorRequestDto -> Doctor
        return doctorRepository.save(updatedDoctor);
    }

    public String deleteDoctor (Long id){
        Optional<Doctor> doctorFromDb = doctorRepository.findById(id);
        if (doctorFromDb.isEmpty()){
            throw new RuntimeException("This doctor could not found!!!");
        }
        else {
            doctorRepository.delete(doctorFromDb.get());
            return "Doctor deleted.";
        }
    }
}
