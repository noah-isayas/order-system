package no.nhicrews.ordersystem.Service;

import no.nhicrews.ordersystem.model.Address;
import no.nhicrews.ordersystem.repository.AddressRepository;
import no.nhicrews.ordersystem.service.AddressService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AddressServiceTest {

    @MockBean
    private AddressRepository addressRepository;

    @Autowired
    private AddressService addressService;

    @Test
    public void getAddressByIdShouldReturnAddress() {
        Long addressId = 1L;
        Address mockAddress = new Address();

        mockAddress.setId(addressId);
        mockAddress.setCity("Hamar");
        mockAddress.setStreet("Testroad 123");

        when(addressRepository.findById(addressId)).thenReturn(Optional.of(mockAddress));

        Address foundAddress = addressService.getAddressById(addressId);

        assertNotNull(foundAddress);
        assertEquals(addressId, foundAddress.getId());
        assertEquals("Hamar", foundAddress.getCity());
        assertEquals("Testroad 123", foundAddress.getStreet());
    }
    @Test
    public void AddressShouldBeDeletedSuccessfully() {
        Long addressId = 1L;

        when(addressRepository.existsById(addressId)).thenReturn(true);
        doNothing().when(addressRepository).deleteById(addressId);

        addressService.deleteAddress(addressId);

        verify(addressRepository).deleteById(addressId);
    }

    @Test
    public void AddressShouldBeUpdated() {
        Address mockAddress = new Address();
        mockAddress.setStreet("prev street");
        mockAddress.setCity("prev city");

        Address updatedInfo = new Address();
        updatedInfo.setStreet("New street");
        updatedInfo.setCity("New city");

        when(addressRepository.findById(anyLong())).thenReturn(Optional.of(mockAddress));
        when(addressRepository.save(any(Address.class))).thenAnswer(invocation -> {
            Address addressToSave = invocation.getArgument(0);
            mockAddress.setStreet(addressToSave.getStreet());
            mockAddress.setCity(addressToSave.getCity());
            return mockAddress;
        });

        Address updatedAddress = addressService.updateAddress(1L, updatedInfo);

        assertNotNull(updatedAddress);
        assertEquals("New street", updatedAddress.getStreet());
        assertEquals("New city", updatedAddress.getCity());
    }
}
