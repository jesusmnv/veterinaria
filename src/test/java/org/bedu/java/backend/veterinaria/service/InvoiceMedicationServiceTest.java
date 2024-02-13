package org.bedu.java.backend.veterinaria.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import org.bedu.java.backend.veterinaria.dto.medication.MedicationDTO;
import org.bedu.java.backend.veterinaria.exception.MedicationNotFoundException;
import org.bedu.java.backend.veterinaria.model.Invoice;
import org.bedu.java.backend.veterinaria.model.Medication;
import org.bedu.java.backend.veterinaria.model.Owner;
import org.bedu.java.backend.veterinaria.repository.InvoiceMedicationRepository;
import org.bedu.java.backend.veterinaria.repository.InvoiceRepository;
import org.bedu.java.backend.veterinaria.repository.MedicationRepository;
import org.bedu.java.backend.veterinaria.repository.OwnerRepository;
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

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @AfterEach
    public void setup() {
        repository.deleteAll();
        medicationRepository.deleteAll();
        invoiceRepository.deleteAll();
        ownerRepository.deleteAll();
    }

    @Test
    @DisplayName("InvoiceMedication injected")
    void smokeTest() {
        assertNotNull(service);
    }

    @Test
    @DisplayName("Add a medication into an invoice")
    void addMedication() throws MedicationNotFoundException {
       
        Invoice invoice = new Invoice();
        invoice.setIssuanceDate(LocalDate.parse("2023-12-12"));
        invoice.setVat(1);
        invoice.setLegalName("qwerty");
        invoice.setClientRFC("ytrewq");
        invoice.setSubtotal(150);
        invoice.setTotal(1500);
        invoice.setOwner(null);
        invoiceRepository.save(invoice);

        Medication model = new Medication();
        model.setName("Amoxicillin");
        model.setClassification("Antibiotic");
        model.setDescription("Treats bacterial infections");
        model.setExpirationDate(LocalDate.parse("2024-01-10"));
        model.setStock(40);
        model.setPrice(18.75F);
        model.setUsageInstructions("Administer 1 capsule every 8 hours");        
        medicationRepository.save(model);

        Medication model2 = new Medication();
        model2.setName("Ibuprofen");
        model2.setClassification("Anti-inflammatory");
        model2.setDescription("Relieves pain");
        model2.setExpirationDate(LocalDate.parse("2023-11-15"));
        model2.setStock(40);
        model2.setPrice(18.75F);
        model2.setUsageInstructions("Administer 1 tablet with food");
        medicationRepository.save(model2);
        
        service.addMedication(invoice.getId(), model.getId(), 2);
        service.addMedication(invoice.getId(), model2.getId(), 20);

        assertNotNull(service);
    }

    @Test
    @DisplayName("Add a medication into an invoice with error")
    void addMedicationWithErrorTest() throws MedicationNotFoundException {
        Invoice invoice = new Invoice();
        invoice.setIssuanceDate(LocalDate.parse("2023-12-12"));
        invoice.setVat(1);
        invoice.setLegalName("qwerty");
        invoice.setClientRFC("ytrewq");
        invoice.setSubtotal(150);
        invoice.setTotal(1500);
        invoice.setOwner(null);
        invoiceRepository.save(invoice);

        Medication model = new Medication();
        model.setName("Amoxicillin");
        model.setClassification("Antibiotic");
        model.setDescription("Treats bacterial infections");
        model.setExpirationDate(LocalDate.parse("2024-01-10"));
        model.setStock(40);
        model.setPrice(18.75F);
        model.setUsageInstructions("Administer 1 capsule every 8 hours");
        medicationRepository.save(model);

        assertThrows(MedicationNotFoundException.class, () -> service.addMedication(invoice.getId(), 100L, 2));
    }

    @Test
    @DisplayName("Returns a list of medications from an invoice")
    void listMedicationsTest() throws MedicationNotFoundException {

        List<Medication> data = new LinkedList<>();

        Invoice invoice = new Invoice();
        invoice.setIssuanceDate(LocalDate.parse("2023-12-12"));
        invoice.setVat(1);
        invoice.setLegalName("qwerty");
        invoice.setClientRFC("ytrewq");
        invoice.setSubtotal(150);
        invoice.setTotal(1500);

        Owner owner = new Owner();
        owner.setName("Carmen");
        owner.setSurname("Sanchez");
        owner.setMaternalSurname("Gomez");
        owner.setAddress("Avenida 567");
        owner.setCellphone("1231231234");
        owner.setEmail("carmen@example.com");
        owner.setBirthdate(LocalDate.parse("1978-06-15"));
        owner.setOccupation("Arquitecta");
        
        ownerRepository.save(owner);
        invoice.setOwner(owner);
        invoiceRepository.save(invoice);

        Medication model = new Medication();
        model.setName("Amoxicillin");
        model.setClassification("Antibiotic");
        model.setDescription("Treats bacterial infections");
        model.setExpirationDate(LocalDate.parse("2024-01-10"));
        model.setStock(40);
        model.setPrice(18.75F);
        model.setUsageInstructions("Administer 1 capsule every 8 hours");

        Medication model2 = new Medication();
        model2.setName("Ibuprofen");
        model2.setClassification("Anti-inflammatory");
        model2.setDescription("Relieves pain");
        model2.setExpirationDate(LocalDate.parse("2023-11-15"));
        model2.setStock(40);
        model2.setPrice(18.75F);
        model2.setUsageInstructions("Administer 1 tablet with food");

        data.add(model);
        data.add(model2);
        
        medicationRepository.save(model);
        medicationRepository.save(model2);

        service.addMedication(invoice.getId(), model.getId(), 4);
        service.addMedication(invoice.getId(), model2.getId(), 10);

        when(repository.findMedicationsByInvoice(anyLong())).thenReturn(data);

        List<MedicationDTO> result = service.findMedicationsByInvoice(invoice.getId());

        assertNotNull(result);
        assertEquals(2, result.size());
        
        // medication 1
        assertEquals(model.getId(), result.get(0).getId());
        assertEquals(model.getName(), result.get(0).getName());
        assertEquals(model.getClassification(), result.get(0).getClassification());

        // medication 2
        assertEquals(model2.getId(), result.get(1).getId());
        assertEquals(model2.getName(), result.get(1).getName());
        assertEquals(model2.getClassification(), result.get(1).getClassification());


    }

}
