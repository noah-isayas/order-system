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

    @GetMapping
    public ResponseEntity<Page<Machine>> getAllMachines(Pageable pageable){
        //handles get requests to /machines, and returns a page of objects
        return ResponseEntity.ok(machineService.getAllMachines(pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Machine> getMachineById(@PathVariable Long id) {
        // handles get request for returning a specific machine by ID
        return ResponseEntity.ok(machineService.getMachineById(id));
    }

    @PostMapping
    public ResponseEntity<Machine> addMachine(@RequestBody Machine machine){
        //handles post request to /machines, adding the machine and returning it
        Machine newMachine = machineService.addMachine(machine);
        return ResponseEntity.status(HttpStatus.CREATED).body(newMachine);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Machine> updateMachine(@PathVariable Long id, @RequestBody Machine machine) {
        //handles put requests to /machine/{id} in order to update a specific machine
        Machine updateMachine = machineService.updateMachine(id, machine);
        return ResponseEntity.ok(updateMachine);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMachine(@PathVariable Long id){
        //handles delete requests in order to delete a machine with a specific ID
        machineService.deleteMachine(id);
        return ResponseEntity.noContent().build();
    }
}
