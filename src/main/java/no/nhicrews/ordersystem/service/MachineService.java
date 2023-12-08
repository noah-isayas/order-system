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
    private final MachineRepository machineRepository;

    @Autowired
    public MachineService(MachineRepository machineRepository){
        this.machineRepository = machineRepository;
    }

    public Machine addMachine(Machine machine){
        return machineRepository.save(machine);
    }
    public Machine getMachineById(Long id) {
        return machineRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("no machine found with id" + id));
    }

    public Page<Machine> getAllMachines(Pageable pageable) {
        return machineRepository.findAll(pageable);
    }

    public Machine updateMachine(Long id, Machine updatedMachine) {
        return machineRepository.findById(id).map(machine -> {
            machine.setModel(updatedMachine.getModel());
            return machineRepository.save(machine);
        }).orElseThrow(() -> new EntityNotFoundException("no machine found with id" + id));
    }

    public void deleteMachine(Long id) {
        if (!machineRepository.existsById(id)) {
            throw new EntityNotFoundException("no machine found with id" + id);
        }
        machineRepository.deleteById(id);
    }
}
