package org.bedu.java.backend.veterinaria.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.bedu.java.backend.veterinaria.dto.medication.CreateMedicationDTO;
import org.bedu.java.backend.veterinaria.dto.medication.MedicationDTO;
import org.bedu.java.backend.veterinaria.dto.medication.UpdateMedicationDTO;
import org.bedu.java.backend.veterinaria.exception.MedicationNotFoundException;
import org.bedu.java.backend.veterinaria.model.Medication;
import org.bedu.java.backend.veterinaria.repository.MedicationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class MedicationServiceTest {

    @MockBean
    private MedicationRepository repository;

    @Autowired
    private MedicationService service;

    @Test
    @DisplayName("Service should be injected")
    void smokeTest() {
        assertNotNull(service);
    }

    @Test
    @DisplayName("Service should return medications from the repository")
    void findAllTest() {
        List<Medication> data = new LinkedList<>();

        Medication medication = new Medication();

        medication.setId(500L);
        medication.setName("Dipyrone");
        medication.setClassification("Analgesic and Antipyretic");
        medication.setDescription("Relieves pain and reduces fever in dogs and cats");
        medication.setExpirationDate(LocalDate.parse("2023-12-15"));
        medication.setStock(80);
        medication.setPrice(9.5F);
        medication.setUsageInstructions("Administer according to the dose prescribed by the vetU");

        data.add(medication);

        when(repository.findAll()).thenReturn(data);

        List<MedicationDTO> result = service.findAll();

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
    @DisplayName("Service should save a medication into the repository")
    void saveTest() {
        CreateMedicationDTO dto = new CreateMedicationDTO();
        dto.setName("Enrofloxacin");
        dto.setClassification("Antibiotic");
        dto.setDescription("Treats bacterial infections in dogs and cats");
        dto.setExpirationDate(LocalDate.parse("2024-01-10"));
        dto.setStock(40);
        dto.setPrice(18.75F);
        dto.setUsageInstructions("Administer according to the dose prescribed by the vetU");

        Medication model = new Medication();

        model.setId(600L);
        model.setName(dto.getName());
        model.setDescription(dto.getDescription());
        model.setClassification(dto.getClassification());
        model.setExpirationDate(dto.getExpirationDate());
        model.setStock(dto.getStock());
        model.setPrice(dto.getPrice());
        model.setUsageInstructions(dto.getUsageInstructions());

        when(repository.save(any(Medication.class))).thenReturn(model);

        MedicationDTO result = service.save(dto);

        assertNotNull(result);
        assertEquals(model.getId(), result.getId());
        assertEquals(model.getName(), result.getName());
        assertEquals(model.getDescription(), result.getDescription());
        assertEquals(model.getClassification(), result.getClassification());
        assertEquals(model.getExpirationDate(), result.getExpirationDate());
        assertEquals(model.getStock(), result.getStock());
        assertEquals(model.getPrice(), result.getPrice());
        assertEquals(model.getUsageInstructions(), result.getUsageInstructions());
    }

    @Test
    @DisplayName("Service should throw an error if the medication is not found when attempting to update it")
    void updateWithErrorTest() {
        UpdateMedicationDTO dto = new UpdateMedicationDTO();
        Optional<Medication> dummy = Optional.empty();

        when(repository.findById(anyLong())).thenReturn(dummy);

        assertThrows(MedicationNotFoundException.class, () -> service.update(100L, dto));
    }

    @Test
    @DisplayName("Service should update a medication in the repository")
    void updateTest() throws MedicationNotFoundException {
        UpdateMedicationDTO dto = new UpdateMedicationDTO();
        dto.setNameU("Meloxicam");
        dto.setClassificationU("Anti-inflammatory");

        Medication medication = new Medication();

        medication.setId(125L);
        medication.setName("MEDICATION");
        medication.setClassification("CLASSIFICATION");

        when(repository.findById(anyLong())).thenReturn(Optional.of(medication));

        service.update(125L, dto);

        assertEquals(dto.getNameU(), medication.getName());
        assertEquals(dto.getClassificationU(), medication.getClassification());
        verify(repository, times(1)).save(medication);

    }

    @Test
    @DisplayName("Service should throw an error if the medication is not found when attempting to delete it")
    void deleteByIdWithErrorTest() {
        Optional<Medication> dummy = Optional.empty();

        when(repository.findById(anyLong())).thenReturn(dummy);

        assertThrows(MedicationNotFoundException.class, () -> service.deleteById(100L));
    }

    @Test
    @DisplayName("Service should delete a medication by ID")
    void deleteByIdTest() throws MedicationNotFoundException {

        Medication medication = new Medication();

        medication.setId(125L);
        medication.setName("");
        medication.setClassification("");

        when(repository.findById(anyLong())).thenReturn(Optional.of(medication));

        service.deleteById(125L);
        verify(repository, times(1)).deleteById(125L);
    }

}