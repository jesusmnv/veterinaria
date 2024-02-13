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
    @DisplayName("Service should return vets from repository")
    void findAllTest() {
        List<Vet> data = new LinkedList<>();

        Vet vet = new Vet();

        vet.setName("Julio");
        vet.setSurname("Avila");
        vet.setMaternalSurname("Robles");
        vet.setBirthdate(LocalDate.parse("1990-01-24"));
        vet.setCellphone("5514785236");
        vet.setEmail("julio@gmail.com");
        vet.setSpecialty("Cirujano");
        vet.setEntryTime(LocalTime.parse("06:00"));
        vet.setExitTime(LocalTime.parse("15:50"));

        data.add(vet);

        when(repository.findAll()).thenReturn(data);

        List<VetDTO> result = service.findAll();

        assertNotNull(result);
        assertTrue(result.size() > 0);
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
    @DisplayName("Service should return a vet from the repository")
    void findByIdTest() {
        Long id = 100L;

        Vet vet = new Vet();
        vet.setId(id);
        vet.setName("Sam");
        vet.setSurname("Obama");
        vet.setEmail("sam@gmail.com");

        when(repository.findById(id)).thenReturn(Optional.of(vet));

        Optional<VetDTO> result = service.findById(id);

        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals(vet.getId(), result.get().getId());
        assertEquals(vet.getName(), result.get().getName());
        assertEquals(vet.getSurname(), result.get().getSurname());
        assertEquals(vet.getEmail(), result.get().getEmail());
    }

    @Test
    @DisplayName("Service should save a vet in repository")
    void saveTest() {

        CreateVetDTO vetDTO = new CreateVetDTO();

        vetDTO.setNameVet("Alonso");
        vetDTO.setSurnameVet("Perez");
        vetDTO.setMaternalSurnameVet("Esquivel");
        vetDTO.setBirthdateVet(LocalDate.parse("1995-11-02"));
        vetDTO.setCellphoneVet("5522641597");
        vetDTO.setEmailVet("alonso@gmail.com");
        vetDTO.setSpecialty("Dentista");
        vetDTO.setEntryTime(LocalTime.parse("06:00"));
        vetDTO.setExitTime(LocalTime.parse("12:30"));

        Vet vet = new Vet();

        vet.setId(151l);
        vet.setName(vetDTO.getNameVet());
        vet.setSurname(vetDTO.getSurnameVet());
        vet.setMaternalSurname(vetDTO.getMaternalSurnameVet());
        vet.setBirthdate(vetDTO.getBirthdateVet());
        vet.setCellphone(vetDTO.getCellphoneVet());
        vet.setEmail(vetDTO.getEmailVet());
        vet.setSpecialty(vetDTO.getSpecialty());
        vet.setEntryTime(vetDTO.getEntryTime());
        vet.setExitTime(vetDTO.getExitTime());

        when(repository.save(any(Vet.class))).thenReturn(vet);

        VetDTO result = service.save(vetDTO);

        assertNotNull(result);
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
    @DisplayName("Service should throws an error if vet was not found")
    void updateWithErrorTest() {

        UpdateVetDTO updateVetDTO = new UpdateVetDTO();
        Optional<Vet> optionalEmpty = Optional.empty();

        when(repository.findById(anyLong())).thenReturn(optionalEmpty);

        assertThrows(VetNotFoundException.class, () -> service.update(100, updateVetDTO));
    }

    @Test
    @DisplayName("Service should update a vet in repository")
    void updateTest() throws VetNotFoundException {

        UpdateVetDTO updateVetDTO = new UpdateVetDTO();

        updateVetDTO.setNameU("Alonso");
        updateVetDTO.setSurnameU("Perez");
        updateVetDTO.setMaternalSurnameU("Esquivel");
        updateVetDTO.setBirthdateU(LocalDate.parse("1995-11-02"));
        updateVetDTO.setCellphoneU("8332584562");
        updateVetDTO.setEmailU("alonsoEnfermero@gmail.com");
        updateVetDTO.setSpecialtyU("Enfermero");
        updateVetDTO.setEntryTimeU(LocalTime.parse("16:30"));
        updateVetDTO.setExitTimeU(LocalTime.parse("22:45"));

        Vet vet = new Vet();

        vet.setId(151l);
        vet.setName("Al0nz0");
        vet.setSurname("Peres");
        vet.setMaternalSurname("Ezkibel");
        vet.setCellphone("5581231478");
        vet.setEmail("alonsoDr@gmail.com");
        vet.setSpecialty("Dentista");
        vet.setEntryTime(LocalTime.parse("02:25"));
        vet.setExitTime(LocalTime.parse("23:04"));

        when(repository.findById(anyLong())).thenReturn(Optional.of(vet));

        service.update(151l, updateVetDTO);

        assertEquals(updateVetDTO.getNameU(), vet.getName());
        assertEquals(updateVetDTO.getSurnameU(), vet.getSurname());
        assertEquals(updateVetDTO.getMaternalSurnameU(), vet.getMaternalSurname());
        assertEquals(updateVetDTO.getBirthdateU(), vet.getBirthdate());
        assertEquals(updateVetDTO.getCellphoneU(), vet.getCellphone());
        assertEquals(updateVetDTO.getEmailU(), vet.getEmail());
        assertEquals(updateVetDTO.getSpecialtyU(), vet.getSpecialty());
        assertEquals(updateVetDTO.getEntryTimeU(), vet.getEntryTime());
        assertEquals(updateVetDTO.getExitTimeU(), vet.getExitTime());

        verify(repository, times(1)).save(vet);

    }

    @Test
    @DisplayName("Service should shows an error if vet doesn't exist")
    void deleteByIdWithErrorTest() {

        when(repository.findById(154l)).thenReturn(Optional.empty());
        assertThrows(VetNotFoundException.class, () -> service.deleteById(154l));
    }

    @Test
    @DisplayName("Service should delete a vet by id in repository")
    void deleteByIdTest() throws VetNotFoundException {

        when(repository.findById(15471l)).thenReturn(Optional.of(new Vet()));

        service.deleteById(15471l);

        verify(repository, times(1)).deleteById(15471l);
    }

}