package no.nhicrews.ordersystem.service;

import no.nhicrews.ordersystem.model.Customer;
import no.nhicrews.ordersystem.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer addCustomer(Customer customer) {
        return null;
    }
    public Customer getCustomerById (Long id){
        return null;
    }

    public Page<Customer> getAllCustomers(Pageable pageable) {
        return null;
    }

    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        return null;
    }

    public void deleteCustomer(Long id) {

    }
}
