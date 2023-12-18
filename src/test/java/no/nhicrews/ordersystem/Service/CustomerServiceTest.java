package no.nhicrews.ordersystem.Service;

import jakarta.persistence.EntityNotFoundException;
import no.nhicrews.ordersystem.service.CustomerService;
import no.nhicrews.ordersystem.model.Customer;
import no.nhicrews.ordersystem.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerServiceTest {

    //Mocking in order to simulate repository interactions without relying on the actual DB
    @MockBean
    private CustomerRepository customerRepository;

    //CustomerService is injected into the test class using @Autowired to test its business logic
    @Autowired
    CustomerService customerService;

    @Test
    public void CustomerShouldBeSavedWhenAddingg() {
        // Tests if adding a new customer saves the customer correctly
        // , mocks a customer and verifies the saved customers name and email
        Customer customer = new Customer();
        customer.setName("Ola Nordmann");
        customer.setEmail("ola@nordmann.com");

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        Customer savedCustomer = customerService.addCustomer(customer);

        assertNotNull(savedCustomer);
        assertEquals("Ola Nordmann", savedCustomer.getName());
        assertEquals("ola@nordmann.com", savedCustomer.getEmail());
    }


    @Test
    public void GettingCustomerByIdShouldGetCorrectCustomer() {
        //This method tests getCustomerById in CustomerService.
        // It sets up a mock customer, retrieves it by ID, and asserts the returned customer matches the mock.
        Long customerId = 1L;
        Customer mockCustomer = new Customer();

        mockCustomer.setId(customerId);
        mockCustomer.setName("Test Customer");
        mockCustomer.setEmail("test@customer.com");

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(mockCustomer));

        Customer foundCustomer = customerService.getCustomerById(customerId);

        assertNotNull(foundCustomer);
        assertEquals(customerId, foundCustomer.getId());
        assertEquals("Test Customer", foundCustomer.getName());
        assertEquals("test@customer.com", foundCustomer.getEmail());
    }

    @Test
    public void NonExsistingIdShouldThrowException() {
        // Tests the service's response when trying to find a customer with a non-existing ID
        // Ensures that an EntityNotFoundException is thrown, indicating the customer doesn't exist
        Long customerId = 2L;
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());
        //Should Throw
        assertThrows(EntityNotFoundException.class, () -> {
            customerService.getCustomerById(customerId);
        });
    }
}

