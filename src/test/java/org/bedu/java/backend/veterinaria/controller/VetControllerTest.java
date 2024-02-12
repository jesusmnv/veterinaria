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
    @DisplayName("Controller should return a list of Veterinarians")
    void findAllTest() {
        List<VetDTO> data = new LinkedList<>();

        VetDTO vet = new VetDTO();

        vet.setId(159l);
        vet.setName("Fernando");
        vet.setPLastName("Ramos");
        vet.setMLastName("Carvajal");
        vet.setBirthDate(LocalDate.parse("1985-12-12"));
        vet.setCellPhone("8332641597");
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
        assertEquals(vet.getPLastName(), result.get(0).getPLastName());
        assertEquals(vet.getMLastName(), result.get(0).getMLastName());
        assertEquals(vet.getBirthDate(), result.get(0).getBirthDate());
        assertEquals(vet.getCellPhone(), result.get(0).getCellPhone());
        assertEquals(vet.getEmail(), result.get(0).getEmail());
        assertEquals(vet.getSpecialty(), result.get(0).getSpecialty());
        assertEquals(vet.getEntryTime(), result.get(0).getEntryTime());
        assertEquals(vet.getExitTime(), result.get(0).getExitTime());

    }

    @Test
    @DisplayName("Controller should save a Vet")
    void saveTest() {

        CreateVetDTO vetDTO = new CreateVetDTO();

        vetDTO.setName("Alonso");
        vetDTO.setPLastName("Perez");
        vetDTO.setMLastName("Esquivel");
        vetDTO.setBirthDate(LocalDate.parse("1995-11-02"));
        vetDTO.setCellPhone("5522641597");
        vetDTO.setEmail("alonso@gmail.com");
        vetDTO.setSpecialty("Dentista");
        vetDTO.setEntryTime(LocalTime.parse("06:00"));
        vetDTO.setExitTime(LocalTime.parse("12:30"));

        VetDTO vet = new VetDTO();

        vet.setId(151l);
        vet.setName(vetDTO.getName());
        vet.setPLastName(vetDTO.getPLastName());
        vet.setMLastName(vetDTO.getMLastName());
        vet.setBirthDate(vetDTO.getBirthDate());
        vet.setCellPhone(vetDTO.getCellPhone());
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
        assertEquals(vet.getPLastName(), result.getPLastName());
        assertEquals(vet.getMLastName(), result.getMLastName());
        assertEquals(vet.getBirthDate(), result.getBirthDate());
        assertEquals(vet.getCellPhone(), result.getCellPhone());
        assertEquals(vet.getEmail(), result.getEmail());
        assertEquals(vet.getSpecialty(), result.getSpecialty());
        assertEquals(vet.getEntryTime(), result.getEntryTime());
        assertEquals(vet.getExitTime(), result.getExitTime());

    }

    @Test
    @DisplayName("Controller should update a Vet")
    void updateTest() throws VetNotFoundException {

        UpdateVetDTO vetDto = new UpdateVetDTO();

        vetDto.setName("Julio");
        vetDto.setPLastName("Avila");
        vetDto.setMLastName("Robles");
        vetDto.setBirthDate(LocalDate.parse("1990-01-24"));
        vetDto.setCellPhone("5514785236");
        vetDto.setEmail("julio@gmail.com");
        vetDto.setSpecialty("Cirujano");
        vetDto.setEntryTime(LocalTime.parse("06:00"));
        vetDto.setExitTime(LocalTime.parse("15:50"));

        controller.update(450l, vetDto);
        verify(service, times(1)).update(450l, vetDto);

    }

    @Test
    @DisplayName("Controller should delete a Vet")
    void deleteByIdTest() throws VetNotFoundException {
        controller.deleteById(1593l);
        verify(service, times(1)).deleteById(1593l);
    }

}