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
import org.bedu.java.backend.veterinaria.model.Factura;
import org.bedu.java.backend.veterinaria.model.Medication;
import org.bedu.java.backend.veterinaria.repository.FacturaMedicamentoRepository;
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
class FacturaMedicationServiceTest {

    @MockBean
    private FacturaMedicamentoRepository repository;

    @Autowired
    private FacturaMedicamentoService service;

    @Autowired
    private MedicationRepository medicationRepository;

    @AfterEach
    public void setup() {
        repository.deleteAll();
        medicationRepository.deleteAll();
    }

    @Test
    @DisplayName("FacturaMedicamento injected")
    void smokeTest() {
        assertNotNull(service);
    }

    @Test
    @DisplayName("Añadir un medicamento a una factura")
    void addMedicamento() throws MedicationNotFoundException {
        Factura factura = new Factura();
        LocalDate fecha = LocalDate.parse("2023-12-12");
        factura.setId(1L);

        factura.setFechaEmision(fecha);
        factura.setIva(1);
        factura.setRazonSocial("qwerty");
        factura.setRfcCliente("ytrewq");
        factura.setSubtotal(150);
        factura.setTotal(1500);
        factura.setPropietario(null);

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
        service.addMedicamento(1L, 3L, 2);
        assertNotNull(repository);
    }

    @Test
    @DisplayName("Añadir un medicamento a una factura")
    void addMedicamentoWithErrortest() throws MedicationNotFoundException {
        Factura factura = new Factura();
        LocalDate fecha = LocalDate.parse("2023-12-12");
        factura.setId(1L);

        factura.setFechaEmision(fecha);
        factura.setIva(1);
        factura.setRazonSocial("qwerty");
        factura.setRfcCliente("ytrewq");
        factura.setSubtotal(150);
        factura.setTotal(1500);
        factura.setPropietario(null);

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

        assertThrows(MedicationNotFoundException.class, () -> service.addMedicamento(1L, 100L, 2));
    }

    @Test
    @DisplayName("Retorna una lista de medicamentos de una factura")
    void listMedicamentosTest() throws MedicationNotFoundException {

        List<Medication> data = new LinkedList<>();

        Factura factura = new Factura();
        LocalDate fecha = LocalDate.parse("2023-12-12");
        factura.setId(1L);

        factura.setFechaEmision(fecha);
        factura.setIva(1);
        factura.setRazonSocial("qwerty");
        factura.setRfcCliente("ytrewq");
        factura.setSubtotal(150);
        factura.setTotal(1500);
        factura.setPropietario(null);

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

        service.addMedicamento(1L, 1L, 2);
        service.addMedicamento(1L, 2L, 2);

        when(repository.findMedicamentosByFactura(1L)).thenReturn(data);

        List<MedicationDTO> dtoData = service.findMedicamentosByFactura(1L);

        assertNotNull(dtoData);
        assertEquals(2, dtoData.size());

    }

}
