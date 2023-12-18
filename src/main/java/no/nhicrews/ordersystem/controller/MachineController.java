package no.nhicrews.ordersystem.controller;

import no.nhicrews.ordersystem.model.Machine;
import no.nhicrews.ordersystem.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/machines")
public class MachineController {

    //using field injections in order to inject MachineService
    private final MachineService machineService;

    @Autowired
    public MachineController (MachineService machineService){
        this.machineService = machineService;
    }

    //handles get requests to /machines, and returns a page of objects
    @GetMapping
    public ResponseEntity<Page<Machine>> getAllMachines(Pageable pageable){
        return ResponseEntity.ok(machineService.getAllMachines(pageable));
    }
    // handles get request for returning a specific machine by ID
    @GetMapping("/{id}")
    public ResponseEntity<Machine> getMachineById(@PathVariable Long id) {
        return ResponseEntity.ok(machineService.getMachineById(id));
    }

    //handles post request to /machines, adding the machine and returning it
    @PostMapping
    public ResponseEntity<Machine> addMachine(@RequestBody Machine machine){
        Machine newMachine = machineService.addMachine(machine);
        return ResponseEntity.status(HttpStatus.CREATED).body(newMachine);
    }

    //handles put requests to /machine/{id} in order to update a specific machine
    @PutMapping("/{id}")
    public ResponseEntity<Machine> updateMachine(@PathVariable Long id, @RequestBody Machine machine) {
        Machine updateMachine = machineService.updateMachine(id, machine);
        return ResponseEntity.ok(updateMachine);
    }

    //handles delete requests in order to delete a machine with a specific ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMachine(@PathVariable Long id){
        machineService.deleteMachine(id);
        return ResponseEntity.noContent().build();
    }
}
