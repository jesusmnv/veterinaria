package org.bedu.java.backend.veterinaria.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.bedu.java.backend.veterinaria.dto.veterinario.VeterinarioDTO;
import org.bedu.java.backend.veterinaria.model.Veterinario;
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

import io.swagger.v3.oas.annotations.parameters.RequestBody;

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

        // Realizar una petición de tipo GET hacia /veterinarios y esperar que el
        // resultado sea 200
        MvcResult result = mockMvc.perform(get(BASE))
                .andExpect(status().isOk()).andReturn();

        String content = result.getResponse().getContentAsString();

        assertEquals("[]", content);
    }

    @Test
    @DisplayName("GET /veterinarios deberia de obtener todos los Veterinarios")
    void getAllTest() throws Exception {

        MvcResult result = mockMvc.perform(get(BASE))
                .andExpect(status().isOk()).andReturn();

        assertNotNull(result.getResponse().getContentAsString());

    }

    @Test
    @DisplayName("POST /veterinarios should be return if name is missing")
    void nameMissingPostTest() throws Exception{
        String jsonVeterinario = "{"
        + "\"apellidoPaterno\": \"Velez\","
        + "\"apellidoMaterno\": \"Ramirez\","
        + "\"fechaNacimiento\": \"1998-11-15\","
        + "\"celular\": 8332547851,"
        + "\"correo\": pepeVlez@gmail.com"
        + "\"especialidad\": \"Cirujano\""
        +  "\"horaEntrada\": \"00:10\""
        +  "\"horaSalida\": \"07:00\""
        + "}";

        MvcResult result = mockMvc.perform(post(BASE).content("application/json").content(jsonVeterinario))
                    .andExpect(status().isBadRequest()).andReturn();

        String content = result.getResponse().getContentAsString();

        String expectedResponse =  "{\"code\":\"ERR_VALID\",\"message\":\"Hubo un error al validar los datos de entrada\",\"details\":[\"El nombre del medicamento es obligatorio\"]}";

        assertEquals(expectedResponse, content);
    }


}



    //@DisplayName("POST /veterinarios should be return error if any data is missing")
      /*   if(veterinario.getNombre() == null || veterinario.getApellidoPaterno() == null || veterinario.getApellidoMaterno() == null 
        || veterinario.getCelular() == null || veterinario.getCorreo() == null || veterinario.getEspecialidad() == null
        || veterinario.getFechaNacimiento() == null || veterinario.getHoraEntrada() == null || veterinario.getHoraSalida() == null){
            result = mockMvc.perform(post(BASE)).andExpect(status().isBadRequest()).andReturn();
        }

        String content = result.getResponse().getContentAsString();

        String expectedResponse = "{\"code\":\"ERR_VALID\",\"message\":\"Hubo un error al validar los datos de entrada\",\"details\":[\"El nombre del medicamento es obligatorio\"]}";

        assertEquals(expectedResponse, content);

        boolean anyDataMissing = Arrays.asList(veterinario.getClass().getDeclaredFields()).stream()
            .anyMatch(field -> {
                try {
                    field.setAccessible(true);
                    return field.get(veterinario) == null;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    return true;
                }
            });

        if (anyDataMissing) {
            result = mockMvc.perform(post(BASE)).andExpect(status().isBadRequest()).andReturn();
        }

        String content = result.getResponse().getContentAsString();

        String expectedResponse = "{\"code\":\"ERR_VALID\",\"message\":\"Hubo un error al validar los datos de entrada\",\"details\":[\"El nombre del medicamento es obligatorio\"]}";

        assertEquals(expectedResponse, content);
        */
