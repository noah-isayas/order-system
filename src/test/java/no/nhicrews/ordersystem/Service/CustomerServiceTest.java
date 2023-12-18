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

    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    CustomerService customerService;

    @Test
    public void whenAddCustomer_thenCustomerShouldBeSaved() {
        Customer customer = new Customer();
        customer.setName("John Doe");
        customer.setEmail("john.doe@example.com");

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        Customer savedCustomer = customerService.addCustomer(customer);

        assertNotNull(savedCustomer);
        assertEquals("John Doe", savedCustomer.getName());
        assertEquals("john.doe@example.com", savedCustomer.getEmail());
    }
    @Test
    public void whenGetCustomerById_thenCustomerShouldBeReturned() {
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
    public void whenGetCustomerById_withNonExistentId_thenThrowEntityNotFoundException() {
        Long customerId = 2L;
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            customerService.getCustomerById(customerId);
        });
    }
}

