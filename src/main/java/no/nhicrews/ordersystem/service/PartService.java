package no.nhicrews.ordersystem.service;

import jakarta.persistence.EntityNotFoundException;
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
        return partRepository.save(part);
    }

    public Part getPartById(Long id) {
        return partRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("couldnt find part with ID" + id));
    }

    public Page<Part> getAllParts(Pageable pageable) {
        return partRepository.findAll(pageable);
    }

    public Part updatePart(Long id, Part updatedPart) {
        return partRepository.findById(id).map(part -> {
            part.setPartNumber(updatedPart.getPartNumber());
            return partRepository.save(part);
        }).orElseThrow(() -> new EntityNotFoundException("couldnt find part with ID" + id));
    }

    public void deletePart(Long id) {
        if (!partRepository.existsById(id)){
            throw new EntityNotFoundException("couldnt find part with ID" + id);
        }
        partRepository.deleteById(id);
    }
}
