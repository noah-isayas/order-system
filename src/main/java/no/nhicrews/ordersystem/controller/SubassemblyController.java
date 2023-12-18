package no.nhicrews.ordersystem.controller;

import no.nhicrews.ordersystem.model.Subassembly;
import no.nhicrews.ordersystem.service.SubassemblyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subassemblies")
public class SubassemblyController {

    //using field injections in order to inject SubassemblyService
    private final SubassemblyService subassemblyService;

    @Autowired
    public SubassemblyController (SubassemblyService subassemblyService) {
        this.subassemblyService = subassemblyService;
    }
    //Handles get requests to /subassemblies and returning a page
    @GetMapping
    public ResponseEntity<Page<Subassembly>> getAllSubassemblies(Pageable pageable) {
        return ResponseEntity.ok(subassemblyService.getAllSubassemblies(pageable));
    }

    // handles get requests to /subassemblies/{id}, returning a specific subassembly by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Subassembly> getSubassemblyById(@PathVariable Long id) {
        return ResponseEntity.ok(subassemblyService.getSubassemblyById(id));
    }

    //handles post requests to /subassemblies, adding a object and returning it
    @PostMapping
    public ResponseEntity<Subassembly> addSubassembly(@RequestBody Subassembly subassembly) {
        Subassembly newSubassembly = subassemblyService.addSubassembly(subassembly);
        return ResponseEntity.status(HttpStatus.CREATED).body(newSubassembly);
    }

    //handles put requests to /subassemblies/{id}, updating the object by its ID
    @PutMapping("/{id}")
    public ResponseEntity<Subassembly> updateSubassembly(@PathVariable Long id, @RequestBody Subassembly subassembly) {
        Subassembly updatedSubassembly = subassemblyService.updateSubassembly(id, subassembly);
        return ResponseEntity.ok(updatedSubassembly);
    }

    //handles delete requests to /subassemblies/{id}, deleting a specific object by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubassembly (@PathVariable Long id) {
        subassemblyService.deleteSubassembly(id);
        return ResponseEntity.noContent().build();
    }
}
