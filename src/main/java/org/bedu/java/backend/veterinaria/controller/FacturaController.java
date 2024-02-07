package org.bedu.java.backend.veterinaria.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.bedu.java.backend.veterinaria.dto.factura.AddMedicamentoDTO;
import org.bedu.java.backend.veterinaria.dto.factura.CreateFacturaDTO;
import org.bedu.java.backend.veterinaria.dto.factura.FacturaDTO;
import org.bedu.java.backend.veterinaria.dto.factura.UpdateFacturaDTO;
import org.bedu.java.backend.veterinaria.dto.medicamento.MedicamentoDTO;
import org.bedu.java.backend.veterinaria.exception.FacturaNotFoundException;
import org.bedu.java.backend.veterinaria.service.FacturaMedicamentoService;
import org.bedu.java.backend.veterinaria.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Endpoints de Facturas", description = "CRUD de Facturas")
@RestController
@RequestMapping("/facturas")
public class FacturaController {

    private FacturaService service;
    private FacturaMedicamentoService facturaMedicamentoService;

    @Autowired
    public FacturaController(FacturaService service, FacturaMedicamentoService facturaMedicamentoService) {
        this.service = service;
        this.facturaMedicamentoService = facturaMedicamentoService;
    }

    @Operation(summary = "Obtiene la lista de todas las facturas")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FacturaDTO> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Registra una factura")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FacturaDTO save(@Valid @RequestBody CreateFacturaDTO data) {
        return service.save(data);
    }

    @Operation(summary = "Actualiza una factura")
    @PutMapping("{facturaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable(name = "facturaId") Long facturaId, @RequestBody UpdateFacturaDTO data)
            throws FacturaNotFoundException {
        service.update(facturaId, data);
    }

    @Operation(summary = "Elimina una factura")
    @DeleteMapping("{facturaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(name = "facturaId") Long facturaId) throws FacturaNotFoundException {
        service.deleteById(facturaId);
    }

    // ==========================================================
    // Nuevos métodos
    @Operation(summary = "Asocia un medicamento a una factura")
    @PostMapping("{facturaId}/medicamentos")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addMedicamento(@PathVariable Long facturaId,
            @RequestBody AddMedicamentoDTO data) {
        facturaMedicamentoService.addMedicamento(facturaId, data.getMedicamentoId(), data.getPrecio(),
                data.getCantidad());
    }

    @Operation(summary = "Obtiene los medicamentos de una factura determinada")
    @GetMapping("{facturaId}/medicamentos")
    @ResponseStatus(HttpStatus.OK)
    public List<MedicamentoDTO> findMedicamentos(@PathVariable Long facturaId) {
        return facturaMedicamentoService.findMedicamentosByFactura(facturaId);
    }

}