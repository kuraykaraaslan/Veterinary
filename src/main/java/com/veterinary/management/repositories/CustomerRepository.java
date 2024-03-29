package com.veterinary.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.veterinary.management.models.Customer;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByEmail(String email);
    Optional<Customer> findByNameAndEmail(String name, String email);
    List<Customer> findByNameContaining(String name);
    
}
