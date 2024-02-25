/*
 * This class is used to handle the business logic for the Vaccination entity
 * It is annotated with the @Service annotation
 * The VaccinationService class is a parameterized class as it is annotated with the @Service annotation
 * 
 * Methods:
 * getVaccinations: List<Vaccination> - returns a list of all the Vaccinations
 * addNewVaccination: void - adds a new Vaccination to the database
 * deleteVaccination: void - deletes a Vaccination from the database
 * updateVaccination: void - updates a Vaccination in the database
 * getVaccinationById: Vaccination - returns a Vaccination with a specific id
 * getVaccinationByName: Vaccination - returns a Vaccination with a specific name
 * 
 * Attributes:
 * id: Long - the id of the Vaccination
 * name: String - the name of the Vaccination
 * type: String - the type of the Vaccination
 * description: String - the description of the Vaccination
 * price: Double - the price of the Vaccination
 * expirationDate: LocalDate - the expiration date of the Vaccination
 * quantity: Integer - the quantity of the Vaccination
 * animals: List<Animal> - the animals that have been vaccinated with the Vaccination
 * appointments: List<Appointment> - the appointments that have been made for the Vaccination
 */

package com.veterinary.management.services;

import com.veterinary.management.entities.Animal;
import com.veterinary.management.entities.Vaccination;
import com.veterinary.management.repositories.VaccinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VaccinationService {
    private final VaccinationRepository vaccinationRepository;

    @Autowired
    public VaccinationService(VaccinationRepository vaccinationRepository) {
        this.vaccinationRepository = vaccinationRepository;
    }

    public List<Vaccination> getVaccinations() {
        return vaccinationRepository.findAll();
    }

    public void addNewVaccination(Vaccination vaccination) {
        vaccinationRepository.save(vaccination);
    }

    public void deleteVaccination(Long vaccinationId) {
        vaccinationRepository.deleteById(vaccinationId);
    }

    public void updateVaccination(Vaccination vaccination) {
        vaccinationRepository.save(vaccination);
    }

    public Vaccination getVaccinationById(Long vaccinationId) {
        return vaccinationRepository.findById(vaccinationId).orElse(null);
    }

    public List<Vaccination> searchVaccinationsByDateRange(LocalDate startDate, LocalDate endDate) {
        return vaccinationRepository.findVaccinationsByExpirationDateBetween(startDate, endDate);
    }

    public List<Vaccination> getVaccinationsForAnimal(Animal animal) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getVaccinationsForAnimal'");
    }

}