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

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.bedu.java.backend.veterinaria.dto.consulta.ConsultaDTO;
import org.bedu.java.backend.veterinaria.dto.consulta.CreateConsultaDTO;
import org.bedu.java.backend.veterinaria.dto.consulta.UpdateConsultaDTO;
import org.bedu.java.backend.veterinaria.exception.ConsultaNotFoundException;
import org.bedu.java.backend.veterinaria.model.Consulta;
import org.bedu.java.backend.veterinaria.model.Mascota;
import org.bedu.java.backend.veterinaria.model.Veterinario;
import org.bedu.java.backend.veterinaria.repository.ConsultaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ConsultaServiceTest {

    @MockBean
    private ConsultaRepository repository;

    @Autowired
    private ConsultaService service;

    @Test
    @DisplayName("Service should be injected")
    void smokeTest() {
        assertNotNull(service);
    }

    @Test
    @DisplayName("Service should return consultations from the repository")
    void findAllTest() {
        List<Consulta> data = new LinkedList<>();

        Consulta consulta = new Consulta();

        consulta.setId(500L);
        consulta.setFechaConsulta(LocalDate.of(2024, 4, 14));
        consulta.setDiagnostico("Gripe felina");
        consulta.setTratamientoIndicado("Antibióticos y reposo");
        consulta.setObservaciones("La mascota parece mejorar, seguir monitoreando");

        Mascota m = new Mascota();
        m.setId(4L);

        Veterinario v = new Veterinario();
        v.setId(4L);

        consulta.setMascota(m);
        consulta.setVeterinario(v);

        data.add(consulta);

        when(repository.findAll()).thenReturn(data);

        List<ConsultaDTO> result = service.findAll();

        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertEquals(consulta.getId(), result.get(0).getId());
        assertEquals(consulta.getFechaConsulta(), result.get(0).getFechaConsulta());
        assertEquals(consulta.getDiagnostico(), result.get(0).getDiagnostico());
        assertEquals(consulta.getTratamientoIndicado(), result.get(0).getTratamientoIndicado());
        assertEquals(consulta.getObservaciones(), result.get(0).getObservaciones());
        assertEquals(consulta.getMascota(), result.get(0).getMascota());
        assertEquals(consulta.getVeterinario(), result.get(0).getVeterinario());
    }

    @Test
    @DisplayName("Service should save a consultation in the repository")
    void saveTest() {
        CreateConsultaDTO dto = new CreateConsultaDTO();
        dto.setFechaConsulta(LocalDate.parse("2025-05-05"));
        dto.setDiagnostico("Gripe felina");
        dto.setTratamientoIndicado("Antibióticos y reposo");
        dto.setObservaciones("La mascota parece mejorar, seguir monitoreando");

        Mascota m = new Mascota();
        m.setId(5L);

        Veterinario v = new Veterinario();
        v.setId(5L);

        dto.setMascota(m);
        dto.setVeterinario(v);

        Consulta model = new Consulta();

        model.setId(600L);
        model.setFechaConsulta(dto.getFechaConsulta());
        model.setDiagnostico(dto.getDiagnostico());
        model.setTratamientoIndicado(dto.getTratamientoIndicado());
        model.setObservaciones(dto.getObservaciones());
        model.setMascota(dto.getMascota());
        model.setVeterinario(dto.getVeterinario());

        when(repository.save(any(Consulta.class))).thenReturn(model);

        ConsultaDTO result = service.save(dto);

        assertNotNull(result);
        assertEquals(model.getId(), result.getId());
        assertEquals(model.getFechaConsulta(), result.getFechaConsulta());
        assertEquals(model.getTratamientoIndicado(), result.getTratamientoIndicado());
        assertEquals(model.getDiagnostico(), result.getDiagnostico());
        assertEquals(model.getObservaciones(), result.getObservaciones());
        assertEquals(model.getMascota(), result.getMascota());
        assertEquals(model.getVeterinario(), result.getVeterinario());
    }

    @Test
    @DisplayName("Service should throw an error if the consultation is not found when attempting to update it")
    void updateWithErrorTest() {
        UpdateConsultaDTO dto = new UpdateConsultaDTO();
        Optional<Consulta> dummy = Optional.empty();

        when(repository.findById(anyLong())).thenReturn(dummy);

        assertThrows(ConsultaNotFoundException.class, () -> service.update(100L, dto));
    }

    @Test
    @DisplayName("Service should update a consultation in the repository")
    void updateTest() throws ConsultaNotFoundException {
        UpdateConsultaDTO dto = new UpdateConsultaDTO();
        dto.setDiagnostico("Diagnóstico..."); //TODO: Terminar de asignar

        Consulta consulta = new Consulta();

        consulta.setId(155L);
        consulta.setDiagnostico("DIAGNOSTICO...");

        when(repository.findById(anyLong())).thenReturn(Optional.of(consulta));

        service.update(155L, dto);

        assertEquals(dto.getFechaConsulta(), consulta.getFechaConsulta());
        verify(repository, times(1)).save(consulta);

    }

    @Test
    @DisplayName("Service should throw an error if the consultation is not found when attempting to delete it")
    void deleteByIdWithErrorTest() {
        Optional<Consulta> dummy = Optional.empty();

        when(repository.findById(anyLong())).thenReturn(dummy);

        assertThrows(ConsultaNotFoundException.class, () -> service.deleteById(222L));
    }

    @Test
    @DisplayName("Service should delete a consultation by ID")
    void deleteByIdTest() throws ConsultaNotFoundException {

        Consulta consulta = new Consulta();

        consulta.setId(222L);
        consulta.setDiagnostico(""); //TODO: Revisar estos setter

        when(repository.findById(anyLong())).thenReturn(Optional.of(consulta));

        service.deleteById(222L);
        verify(repository, times(1)).deleteById(222L);
    }

}
