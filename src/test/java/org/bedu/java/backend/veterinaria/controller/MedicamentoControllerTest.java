package org.bedu.java.backend.veterinaria.controller;

import org.bedu.java.backend.veterinaria.dto.medicamento.CreateMedicamentoDTO;
import org.bedu.java.backend.veterinaria.dto.medicamento.MedicamentoDTO;
import org.bedu.java.backend.veterinaria.dto.medicamento.UpdateMedicamentoDTO;
import org.bedu.java.backend.veterinaria.exception.MedicamentoNotFoundException;
import org.bedu.java.backend.veterinaria.service.MedicamentoService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class MedicamentoControllerTest {

    @MockBean
    private MedicamentoService service;

    @Autowired
    private MedicamentoController controller;

    @Test
    @DisplayName("Controller should be injected")
    void smokeTest() {
        assertNotNull(controller);
    }

    @Test
    @DisplayName("Controller should return a list of medications")
    void findAllTest() {
        List<MedicamentoDTO> data = new LinkedList<>();
        MedicamentoDTO medicamento = new MedicamentoDTO();

        medicamento.setId(100L);
        medicamento.setNombre("Paracetamol");
        medicamento.setClasificacion("Analgésico");
        medicamento.setDescripcion("Alivia el dolor y reduce la fiebre");
        medicamento.setFechaCaducidad(LocalDate.parse("2023-12-01"));

        medicamento.setExistencia(150);
        medicamento.setPrecio(10.5F);
        medicamento.setInstruccionesUso("Tomar 1 tableta cada 6 horas");

        data.add(medicamento);

        when(service.findAll()).thenReturn(data);

        List<MedicamentoDTO> result = controller.findAll();

        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertEquals(medicamento.getId(), result.get(0).getId());
        assertEquals(medicamento.getNombre(), result.get(0).getNombre());
        assertEquals(medicamento.getClasificacion(), result.get(0).getClasificacion());
        assertEquals(medicamento.getDescripcion(), result.get(0).getDescripcion());
        assertEquals(medicamento.getFechaCaducidad(), result.get(0).getFechaCaducidad());
        assertEquals(medicamento.getExistencia(), result.get(0).getExistencia());
        assertEquals(medicamento.getPrecio(), result.get(0).getPrecio());
        assertEquals(medicamento.getInstruccionesUso(), result.get(0).getInstruccionesUso());

    }

    @Test
    @DisplayName("Controller should save a medication")
    void saveTest() {
        CreateMedicamentoDTO dto = new CreateMedicamentoDTO();

        dto.setNombre("Ibuprofeno");
        dto.setClasificacion("Antiinflamatorio");
        dto.setDescripcion("Reduce la inflamación y alivia el dolor");
        dto.setFechaCaducidad(LocalDate.parse("2023-11-15"));
        dto.setExistencia(100);
        dto.setPrecio(8.75F);
        dto.setInstruccionesUso("Tomar 1 tableta con alimentos");

        MedicamentoDTO medicamento = new MedicamentoDTO();
        medicamento.setId(300L);
        medicamento.setNombre(dto.getNombre());
        medicamento.setClasificacion(dto.getClasificacion());
        medicamento.setDescripcion(dto.getDescripcion());
        medicamento.setFechaCaducidad(dto.getFechaCaducidad());
        medicamento.setExistencia(dto.getExistencia());
        medicamento.setPrecio(dto.getPrecio());
        medicamento.setInstruccionesUso(dto.getInstruccionesUso());

        when(service.save(any(CreateMedicamentoDTO.class))).thenReturn(medicamento);

        MedicamentoDTO result = controller.save(dto);

        assertNotNull(result);

        assertEquals(medicamento.getId(), result.getId());
        assertEquals(medicamento.getNombre(), result.getNombre());
        assertEquals(medicamento.getClasificacion(), result.getClasificacion());
        assertEquals(medicamento.getDescripcion(), result.getDescripcion());
        assertEquals(medicamento.getFechaCaducidad(), result.getFechaCaducidad());
        assertEquals(medicamento.getExistencia(), result.getExistencia());
        assertEquals(medicamento.getPrecio(), result.getPrecio());
        assertEquals(medicamento.getInstruccionesUso(), result.getInstruccionesUso());
    }

    @Test
    @DisplayName("Controller should update a medication")
    void updateTest() throws MedicamentoNotFoundException {
        UpdateMedicamentoDTO dto = new UpdateMedicamentoDTO();
        dto.setNombre("Paracetamol");
        dto.setClasificacion("Analgésico");
        dto.setDescripcion("Paracetamol");
        dto.setFechaCaducidad(LocalDate.parse("2023-12-01"));
        dto.setExistencia(200);
        dto.setPrecio(20.5F);
        dto.setInstruccionesUso("Tomar 1 tableta cada 10 horas");

        controller.update(400L, dto);

        verify(service, times(1)).update(400L, dto);
    }

    @Test
    @DisplayName("Controller should delete a medication")
    void deleteByIdTest() throws MedicamentoNotFoundException {
        controller.deleteById(8793L);
        verify(service, times(1)).deleteById(8793L);
    }

}
