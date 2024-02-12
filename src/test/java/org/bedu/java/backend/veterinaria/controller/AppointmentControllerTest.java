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
  @DisplayName("Controller should return a list of appointments")
  void findAllTest() {

    List<AppointmentDTO> data = new LinkedList<>();

    AppointmentDTO appointmentDTO = new AppointmentDTO();

    appointmentDTO.setId(9L);
    appointmentDTO.setAppointmentDate(LocalDate.parse("2025-05-05"));
    appointmentDTO.setAppointmentTime(LocalTime.parse("12:00"));
    appointmentDTO.setFirstAppointment(true);
    appointmentDTO.setAppointmentReason("Operation");
    data.add(appointmentDTO);

    when(appointmentService.findAll()).thenReturn(data);

    List<AppointmentDTO> result = appointmentController.findAll();

    assertNotNull(result);
    assertTrue(result.size() > 0);
    assertEquals(appointmentDTO.getId(), result.get(0).getId());
    assertEquals(appointmentDTO.getAppointmentDate(), result.get(0).getAppointmentDate());
    assertEquals(appointmentDTO.getAppointmentTime(), result.get(0).getAppointmentTime());
    assertEquals(appointmentDTO.getAppointmentReason(), result.get(0).getAppointmentReason());

  }

  @Test
  @DisplayName("Controller should save an appointment")
  void saveTest() {
    CreateAppointmentDTO dto = new CreateAppointmentDTO();

    dto.setAppointmentDate(LocalDate.parse("2025-05-05"));
    dto.setAppointmentTime(LocalTime.parse("12:00"));
    dto.setFirstAppointment(true);
    dto.setAppointmentReason("Operation");

    AppointmentDTO appointmentDTO = new AppointmentDTO();

    appointmentDTO.setId(200L);
    appointmentDTO.setAppointmentDate(dto.getAppointmentDate());
    appointmentDTO.setAppointmentTime(dto.getAppointmentTime());
    appointmentDTO.setFirstAppointment(dto.getFirstAppointment());
    appointmentDTO.setAppointmentReason(dto.getAppointmentReason());

    when(appointmentService.save(any(CreateAppointmentDTO.class))).thenReturn(appointmentDTO);

    AppointmentDTO result = appointmentController.save(dto);

    assertNotNull(result);
    assertEquals(appointmentDTO.getId(), result.getId());
    assertEquals(appointmentDTO.getAppointmentDate(), result.getAppointmentDate());
    assertEquals(appointmentDTO.getAppointmentTime(), result.getAppointmentTime());
    assertEquals(appointmentDTO.getFirstAppointment(), result.getFirstAppointment());
    assertEquals(appointmentDTO.getAppointmentReason(), result.getAppointmentReason());

  }

  @Test
  @DisplayName("Controller should update an appointment")
  void updateTest() throws AppointmentNotFoundException {
    UpdateAppointmentDTO dto = new UpdateAppointmentDTO();

    dto.setAppointmentDateU(LocalDate.parse("2026-06-06"));
    dto.setAppointmentTimeU(LocalTime.parse("16:00"));
    dto.setFirstAppointmentU(true);
    dto.setAppointmentReasonU("Corte de orejas");

    appointmentController.update(6L, dto);

    verify(appointmentService, times(1)).update(6L, dto);
  }

  @Test
  @DisplayName("Controller should delete an appointment")
  void deleteByIdTest() throws AppointmentNotFoundException {

    appointmentController.deleteById(200L);

    verify(appointmentService, times(1)).deleteById(200L);
  }

}
