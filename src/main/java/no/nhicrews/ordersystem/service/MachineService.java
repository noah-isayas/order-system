package no.nhicrews.ordersystem.service;

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
        return null;
    }
    public Machine getMachineById(Long id) {
        return null;
    }

    public Page<Machine> getAllMachines(Pageable pageable) {
        return null;
    }

    public Machine updateMachine(Long id, Machine updatedMachine) {
        return null;
    }

    public void deleteMachine(Long id) {

    }
}
