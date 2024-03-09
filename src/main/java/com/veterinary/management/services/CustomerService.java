package com.veterinary.management.services;

import java.util.List;
import org.springframework.stereotype.Service;

import com.veterinary.management.repositories.CustomerRepository;
import com.veterinary.management.requests.CustomerRequest;
import com.veterinary.management.models.Customer;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    /*
     * This method deletes a customer by id
     */
    public void deleteById(Long id) {
        if (id == null) {
            return;
        }
        customerRepository.deleteById(id);
    }


    /*
     * This method returns all customers
     * @return List<Customer>
     * 
     */
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    /*
     * This method returns a customer by id
     * @param Long id
     * @return Customer
     * 
     */
    public Customer getCustomerById(Long id) {
        if (id == null) {
            return null;
        }
        return customerRepository.findById(id).orElse(null);
    }

    /*
     * This method adds a customer
     * @param CustomerRequest customerRequest
     * @return Customer
     * 
     */

    public Customer addCustomer(CustomerRequest customerRequest) {
        Customer customer = new Customer();
        customer.setName(customerRequest.getName());
        customer.setEmail(customerRequest.getEmail());
        customer.setPhone(customerRequest.getPhone());
        customer.setAddress(customerRequest.getAddress());
        customer.setCity(customerRequest.getCity());
        return customerRepository.save(customer);
    }


    /*
     * This method updates a customer
     * @param Long id
     * @param CustomerRequest customerRequest
     * @return Customer
     * 
     */
    public Customer updateCustomer(Long id, CustomerRequest customerRequest) {
        if (id == null) {
            return null;
        }
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            customer.setName(customerRequest.getName());
            customer.setEmail(customerRequest.getEmail());
            customer.setPhone(customerRequest.getPhone());
            customer.setAddress(customerRequest.getAddress());
            customer.setCity(customerRequest.getCity());
            return customerRepository.save(customer);
        }
        return null;
    }

    /*
     * This method returns a customer by name and email
     * @param String name
     * @param String email
     * @return Customer
     * 
     */

    public Customer getCustomerByNameAndEmail(String name, String email) {
        return customerRepository.findByNameAndEmail(name, email).orElse(null);
    }


    /*
     * This method returns a customer by name
     * @param String name
     * @return Customer
     * 
     */
    public List<Customer> findByName(String name) {
        return customerRepository.findByNameContaining(name);
    }

}

