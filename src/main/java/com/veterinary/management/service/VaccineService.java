/*
 * This service class is used to manage the vaccine operations.
 * Attributes:
 * vaccineRepository: VaccineRepository - the repository that manages the vaccine operations
 * modelMapper: ModelMapper - the model mapper that is used to map the vaccine request dto to vaccine
 * 
 * Methods:
 * findAllVaccines: List<Vaccine> - this method is used to find all the vaccines
 * findVaccineById: Vaccine - this method is used to find a vaccine by its id
 * findVaccinesByAnimal: List<Vaccine> - this method is used to find vaccines by animal
 * findAnimalsByVaccineProtectionFinishDateRange: List<Vaccine> - this method is used to find animals by vaccine protection finish date range
 * createVaccine: Vaccine - this method is used to create a vaccine
 * updateVaccine: Vaccine - this method is used to update a vaccine
 * deleteVaccine: String - this method is used to delete a vaccine
 */
package com.veterinary.management.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.veterinary.management.request.VaccineWithoutCustomerRequestDto;
import com.veterinary.management.entity.Vaccine;
import com.veterinary.management.repository.VaccineRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VaccineService {

    private final VaccineRepository vaccineRepository;
    private final ModelMapper modelMapper;

    public List<Vaccine> findAllVaccines (){
        return vaccineRepository.findAll();
    }

    public Vaccine findVaccineById (Long id){
        return vaccineRepository.findById(id).orElseThrow(() -> new RuntimeException("id:" + id + "Vaccine could not found!!!"));
    }

    public List<Vaccine> findVaccinesByAnimal(Long id) {
        return vaccineRepository.findByAnimalId(id);
    }

    public List<Vaccine> findAnimalsByVaccineProtectionFinishDateRange(LocalDate startDate, LocalDate endDate) {
        return vaccineRepository.findByProtectionFinishDateBetween(startDate,endDate);
    }

    public Vaccine createVaccine(VaccineWithoutCustomerRequestDto vaccineWithoutCustomerRequestDto){
        List<Vaccine> existValidVaccineWithSameSpecsAnd =
                vaccineRepository.findByNameAndCodeAndAnimalIdAndProtectionFinishDateGreaterThanEqual(vaccineWithoutCustomerRequestDto.getName(), vaccineWithoutCustomerRequestDto.getCode(),vaccineWithoutCustomerRequestDto.getAnimalWithoutCustomer().getId(), vaccineWithoutCustomerRequestDto.getProtectionStartDate());

        if (!existValidVaccineWithSameSpecsAnd.isEmpty()){
            throw new RuntimeException("The vaccine you want to save is still protective for this animal.");
        }

        Vaccine newVaccine = modelMapper.map(vaccineWithoutCustomerRequestDto, Vaccine.class);
        return vaccineRepository.save(newVaccine);
    }

    public Vaccine updateVaccine (Long id, VaccineWithoutCustomerRequestDto vaccineWithoutCustomerRequestDto){
        Optional<Vaccine> vaccineFromDb = vaccineRepository.findById(id);
        List<Vaccine> existOtherValidVaccineFromRequest =
                vaccineRepository.findByNameAndCodeAndAnimalIdAndProtectionFinishDateGreaterThanEqual(vaccineWithoutCustomerRequestDto.getName(), vaccineWithoutCustomerRequestDto.getCode(),vaccineWithoutCustomerRequestDto.getAnimalWithoutCustomer().getId(), vaccineWithoutCustomerRequestDto.getProtectionStartDate());

        if (vaccineFromDb.isEmpty()){
            throw new RuntimeException("id:" + id + "Vaccine could not found!!!");
        }

        if (!existOtherValidVaccineFromRequest.isEmpty() && !existOtherValidVaccineFromRequest.get(existOtherValidVaccineFromRequest.size()-1).getId().equals(id)){
            throw new RuntimeException("This Vaccine has already been registered. That's why this request causes duplicate data");
        }

        if (!existOtherValidVaccineFromRequest.isEmpty()){
            throw new RuntimeException("The vaccine you want to update is still protective for this animal.");
        }

        Vaccine updatedVaccine = vaccineFromDb.get();
        modelMapper.map(vaccineWithoutCustomerRequestDto, updatedVaccine); // VaccineRequestDto -> Vaccine
        return vaccineRepository.save(updatedVaccine);
    }

    public String deleteVaccine (Long id){
        Optional<Vaccine> vaccineFromDb = vaccineRepository.findById(id);
        if (vaccineFromDb.isEmpty()){
            throw new RuntimeException("This vaccine could not found!!!");
        }
        else {
            vaccineRepository.delete(vaccineFromDb.get());
            return "Vaccine deleted.";
        }
    }
}
