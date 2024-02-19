package org.bedu.java.backend.veterinaria.controller;

import java.util.List;
import java.util.Optional;

import org.bedu.java.backend.veterinaria.dto.vet.CreateVetDTO;
import org.bedu.java.backend.veterinaria.dto.vet.UpdateVetDTO;
import org.bedu.java.backend.veterinaria.dto.vet.VetDTO;
import org.bedu.java.backend.veterinaria.exception.VetNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.bedu.java.backend.veterinaria.service.VetService;

@Tag(name = "Endpoints de vet", description = "Vet's CRUD")
@RestController
@RequestMapping("/vets")
public class VetController {

    private VetService service;

    @Autowired
    public VetController(VetService service) {
        this.service = service;
    }

    @Operation(summary = "Get the list of vets")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<VetDTO> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Search for a vet in the database")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<VetDTO> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @Operation(summary = "Create a new vet")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VetDTO save(@Valid @RequestBody CreateVetDTO data) {
        return service.save(data);
    }

    @Operation(summary = "Update the information of vet")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @Valid @RequestBody UpdateVetDTO updatedData)
            throws VetNotFoundException {
        service.update(id, updatedData);
    }

    @Operation(summary = "Delete a vet of database")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws VetNotFoundException {
        service.deleteById(id);
    }

}