package no.nhicrews.ordersystem.controller;

import no.nhicrews.ordersystem.model.Part;
import no.nhicrews.ordersystem.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parts")
public class PartController {

    //using field injections in order to inject PartService
    private final PartService partService;

    @Autowired
    public PartController (PartService partService) {
        this.partService = partService;
    }

    @GetMapping
    public ResponseEntity<Page<Part>> getAllParts (Pageable pageable) {
        // handles get requests to /parts, and returning a page
        return ResponseEntity.ok(partService.getAllParts(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Part> getPartById (@PathVariable Long id) {
        //handles get requests to /parts/{id}, returning a specific order by its ID
        return ResponseEntity.ok(partService.getPartById(id));
    }

    @PostMapping
    public ResponseEntity<Part> addPart(@RequestBody Part part) {
        //Handles post requests to /parts, adding a new part and returning it
        Part newPart = partService.addPart(part);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPart);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Part> updatePart(@PathVariable Long id, @RequestBody Part part) {
        //Handles put requests to /parts/{id}, updating a specific part by its ID
        Part updatedPart = partService.updatePart(id, part);
        return ResponseEntity.ok(updatedPart);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePart (@PathVariable Long id) {
        // handles delete requests to /parts/{id}, deleting a part by its ID
        partService.deletePart(id);
        return ResponseEntity.noContent().build();
    }
}
