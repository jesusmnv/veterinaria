package org.bedu.java.backend.veterinaria.controller;

import org.bedu.java.backend.veterinaria.dto.medication.CreateMedicationDTO;
import org.bedu.java.backend.veterinaria.dto.medication.MedicationDTO;
import org.bedu.java.backend.veterinaria.dto.medication.UpdateMedicationDTO;
import org.bedu.java.backend.veterinaria.exception.MedicationNotFoundException;
import org.bedu.java.backend.veterinaria.service.MedicationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class MedicationControllerTest {

    @MockBean
    private MedicationService service;

    @Autowired
    private MedicationController controller;

    @Test
    @DisplayName("Controller should be injected")
    void smokeTest() {
        assertNotNull(controller);
    }

    @Test
    @DisplayName("Controller should return a list of medications")
    void findAllTest() {
        List<MedicationDTO> data = new LinkedList<>();
        MedicationDTO medication = new MedicationDTO();

        medication.setId(100L);
        medication.setName("Ibuprofen");
        medication.setClassification("Anti-inflammatory");
        medication.setDescription("Relieves pain in dogs and cats");
        medication.setExpirationDate(LocalDate.parse("2023-12-01"));
        medication.setStock(150);
        medication.setPrice(10.5F);
        medication.setUsageInstructions("Administer 1 tablet with food as directed by the vetU");

        data.add(medication);

        when(service.findAll()).thenReturn(data);

        List<MedicationDTO> result = controller.findAll();

        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertEquals(medication.getId(), result.get(0).getId());
        assertEquals(medication.getName(), result.get(0).getName());
        assertEquals(medication.getClassification(), result.get(0).getClassification());
        assertEquals(medication.getDescription(), result.get(0).getDescription());
        assertEquals(medication.getExpirationDate(), result.get(0).getExpirationDate());
        assertEquals(medication.getStock(), result.get(0).getStock());
        assertEquals(medication.getPrice(), result.get(0).getPrice());
        assertEquals(medication.getUsageInstructions(), result.get(0).getUsageInstructions());

    }

    @Test
    @DisplayName("Controller should save a medication")
    void saveTest() {
        CreateMedicationDTO dto = new CreateMedicationDTO();

        dto.setName("Ibuprofen");
        dto.setClassification("Anti-inflammatory");
        dto.setDescription("Relieves pain in dogs and cats");
        dto.setExpirationDate(LocalDate.parse("2023-12-01"));
        dto.setStock(150);
        dto.setPrice(10.5F);
        dto.setUsageInstructions("Administer 1 tablet with food as directed by the vetU");

        MedicationDTO medication = new MedicationDTO();
        medication.setId(300L);
        medication.setName(dto.getName());
        medication.setClassification(dto.getClassification());
        medication.setDescription(dto.getDescription());
        medication.setExpirationDate(dto.getExpirationDate());
        medication.setStock(dto.getStock());
        medication.setPrice(dto.getPrice());
        medication.setUsageInstructions(dto.getUsageInstructions());

        when(service.save(any(CreateMedicationDTO.class))).thenReturn(medication);

        MedicationDTO result = controller.save(dto);

        assertNotNull(result);

        assertEquals(medication.getId(), result.getId());
        assertEquals(medication.getName(), result.getName());
        assertEquals(medication.getClassification(), result.getClassification());
        assertEquals(medication.getDescription(), result.getDescription());
        assertEquals(medication.getExpirationDate(), result.getExpirationDate());
        assertEquals(medication.getStock(), result.getStock());
        assertEquals(medication.getPrice(), result.getPrice());
        assertEquals(medication.getUsageInstructions(), result.getUsageInstructions());
    }

    @Test
    @DisplayName("Controller should update a medication")
    void updateTest() throws MedicationNotFoundException {
        UpdateMedicationDTO dto = new UpdateMedicationDTO();
        dto.setName("Prednisone");
        dto.setClassification("Corticosteroid");
        dto.setDescription("Treats inflammatory and allergic conditions in dogs and cats");
        dto.setExpirationDate(LocalDate.parse("2023-12-01"));
        dto.setStock(200);
        dto.setPrice(20.5F);
        dto.setUsageInstructions("Administer according to the dose prescribed by the vetU");

        controller.update(400L, dto);

        verify(service, times(1)).update(400L, dto);
    }

    @Test
    @DisplayName("Controller should delete a medication")
    void deleteByIdTest() throws MedicationNotFoundException {
        controller.deleteById(8793L);
        verify(service, times(1)).deleteById(8793L);
    }

}
