package org.bedu.java.backend.veterinaria.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.bedu.java.backend.veterinaria.dto.pet.CreatePetDTO;
import org.bedu.java.backend.veterinaria.dto.pet.PetDTO;
import org.bedu.java.backend.veterinaria.dto.pet.UpdatePetDTO;
import org.bedu.java.backend.veterinaria.exception.PetNotFoundException;
import org.bedu.java.backend.veterinaria.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Pet's Endpoints", description = "Pet's CRUD")
@RestController
@RequestMapping("/pets")
public class PetController {

    private PetService service;

    @Autowired
    public PetController(PetService service) {
        this.service = service;
    }

    @Operation(summary = "Get list of pets")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PetDTO> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Serch for a pet in the database")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void findById(@PathVariable Long id) {
        service.findById(id);
    }

    @Operation(summary = "Create a new pet")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PetDTO save(@Valid @RequestBody CreatePetDTO data) {
        return service.save(data);
    }

    @Operation(summary = "Update pet info")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable Long id, @Valid @RequestBody UpdatePetDTO updatedData)
            throws PetNotFoundException {
        service.update(id, updatedData);
    }

    @Operation(summary = "Removes a pet from the database")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws PetNotFoundException {
        service.deleteById(id);
    }

}