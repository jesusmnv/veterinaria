package org.bedu.java.backend.veterinaria.controller;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.bedu.java.backend.veterinaria.dto.medication.MedicationDTO;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.List;

import org.bedu.java.backend.veterinaria.model.Medication;
import org.bedu.java.backend.veterinaria.repository.MedicationRepository;

@AutoConfigureTestDatabase(replace = Replace.NONE)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest
class MedicationControllerE2ETest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MedicationRepository repository;

    private ObjectMapper mapper = new ObjectMapper();


    @BeforeEach
    public void setup() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("GET /medications should return an empty list")
    void emptyListTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/medications"))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertEquals("[]", content);
    }

    @Test
    @DisplayName("GET /medications should return a list of medications")
    void findAllTest() throws Exception {

        mapper.registerModule(new JavaTimeModule());

        Medication medication1 = new Medication();
        Medication medication2 = new Medication();

        medication1.setName("Prednisone");
        medication1.setClassification("Corticosteroid");
        medication1.setDescription("Treats inflammatory and allergic conditions in dogs and cats");
        medication1.setExpirationDate(LocalDate.parse("2024-01-20"));
        medication1.setStock(40);
        medication1.setPrice(17.25F);
        medication1.setUsageInstructions("Administer according to the dose prescribed by the vetU");

        medication2.setName("Cephalexin");
        medication2.setClassification("Antibiotic");
        medication2.setDescription("Treats bacterial infections in dogs and cats");
        medication2.setExpirationDate(LocalDate.parse("2023-12-30"));
        medication2.setStock(65);
        medication2.setPrice(10.5F);
        medication2.setUsageInstructions("Administer according to the dose prescribed by the vetU");

        repository.save(medication1);
        repository.save(medication2);

        MvcResult result = mockMvc.perform(get("/medications"))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        TypeReference<List<MedicationDTO>> listTypeReference = new TypeReference<List<MedicationDTO>>() {
        };

        List<MedicationDTO> response = mapper.readValue(content, listTypeReference);

        assertEquals(2, response.size());
        assertEquals(medication1.getName(), response.get(0).getName());
        assertEquals(medication1.getExpirationDate(), response.get(0).getExpirationDate());
        assertEquals(medication2.getName(), response.get(1).getName());
        assertEquals(medication2.getExpirationDate(), response.get(1).getExpirationDate());

    }

    @Test
    @DisplayName("POST /medications should be return an error if name is missing")
    void nameMissingRequestBodyTest() throws Exception {
        String jsonString = "{"
                + "\"classification\": \"Dewormer\","
                + "\"description\": \"It is a useful drug for...\","
                + "\"expirationDate\": \"2001-01-01\","
                + "\"stock\": 100,"
                + "\"price\": 100.00,"
                + "\"usageInstructions\": \"It is administered...\""
                + "}";

        MvcResult result = mockMvc.perform(post("/medications").contentType("application/json").content(jsonString))
                .andExpect(status().isBadRequest())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        String expectedResponse = "{\"code\":\"ERR_VALID\",\"message\":\"There was an error validating the input data\",\"details\":[\"The medication name is mandatory\"]}";

        assertEquals(expectedResponse, content);

    }

}