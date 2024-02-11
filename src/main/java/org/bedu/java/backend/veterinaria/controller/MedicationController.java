package org.bedu.java.backend.veterinaria.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.bedu.java.backend.veterinaria.dto.medication.CreateMedicationDTO;
import org.bedu.java.backend.veterinaria.dto.medication.MedicationDTO;
import org.bedu.java.backend.veterinaria.dto.medication.UpdateMedicationDTO;
import org.bedu.java.backend.veterinaria.exception.MedicationNotFoundException;
import org.bedu.java.backend.veterinaria.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Endpoints of Medications", description = "CRUD of Medications")
@RestController
@RequestMapping("/medications")
public class MedicationController {

    private MedicationService service;

    @Autowired
    public MedicationController(MedicationService service) {
        this.service = service;
    }

    @Operation(summary = "Get the list of all medications")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MedicationDTO> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Create a new medication")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MedicationDTO save(@Valid @RequestBody CreateMedicationDTO data) {
        return service.save(data);
    }

    @Operation(summary = "Update medication information")
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @Valid @RequestBody UpdateMedicationDTO updateData)
            throws MedicationNotFoundException {
        service.update(id, updateData);
    }

    @Operation(summary = "Delete an existing medication")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws MedicationNotFoundException {
        service.deleteById(id);
    }

}