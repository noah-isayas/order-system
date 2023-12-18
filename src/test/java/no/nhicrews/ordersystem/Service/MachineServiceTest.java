package no.nhicrews.ordersystem.Service;

import jakarta.persistence.EntityNotFoundException;
import no.nhicrews.ordersystem.model.Machine;
import no.nhicrews.ordersystem.repository.MachineRepository;
import no.nhicrews.ordersystem.service.MachineService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class MachineServiceTest {

    //Mocking in order to simulate repository interactions without relying on the actual DB
    @MockBean
    private MachineRepository machineRepository;

    //MachineService is injected into the test class using @Autowired to test its business logic
    @Autowired
    private MachineService machineService;

    @Test
    public void MachineShouldBeSavedWhenAdding() {
        // Tests if adding a new machine saves the machine correctly
        // Mocks a machine and verifies the saved model
        Machine machine = new Machine();
        machine.setModel("Lever");

        when(machineRepository.save(any(Machine.class))).thenReturn(machine);

        Machine savedMachine = machineService.addMachine(machine);

        assertNotNull(savedMachine);
        assertEquals("Lever", savedMachine.getModel());
    }

    @Test
    public void DeletingMachineShouldRemoveIt() {
        //This method ensures the service correctly handles deletion.
        // It mocks the existence of a machine and verifies the repository's deleteById method is called.
        Long machineId = 1L;
        when(machineRepository.existsById(machineId)).thenReturn(true);
        doNothing().when(machineRepository).deleteById(machineId);

        machineService.deleteMachine(machineId);

        verify(machineRepository).deleteById(machineId);
    }

    @Test
    public void GettingMachineByIdShouldReturnCorrectMachine() {
        //This method tests getMachineById in MachineService.
        // It sets up a mock machine, retrieves it by ID, and asserts the returned machine matches the mock.
        Long machineId = 1L;
        Machine mockMachine = new Machine();
        mockMachine.setId(machineId);
        mockMachine.setModel("Vaporizor of death");

        when(machineRepository.findById(machineId)).thenReturn(Optional.of(mockMachine));

        Machine foundMachine = machineService.getMachineById(machineId);

        assertNotNull(foundMachine);
        assertEquals(machineId, foundMachine.getId());
        assertEquals("Vaporizor of death", foundMachine.getModel());
    }
    @Test
    public void NonExsistingIdShouldThrowException() {
        // Tests the service's response when trying to find a machine with a non-existing ID
        // Ensures that an EntityNotFoundException is thrown, indicating the machine doesn't exist
        Long machineId = 2L;
        when(machineRepository.findById(machineId)).thenReturn(Optional.empty());
        //should throw
        assertThrows(EntityNotFoundException.class, () -> {
            machineService.getMachineById(machineId);
        });
    }
}
