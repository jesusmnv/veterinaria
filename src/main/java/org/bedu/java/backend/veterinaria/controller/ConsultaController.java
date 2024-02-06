package org.bedu.java.backend.veterinaria.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.bedu.java.backend.veterinaria.dto.consulta.CreateConsultaDTO;
import org.bedu.java.backend.veterinaria.dto.consulta.ConsultaDTO;
import org.bedu.java.backend.veterinaria.dto.consulta.UpdateConsultaDTO;
import org.bedu.java.backend.veterinaria.exception.ConsultaNotFoundException;
import org.bedu.java.backend.veterinaria.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Endpoints de Consultas", description = "CRUD de Consultas")
@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private ConsultaService service;

    @Autowired
    public ConsultaController(ConsultaService service) {
        this.service = service;
    }

    @Operation(summary = "Obtiene la lista de todos las consultas")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ConsultaDTO> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Crea una nueva consulta")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConsultaDTO save(@Valid @RequestBody CreateConsultaDTO data) {
        return service.save(data);
    }

    @Operation(summary = "Actualiza la información de una consulta")
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @Valid @RequestBody UpdateConsultaDTO updateData) throws ConsultaNotFoundException {
        service.update(id, updateData);
    }

    @Operation(summary = "Elimina una consulta existente")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws ConsultaNotFoundException {
        service.deleteById(id);
    }

}
