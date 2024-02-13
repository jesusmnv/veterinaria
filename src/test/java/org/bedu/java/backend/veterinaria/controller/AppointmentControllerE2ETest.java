package org.bedu.java.backend.veterinaria.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.bedu.java.backend.veterinaria.dto.appointment.AppointmentDTO;
import org.bedu.java.backend.veterinaria.model.Appointment;
import org.bedu.java.backend.veterinaria.repository.AppointmentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureTestDatabase(replace = Replace.NONE)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest
class AppointmentControllerE2ETest {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private AppointmentRepository repository;

  private ObjectMapper mapper = new ObjectMapper();


  @Test
  @DisplayName("GET /appointments should return an empty list")
  void emptyListTest() throws Exception {

    MvcResult result = mockMvc.perform(get("/appointments"))
        .andExpect(status().isOk())
        .andReturn();

    String content = result.getResponse().getContentAsString();

    assertEquals("[]", content);
  }

  @Test
  @DisplayName("GET /appointments should return a list of appointments")
  void findAllTest() throws Exception {

    mapper.registerModule(new JavaTimeModule());

    Appointment appointment1 = new Appointment();
    Appointment appointment2 = new Appointment();

    appointment1.setAppointmentDate(LocalDate.parse("2024-01-20"));
    appointment1.setAppointmentTime(LocalTime.parse("14:00"));
    appointment1.setFirstAppointment(true);
    appointment1.setAppointmentReason("Appointment reason...");

    appointment2.setAppointmentDate(LocalDate.parse("2023-12-30"));
    appointment2.setAppointmentTime(LocalTime.parse("16:00"));
    appointment2.setFirstAppointment(true);
    appointment2.setAppointmentReason("Appointment reason...");

    repository.save(appointment1);
    repository.save(appointment2);

    MvcResult result = mockMvc.perform(get("/appointments"))
            .andExpect(status().isOk())
            .andReturn();

    String content = result.getResponse().getContentAsString();

    TypeReference<List<AppointmentDTO>> listTypeReference = new TypeReference<List<AppointmentDTO>>() {
    };

    List<AppointmentDTO> response = mapper.readValue(content, listTypeReference);

    assertEquals(2, response.size());

    assertEquals(appointment1.getAppointmentDate(), response.get(0).getAppointmentDate());
    assertEquals(appointment1.getAppointmentTime(), response.get(0).getAppointmentTime());
    assertEquals(appointment1.getFirstAppointment(), response.get(0).getFirstAppointment());
    assertEquals(appointment1.getAppointmentReason(), response.get(0).getAppointmentReason());

    assertEquals(appointment2.getAppointmentDate(), response.get(1).getAppointmentDate());
    assertEquals(appointment2.getAppointmentTime(), response.get(1).getAppointmentTime());
    assertEquals(appointment2.getFirstAppointment(), response.get(1).getFirstAppointment());
    assertEquals(appointment2.getAppointmentReason(), response.get(1).getAppointmentReason());

  }

  @Test
  @DisplayName("POST /appointments should be return an error if appointment date is missing")
  void appointmentDateMissingInRequestBodyTest() throws Exception {

    String jsonString = "{\"appointmentTime\": \"12:00\", "
        + "\"appointmentReason\": \"Urgency\", "
        + "\"firstAppointment\": true, "
        + "\"vet\": {\"id\": 4}, "
        + "\"owner\": {\"id\": 9}}";

    MvcResult result = mockMvc.perform(post("/appointments").contentType("application/json").content(jsonString))
        .andExpect(status().isBadRequest())
        .andReturn();

    String content = result.getResponse().getContentAsString();

    String expectedResponse = "{\"code\":\"ERR_VALID\",\"message\":\"There was an error validating the input data\",\"details\":[\"Appointment date cannot be null\"]}";
    assertEquals(expectedResponse, content);

  }

  @Test
  @DisplayName("POST /appointments should be return an error if appointment time is missing")
  void appointmentTimeMissingInRequestBodyTest() throws Exception {

    String jsonString = "{\"appointmentDate\": \"2025-05-05\", "
            + "\"appointmentReason\": \"Urgency\", "
            + "\"firstAppointment\": true, "
            + "\"vet\": {\"id\": 4}, "
            + "\"owner\": {\"id\": 9}}";

    MvcResult result = mockMvc.perform(post("/appointments").contentType("application/json").content(jsonString))
            .andExpect(status().isBadRequest())
            .andReturn();

    String content = result.getResponse().getContentAsString();

    String expectedResponse = "{\"code\":\"ERR_VALID\",\"message\":\"There was an error validating the input data\",\"details\":[\"Appointment time cannot be null\"]}";
    assertEquals(expectedResponse, content);

  }

}
