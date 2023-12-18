package no.nhicrews.ordersystem.Service;

import no.nhicrews.ordersystem.model.Part;
import no.nhicrews.ordersystem.repository.PartRepository;
import no.nhicrews.ordersystem.service.PartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PartServiceTest {

    //Mocking in order to simulate repository interactions without relying on the actual DB
    @MockBean
    private PartRepository partRepository;

    //PartService is injected into the test class using @Autowired to test its business logic
    @Autowired
    private PartService partService;

    @Test
    public void deletingPartShouldRemoveIt() {
        //This method ensures the service correctly handles deletion.
        // It mocks the existence of a part and verifies the repository's deleteById method is called.
        Long partId = 2L;
        when(partRepository.existsById(partId)).thenReturn(true);
        doNothing().when(partRepository).deleteById(partId);

        partService.deletePart(partId);

        verify(partRepository).deleteById(partId);

    }

    @Test
    public void addedPartShouldBeSaved() {
        // Tests if adding a new part saves the part correctly
        // , mocks a part and verifies the saved partnumber
        Part part = new Part();
        part.setPartNumber("Hinge1");

        when(partRepository.save(any(Part.class))).thenReturn(part);

        Part savedPart = partService.addPart(part);

        assertNotNull(savedPart);
        assertEquals("Hinge1", savedPart.getPartNumber());
    }

}
