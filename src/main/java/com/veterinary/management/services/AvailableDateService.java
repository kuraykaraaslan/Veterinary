package com.veterinary.management.services;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

import com.veterinary.management.requests.AvailableDateRequest;
import com.veterinary.management.models.AvailableDate;
import com.veterinary.management.models.Doctor;
import com.veterinary.management.repositories.AvailableDateRepository;

@Service
@RequiredArgsConstructor
public class AvailableDateService {

    private final AvailableDateRepository availableDateRepository;
    private final DoctorService doctorService;

    /*
     * This method returns all available dates
     * @return List<AvailableDate>
     * 
     */
    public List<AvailableDate> getAllAvailableDates() {
        return availableDateRepository.findAll();
    }

    /*
     * This method returns an available date by id
     * @param Long id
     * @return AvailableDate
     * 
     */
    public AvailableDate getAvailableDateById(Long id) {
        if (id == null) {
            return null;
        }
        return availableDateRepository.findById(id).orElse(null);
    }

    /*
     * This method adds an available date
     * @param AvailableDateRequest availableDateRequest
     * @return AvailableDate
     * throws UnsupportedOperationException if doctor with id not found
     */
    public AvailableDate addAvailableDate(AvailableDateRequest availableDateRequest) {
        AvailableDate availableDate = new AvailableDate();

        Doctor doctor = doctorService.getDoctorById(availableDateRequest.getDoctorId());

        if (doctor == null) {
            throw new UnsupportedOperationException("Doctor with id " + availableDateRequest.getDoctorId() + " not found");
        }

        availableDate.setDoctor(doctor);
        availableDate.setDate(availableDateRequest.getDate());

        return availableDateRepository.save(availableDate);
    }

    /*
     * This method updates an available date
     * @param Long id
     * @param AvailableDateRequest availableDateRequest
     * @return AvailableDate
     * throws UnsupportedOperationException if doctor with id not found
     */
    public AvailableDate updateAvailableDate(Long id, AvailableDateRequest availableDateRequest) {
        if (id == null) {
            return null;
        }
        AvailableDate availableDate = availableDateRepository.findById(id).orElse(null);
        if (availableDate != null) {
            Doctor doctor = doctorService.getDoctorById(availableDateRequest.getDoctorId());
            if (doctor == null) {
                throw new UnsupportedOperationException("Doctor with id " + availableDateRequest.getDoctorId() + " not found");
            }
            availableDate.setDoctor(doctor);
            availableDate.setDate(availableDateRequest.getDate());
            return availableDateRepository.save(availableDate);
        }
        return null;
    }

    /*
     * This method deletes an available date
     * @param Long id
     * @return AvailableDate
     * 
     */
    public AvailableDate deleteAvailableDate(Long id) {
        if (id == null) {
            return null;
        }
        AvailableDate availableDate = availableDateRepository.findById(id).orElse(null);
        if (availableDate != null) {
            availableDateRepository.deleteById(id);
            return availableDate;
        }
        return null;
    }

}
