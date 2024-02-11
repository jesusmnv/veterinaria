package org.bedu.java.backend.veterinaria.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.bedu.java.backend.veterinaria.dto.consultation.ConsultationDTO;
import org.bedu.java.backend.veterinaria.dto.consultation.CreateConsultationDTO;
import org.bedu.java.backend.veterinaria.dto.consultation.UpdateConsultationDTO;
import org.bedu.java.backend.veterinaria.exception.ConsultationNotFoundException;
import org.bedu.java.backend.veterinaria.model.Consultation;
import org.bedu.java.backend.veterinaria.model.Mascota;
import org.bedu.java.backend.veterinaria.model.Veterinario;
import org.bedu.java.backend.veterinaria.repository.ConsultationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ConsultationServiceTest {

    @MockBean
    private ConsultationRepository repository;

    @Autowired
    private ConsultationService service;

    @Test
    @DisplayName("Service should be injected")
    void smokeTest() {
        assertNotNull(service);
    }

    @Test
    @DisplayName("Service should return consultations from the repository")
    void findAllTest() {
        List<Consultation> data = new LinkedList<>();

        Consultation consultation = new Consultation();

        consultation.setId(500L);
        consultation.setConsultationDate(LocalDate.of(2024, 4, 14));
        consultation.setDiagnosis("Gripe felina");
        consultation.setPrescribedTreatment("Antibióticos y reposo");
        consultation.setObservations("La mascota parece mejorar, seguir monitoreando");

        Mascota m = new Mascota();
        m.setId(4L);

        Veterinario v = new Veterinario();
        v.setId(4L);

        consultation.setPet(m);
        consultation.setVeterinarian(v);

        data.add(consultation);

        when(repository.findAll()).thenReturn(data);

        List<ConsultationDTO> result = service.findAll();

        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertEquals(consultation.getId(), result.get(0).getId());
        assertEquals(consultation.getConsultationDate(), result.get(0).getConsultationDate());
        assertEquals(consultation.getDiagnosis(), result.get(0).getDiagnosis());
        assertEquals(consultation.getPrescribedTreatment(), result.get(0).getPrescribedTreatment());
        assertEquals(consultation.getObservations(), result.get(0).getObservations());
        assertEquals(consultation.getPet(), result.get(0).getPet());
        assertEquals(consultation.getVeterinarian(), result.get(0).getVet());
    }

    @Test
    @DisplayName("Service should save a consultation in the repository")
    void saveTest() {
        CreateConsultationDTO dto = new CreateConsultationDTO();
        dto.setConsultationDate(LocalDate.parse("2025-05-05"));
        dto.setDiagnosis("Gripe felina");
        dto.setPrescribedTreatment("Antibióticos y reposo");
        dto.setObservations("La mascota parece mejorar, seguir monitoreando");

        Mascota m = new Mascota();
        m.setId(5L);

        Veterinario v = new Veterinario();
        v.setId(5L);

        dto.setPet(m);
        dto.setVet(v);

        Consultation model = new Consultation();

        model.setId(600L);
        model.setConsultationDate(dto.getConsultationDate());
        model.setDiagnosis(dto.getDiagnosis());
        model.setPrescribedTreatment(dto.getPrescribedTreatment());
        model.setObservations(dto.getObservations());
        model.setPet(dto.getPet());
        model.setVeterinarian(dto.getVet());

        when(repository.save(any(Consultation.class))).thenReturn(model);

        ConsultationDTO result = service.save(dto);

        assertNotNull(result);
        assertEquals(model.getId(), result.getId());
        assertEquals(model.getConsultationDate(), result.getConsultationDate());
        assertEquals(model.getPrescribedTreatment(), result.getPrescribedTreatment());
        assertEquals(model.getDiagnosis(), result.getDiagnosis());
        assertEquals(model.getObservations(), result.getObservations());
        assertEquals(model.getPet(), result.getPet());
        assertEquals(model.getVeterinarian(), result.getVet());
    }

    @Test
    @DisplayName("Service should throw an error if the consultation is not found when attempting to update it")
    void updateWithErrorTest() {
        UpdateConsultationDTO dto = new UpdateConsultationDTO();
        Optional<Consultation> dummy = Optional.empty();

        when(repository.findById(anyLong())).thenReturn(dummy);

        assertThrows(ConsultationNotFoundException.class, () -> service.update(100L, dto));
    }

    @Test
    @DisplayName("Service should update a consultation in the repository")
    void updateTest() throws ConsultationNotFoundException {
        UpdateConsultationDTO dto = new UpdateConsultationDTO();
        dto.setDiagnosis("Diagnosis...");

        Consultation consultation = new Consultation();

        consultation.setId(155L);
        consultation.setDiagnosis("...");

        when(repository.findById(anyLong())).thenReturn(Optional.of(consultation));

        service.update(155L, dto);

        assertEquals(dto.getConsultationDate(), consultation.getConsultationDate());
        verify(repository, times(1)).save(consultation);

    }

    @Test
    @DisplayName("Service should throw an error if the consultation is not found when attempting to delete it")
    void deleteByIdWithErrorTest() {
        Optional<Consultation> dummy = Optional.empty();

        when(repository.findById(anyLong())).thenReturn(dummy);

        assertThrows(ConsultationNotFoundException.class, () -> service.deleteById(222L));
    }

    @Test
    @DisplayName("Service should delete a consultation by ID")
    void deleteByIdTest() throws ConsultationNotFoundException {

        Consultation consultation = new Consultation();

        consultation.setId(222L);
        consultation.setDiagnosis("");

        when(repository.findById(anyLong())).thenReturn(Optional.of(consultation));

        service.deleteById(222L);
        verify(repository, times(1)).deleteById(222L);
    }

}
