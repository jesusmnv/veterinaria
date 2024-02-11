package org.bedu.java.backend.veterinaria.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bedu.java.backend.veterinaria.dto.invoice.AddMedicationDTO;
import org.bedu.java.backend.veterinaria.dto.invoice.CreateInvoiceDTO;
import org.bedu.java.backend.veterinaria.dto.invoice.InvoiceDTO;
import org.bedu.java.backend.veterinaria.dto.invoice.UpdateInvoiceDTO;
import org.bedu.java.backend.veterinaria.exception.InvoiceNotFoundException;
import org.bedu.java.backend.veterinaria.exception.MedicationNotFoundException;
import org.bedu.java.backend.veterinaria.service.InvoiceMedicationService;
import org.bedu.java.backend.veterinaria.service.InvoiceService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class InvoiceControllerTest {

    @MockBean
    private InvoiceService service;

    @MockBean
    private InvoiceMedicationService serviceAdd;

    @Autowired
    private InvoiceController controller;

    @Test
    @DisplayName("Controller should be injected")
    void smokeTest() {
        assertNotNull(controller);
    }

    @Test
    @DisplayName("Controller regresa una lista de facturas")
    void findAllTest() {
        List<InvoiceDTO> data = new LinkedList<>();
        LocalDate fecha = LocalDate.parse("2023-12-12");
        InvoiceDTO factura = new InvoiceDTO(
                1L, fecha, 10, 15, 15, "dfsbvdsfb", "dfsgsfg", null);

        data.add(factura);

        when(service.findAll()).thenReturn(data);

        List<InvoiceDTO> result = controller.findAll();

        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertEquals(factura.getId(), result.get(0).getId());
        assertEquals(factura.getIssuanceDate(), result.get(0).getIssuanceDate());
        assertEquals(factura.getSubtotal(), result.get(0).getSubtotal());
        assertEquals(factura.getVat(), result.get(0).getVat());
        assertEquals(factura.getTotal(), result.get(0).getTotal());
        assertEquals(factura.getClientRFC(), result.get(0).getClientRFC());
        assertEquals(factura.getLegalName(), result.get(0).getLegalName());
        assertEquals(factura.getOwner(), result.get(0).getOwner());
    }

    @Test
    @DisplayName("Controller guarda una factura")
    void saveInvoice() {
        CreateInvoiceDTO dto = new CreateInvoiceDTO();
        LocalDate date = LocalDate.parse("2023-12-12");
        dto.setIssuanceDate(date);
        dto.setVat(15);
        dto.setLegalName("qwerty");
        dto.setClientRFC("ytrewq");
        dto.setSubtotal(15);
        dto.setTotal(115);
        dto.setOwner(null);

        InvoiceDTO invoiceDTO = new InvoiceDTO(1L, dto.getIssuanceDate(),
                dto.getSubtotal(), dto.getVat(), dto.getTotal(),
                dto.getClientRFC(), dto.getLegalName(), dto.getOwner());

        when(service.save(any(CreateInvoiceDTO.class))).thenReturn(invoiceDTO);

        InvoiceDTO result = controller.save(dto);

        assertNotNull(result);
        assertEquals(result, invoiceDTO);

    }

    @Test
    @DisplayName("Controller actualiza una factura")
    void updateTest() throws InvoiceNotFoundException {
        UpdateInvoiceDTO dto = new UpdateInvoiceDTO();
        dto.setLegalName("qwerty");
        dto.setClientRFC("qwerty");

        controller.update(400L, dto);
        verify(service, times(1)).update(400L, dto);
    }

    @Test
    @DisplayName("Controller elimina una factura")
    void deleteTest() throws InvoiceNotFoundException {
        controller.delete(400L);
        verify(service, times(1)).deleteById(400L);
    }

    @Test
    @DisplayName("Controller añade un medicaento a una factura")
    void addMedicationTest() throws MedicationNotFoundException {

        AddMedicationDTO add = new AddMedicationDTO();
        add.setMedicationId(1L);
        add.setQuantity(5);

        controller.addMedication(1L, add);
        verify(serviceAdd, times(1)).addMedication(1L, 1L, 5);

    }

    @Test
    @DisplayName("Controllador busca la información de los medicamentos asociados a una factura")
    void showMedicationsTest() {
        controller.findMedications(1L);
        verify(serviceAdd, times(1)).findMedicationsByInvoice(1L);
    }
}
