package com.veterinary.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.veterinary.management.entity.Vaccine;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Long> {


    //List<Vaccine> findByNameAndCodeAndProtectionFinishDateGreaterThanEqual(String name, String code, LocalDate protectionStartDate);

    List<Vaccine> findByNameAndCodeAndAnimalIdAndProtectionFinishDateGreaterThanEqual(String name, String code, Long id, LocalDate protectionStartDate);

    List<Vaccine> findByAnimalId(Long id);

    List<Vaccine> findByProtectionFinishDateBetween(LocalDate startDate, LocalDate endDate);
}
