package com.veterinary.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.veterinary.management.models.Vaccine;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Long> {

    List<Vaccine> findByAnimalId(Long id);
    List<Vaccine> findVaccinesByAnimalIdAndApplicationDateBetween(Long animalId, LocalDate startDate, LocalDate endDate);
    
}
