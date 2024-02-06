package org.bedu.java.backend.veterinaria.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.bedu.java.backend.veterinaria.dto.medicamento.CreateMedicamentoDTO;
import org.bedu.java.backend.veterinaria.dto.medicamento.MedicamentoDTO;
import org.bedu.java.backend.veterinaria.dto.medicamento.UpdateMedicamentoDTO;
import org.bedu.java.backend.veterinaria.exception.MedicamentoNotFoundException;
import org.bedu.java.backend.veterinaria.model.Medicamento;
import org.bedu.java.backend.veterinaria.repository.MedicamentoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class MedicamentoServiceTest {

    @MockBean
    private MedicamentoRepository repository;

    @Autowired
    private MedicamentoService service;

    @Test
    @DisplayName("Service should be injected")
    void smokeTest() {
        assertNotNull(service);
    }

    @Test
    @DisplayName("Service should return medications from the repository")
    void findAllTest() {
        List<Medicamento> data = new LinkedList<>();

        Medicamento medicamento = new Medicamento();

        medicamento.setId(500L);
        medicamento.setNombre("Dipirona");
        medicamento.setClasificacion("Analgésico");
        medicamento.setDescripcion("Alivia el dolor y reduce la fiebre");
        medicamento.setFechaCaducidad(LocalDate.parse("2023-12-15"));
        medicamento.setExistencia(80);
        medicamento.setPrecio(9.5F);
        medicamento.setInstruccionesUso("Tomar 1 tableta cada 4 horas");

        data.add(medicamento);

        when(repository.findAll()).thenReturn(data);

        List<MedicamentoDTO> result = service.findAll();

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
    @DisplayName("Service should save a medication into the repository")
    void saveTest() {
        CreateMedicamentoDTO dto = new CreateMedicamentoDTO();
        dto.setNombre("Ciprofloxacino");
        dto.setClasificacion("Antibiótico");
        dto.setDescripcion("Trata infecciones bacterianas");
        dto.setFechaCaducidad(LocalDate.parse("2024-01-10"));
        dto.setExistencia(40);
        dto.setPrecio(18.75F);
        dto.setInstruccionesUso("Tomar 1 tableta cada 12 horas");

        Medicamento model = new Medicamento();

        model.setId(600L);
        model.setNombre(dto.getNombre());
        model.setDescripcion(dto.getDescripcion());
        model.setClasificacion(dto.getClasificacion());
        model.setFechaCaducidad(dto.getFechaCaducidad());
        model.setExistencia(dto.getExistencia());
        model.setPrecio(dto.getPrecio());
        model.setInstruccionesUso(dto.getInstruccionesUso());

        when(repository.save(any(Medicamento.class))).thenReturn(model);

        MedicamentoDTO result = service.save(dto);

        assertNotNull(result);
        assertEquals(model.getId(), result.getId());
        assertEquals(model.getNombre(), result.getNombre());
        assertEquals(model.getDescripcion(), result.getDescripcion());
        assertEquals(model.getClasificacion(), result.getClasificacion());
        assertEquals(model.getFechaCaducidad(), result.getFechaCaducidad());
        assertEquals(model.getExistencia(), result.getExistencia());
        assertEquals(model.getPrecio(), result.getPrecio());
        assertEquals(model.getInstruccionesUso(), result.getInstruccionesUso());
    }

    @Test
    @DisplayName("Service should throw an error if the medication is not found when attempting to update it")
    void updateWithErrorTest() {
        UpdateMedicamentoDTO dto = new UpdateMedicamentoDTO();
        Optional<Medicamento> dummy = Optional.empty();

        when(repository.findById(anyLong())).thenReturn(dummy);

        assertThrows(MedicamentoNotFoundException.class, () -> service.update(100L, dto));
    }

    @Test
    @DisplayName("Service should update a medication in the repository")
    void updateTest() throws MedicamentoNotFoundException {
        UpdateMedicamentoDTO dto = new UpdateMedicamentoDTO();
        dto.setNombre("Salbutamol");
        dto.setClasificacion("Broncodilatador"); //TODO: Terminar de asignar

        Medicamento medicamento = new Medicamento();

        medicamento.setId(125L);
        medicamento.setNombre("MeDiCaMeNtO");
        medicamento.setClasificacion("CLASIFICACION");

        when(repository.findById(anyLong())).thenReturn(Optional.of(medicamento));

        service.update(125L, dto);

        assertEquals(dto.getNombre(), medicamento.getNombre());
        assertEquals(dto.getClasificacion(), medicamento.getClasificacion());
        verify(repository, times(1)).save(medicamento);

    }

    @Test
    @DisplayName("Service should throw an error if the medication is not found when attempting to delete it")
    void deleteByIdWithErrorTest() {
        Optional<Medicamento> dummy = Optional.empty();

        when(repository.findById(anyLong())).thenReturn(dummy);

        assertThrows(MedicamentoNotFoundException.class, () -> service.deleteById(100L));
    }

    @Test
    @DisplayName("Sservice should delete a medication by ID")
    void deleteByIdTest() throws MedicamentoNotFoundException {

        Medicamento medicamento = new Medicamento();

        medicamento.setId(125L);
        medicamento.setNombre(""); //TODO: Revisar estos setter
        medicamento.setClasificacion("");

        when(repository.findById(anyLong())).thenReturn(Optional.of(medicamento));

        service.deleteById(125L);
        verify(repository, times(1)).deleteById(125L);
    }

}