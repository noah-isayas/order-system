package no.nhicrews.ordersystem.service;

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
        return null;
    }
    public Address getAddressById(Long id) {
        return null;
    }
    public Page<Address> getAllAddresses(Pageable pageable){
        return null;
    }
    public Address updateAddress(Long id, Address updatedAddress){
        return null;
    }
    public void deleteAddress(Long id){

    }
}
