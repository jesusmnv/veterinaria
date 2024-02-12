package org.bedu.java.backend.veterinaria.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

import org.bedu.java.backend.veterinaria.dto.vet.CreateVetDTO;
import org.bedu.java.backend.veterinaria.dto.vet.UpdateVetDTO;
import org.bedu.java.backend.veterinaria.dto.vet.VetDTO;
import org.bedu.java.backend.veterinaria.exception.VetNotFoundException;
import org.bedu.java.backend.veterinaria.service.VetService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class VetControllerTest {

    @MockBean
    private VetService service;

    @Autowired
    private VetController controller;

    @Test
    @DisplayName("Controller should be injected")
    void smokeTest() {
        assertNotNull(controller);
    }

    @Test
    @DisplayName("Controller should return a list of vets")
    void findAllTest() {
        List<VetDTO> data = new LinkedList<>();

        VetDTO vet = new VetDTO();

        vet.setId(159l);
        vet.setName("Fernando");
        vet.setSurname("Ramos");
        vet.setMaternalSurname("Carvajal");
        vet.setBirthdate(LocalDate.parse("1985-12-12"));
        vet.setCellphone("8332641597");
        vet.setEmail("fernandoRC@gmail.com");
        vet.setSpecialty("Cirujano");
        vet.setEntryTime(LocalTime.parse("12:25"));
        vet.setExitTime(LocalTime.parse("16:15"));

        data.add(vet);

        when(service.findAll()).thenReturn(data);

        List<VetDTO> result = controller.findAll();

        assertNotNull(result);
        assertTrue(result.size() > 0);

        // assertEquals(data, result);

        assertEquals(vet.getId(), result.get(0).getId());
        assertEquals(vet.getName(), result.get(0).getName());
        assertEquals(vet.getSurname(), result.get(0).getSurname());
        assertEquals(vet.getMaternalSurname(), result.get(0).getMaternalSurname());
        assertEquals(vet.getBirthdate(), result.get(0).getBirthdate());
        assertEquals(vet.getCellphone(), result.get(0).getCellphone());
        assertEquals(vet.getEmail(), result.get(0).getEmail());
        assertEquals(vet.getSpecialty(), result.get(0).getSpecialty());
        assertEquals(vet.getEntryTime(), result.get(0).getEntryTime());
        assertEquals(vet.getExitTime(), result.get(0).getExitTime());

    }

    @Test
    @DisplayName("Controller should save a vet")
    void saveTest() {

        CreateVetDTO vetDTO = new CreateVetDTO();

        vetDTO.setName("Alonso");
        vetDTO.setSurname("Perez");
        vetDTO.setMaternalSurname("Esquivel");
        vetDTO.setBirthdate(LocalDate.parse("1995-11-02"));
        vetDTO.setCellphone("5522641597");
        vetDTO.setEmail("alonso@gmail.com");
        vetDTO.setSpecialty("Dentista");
        vetDTO.setEntryTime(LocalTime.parse("06:00"));
        vetDTO.setExitTime(LocalTime.parse("12:30"));

        VetDTO vet = new VetDTO();

        vet.setId(151l);
        vet.setName(vetDTO.getName());
        vet.setSurname(vetDTO.getSurname());
        vet.setMaternalSurname(vetDTO.getMaternalSurname());
        vet.setBirthdate(vetDTO.getBirthdate());
        vet.setCellphone(vetDTO.getCellphone());
        vet.setEmail(vetDTO.getEmail());
        vet.setSpecialty(vetDTO.getSpecialty());
        vet.setEntryTime(vetDTO.getEntryTime());
        vet.setExitTime(vetDTO.getExitTime());

        when(service.save(any(CreateVetDTO.class))).thenReturn(vet);

        VetDTO result = controller.save(vetDTO);

        assertNotNull(result);

        // assertEquals(vet, result);
        assertEquals(vet.getId(), result.getId());
        assertEquals(vet.getName(), result.getName());
        assertEquals(vet.getSurname(), result.getSurname());
        assertEquals(vet.getMaternalSurname(), result.getMaternalSurname());
        assertEquals(vet.getBirthdate(), result.getBirthdate());
        assertEquals(vet.getCellphone(), result.getCellphone());
        assertEquals(vet.getEmail(), result.getEmail());
        assertEquals(vet.getSpecialty(), result.getSpecialty());
        assertEquals(vet.getEntryTime(), result.getEntryTime());
        assertEquals(vet.getExitTime(), result.getExitTime());

    }

    @Test
    @DisplayName("Controller should update a vet")
    void updateTest() throws VetNotFoundException {

        UpdateVetDTO vetDto = new UpdateVetDTO();

        vetDto.setName("Julio");
        vetDto.setSurname("Avila");
        vetDto.setMaternalSurname("Robles");
        vetDto.setBirthdate(LocalDate.parse("1990-01-24"));
        vetDto.setCellphone("5514785236");
        vetDto.setEmail("julio@gmail.com");
        vetDto.setSpecialty("Cirujano");
        vetDto.setEntryTime(LocalTime.parse("06:00"));
        vetDto.setExitTime(LocalTime.parse("15:50"));

        controller.update(450l, vetDto);
        verify(service, times(1)).update(450l, vetDto);

    }

    @Test
    @DisplayName("Controller should delete a vet")
    void deleteByIdTest() throws VetNotFoundException {
        controller.deleteById(1593l);
        verify(service, times(1)).deleteById(1593l);
    }

}