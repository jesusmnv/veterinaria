package org.bedu.java.backend.veterinaria.controller;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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

import org.bedu.java.backend.veterinaria.dto.medicamento.MedicamentoDTO;
import org.bedu.java.backend.veterinaria.model.Medicamento;
import org.bedu.java.backend.veterinaria.repository.MedicamentoRepository;

@AutoConfigureTestDatabase(replace = Replace.NONE)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest
class MedicamentoControllerE2ETest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MedicamentoRepository repository;

    private ObjectMapper mapper = new ObjectMapper();


    @BeforeEach
    public void setup() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("GET /medicamentos should return an empty list")
    void emptyListTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/medicamentos"))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertEquals("[]", content);
    }

    @Test
    @DisplayName("GET /medicamentos should return a list of medications")
    void findAllTest() throws Exception {

        mapper.registerModule(new JavaTimeModule());

        Medicamento medicamento1 = new Medicamento();
        Medicamento medicamento2 = new Medicamento();

        medicamento1.setNombre("Enrofloxacina");
        medicamento1.setClasificacion("Antibiótico");
        medicamento1.setDescripcion("Trata infecciones bacterianas en perros y gatos");
        medicamento1.setFechaCaducidad(LocalDate.parse("2024-01-20"));
        medicamento1.setExistencia(40);
        medicamento1.setPrecio(17.25F);
        medicamento1.setInstruccionesUso("Administrar según la dosis prescrita por el veterinario");

        medicamento2.setNombre("Dipirona");
        medicamento2.setClasificacion("Analgésico y Antipirético");
        medicamento2.setDescripcion("Alivia el dolor y reduce la fiebre en perros y gatos");
        medicamento2.setFechaCaducidad(LocalDate.parse("2023-12-30"));
        medicamento2.setExistencia(65);
        medicamento2.setPrecio(10.5F);
        medicamento2.setInstruccionesUso("Administrar según la dosis prescrita por el veterinario");

        repository.save(medicamento1);
        repository.save(medicamento2);

        MvcResult result = mockMvc.perform(get("/medicamentos"))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        // Creamos una referencia del tipo al que se va a convertir el JSON
        TypeReference<List<MedicamentoDTO>> listTypeReference = new TypeReference<List<MedicamentoDTO>>() {
        };

        // Convertimos el JSON a un objeto de Java
        List<MedicamentoDTO> response = mapper.readValue(content, listTypeReference);

        // Hacemos las verificaciones basadas en los objetos
        assertEquals(2, response.size());
        assertEquals(medicamento1.getNombre(), response.get(0).getNombre());
        assertEquals(medicamento1.getFechaCaducidad(), response.get(0).getFechaCaducidad());
        assertEquals(medicamento2.getNombre(), response.get(1).getNombre());
        assertEquals(medicamento2.getFechaCaducidad(), response.get(1).getFechaCaducidad());

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