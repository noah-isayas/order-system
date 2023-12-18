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

    @MockBean
    private PartRepository partRepository;

    @Autowired
    private PartService partService;

    @Test
    public void deletingPartShouldRemoveIt() {
        Long partId = 2L;
        when(partRepository.existsById(partId)).thenReturn(true);
        doNothing().when(partRepository).deleteById(partId);

        partService.deletePart(partId);

        verify(partRepository).deleteById(partId);

    }

    @Test
    public void addedPartShouldBeSaved() {
        Part part = new Part();
        part.setPartNumber("Hinge1");

        when(partRepository.save(any(Part.class))).thenReturn(part);

        Part savedPart = partService.addPart(part);

        assertNotNull(savedPart);
        assertEquals("Hinge1", savedPart.getPartNumber());
    }

}
