package org.bedu.java.backend.veterinaria.controller;

import java.util.List;
import org.bedu.java.backend.veterinaria.dto.cita.CitaDTO;
import org.bedu.java.backend.veterinaria.dto.cita.CreateCitaDTO;
import org.bedu.java.backend.veterinaria.dto.cita.UpdateCitaDTO;
import org.bedu.java.backend.veterinaria.exception.CitaNotFoundException;
import org.bedu.java.backend.veterinaria.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Endpoints de Cita", description = "CRUD de Citas")
@RestController
@RequestMapping("/citas")
public class CitaController {

    private CitaService service;

    @Autowired
    public CitaController(CitaService service) {
        this.service = service;
    }

    @Operation(summary = "Obtiene la lista de todos las citas")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CitaDTO> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Crea una nueva cita")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CitaDTO save(@Valid @RequestBody CreateCitaDTO data) {
        return service.save(data);
    }

    @Operation(summary = "Actualiza la información de una cita")
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable long l, @Valid @RequestBody UpdateCitaDTO updateData)
            throws CitaNotFoundException {
        service.update(l, updateData);
    }

    @Operation(summary = "Elimina una cita existente")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws CitaNotFoundException {
        service.deleteById(id);
    }

}