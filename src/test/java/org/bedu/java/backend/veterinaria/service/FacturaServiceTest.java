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
import org.bedu.java.backend.veterinaria.dto.factura.CreateFacturaDTO;
import org.bedu.java.backend.veterinaria.dto.factura.FacturaDTO;
import org.bedu.java.backend.veterinaria.dto.factura.UpdateFacturaDTO;
import org.bedu.java.backend.veterinaria.exception.FacturaNotFoundException;
import org.bedu.java.backend.veterinaria.model.Factura;
import org.bedu.java.backend.veterinaria.repository.FacturaRepository;
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
class FacturaServiceTest {

    @MockBean
    private FacturaRepository repository;

    @Autowired
    private FacturaService service;

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
    @DisplayName("Busqueda de una factura por Id")
    void findByIdTest() {

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

        when(repository.findById(anyLong())).thenReturn(Optional.of(factura));

        Optional<FacturaDTO> result = service.findById(factura.getId());

        assertNotNull(result.get());
        assertEquals(factura.getId(), result.get().getId());
        assertEquals(factura.getFechaEmision(), result.get().getFechaEmision());
        assertEquals(factura.getSubtotal(), result.get().getSubtotal());
        assertEquals(factura.getIva(), result.get().getIva());
        assertEquals(factura.getTotal(), result.get().getTotal());
        assertEquals(factura.getRfcCliente(), result.get().getRfcCliente());
        assertEquals(factura.getRazonSocial(), result.get().getRazonSocial());
        assertEquals(factura.getPropietario(), result.get().getPropietario());

    }

    @Test
    @DisplayName("Service regresa una lista de facturas")
    void findAllTest() {

        List<Factura> data = new LinkedList<>();
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

        data.add(factura);

        when(repository.findAll()).thenReturn(data);

        List<FacturaDTO> result = service.findAll();

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
    @DisplayName("Services guarda una factura")
    void saveTest() {
        LocalDate fecha = LocalDate.parse("2023-12-12");
        CreateFacturaDTO dto = new CreateFacturaDTO();

        dto.setFechaEmision(fecha);
        dto.setIva(1);
        dto.setPropietario(null);
        dto.setRazonSocial("qwerty");
        dto.setRfcCliente("ytrewq");
        dto.setSubtotal(15);
        dto.setTotal(1500);

        Factura factura = new Factura();
        factura.setFechaEmision(dto.getFechaEmision());
        factura.setId(1L);
        factura.setIva(dto.getIva());
        factura.setPropietario(dto.getPropietario());
        factura.setRazonSocial(dto.getRazonSocial());
        factura.setRfcCliente(dto.getRfcCliente());
        factura.setSubtotal(dto.getSubtotal());
        factura.setTotal(dto.getTotal());

        when(repository.save(any(Factura.class))).thenReturn(factura);

        FacturaDTO result = service.save(dto);
        assertNotNull(result);
        assertEquals(factura.getId(), result.getId());
        assertEquals(factura.getFechaEmision(), result.getFechaEmision());
        assertEquals(factura.getSubtotal(), result.getSubtotal());
        assertEquals(factura.getIva(), result.getIva());
        assertEquals(factura.getTotal(), result.getTotal());
        assertEquals(factura.getRfcCliente(), result.getRfcCliente());
        assertEquals(factura.getRazonSocial(), result.getRazonSocial());
        assertEquals(factura.getPropietario(), result.getPropietario());

    }

    @Test
    @DisplayName("Service intenta actualizar pero manda error por factura no encontrada")
    void updateWithErrorTest() {
        Optional<Factura> empty = Optional.empty();

        when(repository.findById(anyLong())).thenReturn(empty);

        UpdateFacturaDTO data = new UpdateFacturaDTO();

        assertThrows(FacturaNotFoundException.class, () -> service.update(100L, data));

    }

    @Test
    @DisplayName("Service actualiza una factura")
    void updateTest() throws FacturaNotFoundException {

        UpdateFacturaDTO data = new UpdateFacturaDTO();
        data.setRazonSocial("qwerty");
        data.setRfcCliente("ytrewq");

        Factura factura = new Factura();

        factura.setId(1L);
        factura.setRazonSocial("ytrewq");
        factura.setRfcCliente("qwerty");

        when(repository.findById(anyLong())).thenReturn(Optional.of(factura));

        service.update(1L, data);

        assertEquals(data.getRazonSocial(), factura.getRazonSocial());
        assertEquals(data.getRfcCliente(), factura.getRfcCliente());
        verify(repository, times(1)).save(factura);

    }

    @Test
    @DisplayName("Service intenta eliminar una factura pero marca error por no encontrarla")
    void deleteWithErrorTest() throws FacturaNotFoundException {
        assertThrows(FacturaNotFoundException.class, () -> service.deleteById(1L));

    }

    @Test
    @DisplayName("Service elimina una factura por Id")
    void deleteByIdTest() throws FacturaNotFoundException {

        Factura factura = new Factura();

        factura.setId(1L);
        factura.setRazonSocial("ytrewq");
        factura.setRfcCliente("qwerty");

        when(repository.findById(anyLong())).thenReturn(Optional.of(factura));

        service.deleteById(factura.getId());
        verify(repository, times(1)).deleteById(factura.getId());

    }
}
