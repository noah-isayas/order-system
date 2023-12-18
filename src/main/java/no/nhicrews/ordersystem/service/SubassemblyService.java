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

    //Injecting SubbassemblyRepository in order to interact with the DB
    private final SubassemblyRepository subassemblyRepository;

    @Autowired
    public SubassemblyService(SubassemblyRepository subassemblyRepository){
        this.subassemblyRepository = subassemblyRepository;
    }

    //adds subassembly to the DB, and returns it
    public Subassembly addSubassembly(Subassembly subassembly) {
        return subassemblyRepository.save(subassembly);
    }

    //retrieves a subassembly by its ID, or throws exception if not found
    public Subassembly getSubassemblyById(Long id) {
        return subassemblyRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Couldnt find subassembly with ID" + id));
    }

    //Returns paginated list of all subassemblies
    public Page<Subassembly> getAllSubassemblies(Pageable pageable) {
        return subassemblyRepository.findAll(pageable);
    }

    //updates subassembly by its id, or throws exception if not found
    public Subassembly updateSubassembly(Long id, Subassembly updatedSubassembly) {
        return subassemblyRepository.findById(id).map(subassembly -> {
            subassembly.setName(updatedSubassembly.getName());
            return subassemblyRepository.save(subassembly);
        }).orElseThrow(() -> new EntityNotFoundException("Couldnt find subassembly with ID" + id));
    }

    //deletes subassembly by its ID, or throws exception if not found
    public void deleteSubassembly(Long id) {
        if (!subassemblyRepository.existsById(id)) {
            throw new EntityNotFoundException("Couldn't find subassembly with ID " + id);
        }
        subassemblyRepository.deleteById(id);
    }
}
