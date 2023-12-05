package no.nhicrews.ordersystem.service;

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
        return null;
    }

    public Subassembly getSubassemblyById(Long id) {
        return null;
    }

    public Page<Subassembly> getAllSubassemblies(Pageable pageable) {
        return null;
    }

    public Subassembly updateSubassembly(Long id, Subassembly updatedSubassembly) {
        return null;
    }

    public void deleteSubassembly(Long id) {
    }
}
