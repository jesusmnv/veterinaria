package org.bedu.java.backend.veterinaria.controller;

import java.util.List;

import org.bedu.java.backend.veterinaria.dto.veterinario.CreateVeterinarioDTO;
import org.bedu.java.backend.veterinaria.dto.veterinario.UpdateVeterinarioDTO;
import org.bedu.java.backend.veterinaria.dto.veterinario.VeterinarioDTO;
import org.bedu.java.backend.veterinaria.exception.VeterinarioNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.bedu.java.backend.veterinaria.service.VeterinarioService;

@Tag(name = "Endpoints de vet", description = "CRUD de vet")
@RestController
@RequestMapping("/veterinarios")
public class VeterinarioController {

    private VeterinarioService service;

    @Autowired
    public VeterinarioController(VeterinarioService service) {
        this.service = service;
    }

    @Operation(summary = "Obtiene la lista de veterinarios")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<VeterinarioDTO> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Crea un nuevo vet")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VeterinarioDTO save(@Valid @RequestBody CreateVeterinarioDTO data) {
        return service.save(data);
    }

    @Operation(summary = "Actualiza la información de un vet")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable Long id, @Valid @RequestBody UpdateVeterinarioDTO updatedData)
            throws VeterinarioNotFoundException {
        service.update(id, updatedData);
    }

    @Operation(summary = "Elimina un vet de la base de datos")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws VeterinarioNotFoundException {
        service.deleteById(id);
    }

}