package org.bedu.java.backend.veterinaria.controller;

import org.bedu.java.backend.veterinaria.repository.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest
class OwnerControllerE2ETest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OwnerRepository repository;

    @BeforeEach
    public void setup() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("GET /owners should return an empty list")
    void emptyListTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertEquals("[]", content);
    }

    @Test
    @DisplayName("POST /owners should be return an error if name is missing")
    void nameMissingRequestBodyTest() throws Exception {
        String jsonString = "{"
                + "\"surname\": \"Cruz\","
                + "\"maternalSurname\": \"Cruz\","
                + "\"address\": \"2001-01-01\","
                + "\"cellphone\": \"1234567890\","
                + "\"email\": \"example@email.com\","
                + "\"birthdate\": \"2001-01-01\","
                + "\"occupation\": \"Teacher\""
                + "}";

        MvcResult result = mockMvc.perform(post("/owners").contentType("application/json").content(jsonString))
                .andExpect(status().isBadRequest())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        String expectedResponse = "{\"code\":\"ERR_VALID\",\"message\":\"There was an error validating the input data\",\"details\":[\"Owner name is mandatory\"]}";

        assertEquals(expectedResponse, content);

    }

    @Test
    @DisplayName("POST /owners should be return an error if cellphone is missing")
    void cellphoneMissingRequestBodyTest() throws Exception {
        String jsonString = "{"
                + "\"name\": \"John\","
                + "\"surname\": \"Cruz\","
                + "\"maternalSurname\": \"Cruz\","
                + "\"address\": \"2001-01-01\","
                + "\"email\": \"example@email.com\","
                + "\"birthdate\": \"2001-01-01\","
                + "\"occupation\": \"Teacher\""
                + "}";

        MvcResult result = mockMvc.perform(post("/owners").contentType("application/json").content(jsonString))
                .andExpect(status().isBadRequest())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        String expectedResponse = "{\"code\":\"ERR_VALID\",\"message\":\"There was an error validating the input data\",\"details\":[\"Cellphone is mandatory\"]}";

        assertEquals(expectedResponse, content);

    }

}
