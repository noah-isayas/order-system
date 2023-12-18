package no.nhicrews.ordersystem.service;

import jakarta.persistence.EntityNotFoundException;
import no.nhicrews.ordersystem.model.Address;
import no.nhicrews.ordersystem.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    //injection of AddressRepository in order to interact with the DB
    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository){
        this.addressRepository = addressRepository;
    }

    //adds a new address to the db and returns it
    public Address addAddress(Address address){
        return addressRepository.save(address);
    }

    //retrieves address by id, or throws exception if not found
    public Address getAddressById(Long id) {
        return addressRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Couldn't find address by" + id));
    }

    //Returns paginated list of addresses
    public Page<Address> getAllAddresses(Pageable pageable){
        return addressRepository.findAll(pageable);
    }

    // updates an address by its id, and returns it
    public Address updateAddress(Long id, Address updatedAddress){
        return addressRepository.findById(id).map(address-> {
            address.setStreet(updatedAddress.getStreet());
            address.setCity(updatedAddress.getCity());
            return addressRepository.save(address);
        }).orElseThrow(() -> new EntityNotFoundException("Couldnt find any address with" + id));
    }

    // deletes an address by id, or throws exception if not found
    public void deleteAddress(Long id){
        if (!addressRepository.existsById(id)) {
            throw new EntityNotFoundException("Couldnt find any address with" + id);
        }
        addressRepository.deleteById(id);
    }
}
