/*
 * This class is used to provide the service for the customer
 * It is annotated with the @Service annotation
 * The CustomerService class is a parameterized class as it is annotated with the @Service annotation
 * The CustomerService class is used to provide the service for the Customer entity
 * Methods:
 * getCustomers: List<Customer> - this method is used to get all the customers
 * addNewCustomer: void - this method is used to add a new customer
 * deleteCustomer: void - this method is used to delete a customer
 * updateCustomer: void - this method is used to update a customer
 * getCustomerById: Customer - this method is used to get a customer by its id
 * getCustomerByName: Customer - this method is used to get a customer by its name
 * getCustomerByPhoneNumber: Customer - this method is used to get a customer by its phone number
 * getCustomerByEmail: Customer - this method is used to get a customer by its email
 * 
 * Attributes:
 * id: Long - the id of the customer
 * name: String - the name of the customer
 * surname: String - the surname of the customer
 * dateOfBirth: LocalDate - the date of birth of the customer
 * phoneNumber: String
 * email: String
 * address: String
 * animals: List<Animal> - the animals that the customer owns
 * appointments: List<Appointment> - the appointments that the customer has
 */


package com.veterinary.management.services;

import com.veterinary.management.entities.Customer;
import com.veterinary.management.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
   
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerByPhoneNumber(String phoneNumber) {
        return customerRepository.findCustomerByPhoneNumber(phoneNumber);
    }
    
    public Customer getCustomerByEmail(String email) {
        return customerRepository.findCustomerByEmail(email);
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findCustomerById(id);
    }

    public void addNewCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        boolean exists = customerRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Customer with id " + id + " does not exist");
        }
        customerRepository.deleteById(id);
    }

    public void updateCustomer(Long id, Customer customer) {
        Customer customerOptional = customerRepository.findById(id).orElse(null);
        if (!customerOptional.getId().equals(id)) {
            throw new IllegalStateException("Customer with id " + id + " does not exist");
        }
        customer.setId(id);
        customerRepository.save(customer);
    }

    public Customer getCustomerByName(String name) {
        return customerRepository.findCustomerByName(name);
    }

    public List<Customer> searchCustomerByName(String name) {
        return customerRepository.findCustomersByName(name);
    }

}