/*
 * This class is a repository for the WorkingDay entity
 * It extends the JpaRepository interface
 * The WorkingDayRepository interface is a parameterized interface as it extends the JpaRepository interface
 * Attributes:
 * id: Long - the id of the working day
 * Veterinarian: Veterinarian - the Veterinarian that works on the day
 * day: String - the day of the week
 */

package com.veterinary.management.repositories;

import com.veterinary.management.entities.WorkingDay;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.veterinary.management.entities.Veterinarian;

@Repository
public interface WorkingDayRepository extends JpaRepository<WorkingDay, Long> {

    WorkingDay findByVeterinarianAndDay(Veterinarian veterinarian, LocalDate day);
}
