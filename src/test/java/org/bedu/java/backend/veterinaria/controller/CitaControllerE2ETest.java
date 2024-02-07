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
public class CitaControllerE2ETest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @DisplayName("GET /citas should return an empty list")
  void emptyListTest() throws Exception {

    MvcResult result = mockMvc.perform(get("/citas"))
        .andExpect(status().isOk())
        .andReturn();

    String content = result.getResponse().getContentAsString();

    assertEquals("[]", content);
  }

  /**
   * @throws Exception
   */
  @Test
  @DisplayName("POST /citas should be return an error if appointment date is missing")
  void appointmentDateMissingInRequestBodyTest() throws Exception {

    String jsonString = "{\"horaCita\": \"12:00\", "
        + "\"motivoCita\": \"Urgencia\", "
        + "\"primeraCita\": true, "
        + "\"veterinario\": {\"id\": 4}, "
        + "\"propietario\": {\"id\": 9}}";

    MvcResult result = mockMvc.perform(post("/citas").contentType("application/json").content(jsonString))
        .andExpect(status().isBadRequest())
        .andReturn();

    String content = result.getResponse().getContentAsString();

    String expectedResponse = "{\"code\":\"ERR_VALID\",\"message\":\"Hubo un error al validar los datos de entrada\",\"details\":[\"La fecha de la cita no puede ser nula\"]}";
    assertEquals(expectedResponse, content);

  }

}
