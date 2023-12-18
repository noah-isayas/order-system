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

    //Injecting repositories in order to interact with the db
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, AddressRepository addressRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
    }

    //Adds a new customer to db and returns it
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    //associates an address to a customer, and saves it
    public Customer addAddressToCustomer (Long customerId, Address address) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new EntityNotFoundException("Couldnt find customer with id" + customerId));
        address.setCustomer(customer);
        addressRepository.save(address);
        return customer;
    }

    //Retrieves a Customer by their ID, and throws exception if not found
    public Customer getCustomerById (Long id){
        return customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No customer found with that ID" + id));
    }

    //returns paginated list of customers
    public Page<Customer> getAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    //Updates an exsisting customers details, and returns the updated customer
    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        return customerRepository.findById(id).map(customer -> {
            customer.setName(updatedCustomer.getName());
            customer.setEmail(updatedCustomer.getEmail());
            return customerRepository.save(customer);
        }).orElseThrow(() -> new EntityNotFoundException("No customer found with that ID" + id));
    }


    //Delete customer by their id if they exist
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new EntityNotFoundException("No customer found with that ID" + id);
        }
        customerRepository.deleteById(id);

    }
}
