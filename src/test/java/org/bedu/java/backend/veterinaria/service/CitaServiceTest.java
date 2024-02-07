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

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.bedu.java.backend.veterinaria.dto.cita.CitaDTO;
import org.bedu.java.backend.veterinaria.dto.cita.CreateCitaDTO;
import org.bedu.java.backend.veterinaria.dto.cita.UpdateCitaDTO;
import org.bedu.java.backend.veterinaria.exception.CitaNotFoundException;
import org.bedu.java.backend.veterinaria.model.Cita;
import org.bedu.java.backend.veterinaria.repository.CitaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CitaServiceTest {
  @MockBean
  private CitaRepository citaRepository;

  @Autowired
  private CitaService citaService;

  @Test
  @DisplayName("Service debe ser inyectado")
  void smokeTest() {
    assertNotNull(citaService);
  }

  @Test
  @DisplayName("Service debe retonar cita desde el repositorio")
  void findAllTest() {
    List<Cita> data = new LinkedList<>();

    Cita cita = new Cita();

    cita.setId(4L);
    cita.setFechaCita(null);
    cita.setHoraCita(null);
    cita.setMotivoCita("Vacunacion");

    data.add(cita);

    when(citaRepository.findAll()).thenReturn(data);

    List<CitaDTO> result = citaService.findAll();

    assertNotNull(result);
    assertTrue(result.size() > 0);
    assertEquals(cita.getId(), result.get(0).getId());
    assertEquals(cita.getFechaCita(), result.get(0).getFechaCita());
    assertEquals(cita.getHoraCita(), result.get(0).getHoraCita());
    assertEquals(cita.getMotivoCita(), result.get(0).getMotivoCita());
  }

  @SuppressWarnings("null")
  @Test
  @DisplayName("Service debe guardar una cita en el repositorio")
  void saveTest() {
    CreateCitaDTO dto = new CreateCitaDTO();

    dto.setFechaCita(null);
    dto.setHoraCita(null);
    dto.setMotivoCita("Limpieza dental");

    Cita model = new Cita();

    model.setId(7L);
    model.setFechaCita(dto.getFechaCita());
    model.setHoraCita((dto.getHoraCita()));
    model.setMotivoCita(dto.getMotivoCita());

    when(citaRepository.save(any(Cita.class))).thenReturn(model);

    CitaDTO result = citaService.save(dto);

    assertNotNull(result);
    assertEquals(model.getId(), result.getId());
    assertEquals(model.getFechaCita(), result.getFechaCita());
    assertEquals(model.getHoraCita(), result.getHoraCita());
    assertEquals(model.getMotivoCita(), result.getMotivoCita());
  }

  @Test
  @DisplayName("Service lanza error si la cita no se encontró")
  void updateWithErrorTest() {
    UpdateCitaDTO dto = new UpdateCitaDTO();
    Optional<Cita> dummy = Optional.empty();

    when(citaRepository.findById(anyLong())).thenReturn(dummy);

    assertThrows(CitaNotFoundException.class, () -> citaService.update(100L, dto));
  }

  @Test
  @DisplayName("El Service debe actualizar una cita en repository")
  void updateTest() throws CitaNotFoundException {
    UpdateCitaDTO dto = new UpdateCitaDTO();

    dto.setFechaCita(null);
    dto.setHoraCita(null);
    dto.setMotivoCita("Peinado");

    Cita cita = new Cita();

    cita.setId(8L);
    cita.setFechaCita(null);
    cita.setHoraCita(null);
    cita.setMotivoCita("Corte de uñas");

    when(citaRepository.findById(anyLong())).thenReturn(Optional.of(cita));

    citaService.update(1L, dto);

    assertEquals(dto.getFechaCita(), cita.getFechaCita());
    assertEquals(dto.getHoraCita(), cita.getHoraCita());
    assertEquals(dto.getMotivoCita(), cita.getMotivoCita());
    verify(citaRepository, times(1)).save(cita);
  }

  @Test
  @DisplayName("Service borra una cita en el repository")
  void deleteById() {

    citaRepository.deleteById(87L);

    verify(citaRepository, times(1)).deleteById(87L);
  }

}
