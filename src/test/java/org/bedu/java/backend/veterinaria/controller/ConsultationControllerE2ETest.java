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
class ConsultationControllerE2ETest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /consultations should return an empty list")
    void emptyListTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/consultations"))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertEquals("[]", content);
    }

    @Test
    @DisplayName("POST /consultations should be return an error if consultation date is missing")
    void consultationDateMissingRequestBodyTest() throws Exception {
        String jsonString = "{"
        + "\"diagnosis\": \"Feline flu\","
        + "\"prescribedTreatment\": \"Antibiotics and rest\","
        + "\"observations\": \"The pet seems to be improving, continue monitoring\","
        + "\"pet\": {"
        + "  \"id\": 9"
        + "},"
        + "\"vet\": {"
        + "  \"id\": 8"
        + "}"
        + "}";


        MvcResult result = mockMvc.perform(post("/consultations").contentType("application/json").content(jsonString))
        .andExpect(status().isBadRequest())
        .andReturn();

        String content = result.getResponse().getContentAsString();

        String expectedResponse = "{\"code\":\"ERR_VALID\",\"message\":\"There was an error validating the input data\",\"details\":[\"Consultation date cannot be null\"]}";

        assertEquals(expectedResponse, content);
        
    }
}