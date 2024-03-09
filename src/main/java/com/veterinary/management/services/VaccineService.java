package com.veterinary.management.services;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.veterinary.management.models.Animal;
import com.veterinary.management.models.Vaccine;
import com.veterinary.management.repositories.VaccineRepository;
import com.veterinary.management.requests.VaccineRequest;

@Service
@RequiredArgsConstructor
public class VaccineService {

    private final VaccineRepository vaccineRepository;
    private final AnimalService animalService;

    /*
     * This method returns all vaccines
     */
    public List<Vaccine> getAllVaccines() {
        return vaccineRepository.findAll();
    }

    /*
     * This method returns a vaccine by id
     * @param Long id
     * @return Vaccine
     * 
     */
    public Vaccine getVaccineById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }

        return vaccineRepository.findById(id).orElse(null);
    }

    /*
     * This method adds a vaccine
     * @param VaccineRequest vaccineRequest
     * @return Vaccine
     * 
     */
    public Vaccine addVaccine(VaccineRequest vaccineRequest) {
        if (vaccineRequest == null) {
            throw new IllegalArgumentException("Vaccine cannot be null");
        }

        Vaccine vaccine = new Vaccine();
        vaccine.setName(vaccineRequest.getName());
        vaccine.setApplicationDate(vaccineRequest.getApplicationDate());
        vaccine.setExpirationDate(vaccineRequest.getExpirationDate());

        Animal animal = animalService.getAnimalById(vaccineRequest.getAnimalId());

        if (animal == null) {
            throw new UnsupportedOperationException("Animal with id " + vaccineRequest.getAnimalId() + " not found");
        }

        /*Check if the animal is already vaccinated with the same vaccine and if the expiration date is not passed
         * Değerlendirme Formu: 22
         */
        List<Vaccine> vaccines = animal.getVaccines();
        for (Vaccine v : vaccines) {
            if (v.getName().equals(vaccine.getName()) && v.getExpirationDate().isAfter(vaccine.getApplicationDate())) {
                throw new UnsupportedOperationException("The animal is already vaccinated with the same vaccine and the expiration date is not passed");
            }
        }

        vaccine.setAnimal(animal);

        return vaccineRepository.save(vaccine);
    }

    /*
     * This method updates a vaccine
     * @param Long id
     * @param VaccineRequest vaccineRequest
     * @return Vaccine
     * 
     */
    public Vaccine updateVaccine(Long id, VaccineRequest vaccineRequest) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }

        if (vaccineRequest == null) {
            throw new IllegalArgumentException("Vaccine cannot be null");
        }

        Vaccine vaccine = vaccineRepository.findById(id).orElse(null);
        vaccine.setName(vaccineRequest.getName());
        vaccine.setApplicationDate(vaccineRequest.getApplicationDate());
        vaccine.setExpirationDate(vaccineRequest.getExpirationDate());

        Animal animal = animalService.getAnimalById(vaccineRequest.getAnimalId());

        if (animal == null) {
            throw new UnsupportedOperationException("Animal with id " + vaccineRequest.getAnimalId() + " not found");
        }

        vaccine.setAnimal(animal);

        return vaccineRepository.save(vaccine);
    }

    /*
     * This method deletes a vaccine
     * @param Long id
     * 
     */
    public void deleteVaccine(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }

        vaccineRepository.deleteById(id);
    }

    /*
     * This method returns all vaccines by application date range
     * @param LocalDateTime startDate
     * @param LocalDateTime endDate
     * @return List<Vaccine>
     * 
     */
    public List<Vaccine> findVaccinesByApplicationDateBetween(LocalDate startDate, LocalDate endDate) {
        return vaccineRepository.findVaccinesByApplicationDateBetween(startDate, endDate);
    }

}