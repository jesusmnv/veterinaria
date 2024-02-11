package org.bedu.java.backend.veterinaria.controller;

import java.util.List;

import org.bedu.java.backend.veterinaria.dto.appointment.AppointmentDTO;
import org.bedu.java.backend.veterinaria.dto.appointment.CreateAppointmentDTO;
import org.bedu.java.backend.veterinaria.dto.appointment.UpdateAppointmentDTO;
import org.bedu.java.backend.veterinaria.exception.AppointmentNotFoundException;
import org.bedu.java.backend.veterinaria.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Endpoints de Appointments", description = "CRUD of Appointment")
@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private AppointmentService service;

    @Autowired
    public AppointmentController(AppointmentService service) {
        this.service = service;
    }

    @Operation(summary = "Get the list of all appointments")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AppointmentDTO> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Create a new appointment")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentDTO save(@Valid @RequestBody CreateAppointmentDTO data) {
        return service.save(data);
    }

    @Operation(summary = "Update the information of an appointment")
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable long id, @Valid @RequestBody UpdateAppointmentDTO updateData)
            throws AppointmentNotFoundException {
        service.update(id, updateData);
    }

    @Operation(summary = "Delete an existing appointment")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws AppointmentNotFoundException {
        service.deleteById(id);
    }

}