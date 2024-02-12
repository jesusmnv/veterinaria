package org.bedu.java.backend.veterinaria.controller;

import org.bedu.java.backend.veterinaria.model.Invoice;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.bedu.java.backend.veterinaria.dto.ErrorDTO;
import org.bedu.java.backend.veterinaria.dto.invoice.InvoiceDTO;
import org.bedu.java.backend.veterinaria.repository.InvoiceRepository;
import org.bedu.java.backend.veterinaria.repository.OwnerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.List;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.core.type.TypeReference;

@AutoConfigureTestDatabase(replace = Replace.NONE)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest
class InvoiceControllerE2ETest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private InvoiceRepository repository;

    @Autowired
    private OwnerRepository propietarioRepository;

    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        repository.deleteAll();
        propietarioRepository.deleteAll();
    }

    @Test
    @DisplayName("GET /invoices should return an empty list")
    void emptyListTest() throws Exception {
        // Realiza una peticion de tipo GET /invoices
        MvcResult result = mockMvc.perform(get("/invoices")).andExpect(status().isOk()).andReturn();

        String content = result.getResponse().getContentAsString();

        assertEquals("[]", content);
    }

    @Test
    @DisplayName("GET /invoices should return a list of invoices")
    void findAll() throws Exception {
        Invoice invoice = new Invoice();
        LocalDate fecha = LocalDate.parse("2023-12-12");
        invoice.setId(1L);
        invoice.setIssuanceDate(fecha);
        invoice.setVat(1);
        invoice.setLegalName("qwerty");
        invoice.setClientRFC("qwerty");
        invoice.setSubtotal(150);
        invoice.setTotal(1500);
        invoice.setOwner(null);

        repository.save(invoice);

        MvcResult result = mockMvc.perform(get("/invoices"))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        // Creamos una referencia del tipo al que se va a convertir el JSON
        TypeReference<List<InvoiceDTO>> listTypeReference = new TypeReference<List<InvoiceDTO>>() {
        };

        mapper.registerModule(new JavaTimeModule());
        // Convertimos el JSON a un objeto de Java
        List<InvoiceDTO> response = mapper.readValue(content, listTypeReference);

        // Hacemos las verificaciones basadas en los objetos
        assertEquals(1, response.size());
        assertEquals(invoice.getId(), response.get(0).getId());
    }

    @Test
    @DisplayName("POST /invoices should return an error if any parameter is missing")
    void postMissingParameterRequestBodyTest() throws Exception {
        MvcResult result = mockMvc
                .perform(post("/invoices").contentType("application/json").content("{\"clientRFC\":\"qwerty\"}"))
                .andExpect(status().isBadRequest())
                .andReturn();

        String content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        ErrorDTO response = mapper.readValue(content, ErrorDTO.class);
        assertEquals("ERR_VALID", response.getCode());
        assertEquals("There was an error validating the input data", response.getMessage());
    }

}
