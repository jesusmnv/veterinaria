package org.bedu.java.backend.veterinaria.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.bedu.java.backend.veterinaria.dto.consultation.ConsultationDTO;
import org.bedu.java.backend.veterinaria.dto.consultation.CreateConsultationDTO;
import org.bedu.java.backend.veterinaria.dto.consultation.UpdateConsultationDTO;
import org.bedu.java.backend.veterinaria.exception.ConsultationNotFoundException;
import org.bedu.java.backend.veterinaria.service.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Endpoints of Consultations", description = "CRUD of Consultations")
@RestController
@RequestMapping("/consultations")
public class ConsultationController {

    private ConsultationService service;

    @Autowired
    public ConsultationController(ConsultationService service) {
        this.service = service;
    }

    @Operation(summary = "Get the list of all consultations")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ConsultationDTO> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Create a new consultation")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConsultationDTO save(@Valid @RequestBody CreateConsultationDTO data) {
        return service.save(data);
    }

    @Operation(summary = "Update consultation information")
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @Valid @RequestBody UpdateConsultationDTO updateData)
            throws ConsultationNotFoundException {
        service.update(id, updateData);
    }

    @Operation(summary = "Delete an existing consultation")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws ConsultationNotFoundException {
        service.deleteById(id);
    }

}
