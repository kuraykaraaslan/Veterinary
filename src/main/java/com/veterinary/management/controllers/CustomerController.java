/*
 * This class is a controller that handles the requests for the customer entity.
 * 
 * Routes:
 * - GET /api/customers
 * - GET /api/customers/{id}
 * - POST /api/customers
 * - PUT /api/customers/{id}
 * - DELETE /api/customers/{id}
 */
package com.veterinary.management.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.veterinary.management.models.Animal;
import com.veterinary.management.models.Customer;
import com.veterinary.management.requests.CustomerRequest;
import com.veterinary.management.services.CustomerService;

import java.util.List;

/*
* This class is a controller that handles the requests for the customer entity.
*/
@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    /*
     * This method handles the request for getting all the customers.
     * 
     * @return a list of all the customers.
     */
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    /*
     * This method handles the request for getting a customer by its id.
     * 
     * @param id the id of the customer.
     * 
     * @return the customer with the given id.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    /*
     * This method handles the request for adding a new customer.
     * 
     * @param customerRequest the request for adding a new customer.
     * 
     * @return the added customer.
     */
    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody CustomerRequest customerRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.addCustomer(customerRequest));
    }

    /*
     * This method handles the request for updating a customer.
     * 
     * @param id the id of the customer.
     * 
     * @param customerRequest the request for updating a customer.
     * 
     * @return the updated customer.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id,
            @RequestBody CustomerRequest customerRequest) {
        return ResponseEntity.ok(customerService.updateCustomer(id, customerRequest));
    }

    /*
     * This method handles the request for deleting a customer.
     * 
     * @param id the id of the customer.
     * 
     * @return a message that confirms the deletion.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        customerService.deleteById(id);
        return ResponseEntity.ok("Customer with id " + id + " has been deleted.");
    }

    /*
     * This method handles the request for finding a customer by its name.
     * 
     * @param name the name of the customer.
     */
    @GetMapping("/findByName")
    public ResponseEntity<List<Customer>> findByName(@RequestParam String name) {
        return ResponseEntity.ok(customerService.findByName(name));
    }

    /*
     * This method handles the request for listing all the animals of a customer.
     */
    @GetMapping("/{id}/animals")
    public ResponseEntity<List<Animal>> getAnimalsByCustomerId(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);

        if (customer == null) {
            throw new UnsupportedOperationException("There is no customer with the given id");
        }

        return ResponseEntity.ok(customer.getAnimals());

    }

}
