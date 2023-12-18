package no.nhicrews.ordersystem.controller;


import jakarta.persistence.EntityNotFoundException;
import no.nhicrews.ordersystem.model.Address;
import no.nhicrews.ordersystem.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    //using field injections in order to inject AddressService
    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService){
        this.addressService = addressService;
    }


    @GetMapping
    public ResponseEntity<Page<Address>> getAllAddresses(Pageable pageable) {
        //Handles the get requests to /addresses, returning a page of addresses
        return ResponseEntity.ok(addressService.getAllAddresses(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id) {
        //handles the get request to /addresses/{id}, returning it by ID or 404 if it doesn't exist
        try {
            Address address = addressService.getAddressById(id);
            return ResponseEntity.ok(address);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Address> addAddress(@RequestBody Address address) {
        //Handles post requests to /addresses, and adding a new address
        //also returns it
        Address newAddress = addressService.addAddress(address);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAddress);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable Long id, @RequestBody Address address) {
        //Handles the put request, and updates the address
        Address updatedAddress = addressService.updateAddress(id, address);
        return ResponseEntity.ok(updatedAddress);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        //Handles delete requests, and deletes address with corresponding ID
        addressService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }
}
