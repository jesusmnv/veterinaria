package org.bedu.java.backend.veterinaria.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

@AutoConfigureTestDatabase(replace = Replace.NONE)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest
class ConsultaControllerE2ETest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /consultas should return an empty list")
    void emptyListTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/consultas"))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertEquals("[]", content);
    }

    @Test
    @DisplayName("POST /consultas should be return an error if consultation date is missing")
    void consultationDateMissingRequestBodyTest() throws Exception {
        String jsonString = "{"
        + "\"diagnostico\": \"Gripe felina\","
        + "\"tratamientoIndicado\": \"Antibióticos y reposo\","
        + "\"observaciones\": \"La mascota parece mejorar, seguir monitoreando\","
        + "\"mascota\": {"
        + "  \"id\": 9"
        + "},"
        + "\"veterinario\": {"
        + "  \"id\": 8"
        + "}"
        + "}";

        MvcResult result = mockMvc.perform(post("/consultas").contentType("application/json").content(jsonString))
        .andExpect(status().isBadRequest())
        .andReturn();

        String content = result.getResponse().getContentAsString();

        String expectedResponse = "{\"code\":\"ERR_VALID\",\"message\":\"Hubo un error al validar los datos de entrada\",\"details\":[\"La fecha de la consulta no puede ser nula\"]}";

        assertEquals(expectedResponse, content);
        
    }
}