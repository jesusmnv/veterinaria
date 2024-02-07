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

import org.bedu.java.backend.veterinaria.dto.veterinario.CreateVeterinarioDTO;
import org.bedu.java.backend.veterinaria.dto.veterinario.UpdateVeterinarioDTO;
import org.bedu.java.backend.veterinaria.dto.veterinario.VeterinarioDTO;
import org.bedu.java.backend.veterinaria.exception.VeterinarioNotFoundException;
import org.bedu.java.backend.veterinaria.model.Veterinario;
import org.bedu.java.backend.veterinaria.repository.VeterinarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class VeterinarioServiceTest {

    @MockBean
    private VeterinarioRepository repository;

    @Autowired
    private VeterinarioService service;

    @Test
    @DisplayName("Comprobar si el servicio esta siendo inyectado")
    void smokeTest() {
        assertNotNull(service);
    }

    @Test
    @DisplayName("Comprobar si el servicio esta retornando los Veterinarios desde el repositorio")
    void findAllTest() {
        List<Veterinario> data = new LinkedList<>();

        Veterinario veterinario = new Veterinario();

        veterinario.setNombre("Julio");
        veterinario.setApellidoPaterno("Avila");
        veterinario.setApellidoMaterno("Robles");

        veterinario.setFechaNacimiento(LocalDate.parse("1990-01-24"));
        veterinario.setCelular("5514785236");
        veterinario.setCorreo("julio@gmail.com");
        veterinario.setEspecialidad("Cirujano");
        veterinario.setHoraEntrada(LocalTime.parse("06:00"));
        veterinario.setHoraSalida(LocalTime.parse("15:50"));

        data.add(veterinario);

        when(repository.findAll()).thenReturn(data);

        List<VeterinarioDTO> result = service.findAll();

        assertNotNull(result);

        assertTrue(result.size() > 0);

        // assertEquals(data, result);

        assertEquals(veterinario.getId(), result.get(0).getId());
        assertEquals(veterinario.getNombre(), result.get(0).getNombre());
        assertEquals(veterinario.getApellidoPaterno(), result.get(0).getApellidoPaterno());
        assertEquals(veterinario.getApellidoMaterno(), result.get(0).getApellidoMaterno());
        assertEquals(veterinario.getFechaNacimiento(), result.get(0).getFechaNacimiento());
        assertEquals(veterinario.getCelular(), result.get(0).getCelular());
        assertEquals(veterinario.getCorreo(), result.get(0).getCorreo());
        assertEquals(veterinario.getEspecialidad(), result.get(0).getEspecialidad());
        assertEquals(veterinario.getHoraEntrada(), result.get(0).getHoraEntrada());
        assertEquals(veterinario.getHoraSalida(), result.get(0).getHoraSalida());

    }

    @Test
    @DisplayName("Comprobar si el servicio esta guardando a un Veterinario desde el repositorio")
    void saveTest() {

        CreateVeterinarioDTO veterinarioDTO = new CreateVeterinarioDTO();

        veterinarioDTO.setNombre("Alonso");
        veterinarioDTO.setApellidoPaterno("Perez");
        veterinarioDTO.setApellidoMaterno("Esquivel");

        veterinarioDTO.setFechaNacimiento(LocalDate.parse("1995-11-02"));
        veterinarioDTO.setCelular("5522641597");
        veterinarioDTO.setCorreo("alonso@gmail.com");
        veterinarioDTO.setEspecialidad("Dentista");
        veterinarioDTO.setHoraEntrada(LocalTime.parse("06:00"));
        veterinarioDTO.setHoraSalida(LocalTime.parse("12:30"));

        Veterinario veterinario = new Veterinario();

        veterinario.setId(151l);
        veterinario.setNombre(veterinarioDTO.getNombre());
        veterinario.setApellidoPaterno(veterinarioDTO.getApellidoPaterno());
        veterinario.setApellidoMaterno(veterinarioDTO.getApellidoMaterno());
        veterinario.setFechaNacimiento(veterinarioDTO.getFechaNacimiento());
        veterinario.setCelular(veterinarioDTO.getCelular());
        veterinario.setCorreo(veterinarioDTO.getCorreo());
        veterinario.setEspecialidad(veterinarioDTO.getEspecialidad());
        veterinario.setHoraEntrada(veterinarioDTO.getHoraEntrada());
        veterinario.setHoraSalida(veterinarioDTO.getHoraSalida());

        when(repository.save(any(Veterinario.class))).thenReturn(veterinario);

        VeterinarioDTO result = service.save(veterinarioDTO);

        assertNotNull(result);

        // Checa si el Veterinario modelo tiene los mismos valores que el DTO de
        // CreateDTOVeterinario
        assertEquals(veterinario.getId(), result.getId());
        assertEquals(veterinario.getNombre(), result.getNombre());
        assertEquals(veterinario.getApellidoPaterno(), result.getApellidoPaterno());
        assertEquals(veterinario.getApellidoMaterno(), result.getApellidoMaterno());
        assertEquals(veterinario.getFechaNacimiento(), result.getFechaNacimiento());
        assertEquals(veterinario.getCelular(), result.getCelular());
        assertEquals(veterinario.getCorreo(), result.getCorreo());
        assertEquals(veterinario.getEspecialidad(), result.getEspecialidad());
        assertEquals(veterinario.getHoraEntrada(), result.getHoraEntrada());
        assertEquals(veterinario.getHoraSalida(), result.getHoraSalida());

    }

    @Test
    @DisplayName("Comprobar que se lance un error cuando un Veterinario no es encontrado")
    void updateWithErrorTest() {

        Optional<Veterinario> optionalEmpty = Optional.empty();

        UpdateVeterinarioDTO veterinarioDTO = new UpdateVeterinarioDTO();

        when(repository.findById(anyLong())).thenReturn(optionalEmpty);

        // 1.- Tipo de exception que lanzará
        // 2.- Bloque de código encerrado en una lambda
        assertThrows(VeterinarioNotFoundException.class, () -> service.update(100, veterinarioDTO));
    }

    @Test
    @DisplayName("Comprobar que el servicio actualice a un Veterinario desde el repositorio")
    void updatetest() throws VeterinarioNotFoundException {

        UpdateVeterinarioDTO veterinarioDTO = new UpdateVeterinarioDTO();

        veterinarioDTO.setNombre("Alonso");
        veterinarioDTO.setApellidoPaterno("Perez");
        veterinarioDTO.setApellidoMaterno("Esquivel");

        veterinarioDTO.setFechaNacimiento(LocalDate.parse("1995-11-02"));
        veterinarioDTO.setCelular("8332584562");
        veterinarioDTO.setCorreo("alonsoEnfermero@gmail.com");
        veterinarioDTO.setEspecialidad("Enfermero");
        veterinarioDTO.setHoraEntrada(LocalTime.parse("16:30"));
        veterinarioDTO.setHoraSalida(LocalTime.parse("22:45"));

        Veterinario veterinario = new Veterinario();

        veterinario.setId(151l);
        veterinario.setNombre("Al0nz0");
        veterinario.setApellidoPaterno("Peres");
        veterinario.setApellidoMaterno("Ezkibel");
        veterinario.setCelular("5581231478");
        veterinario.setCorreo("alonsoDr@gmail.com");
        veterinario.setEspecialidad("Dentista");
        veterinario.setHoraEntrada(LocalTime.parse("02:25"));
        veterinario.setHoraSalida(LocalTime.parse("23:04"));

        when(repository.findById(anyLong())).thenReturn(Optional.of(veterinario));

        service.update(151l, veterinarioDTO);

        assertEquals(veterinarioDTO.getNombre(), veterinario.getNombre());
        assertEquals(veterinarioDTO.getApellidoPaterno(), veterinario.getApellidoPaterno());
        assertEquals(veterinarioDTO.getApellidoMaterno(), veterinario.getApellidoMaterno());
        assertEquals(veterinarioDTO.getFechaNacimiento(), veterinario.getFechaNacimiento());
        assertEquals(veterinarioDTO.getCelular(), veterinario.getCelular());
        assertEquals(veterinarioDTO.getCorreo(), veterinario.getCorreo());
        assertEquals(veterinarioDTO.getEspecialidad(), veterinario.getEspecialidad());
        assertEquals(veterinarioDTO.getHoraEntrada(), veterinario.getHoraEntrada());
        assertEquals(veterinarioDTO.getHoraSalida(), veterinario.getHoraSalida());

        verify(repository, times(1)).save(veterinario);

    }

    @Test
    @DisplayName("Comprobar que el servicio si este eliminando a un  Veterinario desde el repositorio")
    void deleteByIdTest() throws VeterinarioNotFoundException {
        // service.deleteById(1547l);
        // verify(repository, times(1)).deleteById(1547l);
        when(repository.findById(15471l)).thenReturn(Optional.of(new Veterinario()));

        service.deleteById(15471l);

        verify(repository).deleteById(15471l);
    }

    @Test
    @DisplayName("Comprobar que el servicio muestre ERROR cuando no exista el Veterinario buscado")
    void deleteByIdErrorTest() {
        when(repository.findById(154l)).thenReturn(Optional.empty());

        assertThrows(VeterinarioNotFoundException.class, () -> service.deleteById(154l));
    }

}