package org.bedu.java.backend.veterinaria.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.bedu.java.backend.veterinaria.dto.medicamento.CreateMedicamentoDTO;
import org.bedu.java.backend.veterinaria.dto.medicamento.MedicamentoDTO;
import org.bedu.java.backend.veterinaria.dto.medicamento.UpdateMedicamentoDTO;
import org.bedu.java.backend.veterinaria.exception.MedicamentoNotFoundException;
import org.bedu.java.backend.veterinaria.service.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Endpoints de Medicamentos", description = "CRUD de Medicamentos")
@RestController
@RequestMapping("/medicamentos")
public class MedicamentoController {

    @Autowired
    private MedicamentoService service;

    @Operation(summary = "Obtiene la lista de todos los medicamentos")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MedicamentoDTO> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Crea un nuevo medicamento")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MedicamentoDTO save(@Valid @RequestBody CreateMedicamentoDTO data) {
        return service.save(data);
    }

    @Operation(summary = "Actualiza la información de un medicamento")
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @Valid @RequestBody UpdateMedicamentoDTO updateData) throws MedicamentoNotFoundException {
        service.update(id, updateData);
    }

    @Operation(summary = "Elimina un medicamento existente")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws MedicamentoNotFoundException {
        service.deleteById(id);
    }

}