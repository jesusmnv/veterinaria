/* package org.bedu.java.backend.veterinaria.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.bedu.java.backend.veterinaria.dto.propietario.CreatePropietarioDTO;
import org.bedu.java.backend.veterinaria.dto.propietario.PropietarioDTO;
import org.bedu.java.backend.veterinaria.dto.propietario.UpdatePropietarioDTO;
import org.bedu.java.backend.veterinaria.exception.PropietarioNotFoundException;
import org.bedu.java.backend.veterinaria.service.PropietarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Endpoints de Propietarios", description = "CRUD de Propietarios")
@RestController
@RequestMapping("/propietarios")
public class PropietarioController {

    @Autowired
    private PropietarioService service;

    @Operation(summary = "Obtiene la lista de las propietarios")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PropietarioDTO> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Crea una nueva propietario")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PropietarioDTO save(@Valid @RequestBody CreatePropietarioDTO data) {
        return service.save(data);
    }

    @Operation(summary = "Actualiza la información de una propietario")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable Long id, @Valid @RequestBody UpdatePropietarioDTO updatedData)
            throws PropietarioNotFoundException {
        service.update(id, updatedData);
    }

    @Operation(summary = "Elimina una propietario de la base de datos")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws PropietarioNotFoundException {
        service.deleteById(id);
    }

} */

package org.bedu.java.backend.veterinaria.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.bedu.java.backend.veterinaria.dto.propietario.CreatePropietarioDTO;
import org.bedu.java.backend.veterinaria.dto.propietario.PropietarioDTO;
import org.bedu.java.backend.veterinaria.dto.propietario.UpdatePropietarioDTO;
import org.bedu.java.backend.veterinaria.exception.PropietarioNotFoundException;
import org.bedu.java.backend.veterinaria.service.PropietarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Endpoints de Propietarios", description = "CRUD de Propietarios")
@RestController
@RequestMapping("/propietarios")
public class PropietarioController {

    private PropietarioService service;

    @Autowired
    public PropietarioController(PropietarioService service) {
        this.service = service;
    }

    @Operation(summary = "Obtiene la lista de todos los propietarios")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PropietarioDTO> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Crea un nuevo propietario")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PropietarioDTO save(@Valid @RequestBody CreatePropietarioDTO data) {
        return service.save(data);
    }

    @Operation(summary = "Actualiza la información de un propietario")
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @Valid @RequestBody UpdatePropietarioDTO updateData)
            throws PropietarioNotFoundException {
        service.update(id, updateData);
    }

    @Operation(summary = "Elimina un propietario existente")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws PropietarioNotFoundException {
        service.deleteById(id);
    }

}