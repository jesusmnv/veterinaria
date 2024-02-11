package org.bedu.java.backend.veterinaria.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

import org.bedu.java.backend.veterinaria.dto.appointment.AppointmentDTO;
import org.bedu.java.backend.veterinaria.dto.appointment.CreateAppointmentDTO;
import org.bedu.java.backend.veterinaria.dto.appointment.UpdateAppointmentDTO;
import org.bedu.java.backend.veterinaria.exception.AppointmentNotFoundException;
import org.bedu.java.backend.veterinaria.service.AppointmentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class AppointmentControllerTest {

  @MockBean
  private AppointmentService appointmentService;

  @Autowired
  private AppointmentController appointmentController;

  @Test
  @DisplayName("Controller debe inyectarse")
  void smokeTest() {
    assertNotNull(appointmentController);
  }

  @Test
  @DisplayName("Controller regresa una lista de citas")
  void findAllTest() {

    List<AppointmentDTO> data = new LinkedList<>();

    AppointmentDTO cita = new AppointmentDTO();

    cita.setId(9L);
    cita.setAppointmentReason("Operation");

    data.add(cita);

    when(appointmentService.findAll()).thenReturn(data);

    java.util.List<AppointmentDTO> result = appointmentController.findAll();

    assertNotNull(result);
    assertTrue(result.size() > 0);
    assertEquals(cita.getId(), result.get(0).getId());
    assertEquals(cita.getAppointmentReason(), result.get(0).getAppointmentReason());

  }

  @Test
  @DisplayName("Controller guarda una cita")
  void saveTest() {
    CreateAppointmentDTO dto = new CreateAppointmentDTO();

    dto.setAppointmentDate(LocalDate.parse("2025-05-05"));
    dto.setAppointmentTime(LocalTime.parse("12:00"));
    dto.setAppointmentReason("Operation");

    AppointmentDTO cita = new AppointmentDTO();

    cita.setId(200L);
    cita.setAppointmentDate(dto.getAppointmentDate());
    cita.setAppointmentTime(dto.getAppointmentTime());
    cita.setAppointmentReason(dto.getAppointmentReason());

    when(appointmentService.save(any(CreateAppointmentDTO.class))).thenReturn(cita);

    AppointmentDTO result = appointmentController.save(dto);

    assertNotNull(result);
    assertEquals(cita.getId(), result.getId());
    assertEquals(cita.getAppointmentDate(), result.getAppointmentDate());
    assertEquals(cita.getAppointmentTime(), result.getAppointmentTime());
    assertEquals(cita.getAppointmentReason(), result.getAppointmentReason());

  }

  @Test
  @DisplayName("Controller debe actualizar una cita")
  void updateTest() throws AppointmentNotFoundException {
    UpdateAppointmentDTO dto = new UpdateAppointmentDTO();

    dto.setAppointmentDate(LocalDate.parse("2026-06-06"));
    dto.setAppointmentTime(LocalTime.parse("16:00"));
    dto.setAppointmentReason("Corte de orejas");

    appointmentController.update(6L, dto);

    verify(appointmentService, times(1)).update(6L, dto);
  }

  @Test
  @DisplayName("Controller should delete a movie")
  void deleteByIdTest() throws AppointmentNotFoundException {

    appointmentController.deleteById(200L);

    verify(appointmentService, times(1)).deleteById(200L);
  }

}
