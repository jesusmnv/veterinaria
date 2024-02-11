package org.bedu.java.backend.veterinaria.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.bedu.java.backend.veterinaria.dto.owner.CreateOwnerDTO;
import org.bedu.java.backend.veterinaria.dto.owner.OwnerDTO;
import org.bedu.java.backend.veterinaria.dto.owner.UpdateOwnerDTO;
import org.bedu.java.backend.veterinaria.exception.OwnerNotFoundException;
import org.bedu.java.backend.veterinaria.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Owner's endpoints", description = "Owner's CRUD")
@RestController
@RequestMapping("/owners")
public class OwnerController {

    private OwnerService service;

    @Autowired
    public OwnerController(OwnerService service) {
        this.service = service;
    }

    @Operation(summary = "Gets the list of all owners")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OwnerDTO> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Create a new owner")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OwnerDTO save(@Valid @RequestBody CreateOwnerDTO data) {
        return service.save(data);
    }

    @Operation(summary = "Update owner info")
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @Valid @RequestBody UpdateOwnerDTO updateData)
            throws OwnerNotFoundException {
        service.update(id, updateData);
    }

    @Operation(summary = "Delete an existing owner")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws OwnerNotFoundException {
        service.deleteById(id);
    }

}