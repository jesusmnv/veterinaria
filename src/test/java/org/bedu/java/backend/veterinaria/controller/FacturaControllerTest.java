package org.bedu.java.backend.veterinaria.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bedu.java.backend.veterinaria.dto.factura.AddMedicamentoDTO;
import org.bedu.java.backend.veterinaria.dto.factura.CreateFacturaDTO;
import org.bedu.java.backend.veterinaria.dto.factura.FacturaDTO;
import org.bedu.java.backend.veterinaria.dto.factura.UpdateFacturaDTO;
import org.bedu.java.backend.veterinaria.exception.FacturaNotFoundException;
import org.bedu.java.backend.veterinaria.exception.MedicationNotFoundException;
import org.bedu.java.backend.veterinaria.service.FacturaMedicamentoService;
import org.bedu.java.backend.veterinaria.service.FacturaService;
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
class FacturaControllerTest {

    @MockBean
    private FacturaService service;

    @MockBean
    private FacturaMedicamentoService serviceAdd;

    @Autowired
    private FacturaController controller;

    @Test
    @DisplayName("Controller should be injected")
    void smokeTest() {
        assertNotNull(controller);
    }

    @Test
    @DisplayName("Controller regresa una lista de facturas")
    void findAllTest() {
        List<FacturaDTO> data = new LinkedList<>();
        LocalDate fecha = LocalDate.parse("2023-12-12");
        FacturaDTO factura = new FacturaDTO(
                1L, fecha, 10, 15, 15, "dfsbvdsfb", "dfsgsfg", null);

        data.add(factura);

        when(service.findAll()).thenReturn(data);

        List<FacturaDTO> result = controller.findAll();

        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertEquals(factura.getId(), result.get(0).getId());
        assertEquals(factura.getFechaEmision(), result.get(0).getFechaEmision());
        assertEquals(factura.getSubtotal(), result.get(0).getSubtotal());
        assertEquals(factura.getIva(), result.get(0).getIva());
        assertEquals(factura.getTotal(), result.get(0).getTotal());
        assertEquals(factura.getRfcCliente(), result.get(0).getRfcCliente());
        assertEquals(factura.getRazonSocial(), result.get(0).getRazonSocial());
        assertEquals(factura.getPropietario(), result.get(0).getPropietario());
    }

    @Test
    @DisplayName("Controller guarda una factura")
    void saveFactura() {
        CreateFacturaDTO dto = new CreateFacturaDTO();
        LocalDate fecha = LocalDate.parse("2023-12-12");
        dto.setFechaEmision(fecha);
        dto.setIva(15);
        dto.setRazonSocial("qwerty");
        dto.setRfcCliente("ytrewq");
        dto.setSubtotal(15);
        dto.setTotal(115);
        dto.setPropietario(null);

        FacturaDTO facturaDTO = new FacturaDTO(1L, dto.getFechaEmision(),
                dto.getSubtotal(), dto.getIva(), dto.getTotal(),
                dto.getRfcCliente(), dto.getRazonSocial(), dto.getPropietario());

        when(service.save(any(CreateFacturaDTO.class))).thenReturn(facturaDTO);

        FacturaDTO result = controller.save(dto);

        assertNotNull(result);
        assertEquals(result, facturaDTO);

    }

    @Test
    @DisplayName("Controller actualiza una factura")
    void updateTest() throws FacturaNotFoundException {
        UpdateFacturaDTO dto = new UpdateFacturaDTO();
        dto.setRazonSocial("qwerty");
        dto.setRfcCliente("qwerty");

        controller.update(400L, dto);
        verify(service, times(1)).update(400L, dto);
    }

    @Test
    @DisplayName("Controller elimina una factura")
    void deleteTest() throws FacturaNotFoundException {
        controller.delete(400L);
        verify(service, times(1)).deleteById(400L);
    }

    @Test
    @DisplayName("Controller añade un medicaento a una factura")
    void addMedicamentoTest() throws MedicationNotFoundException {

        AddMedicamentoDTO add = new AddMedicamentoDTO();
        add.setMedicamentoId(1L);
        add.setCantidad(5);

        controller.addMedicamento(1L, add);
        verify(serviceAdd, times(1)).addMedicamento(1L, 1L, 5);

    }

    @Test
    @DisplayName("Controllador busca la información de los medicamentos asociados a una factura")
    void showMedicamentosTest() {
        controller.findMedicamentos(1L);
        verify(serviceAdd, times(1)).findMedicamentosByFactura(1L);
    }
}
