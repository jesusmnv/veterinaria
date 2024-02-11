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
  @DisplayName("Service debe ser inyectado")
  void smokeTest() {
    assertNotNull(appointmentService);
  }

  @Test
  @DisplayName("Service debe retonar cita desde el repositorio")
  void findAllTest() {
    List<Appointment> data = new LinkedList<>();

    Appointment appointment = new Appointment();

    appointment.setId(4L);
    appointment.setAppointmentDate(null);
    appointment.setAppointmentTime(null);
    appointment.setAppointmentReason("Vacunacion");

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
  @DisplayName("Service debe guardar una cita en el repositorio")
  void saveTest() {
    
    CreateAppointmentDTO dto = new CreateAppointmentDTO();
    
    dto.setAppointmentDate(LocalDate.parse("2025-05-05"));
    dto.setAppointmentTime(LocalTime.parse("12:00"));
    dto.setAppointmentReason("Limpieza dental");

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
  @DisplayName("Service lanza error si la cita no se encontró")
  void updateWithErrorTest() {
    UpdateAppointmentDTO dto = new UpdateAppointmentDTO();
    Optional<Appointment> dummy = Optional.empty();

    when(appointmentRepository.findById(anyLong())).thenReturn(dummy);

    assertThrows(AppointmentNotFoundException.class, () -> appointmentService.update(100L, dto));
  }

  @Test
  @DisplayName("El Service debe actualizar una cita en repository")
  void updateTest() throws AppointmentNotFoundException {

    UpdateAppointmentDTO dto = new UpdateAppointmentDTO();

    dto.setAppointmentDate(LocalDate.parse("2026-06-06"));
    dto.setAppointmentTime(LocalTime.parse("16:00"));
    dto.setAppointmentReason("Peinado");

    Appointment appointment = new Appointment();

    appointment.setId(8L);
    appointment.setAppointmentDate(LocalDate.parse("2027-07-07"));
    appointment.setAppointmentTime(LocalTime.parse("17:00"));
    appointment.setAppointmentReason("Corte ==== UÑAS");

    when(appointmentRepository.findById(anyLong())).thenReturn(Optional.of(appointment));

    appointmentService.update(8L, dto);

    assertEquals(dto.getAppointmentDate(), appointment.getAppointmentDate());
    assertEquals(dto.getAppointmentTime(), appointment.getAppointmentTime());
    assertEquals(dto.getAppointmentReason(), appointment.getAppointmentReason());
    verify(appointmentRepository, times(1)).save(appointment);
  }

  @Test
  @DisplayName("Service borra una cita en el repository")
  void deleteById() {

    appointmentRepository.deleteById(87L);

    verify(appointmentRepository, times(1)).deleteById(87L);
  }

}
