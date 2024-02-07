package org.bedu.java.backend.veterinaria.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.bedu.java.backend.veterinaria.dto.cita.CitaDTO;
import org.bedu.java.backend.veterinaria.dto.cita.CreateCitaDTO;
import org.bedu.java.backend.veterinaria.dto.cita.UpdateCitaDTO;
import org.bedu.java.backend.veterinaria.exception.CitaNotFoundException;
import org.bedu.java.backend.veterinaria.service.CitaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.LinkedList;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CitaControllerTest {

  @MockBean
  private CitaService citaService;

  @Autowired
  private CitaController citaController;

  @Test
  @DisplayName("Controller debe inyectarse")
  void smokeTest() {
    assertNotNull(citaController);
  }

  @Test
  @DisplayName("Controller regresa una lista de citas")
  void findAllTest() {

    List<CitaDTO> data = new LinkedList<>();

    CitaDTO cita = new CitaDTO();

    cita.setId(9L);
    cita.setMotivoCita("Operacion");

    data.add(cita);

    when(citaService.findAll()).thenReturn(data);

    java.util.List<CitaDTO> result = citaController.findAll();

    assertNotNull(result);
    assertTrue(result.size() > 0);
    assertEquals(cita.getId(), result.get(0).getId());
    assertEquals(cita.getMotivoCita(), result.get(0).getMotivoCita());

  }

  @Test
  @DisplayName("Controller guarda una cita")
  void saveTest() {
    CreateCitaDTO dto = new CreateCitaDTO();

    dto.setFechaCita(null);
    dto.setHoraCita(null);

    CitaDTO cita = new CitaDTO();

    cita.setId(200L);
    cita.setFechaCita(dto.getFechaCita());
    cita.setHoraCita(dto.getHoraCita());
    cita.setMotivoCita(dto.getMotivoCita());

    when(citaService.save(any(CreateCitaDTO.class))).thenReturn(cita);

    CitaDTO result = citaController.save(dto);

    assertNotNull(result);
    assertEquals(cita.getId(), result.getId());
    assertEquals(cita.getFechaCita(), result.getFechaCita());
    assertEquals(cita.getHoraCita(), result.getHoraCita());
    assertEquals(cita.getHoraCita(), result.getHoraCita());
    assertEquals(cita.getMotivoCita(), result.getMotivoCita());

  }

  @Test
  @DisplayName("Controller debe actualizar una cita")
  void updateTest() throws CitaNotFoundException {
    UpdateCitaDTO dto = new UpdateCitaDTO();

    dto.setFechaCita(null);
    dto.setHoraCita(null);
    dto.setMotivoCita("Corte de orejas");

    citaController.update(6L, dto);

    // Verificando que el método update del servicio
    // haya sido ejecutado 1 vez
    verify(citaService, times(1)).update(6L, dto);
  }

  @Test
  @DisplayName("Controller should delete a movie")
  void deleteByIdTest() throws CitaNotFoundException {

    citaController.deleteById(200L);

    verify(citaService, times(1)).deleteById(200L);
  }

}
