package org.bedu.java.backend.veterinaria.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import org.bedu.java.backend.veterinaria.dto.medicamento.MedicamentoDTO;
import org.bedu.java.backend.veterinaria.exception.MedicamentoNotFoundException;
import org.bedu.java.backend.veterinaria.model.Factura;
import org.bedu.java.backend.veterinaria.model.Medicamento;
import org.bedu.java.backend.veterinaria.repository.FacturaMedicamentoRepository;
import org.bedu.java.backend.veterinaria.repository.MedicamentoRepository;
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
class FacturaMedicamentoServiceTest {

    @MockBean
    private FacturaMedicamentoRepository repository;

    @Autowired
    private FacturaMedicamentoService service;

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @AfterEach
    public void setup() {
        repository.deleteAll();
        medicamentoRepository.deleteAll();
    }

    @Test
    @DisplayName("FacturaMedicamento injected")
    void smokeTest() {
        assertNotNull(service);
    }

    @Test
    @DisplayName("Añadir un medicamento a una factura")
    void addMedicamento() throws MedicamentoNotFoundException {
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

        Medicamento model = new Medicamento();

        model.setId(1L);
        model.setNombre("Ciprofloxacino");
        model.setClasificacion("Antibiótico");
        model.setDescripcion("Trata infecciones bacterianas");
        model.setFechaCaducidad(LocalDate.parse("2024-01-10"));
        model.setExistencia(40);
        model.setPrecio(18.75F);
        model.setInstruccionesUso("Tomar 1 tableta cada 12 horas");
        medicamentoRepository.save(model);
        service.addMedicamento(1L, 1L, 2);
        assertNotNull(repository);
    }

    @Test
    @DisplayName("Añadir un medicamento a una factura")
    void addMedicamentoWithErrortest() throws MedicamentoNotFoundException {
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

        Medicamento model = new Medicamento();

        model.setId(1L);
        model.setNombre("Ciprofloxacino");
        model.setClasificacion("Antibiótico");
        model.setDescripcion("Trata infecciones bacterianas");
        model.setFechaCaducidad(LocalDate.parse("2024-01-10"));
        model.setExistencia(40);
        model.setPrecio(18.75F);
        model.setInstruccionesUso("Tomar 1 tableta cada 12 horas");

        medicamentoRepository.save(model);

        assertThrows(MedicamentoNotFoundException.class, () -> service.addMedicamento(1L, 100L, 2));
    }

    @Test
    @DisplayName("Retorna una lista de medicamentos de una factura")
    void listMedicamentosTest() throws MedicamentoNotFoundException {

        List<Medicamento> data = new LinkedList<>();

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

        Medicamento model = new Medicamento();

        model.setId(1L);
        model.setNombre("Ciprofloxacino");
        model.setClasificacion("Antibiótico");
        model.setDescripcion("Trata infecciones bacterianas");
        model.setFechaCaducidad(LocalDate.parse("2024-01-10"));
        model.setExistencia(40);
        model.setPrecio(18.75F);
        model.setInstruccionesUso("Tomar 1 tableta cada 12 horas");
        Medicamento model2 = new Medicamento();

        model2.setId(2L);
        model2.setNombre("Ciprofloxacino");
        model2.setClasificacion("Antibiótico");
        model2.setDescripcion("Trata infecciones bacterianas");
        model2.setFechaCaducidad(LocalDate.parse("2024-01-10"));
        model2.setExistencia(40);
        model2.setPrecio(18.75F);
        model2.setInstruccionesUso("Tomar 1 tableta cada 12 horas");

        data.add(model);
        data.add(model2);

        medicamentoRepository.save(model);
        medicamentoRepository.save(model2);

        service.addMedicamento(1L, 1L, 2);
        service.addMedicamento(1L, 2L, 2);

        when(repository.findMedicamentosByFactura(1L)).thenReturn(data);

        List<MedicamentoDTO> dtoData = service.findMedicamentosByFactura(1L);

        assertNotNull(dtoData);
        assertEquals(2, dtoData.size());

    }

}
