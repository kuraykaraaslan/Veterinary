/*
 * This class is used to provide the services for the Veterinarian
 * It is annotated with the @Service annotation
 * The VeterinarianService class is a parameterized class as it is annotated with the @Service annotation
 * The VeterinarianService class is used to provide the services for the Veterinarian entity
 * 
 * Methods:
 * getVeterinarians: List<Veterinarian> - this method is used to get all the veterinarians
 * addNewVeterinarian: void - this method is used to add a new veterinarian
 * deleteVeterinarian: void - this method is used to delete a veterinarian
 * updateVeterinarian: void - this method is used to update a veterinarian
 * getVeterinarianById: Veterinarian - this method is used to get a veterinarian by its id
 * getVeterinarianByName: Veterinarian - this method is used to get a veterinarian by its name
 * getVeterinarianByPhoneNumber: Veterinarian - this method is used to get a veterinarian by its phone number
 * getVeterinarianByEmail: Veterinarian - this method is used to get a veterinarian by its email
 * 
 * Attributes:
 * id: Long - the id of the vetenerian
 * name: String - the name of the vetenerian
 * surname: String - the surname of the vetenerian
 * dateOfBirth: LocalDate - the date of birth of the vet
 * phoneNumber: String
 * email: String
 * address: String
 */

package com.veterinary.management.services;

import com.veterinary.management.entities.Veterinarian;
import com.veterinary.management.repositories.VeterinarianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeterinarianService {
    private final VeterinarianRepository veterinarianRepository;

    @Autowired
    public VeterinarianService(VeterinarianRepository veterinarianRepository) {
        this.veterinarianRepository = veterinarianRepository;
    }

    public List<Veterinarian> getVeterinarians() {
        return veterinarianRepository.findAll();
    }

    public void addNewVeterinarian(Veterinarian veterinarian) {
        veterinarianRepository.save(veterinarian);
    }

    public void deleteVeterinarian(Long veterinarianId) {
        veterinarianRepository.deleteById(veterinarianId);
    }

    public void updateVeterinarian(Veterinarian veterinarian) {
        veterinarianRepository.save(veterinarian);
    }

    public Veterinarian getVeterinarianById(Long veterinarianId) {
        return veterinarianRepository.findVeterinarianById(veterinarianId);
    }
}