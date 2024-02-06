package org.bedu.java.backend.veterinaria.controller;

import org.bedu.java.backend.veterinaria.dto.consulta.ConsultaDTO;
import org.bedu.java.backend.veterinaria.dto.consulta.CreateConsultaDTO;
import org.bedu.java.backend.veterinaria.dto.consulta.UpdateConsultaDTO;
import org.bedu.java.backend.veterinaria.exception.ConsultaNotFoundException;
import org.bedu.java.backend.veterinaria.model.Mascota;
import org.bedu.java.backend.veterinaria.model.Veterinario;
import org.bedu.java.backend.veterinaria.service.ConsultaService;
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
class ConsultaControllerTest {
    @MockBean
    private ConsultaService service;

    @Autowired
    private ConsultaController controller;

    @Test
    @DisplayName("El controlador debería ser inyectado")
    void smokeTest() {
        assertNotNull(controller);
    }

    @Test
    @DisplayName("El controlador debería retornar una lista de consultas")
    void findAllTest() {
        List<ConsultaDTO> data = new LinkedList<>();
        ConsultaDTO consulta = new ConsultaDTO();

        consulta.setId(100L);
        consulta.setFechaConsulta(LocalDate.of(2024, 1, 22));
        consulta.setDiagnostico("Gripe felina");
        consulta.setTratamientoIndicado("Antibióticos y reposo");
        consulta.setObservaciones("La mascota parece mejorar, seguir monitoreando");

        //Revisar objeto Mascota y Veterinario
        Mascota m = new Mascota();
        m.setId(9L);

        Veterinario v = new Veterinario();
        v.setId(8L);

        consulta.setMascota(m);
        consulta.setVeterinario(v);

        data.add(consulta);

        when(service.findAll()).thenReturn(data);

        List<ConsultaDTO> result = controller.findAll();

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
    @DisplayName("El controlador debería guardar una consulta")
    void saveTest() {
        CreateConsultaDTO dto = new CreateConsultaDTO();
        dto.setFechaConsulta(LocalDate.of(2023, 11, 15));
        dto.setDiagnostico("Gripe felina");
        dto.setTratamientoIndicado("Antibióticos y reposo");
        dto.setObservaciones("La mascota parece mejorar, seguir monitoreando");

        ConsultaDTO consulta = new ConsultaDTO();
        consulta.setId(100L);
        consulta.setFechaConsulta(dto.getFechaConsulta());
        consulta.setDiagnostico(dto.getDiagnostico());
        consulta.setTratamientoIndicado(dto.getTratamientoIndicado());
        consulta.setObservaciones(dto.getObservaciones());

        //Revisar objeto Mascota y Veterinario
        Mascota m = new Mascota();
        m.setId(3L);

        Veterinario v = new Veterinario();
        v.setId(3L);

        consulta.setMascota(m);
        consulta.setVeterinario(v);

        when(service.save(any(CreateConsultaDTO.class))).thenReturn(consulta);

        ConsultaDTO result = controller.save(dto);

        assertNotNull(result);

        assertEquals(consulta.getId(), result.getId());
        assertEquals(consulta.getFechaConsulta(), result.getFechaConsulta());
        assertEquals(consulta.getDiagnostico(), result.getDiagnostico());
        assertEquals(consulta.getTratamientoIndicado(), result.getTratamientoIndicado());
        assertEquals(consulta.getObservaciones(), result.getObservaciones());
        assertEquals(consulta.getMascota(), result.getMascota());
        assertEquals(consulta.getVeterinario(), result.getVeterinario());
    
    }

    @Test
    @DisplayName("El controlador debería actualizar una consulta")
    void updateTest() throws ConsultaNotFoundException {
        UpdateConsultaDTO dto = new UpdateConsultaDTO();
        dto.setFechaConsulta(LocalDate.of(2021, 1, 1));
        dto.setDiagnostico("Infección de oído");
        dto.setTratamientoIndicado("Antibióticos y gotas para los oídos");
        dto.setObservaciones("El propietario reporta que el gato ha estado rascándose las orejas con frecuencia.");
        
        //Revisar objeto Mascota y Veterinario
        Mascota m = new Mascota();
        m.setId(2L);

        Veterinario v = new Veterinario();
        v.setId(2L);

        dto.setMascota(m);
        dto.setVeterinario(v);

        controller.update(300L, dto);

        verify(service, times(1)).update(300L, dto);
    }

    @Test
    @DisplayName("El controlador debería eliminar una consulta")
    void deleteByIdTest() throws ConsultaNotFoundException {
        controller.deleteById(10L);
        verify(service, times(1)).deleteById(10L);
    }


}