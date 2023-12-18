package no.nhicrews.ordersystem.service;

import jakarta.persistence.EntityNotFoundException;
import no.nhicrews.ordersystem.model.Machine;
import no.nhicrews.ordersystem.repository.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MachineService {

    //Injection of machinrepository in order to interact with the db
    private final MachineRepository machineRepository;

    @Autowired
    public MachineService(MachineRepository machineRepository){
        this.machineRepository = machineRepository;
    }

    //adds a machine to the DB and returns it
    public Machine addMachine(Machine machine){
        return machineRepository.save(machine);
    }

    //Returns a machine by its id, or throws exception if not found
    public Machine getMachineById(Long id) {
        return machineRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("no machine found with id" + id));
    }

    // Returns a paginated list of machines
    public Page<Machine> getAllMachines(Pageable pageable) {
        return machineRepository.findAll(pageable);
    }

    //Updates an existing machine, and returns it
    public Machine updateMachine(Long id, Machine updatedMachine) {
        return machineRepository.findById(id).map(machine -> {
            machine.setModel(updatedMachine.getModel());
            return machineRepository.save(machine);
        }).orElseThrow(() -> new EntityNotFoundException("no machine found with id" + id));
    }

    //Deletes machine by its id, or throws exception if not found
    public void deleteMachine(Long id) {
        if (!machineRepository.existsById(id)) {
            throw new EntityNotFoundException("no machine found with id" + id);
        }
        machineRepository.deleteById(id);
    }
}
