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

    @MockBean
    private MachineRepository machineRepository;

    @Autowired
    private MachineService machineService;

    @Test
    public void MachineShouldBeSavedWhenAdding() {
        Machine machine = new Machine();
        machine.setModel("Lever");

        when(machineRepository.save(any(Machine.class))).thenReturn(machine);

        Machine savedMachine = machineService.addMachine(machine);

        assertNotNull(savedMachine);
        assertEquals("Lever", savedMachine.getModel());
    }

    @Test
    public void DeletingMachineShouldRemoveIt() {
        Long machineId = 1L;
        when(machineRepository.existsById(machineId)).thenReturn(true);
        doNothing().when(machineRepository).deleteById(machineId);

        machineService.deleteMachine(machineId);

        verify(machineRepository).deleteById(machineId);
    }

    @Test
    public void GettingMachineByIdShouldReturnCorrectMachine() {
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
        Long machineId = 2L;
        when(machineRepository.findById(machineId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            machineService.getMachineById(machineId);
        });
    }
}
