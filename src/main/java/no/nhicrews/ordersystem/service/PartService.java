package no.nhicrews.ordersystem.service;

import no.nhicrews.ordersystem.model.Part;
import no.nhicrews.ordersystem.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PartService {
    private final PartRepository partRepository;

    @Autowired
    public PartService(PartRepository partRepository){
        this.partRepository = partRepository;
    }
    public Part addPart(Part part) {
        return null;
    }

    public Part getPartById(Long id) {
        return null;
    }

    public Page<Part> getAllParts(Pageable pageable) {
        return null;
    }

    public Part updatePart(Long id, Part updatedPart) {
        return null;
    }

    public void deletePart(Long id) {
    }
}
