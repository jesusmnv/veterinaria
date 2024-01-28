package org.bedu.java.backend.veterinaria.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.bedu.java.backend.veterinaria.dto.factura.CreateFacturaDTO;
import org.bedu.java.backend.veterinaria.dto.factura.FacturaDTO;
import org.bedu.java.backend.veterinaria.dto.factura.UpdateFacturaDTO;
import org.bedu.java.backend.veterinaria.exception.FacturaNotFoundException;
import org.bedu.java.backend.veterinaria.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Endpoints de Facturas", description = "CRUD de Facturas")
@RestController
@RequestMapping("/facturas")
public class FacturaController {

    @Autowired
    private FacturaService service;

    @Operation(summary = "Obtiene la lista de las facturas")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FacturaDTO> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Crea una nueva factura")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FacturaDTO save(@Valid @RequestBody CreateFacturaDTO data) {
        return service.save(data);
    }

    @Operation(summary = "Actualiza la información de una factura")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable Long id, @Valid @RequestBody UpdateFacturaDTO updatedData)
            throws FacturaNotFoundException {
        service.update(id, updatedData);
    }

    @Operation(summary = "Elimina una factura de la base de datos")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws FacturaNotFoundException {
        service.deleteById(id);
    }

}