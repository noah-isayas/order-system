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
    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository){
        this.addressRepository = addressRepository;
    }
    public Address addAddress(Address address){
        return addressRepository.save(address);
    }
    public Address getAddressById(Long id) {
        return addressRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Couldn't find address by" + id));
    }
    public Page<Address> getAllAddresses(Pageable pageable){
        return addressRepository.findAll(pageable);
    }
    public Address updateAddress(Long id, Address updatedAddress){
        return addressRepository.findById(id).map(address-> {
            address.setStreet(updatedAddress.getStreet());
            address.setCity(updatedAddress.getCity());
            return addressRepository.save(address);
        }).orElseThrow(() -> new EntityNotFoundException("Couldnt find any address with" + id));
    }
    public void deleteAddress(Long id){
        if (!addressRepository.existsById(id)) {
            throw new EntityNotFoundException("Couldnt find any address with" + id);
        }
        addressRepository.deleteById(id);
    }
}
