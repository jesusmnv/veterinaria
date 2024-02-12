package org.bedu.java.backend.veterinaria.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.bedu.java.backend.veterinaria.dto.invoice.AddMedicationDTO;
import org.bedu.java.backend.veterinaria.dto.invoice.CreateInvoiceDTO;
import org.bedu.java.backend.veterinaria.dto.invoice.InvoiceDTO;
import org.bedu.java.backend.veterinaria.dto.invoice.UpdateInvoiceDTO;
import org.bedu.java.backend.veterinaria.dto.medication.MedicationDTO;
import org.bedu.java.backend.veterinaria.exception.InvoiceNotFoundException;
import org.bedu.java.backend.veterinaria.exception.MedicationNotFoundException;
import org.bedu.java.backend.veterinaria.service.InvoiceMedicationService;
import org.bedu.java.backend.veterinaria.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Endpoints of invoices", description = "Invoices CRUD")
@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    private InvoiceService service;
    private InvoiceMedicationService invoiceMedicationService;

    @Autowired
    public InvoiceController(InvoiceService service, InvoiceMedicationService invoiceMedicationService) {
        this.service = service;
        this.invoiceMedicationService = invoiceMedicationService;
    }

    @Operation(summary = "Gets the list of all invoices")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceDTO> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Register an invoice")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceDTO save(@Valid @RequestBody CreateInvoiceDTO data) {
        return service.save(data);
    }

    @Operation(summary = "Update an invoice")
    @PutMapping("{invoiceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable(name = "invoiceId") Long invoiceId, @RequestBody UpdateInvoiceDTO data)
            throws InvoiceNotFoundException {
        service.update(invoiceId, data);
    }

    @Operation(summary = "Delete an invoice")
    @DeleteMapping("{invoiceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(name = "invoiceId") Long invoiceId) throws InvoiceNotFoundException {
        service.deleteById(invoiceId);
    }

    // ==========================================================
    // Nuevos métodos
    @Operation(summary = "Associate a medication with an invoice")
    @PostMapping("{invoiceId}/medications")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addMedication(@PathVariable Long invoiceId,
            @RequestBody AddMedicationDTO data) throws MedicationNotFoundException {
        invoiceMedicationService.addMedication(invoiceId, data.getMedicationId(), data.getQuantity());
    }

    @Operation(summary = "Obtains medications from a given invoice")
    @GetMapping("{invoiceId}/medications")
    @ResponseStatus(HttpStatus.OK)
    public List<MedicationDTO> findMedications(@PathVariable Long invoiceId) {
        return invoiceMedicationService.findMedicationsByInvoice(invoiceId);
    }

}