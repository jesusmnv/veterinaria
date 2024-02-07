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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureTestDatabase(replace = Replace.NONE)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest
class VeterinarioControllerE2ETest {
    
    private static final String BASE = "/veterinarios";

    @Autowired
    private MockMvc mockMvc;
    @Test
    @DisplayName("GET /veterinarios debería regresar una lista vacia")
    void emptyListTest() throws Exception {

        // Realizar una petición de tipo GET hacia /veterinarios y esperar que el resultado sea 200
        MvcResult result = mockMvc.perform(get(BASE))
        .andExpect(status().isOk()).andReturn();

        String content = result.getResponse().getContentAsString();

        assertEquals("[]", content);
    }

    
    @Test
    @DisplayName("GET /veterinarios deberia de obtener todos los Veterinarios")
    void getAllTest() throws Exception{

        MvcResult result = mockMvc.perform(get(BASE))
        .andExpect(status().isOk()).andReturn();

        String content = result.getResponse().getContentAsString();

        assertNotNull(result.getResponse().getContentAsString());
        //assertEquals("[{}]", content);
    }
    

    
    //@Test
    //@DisplayName("POST /veterinarios regresa un error si falta el nombre del Veterinario")

    // BUSCAR ERROR CUANDO SEA DATOS DE ENTRADA

}

