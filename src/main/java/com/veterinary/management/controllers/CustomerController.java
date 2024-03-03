/*
 * This class is used to handle the customer related requests
 * It is a parameterized class as it is annotated with the @RestController annotation
 * 
 * Routes:
 *   GET /api/customers
 *   GET /api/customers/{id}
 *   GET /api/customers/searchByName?name=
 *   POST /api/customers
 *   PUT /api/customers/{id}
 *   DELETE /api/customers/{id}
 */

package com.veterinary.management.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.veterinary.management.request.CustomerRequestDto;
import com.veterinary.management.entity.Customer;
import com.veterinary.management.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    public final CustomerService customerService;

    @GetMapping
    public List<Customer> findAllCustomers() {
        return customerService.findAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer findCustomerById(@PathVariable Long id) {
        return customerService.findCustomerById(id);
    }

    @GetMapping("/searchByName")
    public List<Customer> findCustomersByName(@RequestParam String name) {
        return customerService.findCustomersByName(name);
    }

    @PostMapping
    public Customer createCustomer(@RequestBody CustomerRequestDto customerRequestDto) {
        return customerService.createCustomer(customerRequestDto);
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody CustomerRequestDto customerRequestDto) {
        return customerService.updateCustomer(id, customerRequestDto);
    }

    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        return customerService.deleteCustomer(id);
    }

}
