package org.bedu.java.backend.veterinaria.controller;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureTestDatabase(replace = Replace.NONE)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest
class MedicamentoControllerE2ETest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /medicamentos should return an empty llist")
    void emptyListTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/medicamentos"))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertEquals("[]", content);
    }

    @Test
    @DisplayName("POST /medicamentos should be return an error if name is missing")
    void nameMissingRequestBodyTest() throws Exception {
        String jsonString = "{"
        + "\"clasificacion\": \"Desparasitante...\","
        + "\"descripcion\": \"Es un fármaco útil para...\","
        + "\"fechaCaducidad\": \"2001-01-01\","
        + "\"existencia\": 100,"
        + "\"precio\": 100.00,"
        + "\"instruccionesUso\": \"Se administra...\""
        + "}";

        MvcResult result = mockMvc.perform(post("/medicamentos").contentType("application/json").content(jsonString))
        .andExpect(status().isBadRequest())
        .andReturn();

        String content = result.getResponse().getContentAsString();

        String expectedResponse = "{\"code\":\"ERR_VALID\",\"message\":\"Hubo un error al validar los datos de entrada\",\"details\":[\"El nombre del medicamento es obligatorio\"]}";

        assertEquals(expectedResponse, content);
        
    }

}