package org.bedu.java.backend.veterinaria.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.bedu.java.backend.veterinaria.dto.mascota.CreateMascotaDTO;
import org.bedu.java.backend.veterinaria.dto.mascota.MascotaDTO;
import org.bedu.java.backend.veterinaria.dto.mascota.UpdateMascotaDTO;
import org.bedu.java.backend.veterinaria.exception.MascotaNotFoundException;
import org.bedu.java.backend.veterinaria.service.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Endpoints de Mascotas", description = "CRUD de Mascotas")
@RestController
@RequestMapping("/mascotas")
public class MascotaController {

    @Autowired
    private MascotaService service;

    /*
     * @Autowired
     * public MascotaController(MascotaService service) {
     * this.service = service;
     * }
     */

    @Operation(summary = "Obtiene la lista de las mascotas")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MascotaDTO> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Busca una mascota en la base de datos")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void findById(@PathVariable Long id) throws MascotaNotFoundException {
        service.findById(id);
    }

    @Operation(summary = "Crea una nueva mascota")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MascotaDTO save(@Valid @RequestBody CreateMascotaDTO data) {
        return service.save(data);
    }

    @Operation(summary = "Actualiza la información de una mascota")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable Long id, @Valid @RequestBody UpdateMascotaDTO updatedData)
            throws MascotaNotFoundException {
        service.update(id, updatedData);
    }

    @Operation(summary = "Elimina una mascota de la base de datos")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws MascotaNotFoundException {
        service.deleteById(id);
    }

}