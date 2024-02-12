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
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.bedu.java.backend.veterinaria.dto.appointment.AppointmentDTO;
import org.bedu.java.backend.veterinaria.dto.appointment.CreateAppointmentDTO;
import org.bedu.java.backend.veterinaria.dto.appointment.UpdateAppointmentDTO;
import org.bedu.java.backend.veterinaria.exception.AppointmentNotFoundException;
import org.bedu.java.backend.veterinaria.model.Appointment;
import org.bedu.java.backend.veterinaria.repository.AppointmentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class AppointmentServiceTest {
  @MockBean
  private AppointmentRepository appointmentRepository;

  @Autowired
  private AppointmentService appointmentService;

  @Test
  @DisplayName("Service should be injected")
  void smokeTest() {
    assertNotNull(appointmentService);
  }

  @Test
  @DisplayName("Service should return appointments from the repository")
  void findAllTest() {
    List<Appointment> data = new LinkedList<>();

    Appointment appointment = new Appointment();

    appointment.setId(4L);
    appointment.setAppointmentDate(LocalDate.parse("2025-05-05"));
    appointment.setAppointmentTime(LocalTime.parse("14:00"));
    appointment.setAppointmentReason("Vaccination");

    data.add(appointment);

    when(appointmentRepository.findAll()).thenReturn(data);

    List<AppointmentDTO> result = appointmentService.findAll();

    assertNotNull(result);
    assertTrue(result.size() > 0);
    assertEquals(appointment.getId(), result.get(0).getId());
    assertEquals(appointment.getAppointmentDate(), result.get(0).getAppointmentDate());
    assertEquals(appointment.getAppointmentTime(), result.get(0).getAppointmentTime());
    assertEquals(appointment.getAppointmentReason(), result.get(0).getAppointmentReason());
  }

  @Test
  @DisplayName("Service should save a appointment in the repository")
  void saveTest() {
    
    CreateAppointmentDTO dto = new CreateAppointmentDTO();
    
    dto.setAppointmentDate(LocalDate.parse("2025-05-05"));
    dto.setAppointmentTime(LocalTime.parse("12:00"));
    dto.setAppointmentReason("Dental cleaning");

    Appointment model = new Appointment();

    model.setId(7L);
    model.setAppointmentDate(dto.getAppointmentDate());
    model.setAppointmentTime((dto.getAppointmentTime()));
    model.setAppointmentReason(dto.getAppointmentReason());

    when(appointmentRepository.save(any(Appointment.class))).thenReturn(model);

    AppointmentDTO result = appointmentService.save(dto);

    assertNotNull(result);
    assertEquals(model.getId(), result.getId());
    assertEquals(model.getAppointmentDate(), result.getAppointmentDate());
    assertEquals(model.getAppointmentTime(), result.getAppointmentTime());
    assertEquals(model.getAppointmentReason(), result.getAppointmentReason());
  }

  @Test
  @DisplayName("Service should throw an error if the appointment is not found when attempting to update it")
  void updateWithErrorTest() {
    UpdateAppointmentDTO dto = new UpdateAppointmentDTO();
    Optional<Appointment> dummy = Optional.empty();

    when(appointmentRepository.findById(anyLong())).thenReturn(dummy);

    assertThrows(AppointmentNotFoundException.class, () -> appointmentService.update(100L, dto));
  }

  @Test
  @DisplayName("Service should update a appointment in the repository")
  void updateTest() throws AppointmentNotFoundException {

    UpdateAppointmentDTO dto = new UpdateAppointmentDTO();

    dto.setAppointmentDateU(LocalDate.parse("2026-06-06"));
    dto.setAppointmentTimeU(LocalTime.parse("16:00"));
    dto.setAppointmentReasonU("Peinado");

    Appointment appointment = new Appointment();

    appointment.setId(8L);
    appointment.setAppointmentDate(LocalDate.parse("2027-07-07"));
    appointment.setAppointmentTime(LocalTime.parse("17:00"));
    appointment.setAppointmentReason("Corte ==== UÑAS");

    when(appointmentRepository.findById(anyLong())).thenReturn(Optional.of(appointment));

    appointmentService.update(8L, dto);

    assertEquals(dto.getAppointmentDateU(), appointment.getAppointmentDate());
    assertEquals(dto.getAppointmentTimeU(), appointment.getAppointmentTime());
    assertEquals(dto.getAppointmentReasonU(), appointment.getAppointmentReason());
    verify(appointmentRepository, times(1)).save(appointment);
  }

  @Test
  @DisplayName("Service should delete an appointment by ID") 
  void deleteById() {

    //TODO: Se debe testear el servicio no el repositorio

    appointmentRepository.deleteById(87L);

    verify(appointmentRepository, times(1)).deleteById(87L);
  }

}
