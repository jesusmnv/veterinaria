package org.bedu.java.backend.veterinaria.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.bedu.java.backend.veterinaria.dto.vet.VetDTO;
import org.bedu.java.backend.veterinaria.model.Vet;
import org.bedu.java.backend.veterinaria.repository.VetRepository;
import org.junit.jupiter.api.BeforeEach;
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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@AutoConfigureTestDatabase(replace = Replace.NONE)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest
class VetControllerE2ETest {

        private static final String BASE = "/vets";

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private VetRepository repository;

        private ObjectMapper mapper = new ObjectMapper();

        @BeforeEach
        public void setup() {
                repository.deleteAll();
        }

        @Test
        @DisplayName("GET /vets should return an empty list")
        void emptyListTest() throws Exception {

                MvcResult result = mockMvc.perform(get(BASE))
                                .andExpect(status().isOk()).andReturn();

                String content = result.getResponse().getContentAsString();

                assertEquals("[]", content);
        }

        @Test
        @DisplayName("GET /vets should return a list of vets")
        void findAllTest() throws Exception {

                mapper.registerModule(new JavaTimeModule());

                Vet vet1 = new Vet();
                Vet vet2 = new Vet();

                vet1.setName("Fernando");
                vet1.setSurname("Ramos");
                vet1.setMaternalSurname("Carvajal");
                vet1.setBirthdate(LocalDate.parse("1985-12-12"));
                vet1.setCellphone("8332641597");
                vet1.setEmail("fernandoRC@gmail.com");
                vet1.setSpecialty("Cirujano");
                vet1.setEntryTime(LocalTime.parse("12:25"));
                vet1.setExitTime(LocalTime.parse("16:15"));

                vet2.setName("Marcos");
                vet2.setSurname("Cruz");
                vet2.setMaternalSurname("Carvajal");
                vet2.setBirthdate(LocalDate.parse("1985-12-12"));
                vet2.setCellphone("8332641597");
                vet2.setEmail("marcosC@gmail.com");
                vet2.setSpecialty("Cirujano");
                vet2.setEntryTime(LocalTime.parse("12:25"));
                vet2.setExitTime(LocalTime.parse("16:15"));

                repository.save(vet1);
                repository.save(vet2);

                MvcResult result = mockMvc.perform(get("/vets"))
                                .andExpect(status().isOk())
                                .andReturn();

                String content = result.getResponse().getContentAsString();

                TypeReference<List<VetDTO>> listTypeReference = new TypeReference<List<VetDTO>>() {
                };

                List<VetDTO> response = mapper.readValue(content, listTypeReference);

                assertEquals(2, response.size());
                assertEquals(vet1.getName(), response.get(0).getName());
                assertEquals(vet1.getSurname(), response.get(0).getSurname());
                assertEquals(vet1.getMaternalSurname(), response.get(0).getMaternalSurname());
                assertEquals(vet1.getBirthdate(), response.get(0).getBirthdate());
                assertEquals(vet1.getCellphone(), response.get(0).getCellphone());
                assertEquals(vet1.getEmail(), response.get(0).getEmail());

                assertEquals(vet2.getName(), response.get(1).getName());
                assertEquals(vet2.getSurname(), response.get(1).getSurname());
                assertEquals(vet2.getMaternalSurname(), response.get(1).getMaternalSurname());
                assertEquals(vet2.getBirthdate(), response.get(1).getBirthdate());
                assertEquals(vet2.getCellphone(), response.get(1).getCellphone());
                assertEquals(vet2.getEmail(), response.get(1).getEmail());

        }

        @Test
        @DisplayName("POST /vets should be return an error if name is missing")
        void nameMissingRequestBodyTest() throws Exception {
                String jsonString = "{"
                                + "\"surname\": \"Velez\","
                                + "\"maternalSurname\": \"Ramirez\","
                                + "\"birthdate\": \"2001-01-01\","
                                + "\"cellphone\": \"1234567890\","
                                + "\"email\": \"velez@gmail.com\","
                                + "\"specialty\": \"Cirujano\","
                                + "\"entryTime\": \"08:00\","
                                + "\"exitTime\": \"10:00\""
                                + "}";

                MvcResult result = mockMvc.perform(post("/vets").contentType("application/json").content(jsonString))
                                .andExpect(status().isBadRequest())
                                .andReturn();

                String content = result.getResponse().getContentAsString();

                String expectedResponse = "{\"code\":\"ERR_VALID\",\"message\":\"There was an error validating the input data\",\"details\":[\"The vet name is mandatory\"]}";

                assertEquals(expectedResponse, content);

        }

}