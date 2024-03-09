package com.veterinary.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.veterinary.management.models.Animal;
import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    
    List<Animal> findByNameContaining(String name);
    List<Animal> findByCustomerId(Long id);
    
}
