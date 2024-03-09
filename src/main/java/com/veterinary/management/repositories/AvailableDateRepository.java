package com.veterinary.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.veterinary.management.models.AvailableDate;

@Repository
public interface AvailableDateRepository extends JpaRepository<AvailableDate, Long> {

}
