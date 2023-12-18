package no.nhicrews.ordersystem.Service;

import no.nhicrews.ordersystem.model.Subassembly;
import no.nhicrews.ordersystem.repository.SubassemblyRepository;
import no.nhicrews.ordersystem.service.SubassemblyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SubassemblyServiceTest {

    //Mocking in order to simulate repository interactions without relying on the actual DB
    @MockBean
    private SubassemblyRepository subassemblyRepository;

    //SubassemblyService is injected into the test class using @Autowired to test its business logic
    @Autowired
    private SubassemblyService subassemblyService;

    @Test
    public void deletingSubassemblyShouldRemoveIt() {
        //This method ensures the service correctly handles deletion.
        // It mocks the existence of a subassembly and verifies the repository's deleteById method is called.
        Long subassemblyId = 2L;
        when(subassemblyRepository.existsById(subassemblyId)).thenReturn(true);
        doNothing().when(subassemblyRepository).deleteById(subassemblyId);

        subassemblyService.deleteSubassembly(subassemblyId);

        verify(subassemblyRepository).deleteById(subassemblyId);
    }

    @Test
    public void addedSubassemblyShouldBeSaved() {
        // Tests if adding a new subassembly saves the subassembly correctly
        // , mocks a subassembly and verifies the saved model name
        Subassembly subassembly = new Subassembly();
        subassembly.setName("engine for vaporizor of death");

        when(subassemblyRepository.save(any(Subassembly.class))).thenReturn(subassembly);

        Subassembly savedSubassembly = subassemblyService.addSubassembly(subassembly);

        assertNotNull(savedSubassembly);
        assertEquals("engine for vaporizor of death", savedSubassembly.getName());
    }


}
