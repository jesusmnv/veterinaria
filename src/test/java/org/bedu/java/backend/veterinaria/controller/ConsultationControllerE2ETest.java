package org.bedu.java.backend.veterinaria.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.bedu.java.backend.veterinaria.dto.consultation.ConsultationDTO;
import org.bedu.java.backend.veterinaria.model.Consultation;
import org.bedu.java.backend.veterinaria.repository.ConsultationRepository;
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
import java.util.List;

@AutoConfigureTestDatabase(replace = Replace.NONE)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest
class ConsultationControllerE2ETest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ConsultationRepository repository;

    private ObjectMapper mapper = new ObjectMapper();

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
    @DisplayName("GET /consultations should return a list of consultations")
    void findAllTest() throws Exception {

        mapper.registerModule(new JavaTimeModule());

        Consultation consultation1 = new Consultation();
        Consultation consultation2 = new Consultation();

        consultation1.setConsultationDate(LocalDate.parse("2024-01-20"));
        consultation1.setDiagnosis("Diagnosis...");
        consultation1.setPrescribedTreatment("Prescribed Treatment...");
        consultation1.setObservations("Administer according to the dose prescribed by the vet");

        consultation2.setConsultationDate(LocalDate.parse("2023-12-30"));
        consultation2.setDiagnosis("Diagnosis...");
        consultation2.setPrescribedTreatment("Prescribed Treatment...");
        consultation2.setObservations("Administer according to the dose prescribed by the vet");

        repository.save(consultation1);
        repository.save(consultation2);

        MvcResult result = mockMvc.perform(get("/consultations"))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        TypeReference<List<ConsultationDTO>> listTypeReference = new TypeReference<List<ConsultationDTO>>() {
        };

        List<ConsultationDTO> response = mapper.readValue(content, listTypeReference);

        assertEquals(2, response.size());

        assertEquals(consultation1.getConsultationDate(), response.get(0).getConsultationDate());
        assertEquals(consultation1.getDiagnosis(), response.get(0).getDiagnosis());
        assertEquals(consultation1.getPrescribedTreatment(), response.get(0).getPrescribedTreatment());
        assertEquals(consultation1.getObservations(), response.get(0).getObservations());

        assertEquals(consultation2.getConsultationDate(), response.get(1).getConsultationDate());
        assertEquals(consultation2.getDiagnosis(), response.get(1).getDiagnosis());
        assertEquals(consultation2.getPrescribedTreatment(), response.get(1).getPrescribedTreatment());
        assertEquals(consultation2.getObservations(), response.get(1).getObservations());

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

    @Test
    @DisplayName("POST /consultations should be return an error if diagnosis is missing")
    void diagnosisMissingRequestBodyTest() throws Exception {
        String jsonString = "{"
                + "\"consultationDate\": \"2025-05-05\","
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

        String expectedResponse = "{\"code\":\"ERR_VALID\",\"message\":\"There was an error validating the input data\",\"details\":[\"Diagnosis is mandatory\"]}";

        assertEquals(expectedResponse, content);

    }

}