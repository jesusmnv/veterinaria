package org.bedu.java.backend.veterinaria.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import org.bedu.java.backend.veterinaria.dto.consultation.ConsultationDTO;
import org.bedu.java.backend.veterinaria.dto.consultation.CreateConsultationDTO;
import org.bedu.java.backend.veterinaria.dto.consultation.UpdateConsultationDTO;
import org.bedu.java.backend.veterinaria.exception.ConsultationNotFoundException;
import org.bedu.java.backend.veterinaria.model.Pet;
import org.bedu.java.backend.veterinaria.model.Vet;
import org.bedu.java.backend.veterinaria.service.ConsultationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ConsultationControllerTest {
    @MockBean
    private ConsultationService service;

    @Autowired
    private ConsultationController controller;

    @Test
    @DisplayName("Controller should be injected")
    void smokeTest() {
        assertNotNull(controller);
    }

    @Test
    @DisplayName("Controller should return a list of consultations")
    void findAllTest() {
        List<ConsultationDTO> data = new LinkedList<>();
        ConsultationDTO consultationDTO = new ConsultationDTO();

        consultationDTO.setId(100L);
        consultationDTO.setConsultationDate(LocalDate.of(2024, 1, 22));
        consultationDTO.setDiagnosis("Feline flu");
        consultationDTO.setPrescribedTreatment("Antibiotics and rest");
        consultationDTO.setObservations("The pet seems to be improving, continue monitoring");

        Pet p = new Pet();
        p.setId(9L);

        Vet v = new Vet();
        v.setId(8L);

        consultationDTO.setPet(p);
        consultationDTO.setVet(v);

        data.add(consultationDTO);

        when(service.findAll()).thenReturn(data);

        List<ConsultationDTO> result = controller.findAll();

        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertEquals(consultationDTO.getId(), result.get(0).getId());
        assertEquals(consultationDTO.getConsultationDate(), result.get(0).getConsultationDate());
        assertEquals(consultationDTO.getDiagnosis(), result.get(0).getDiagnosis());
        assertEquals(consultationDTO.getPrescribedTreatment(), result.get(0).getPrescribedTreatment());
        assertEquals(consultationDTO.getObservations(), result.get(0).getObservations());
        assertEquals(consultationDTO.getPet(), result.get(0).getPet());
        assertEquals(consultationDTO.getVet(), result.get(0).getVet());

    }

    @Test
    @DisplayName("Controller should save a consultation")
    void saveTest() {
        CreateConsultationDTO dto = new CreateConsultationDTO();
        dto.setConsultationDate(LocalDate.of(2023, 11, 15));
        dto.setDiagnosis("Feline flu");
        dto.setPrescribedTreatment("Antibiotics and rest");
        dto.setObservations("The pet seems to be improving, continue monitoring");


        ConsultationDTO consultationDTO = new ConsultationDTO();
        consultationDTO.setId(100L);
        consultationDTO.setConsultationDate(dto.getConsultationDate());
        consultationDTO.setDiagnosis(dto.getDiagnosis());
        consultationDTO.setPrescribedTreatment(dto.getPrescribedTreatment());
        consultationDTO.setObservations(dto.getObservations());

        Pet p = new Pet();
        p.setId(3L);

        Vet v = new Vet();
        v.setId(3L);

        consultationDTO.setPet(p);
        consultationDTO.setVet(v);

        when(service.save(any(CreateConsultationDTO.class))).thenReturn(consultationDTO);

        ConsultationDTO result = controller.save(dto);

        assertNotNull(result);

        assertEquals(consultationDTO.getId(), result.getId());
        assertEquals(consultationDTO.getConsultationDate(), result.getConsultationDate());
        assertEquals(consultationDTO.getDiagnosis(), result.getDiagnosis());
        assertEquals(consultationDTO.getPrescribedTreatment(), result.getPrescribedTreatment());
        assertEquals(consultationDTO.getObservations(), result.getObservations());
        assertEquals(consultationDTO.getPet(), result.getPet());
        assertEquals(consultationDTO.getVet(), result.getVet());
    
    }

    @Test
    @DisplayName("Controller should update a consultation")
    void updateTest() throws ConsultationNotFoundException {
        UpdateConsultationDTO dto = new UpdateConsultationDTO();
        dto.setConsultationDate(LocalDate.of(2021, 1, 1));
        dto.setDiagnosis("Ear infection");
        dto.setPrescribedTreatment("Antibiotics and ear drops");
        dto.setObservations("The owner reports that the cat has been scratching its ears frequently.");

        Pet p = new Pet();
        p.setId(2L);

        Vet v = new Vet();
        v.setId(2L);

        dto.setPet(p);
        dto.setVet(v);

        controller.update(300L, dto);

        verify(service, times(1)).update(300L, dto);
    }

    @Test
    @DisplayName("Controller should delete a consultation")
    void deleteByIdTest() throws ConsultationNotFoundException {
        controller.deleteById(10L);
        verify(service, times(1)).deleteById(10L);
    }


}