/*
 * This class is used to manage the available date operations.
 * Attributes:
 * availableDateRepository: AvailableDateRepository - the repository that manages the available date operations
 * modelMapper: ModelMapper - the model mapper that is used to map the available date request dto to available date
 * Methods:
 * findAllAvailableDates: List<AvailableDate> - this method is used to find all the available dates
 * findAvailableDateById: AvailableDate - this method is used to find an available date by its id
 * createAvailableDate: AvailableDate - this method is used to create an available date
 * updateAvailableDate: AvailableDate - this method is used to update an available date
 * deleteAvailableDate: String - this method is used to delete an available date
 * findByDoctorIdAndDate: Optional<AvailableDate> - this method is used to find an available date by doctor id and date
 * 
 */


package com.veterinary.management.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.veterinary.management.request.AvailableDateRequestDto;
import com.veterinary.management.entity.AvailableDate;
import com.veterinary.management.repository.AvailableDateRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AvailableDateService {

    private final AvailableDateRepository availableDateRepository;
    private final ModelMapper modelMapper;

    public List<AvailableDate> findAllAvailableDates (){
        return availableDateRepository.findAll();
    }

    public AvailableDate findAvailableDateById (Long id){
        return availableDateRepository.findById(id).orElseThrow(() -> new RuntimeException("id:" + id + "Available Date could not found!!!"));
    }

    public AvailableDate createAvailableDate(AvailableDateRequestDto availableDateRequestDto){
        Optional<AvailableDate> existAvailableDateWithSameSpecs = availableDateRepository.findAvailableDateAndDoctorIdOrDoctorEmail(availableDateRequestDto.getAvailableDate()
                                                                    ,availableDateRequestDto.getDoctor().getId(), availableDateRequestDto.getDoctor().getEmail());

        if (existAvailableDateWithSameSpecs.isPresent()){
            throw new RuntimeException("The available Date has already been saved.");
        }
        AvailableDate newAvailableDate = modelMapper.map(availableDateRequestDto, AvailableDate.class);
        System.out.println(newAvailableDate.toString());
        return availableDateRepository.save(newAvailableDate);
    }

    public AvailableDate updateAvailableDate (Long id, AvailableDateRequestDto availableDateRequestDto){
        Optional<AvailableDate> availableDateFromDb = availableDateRepository.findById(id);
        Optional<AvailableDate> existOtherAvailableDateFromRequest = availableDateRepository.findAvailableDateAndDoctorIdOrDoctorEmail(availableDateRequestDto.getAvailableDate()
                                                                        ,availableDateRequestDto.getDoctor().getId(), availableDateRequestDto.getDoctor().getEmail());

        if (availableDateFromDb.isEmpty()){
            throw new RuntimeException("id:" + id + "available date could not found!!!");
        }

        if (existOtherAvailableDateFromRequest.isPresent() && !existOtherAvailableDateFromRequest.get().getId().equals(id)){
            throw new RuntimeException("This available date has already been registered. That's why this request causes duplicate data");
        }

        AvailableDate updatedAvailableDate = availableDateFromDb.get();
        modelMapper.map(availableDateRequestDto, updatedAvailableDate); // AvailableDateRequestDto -> AvailableDate
        return availableDateRepository.save(updatedAvailableDate);
    }

    public String deleteAvailableDate (Long id){
        Optional<AvailableDate> availableDateFromDb = availableDateRepository.findById(id);

        if (availableDateFromDb.isEmpty()){
            throw new RuntimeException("This available date with id :" + id + " could not found!!!");
        }
        else {
            availableDateRepository.delete(availableDateFromDb.get());
            return "Available date deleted.";
        }
    }

    public Optional<AvailableDate> findByDoctorIdAndDate(Long id, LocalDate localDate) {
        return availableDateRepository.findByDoctorIdAndAvailableDate(id, localDate);
    }
}
