/*
 * This class is used to provide the service for the customer
 * 
 * Methods:
 * findAllCustomers()
 * findCustomerById(Long id)
 * findCustomersByName(String name)
 * createCustomer(CustomerRequestDto customerRequestDto)
 * updateCustomer(Long id, CustomerRequestDto customerRequestDto)
 * deleteCustomer(Long id)
 * 
 * Attributes:
 * customerRepository - This is the repository for the Customer entity
 * modelMapper - This is the model mapper for the Customer entity
 */


package com.veterinary.management.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.veterinary.management.request.CustomerRequestDto;
import com.veterinary.management.entity.Customer;
import com.veterinary.management.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public List<Customer> findAllCustomers (){
        return customerRepository.findAll();
    }

    public Customer findCustomerById (Long id){
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("id:" + id + "Customer could not found!!!"));
    }

    public List<Customer> findCustomersByName(String name) {
        return customerRepository.findByNameContaining(name);
    }

    public Customer createCustomer(CustomerRequestDto customerRequestDto){
        Optional<Customer> existCustomerWithSameSpecs = customerRepository.findByNameAndEmail(customerRequestDto.getName(), customerRequestDto.getMail());

        if (existCustomerWithSameSpecs.isPresent()){
            throw new RuntimeException("The Customer has already been saved.");
        }
        Customer newCustomer = modelMapper.map(customerRequestDto, Customer.class);
        return customerRepository.save(newCustomer);
    }

    public Customer updateCustomer (Long id, CustomerRequestDto customerRequestDto){
        Optional<Customer> customerFromDb = customerRepository.findById(id);
        Optional<Customer> existOtherCustomerFromRequest = customerRepository.findByNameAndEmail(customerRequestDto.getName(), customerRequestDto.getMail());

        if (customerFromDb.isEmpty()){
            throw new RuntimeException("id:" + id + "Customer could not found!!!");
        }

        if (existOtherCustomerFromRequest.isPresent() && !existOtherCustomerFromRequest.get().getId().equals(id)){
            throw new RuntimeException("This Customer has already been registered. That's why this request causes duplicate data");
        }

        Customer updatedCustomer = customerFromDb.get();
        modelMapper.map(customerRequestDto, updatedCustomer);
        return customerRepository.save(updatedCustomer);
    }


    public String deleteCustomer (Long id){
        Optional<Customer> customerFromDb = customerRepository.findById(id);
        if (customerFromDb.isEmpty()){
            throw new RuntimeException("This doctor could not found!!!");
        }
        else {
            customerRepository.delete(customerFromDb.get());
            return "Customer deleted.";
        }
    }



}
