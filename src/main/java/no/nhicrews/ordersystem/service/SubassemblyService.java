package no.nhicrews.ordersystem.service;

import jakarta.persistence.EntityNotFoundException;
import no.nhicrews.ordersystem.model.Subassembly;
import no.nhicrews.ordersystem.repository.SubassemblyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SubassemblyService {
    private final SubassemblyRepository subassemblyRepository;

    @Autowired
    public SubassemblyService(SubassemblyRepository subassemblyRepository){
        this.subassemblyRepository = subassemblyRepository;
    }
    public Subassembly addSubassembly(Subassembly subassembly) {
        return subassemblyRepository.save(subassembly);
    }

    public Subassembly getSubassemblyById(Long id) {
        return subassemblyRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Couldnt find subassembly with ID" + id));
    }

    public Page<Subassembly> getAllSubassemblies(Pageable pageable) {
        return subassemblyRepository.findAll(pageable);
    }

    public Subassembly updateSubassembly(Long id, Subassembly updatedSubassembly) {
        return subassemblyRepository.findById(id).map(subassembly -> {
            subassembly.setName(updatedSubassembly.getName());
            return subassemblyRepository.save(subassembly);
        }).orElseThrow(() -> new EntityNotFoundException("Couldnt find subassembly with ID" + id));
    }

    public void deleteSubassembly(Long id) {
        if (!subassemblyRepository.existsById(id)) {
            throw new EntityNotFoundException("Couldn't find subassembly with ID " + id);
        }
        subassemblyRepository.deleteById(id);
    }
}
