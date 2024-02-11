package org.bedu.java.backend.veterinaria.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
  @DisplayName("POST /appointments should be return an error if appointment date is missing")
  void appointmentDateMissingInRequestBodyTest() throws Exception {

    String jsonString = "{\"appointmentTime\": \"12:00\", "
        + "\"appointmentReason\": \"Urgencia\", "
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

}
