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
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.bedu.java.backend.veterinaria.dto.vet.CreateVetDTO;
import org.bedu.java.backend.veterinaria.dto.vet.UpdateVetDTO;
import org.bedu.java.backend.veterinaria.dto.vet.VetDTO;
import org.bedu.java.backend.veterinaria.exception.VetNotFoundException;
import org.bedu.java.backend.veterinaria.model.Vet;
import org.bedu.java.backend.veterinaria.repository.VetRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class VetServiceTest {

    @MockBean
    private VetRepository repository;

    @Autowired
    private VetService service;

    @Test
    @DisplayName("Controller should be injected")
    void smokeTest() {
        assertNotNull(service);
    }

    @Test
    @DisplayName("Service should return veterinarians from repository")
    void findAllTest() {
        List<Vet> data = new LinkedList<>();

        Vet vet = new Vet();

        vet.setName("Julio");
        vet.setPLastName("Avila");
        vet.setMLastName("Robles");
        vet.setBirthDate(LocalDate.parse("1990-01-24"));
        vet.setCellPhone("5514785236");
        vet.setEmail("julio@gmail.com");
        vet.setSpecialty("Cirujano");
        vet.setEntryTime(LocalTime.parse("06:00"));
        vet.setExitTime(LocalTime.parse("15:50"));

        data.add(vet);

        when(repository.findAll()).thenReturn(data);

        List<VetDTO> result = service.findAll();

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
    @DisplayName("Service should save a Vet in repository")
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

        Vet vet = new Vet();

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

        when(repository.save(any(Vet.class))).thenReturn(vet);

        VetDTO result = service.save(vetDTO);

        assertNotNull(result);

        // Checa si el Veterinario modelo tiene los mismos valores que el DTO de
        // CreateDTOVeterinario
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
    @DisplayName("Service should throws an error if Vet was not found")
    void updateWithErrorTest() {

        Optional<Vet> optionalEmpty = Optional.empty();

        UpdateVetDTO updateVetDTO = new UpdateVetDTO();

        when(repository.findById(anyLong())).thenReturn(optionalEmpty);

        // 1.- Tipo de exception que lanzará
        // 2.- Bloque de código encerrado en una lambda
        assertThrows(VetNotFoundException.class, () -> service.update(100, updateVetDTO));
    }

    @Test
    @DisplayName("Service should update a vet in repository")
    void updateTest() throws VetNotFoundException {

        UpdateVetDTO updateVetDTO = new UpdateVetDTO();

        updateVetDTO.setName("Alonso");
        updateVetDTO.setPLastName("Perez");
        updateVetDTO.setMLastName("Esquivel");
        updateVetDTO.setBirthDate(LocalDate.parse("1995-11-02"));
        updateVetDTO.setCellPhone("8332584562");
        updateVetDTO.setEmail("alonsoEnfermero@gmail.com");
        updateVetDTO.setSpecialty("Enfermero");
        updateVetDTO.setEntryTime(LocalTime.parse("16:30"));
        updateVetDTO.setExitTime(LocalTime.parse("22:45"));

        Vet vet = new Vet();

        vet.setId(151l);
        vet.setName("Al0nz0");
        vet.setPLastName("Peres");
        vet.setMLastName("Ezkibel");
        vet.setCellPhone("5581231478");
        vet.setEmail("alonsoDr@gmail.com");
        vet.setSpecialty("Dentista");
        vet.setEntryTime(LocalTime.parse("02:25"));
        vet.setExitTime(LocalTime.parse("23:04"));

        when(repository.findById(anyLong())).thenReturn(Optional.of(vet));

        service.update(151l, updateVetDTO);

        assertEquals(updateVetDTO.getName(), vet.getName());
        assertEquals(updateVetDTO.getPLastName(), vet.getPLastName());
        assertEquals(updateVetDTO.getMLastName(), vet.getMLastName());
        assertEquals(updateVetDTO.getBirthDate(), vet.getBirthDate());
        assertEquals(updateVetDTO.getCellPhone(), vet.getCellPhone());
        assertEquals(updateVetDTO.getEmail(), vet.getEmail());
        assertEquals(updateVetDTO.getSpecialty(), vet.getSpecialty());
        assertEquals(updateVetDTO.getEntryTime(), vet.getEntryTime());
        assertEquals(updateVetDTO.getExitTime(), vet.getExitTime());

        verify(repository, times(1)).save(vet);

    }

    @Test
    @DisplayName("Service should delete a vet by id in repository")
    void deleteByIdTest() throws VetNotFoundException {
        // service.deleteById(1547l);
        // verify(repository, times(1)).deleteById(1547l);
        when(repository.findById(15471l)).thenReturn(Optional.of(new Vet()));

        service.deleteById(15471l);

        verify(repository).deleteById(15471l);
    }

    @Test
    @DisplayName("Service should shows an error if vet don't exist")
    void deleteByIdErrorTest() {
        when(repository.findById(154l)).thenReturn(Optional.empty());

        assertThrows(VetNotFoundException.class, () -> service.deleteById(154l));
    }

}