/*
 * This class is used to handle the customer related requests
 * It is a parameterized class as it is annotated with the @RestController annotation
 * 
 * Routes:
 * /api/customers - GET: get all the customers, Returns ResponseEntity<List<Customer>>
 * /api/customers - POST: add a new customer, Returns ResponseEntity<Customer>
 * /api/customers/{customerId} - DELETE: delete a customer, Returns ResponseEntity<?>
 * /api/customers/{customerId} - PUT: update a customer, Returns ResponseEntity<Customer>
 * /api/customers/{customerId} - GET: get a customer by its id, Returns ResponseEntity<Customer>
 * /api/customers/{customerId}/animals - GET: get all the animals of a customer, Returns ResponseEntity<List<Animal>>
 * 
 * Search:
 * /api/customers/search - GET: search for a customer by its name, Returns ResponseEntity<List<Customer>>
 */

package com.veterinary.management.controllers;

import com.veterinary.management.entities.Customer;
import com.veterinary.management.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping(path = "api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /*
     * This method is used to add a new customer
     */
    @GetMapping
    public ResponseEntity<?> getCustomers() {
        try {
            List<Customer> customers = customerService.getCustomers();
            return new ResponseEntity<>(customers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }   

    /*
     * This method is used to add a new customer
     */
    @PostMapping
    public ResponseEntity<?> addNewCustomer(@RequestBody Customer customer) {
    
        //Check if the customer already exists with the same phone number
        Customer customerByPhoneNumber = customerService.getCustomerByPhoneNumber(customer.getPhoneNumber());
        if (customerByPhoneNumber != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        //Check if the customer already exists with the same email
        Customer customerByEmail = customerService.getCustomerByEmail(customer.getEmail());
        if (customerByEmail != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        customerService.addNewCustomer(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    /*
     * This method is used to delete a customer
     */
    @DeleteMapping(path = "{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("customerId") Long customerId) {
        
        //Check if the customer exists
        Customer customer = customerService.getCustomerById(customerId);
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        customerService.deleteCustomer(customerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*
     * This method is used to update a customer
     */
    @PutMapping(path = "{customerId}")
    public ResponseEntity<?> updateCustomer(@PathVariable("customerId") Long customerId, @RequestBody Customer customer) {
        
        //Check if the customer exists
        Customer customerById = customerService.getCustomerById(customerId);
        if (customerById == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        customerService.updateCustomer(customerId, customer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    /*
     * This method is used to get a customer by its id
     */
    @GetMapping(path = "{customerId}")
    public ResponseEntity<?> getCustomerById(@PathVariable("customerId") Long customerId) {
        
        //Check if the customer exists
        Customer customer = customerService.getCustomerById(customerId);
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    /*
     * This method is used to get all the animals of a customer
     */
    @GetMapping(path = "{customerId}/animals")
    public ResponseEntity<?> getAnimalsOfCustomer(@PathVariable("customerId") Long customerId) {
        
        //Check if the customer exists
        Customer customer = customerService.getCustomerById(customerId);
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<>(customer.getAnimals(), HttpStatus.OK);
    }

    /*
     * This method is used to get all the appointments of a customer
     */
    @GetMapping(path = "{customerId}/appointments")
    public ResponseEntity<?> getAppointmentsOfCustomer(@PathVariable("customerId") Long customerId) {
        
        //Check if the customer exists
        Customer customer = customerService.getCustomerById(customerId);
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<>(customer.getAppointments(), HttpStatus.OK);
    }

    /*
     * This method is used to search for a customer by its name
     */
    @GetMapping(path = "search")
    public ResponseEntity<?> searchCustomerByName(@RequestParam String name) {
        try {
            List<Customer> customers = customerService.searchCustomerByName(name);
            return new ResponseEntity<>(customers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
