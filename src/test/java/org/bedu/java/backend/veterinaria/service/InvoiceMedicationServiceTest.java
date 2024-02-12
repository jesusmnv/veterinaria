package org.bedu.java.backend.veterinaria.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import org.bedu.java.backend.veterinaria.dto.medication.MedicationDTO;
import org.bedu.java.backend.veterinaria.exception.MedicationNotFoundException;
import org.bedu.java.backend.veterinaria.model.Invoice;
import org.bedu.java.backend.veterinaria.model.Medication;
import org.bedu.java.backend.veterinaria.repository.InvoiceMedicationRepository;
import org.bedu.java.backend.veterinaria.repository.MedicationRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class InvoiceMedicationServiceTest {

    @MockBean
    private InvoiceMedicationRepository repository;

    @Autowired
    private InvoiceMedicationService service;

    @Autowired
    private MedicationRepository medicationRepository;

    @AfterEach
    public void setup() {
        repository.deleteAll();
        medicationRepository.deleteAll();
    }

    @Test
    @DisplayName("InvoiceMedication injected")
    void smokeTest() {
        assertNotNull(service);
    }

    @Test
    @DisplayName("Add a medication into an invoice")
    void addMedicamento() throws MedicationNotFoundException {
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

        Medication model = new Medication();

        model.setId(1L);
        model.setName("Ciprofloxacino");
        model.setClassification("Antibiótico");
        model.setDescription("Trata infecciones bacterianas");
        model.setExpirationDate(LocalDate.parse("2024-01-10"));
        model.setStock(40);
        model.setPrice(18.75F);
        model.setUsageInstructions("Tomar 1 tableta cada 12 horas");
        medicationRepository.save(model);
        service.addMedication(1L, 3L, 2);
        assertNotNull(repository);
    }

    @Test
    @DisplayName("Add a medication into an invoice with error")
    void addMedicamentoWithErrortest() throws MedicationNotFoundException {
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

        Medication model = new Medication();

        model.setId(1L);
        model.setName("Ciprofloxacino");
        model.setClassification("Antibiótico");
        model.setDescription("Trata infecciones bacterianas");
        model.setExpirationDate(LocalDate.parse("2024-01-10"));
        model.setStock(40);
        model.setPrice(18.75F);
        model.setUsageInstructions("Tomar 1 tableta cada 12 horas");

        medicationRepository.save(model);

        assertThrows(MedicationNotFoundException.class, () -> service.addMedication(1L, 100L, 2));
    }

    @Test
    @DisplayName("Returns a list of medications from an invoice")
    void listMedicamentosTest() throws MedicationNotFoundException {

        List<Medication> data = new LinkedList<>();

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

        Medication model = new Medication();

        model.setId(1L);
        model.setName("Ciprofloxacino");
        model.setClassification("Antibiótico");
        model.setDescription("Trata infecciones bacterianas");
        model.setExpirationDate(LocalDate.parse("2024-01-10"));
        model.setStock(40);
        model.setPrice(18.75F);
        model.setUsageInstructions("Tomar 1 tableta cada 12 horas");
        Medication model2 = new Medication();

        model2.setId(2L);
        model2.setName("Ciprofloxacino");
        model2.setClassification("Antibiótico");
        model2.setDescription("Trata infecciones bacterianas");
        model2.setExpirationDate(LocalDate.parse("2024-01-10"));
        model2.setStock(40);
        model2.setPrice(18.75F);
        model2.setUsageInstructions("Tomar 1 tableta cada 12 horas");

        data.add(model);
        data.add(model2);

        medicationRepository.save(model);
        medicationRepository.save(model2);

        service.addMedication(1L, 1L, 2);
        service.addMedication(1L, 2L, 2);

        when(repository.findMedicationsByInvoice(1L)).thenReturn(data);

        List<MedicationDTO> dtoData = service.findMedicationsByInvoice(1L);

        assertNotNull(dtoData);
        assertEquals(2, dtoData.size());

    }

}
