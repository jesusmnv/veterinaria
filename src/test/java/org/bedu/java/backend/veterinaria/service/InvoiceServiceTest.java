package org.bedu.java.backend.veterinaria.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.bedu.java.backend.veterinaria.dto.invoice.CreateInvoiceDTO;
import org.bedu.java.backend.veterinaria.dto.invoice.InvoiceDTO;
import org.bedu.java.backend.veterinaria.dto.invoice.UpdateInvoiceDTO;
import org.bedu.java.backend.veterinaria.exception.InvoiceNotFoundException;
import org.bedu.java.backend.veterinaria.model.Invoice;
import org.bedu.java.backend.veterinaria.repository.InvoiceRepository;
import org.junit.jupiter.api.AfterEach;
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
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class InvoiceServiceTest {

    @MockBean
    private InvoiceRepository repository;

    @Autowired
    private InvoiceService service;

    @Test
    @DisplayName("Service should be injected")
    void smokeTest() {
        assertNotNull(service);
    }

    @AfterEach
    public void setup() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("Search for an invoice by Id")
    void findByIdTest() {

        Invoice invoice = new Invoice();
        LocalDate fecha = LocalDate.parse("2023-12-12");
        invoice.setId(1L);

        invoice.setIssuanceDate(fecha);
        invoice.setVat(1);
        invoice.setLegalName("qwerty");
        invoice.setClientRFC("ytrewq");
        invoice.setSubtotal(150);
        invoice.setTotal(1500);
        invoice.setOwner(null);

        when(repository.findById(anyLong())).thenReturn(Optional.of(invoice));

        Optional<InvoiceDTO> result = service.findById(invoice.getId());

        assertNotNull(result.get());
        assertEquals(invoice.getId(), result.get().getId());
        assertEquals(invoice.getIssuanceDate(), result.get().getIssuanceDate());
        assertEquals(invoice.getSubtotal(), result.get().getSubtotal());
        assertEquals(invoice.getVat(), result.get().getVat());
        assertEquals(invoice.getTotal(), result.get().getTotal());
        assertEquals(invoice.getClientRFC(), result.get().getClientRFC());
        assertEquals(invoice.getLegalName(), result.get().getLegalName());
        assertEquals(invoice.getOwner(), result.get().getOwner());

    }

    @Test
    @DisplayName("Service returns a list of invoices")
    void findAllTest() {

        List<Invoice> data = new LinkedList<>();
        Invoice invoice = new Invoice();

        LocalDate fecha = LocalDate.parse("2023-12-12");
        invoice.setId(1L);

        invoice.setIssuanceDate(fecha);
        invoice.setVat(1);
        invoice.setLegalName("qwerty");
        invoice.setClientRFC("ytrewq");
        invoice.setSubtotal(150);
        invoice.setTotal(1500);
        invoice.setOwner(null);

        data.add(invoice);

        when(repository.findAll()).thenReturn(data);

        List<InvoiceDTO> result = service.findAll();

        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertEquals(invoice.getId(), result.get(0).getId());
        assertEquals(invoice.getIssuanceDate(), result.get(0).getIssuanceDate());
        assertEquals(invoice.getSubtotal(), result.get(0).getSubtotal());
        assertEquals(invoice.getVat(), result.get(0).getVat());
        assertEquals(invoice.getTotal(), result.get(0).getTotal());
        assertEquals(invoice.getClientRFC(), result.get(0).getClientRFC());
        assertEquals(invoice.getLegalName(), result.get(0).getLegalName());
        assertEquals(invoice.getOwner(), result.get(0).getOwner());

    }

    @Test
    @DisplayName("Services saves an invoice")
    void saveTest() {
        LocalDate fecha = LocalDate.parse("2023-12-12");
        CreateInvoiceDTO dto = new CreateInvoiceDTO();

        dto.setIssuanceDate(fecha);
        dto.setVat(1);
        dto.setOwner(null);
        dto.setLegalName("qwerty");
        dto.setClientRFC("ytrewq");
        dto.setSubtotal(15);
        dto.setTotal(1500);

        Invoice invoice = new Invoice();
        invoice.setIssuanceDate(dto.getIssuanceDate());
        invoice.setId(1L);
        invoice.setVat(dto.getVat());
        invoice.setOwner(dto.getOwner());
        invoice.setLegalName(dto.getLegalName());
        invoice.setClientRFC(dto.getClientRFC());
        invoice.setSubtotal(dto.getSubtotal());
        invoice.setTotal(dto.getTotal());

        when(repository.save(any(Invoice.class))).thenReturn(invoice);

        InvoiceDTO result = service.save(dto);
        assertNotNull(result);
        assertEquals(invoice.getId(), result.getId());
        assertEquals(invoice.getIssuanceDate(), result.getIssuanceDate());
        assertEquals(invoice.getSubtotal(), result.getSubtotal());
        assertEquals(invoice.getVat(), result.getVat());
        assertEquals(invoice.getTotal(), result.getTotal());
        assertEquals(invoice.getClientRFC(), result.getClientRFC());
        assertEquals(invoice.getLegalName(), result.getLegalName());
        assertEquals(invoice.getOwner(), result.getOwner());

    }

    @Test
    @DisplayName("Service tries to update but sends error for invoice not found.")
    void updateWithErrorTest() {
        Optional<Invoice> empty = Optional.empty();

        when(repository.findById(anyLong())).thenReturn(empty);

        UpdateInvoiceDTO data = new UpdateInvoiceDTO();

        assertThrows(InvoiceNotFoundException.class, () -> service.update(100L, data));

    }

    @Test
    @DisplayName("Service updates an invoice")
    void updateTest() throws InvoiceNotFoundException {

        UpdateInvoiceDTO data = new UpdateInvoiceDTO();
        data.setLegalNameU("qwerty");
        data.setClientRFCU("ytrewq");

        Invoice invoice = new Invoice();

        invoice.setId(1L);
        invoice.setLegalName("ytrewq");
        invoice.setClientRFC("qwerty");

        when(repository.findById(anyLong())).thenReturn(Optional.of(invoice));

        service.update(1L, data);

        assertEquals(data.getLegalNameU(), invoice.getLegalName());
        assertEquals(data.getClientRFCU(), invoice.getClientRFC());
        verify(repository, times(1)).save(invoice);

    }

    @Test
    @DisplayName("Service tries to delete an invoice but fails to find it.")
    void deleteWithErrorTest() throws InvoiceNotFoundException {
        assertThrows(InvoiceNotFoundException.class, () -> service.deleteById(1L));

    }

    @Test
    @DisplayName("Service deletes an invoice by Id")
    void deleteByIdTest() throws InvoiceNotFoundException {

        Invoice invoice = new Invoice();

        invoice.setId(1L);
        invoice.setLegalName("ytrewq");
        invoice.setClientRFC("qwerty");

        when(repository.findById(anyLong())).thenReturn(Optional.of(invoice));

        service.deleteById(invoice.getId());
        verify(repository, times(1)).deleteById(invoice.getId());

    }
}
