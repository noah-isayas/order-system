package no.nhicrews.ordersystem.controller;

import no.nhicrews.ordersystem.model.Address;
import no.nhicrews.ordersystem.model.Customer;
import no.nhicrews.ordersystem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    //using field injections in order to inject CustomerService
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<Page<Customer>> getAllCustomers(Pageable pageable){
        // Handles the get request /customers, and returns a page
        return ResponseEntity.ok(customerService.getAllCustomers(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id){
        //handles get request to /address/{id}, returning the customer
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
        //handles post requests to /customers, and returns the customer if added
        Customer newCustomer = customerService.addCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCustomer);
    }

    @PostMapping("/{customerId}/addAddress")
    public ResponseEntity<Customer> addAddressToCustomer(@PathVariable Long customerId, @RequestBody Address address) {
        // adds an address to an existing customer, this is done additionally to what was required
        Customer customer = customerService.addAddressToCustomer(customerId, address);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        // updates and existing customer with a put request
        Customer updatedCustomer = customerService.updateCustomer(id, customer);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        //handles delete requests to /customers/{id}, allowing user to delete a customer
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

}
