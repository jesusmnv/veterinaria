package org.bedu.java.backend.veterinaria.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.bedu.java.backend.veterinaria.model.Pet;
import org.bedu.java.backend.veterinaria.dto.pet.PetDTO;
import org.bedu.java.backend.veterinaria.repository.PetRepository;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest
class PetControllerE2ETest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PetRepository repository;

    private ObjectMapper mapper = new ObjectMapper();
    @Test
    @DisplayName("GET /pets should return an empty list")
    void emptyListTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/consultations"))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertEquals("[]", content);
    }


    @Test
    @DisplayName("GET /pets should return a list of pets")
    void findAllTest() throws Exception {

        mapper.registerModule(new JavaTimeModule());

        Pet pet1 = new Pet();
        Pet pet2 = new Pet();

        pet1.setName("Onix");
        pet1.setSpecies("Turtle");
        pet1.setBreed("Reeves");
        pet1.setAge(7);
        pet1.setHeight(0.50F);
        pet1.setWeight(10.5F);
        pet1.setGender("Male");
        pet1.setColor("Brown with spots");

        pet2.setName("Darus");
        pet2.setSpecies("Turtle");
        pet2.setBreed("Reeves");
        pet2.setAge(3);
        pet2.setHeight(0.22F);
        pet2.setWeight(5.6F);
        pet2.setGender("Male");
        pet2.setColor("Brown with spots");

        repository.save(pet1);
        repository.save(pet2);

        MvcResult result = mockMvc.perform(get("/pets"))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        TypeReference<List<PetDTO>> listTypeReference = new TypeReference<List<PetDTO>>() {
        };

        List<PetDTO> response = mapper.readValue(content, listTypeReference);

        assertEquals(2, response.size());

        assertEquals(pet1.getName(), response.get(0).getName());
        assertEquals(pet1.getAge(), response.get(0).getAge());
        assertEquals(pet1.getHeight(), response.get(0).getHeight());
        assertEquals(pet1.getWeight(), response.get(0).getWeight());

        assertEquals(pet2.getName(), response.get(1).getName());
        assertEquals(pet2.getAge(), response.get(1).getAge());
        assertEquals(pet2.getHeight(), response.get(1).getHeight());
        assertEquals(pet2.getWeight(), response.get(1).getWeight());

    }

    @Test
    @DisplayName("POST /pets should be return an error if name is missing")
    void nameMissingRequestBodyTest() throws Exception {
        String jsonString = "{"
                + "\"species\": \"Canine\","
                + "\"breed\": \"Labrador Retriever\","
                + "\"age\": 4,"
                + "\"height\": 0.6,"
                + "\"weight\": 25.5,"
                + "\"gender\": \"Male\","
                + "\"color\": \"Golden\","
                + "\"owner\": {"
                + "  \"id\": 10"
                + "}"
                + "}";


        MvcResult result = mockMvc.perform(post("/pets").contentType("application/json").content(jsonString))
                .andExpect(status().isBadRequest())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        String expectedResponse = "{\"code\":\"ERR_VALID\",\"message\":\"There was an error validating the input data\",\"details\":[\"The pet's name is mandatory\"]}";

        assertEquals(expectedResponse, content);

    }

}
