package no.nhicrews.ordersystem.service;

import jakarta.persistence.EntityNotFoundException;
import no.nhicrews.ordersystem.model.Address;
import no.nhicrews.ordersystem.model.Customer;
import no.nhicrews.ordersystem.repository.CustomerRepository;
import no.nhicrews.ordersystem.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, AddressRepository addressRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
    }

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer addAddressToCustomer (Long customerId, Address address) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new EntityNotFoundException("Couldnt find customer with id" + customerId));
        address.setCustomer(customer);
        addressRepository.save(address);
        return customer;
    }
    public Customer getCustomerById (Long id){
        return customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No customer found with that ID" + id));
    }

    public Page<Customer> getAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        return customerRepository.findById(id).map(customer -> {
            customer.setName(updatedCustomer.getName());
            customer.setEmail(updatedCustomer.getEmail());
            return customerRepository.save(customer);
        }).orElseThrow(() -> new EntityNotFoundException("No customer found with that ID" + id));
    }


    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new EntityNotFoundException("No customer found with that ID" + id);
        }
        customerRepository.deleteById(id);

    }
}
