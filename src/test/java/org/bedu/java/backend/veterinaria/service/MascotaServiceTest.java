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

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.bedu.java.backend.veterinaria.dto.mascota.CreateMascotaDTO;
import org.bedu.java.backend.veterinaria.dto.mascota.MascotaDTO;
import org.bedu.java.backend.veterinaria.dto.mascota.UpdateMascotaDTO;
import org.bedu.java.backend.veterinaria.exception.MascotaNotFoundException;
import org.bedu.java.backend.veterinaria.model.Mascota;
import org.bedu.java.backend.veterinaria.model.Propietario;
import org.bedu.java.backend.veterinaria.repository.MascotaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class MascotaServiceTest {

    @MockBean
    private MascotaRepository repository;

    @Autowired
    private MascotaService service;

    @Test
    @DisplayName("Service should be injected")
    void smokeTest() {
        assertNotNull(service);
    }

    @Test
    @DisplayName("Service should return pets from repository")
    void findAllTest() {
        List<Mascota> data = new LinkedList<>();

        Mascota mascota = new Mascota();
        Propietario propietario = new Propietario();

        propietario.setId(32L);
        mascota.setId(8746L);
        mascota.setNombre("Ninja");
        mascota.setEspecie("Perro");
        mascota.setRaza("Pitbull");
        mascota.setEdad(2);
        mascota.setAltura(0.45F);
        mascota.setPeso(30);
        mascota.setSexo("Macho");
        mascota.setColor("negro");
        mascota.setPropietario(propietario);

        data.add(mascota);

        when(repository.findAll()).thenReturn(data);

        List<MascotaDTO> result = service.findAll();

        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertEquals(mascota.getId(), result.get(0).getId());
        assertEquals(mascota.getNombre(), result.get(0).getNombre());
        assertEquals(mascota.getEspecie(), result.get(0).getEspecie());
        assertEquals(mascota.getRaza(), result.get(0).getRaza());
        assertEquals(mascota.getEdad(), result.get(0).getEdad());
        assertEquals(mascota.getAltura(), result.get(0).getAltura());
        assertEquals(mascota.getPeso(), result.get(0).getPeso());
        assertEquals(mascota.getSexo(), result.get(0).getSexo());
        assertEquals(mascota.getColor(), result.get(0).getColor());
        assertEquals(mascota.getPropietario(), result.get(0).getPropietario());
    }

    @Test
    @DisplayName("Service should save a pet in repository")
    void saveTest() {
        CreateMascotaDTO dto = new CreateMascotaDTO();

        dto.setNombre("Bones");
        dto.setEspecie("Perro");
        dto.setRaza("Dalmata");

        Mascota model = new Mascota();

        model.setId(2345L);
        model.setNombre(dto.getNombre());
        model.setEspecie(dto.getEspecie());
        model.setRaza(dto.getRaza());

        when(repository.save(any(Mascota.class))).thenReturn(model);

        MascotaDTO result = service.save(dto);

        assertNotNull(result);
        assertEquals(model.getId(), result.getId());
        assertEquals(model.getNombre(), result.getNombre());
        assertEquals(model.getEspecie(), result.getEspecie());
        assertEquals(model.getRaza(), result.getRaza());

    }

    @Test
    @DisplayName("Service should throws an error if pet was not found")
    void updateWithErrorTest() {
        UpdateMascotaDTO dto = new UpdateMascotaDTO();
        Optional<Mascota> dummy = Optional.empty();

        when(repository.findById(anyLong())).thenReturn(dummy);

        assertThrows(MascotaNotFoundException.class, () -> service.update(100L, dto));
    }

    @Test
    @DisplayName("Service should update a pet in repository")
    void updateTest() throws MascotaNotFoundException {
        UpdateMascotaDTO dto = new UpdateMascotaDTO();

        dto.setNombre("Romeo");
        dto.setEdad(35);

        Mascota mascota = new Mascota();

        mascota.setId(12L);
        mascota.setNombre("Romulo");
        mascota.setEdad(2);

        when(repository.findById(anyLong())).thenReturn(Optional.of(mascota));

        service.update(12L, dto);

        assertEquals(dto.getNombre(), mascota.getNombre());
        assertEquals(dto.getEdad(), mascota.getEdad());
        verify(repository, times(1)).save(mascota);
    }

    @Test
    @DisplayName("Service should shows an error if pet don't exist")
    void updateMascotaNotFoundExceptionTest() throws MascotaNotFoundException {
        UpdateMascotaDTO dto = new UpdateMascotaDTO();
        Optional<Mascota> empty = Optional.empty();

        when(repository.findById(anyLong())).thenReturn(empty);
        assertThrows(MascotaNotFoundException.class, () -> service.update(35L, dto));
    }

    @Test
    @DisplayName("Service should delete a pet by id in repository")
    void deleteByIdTest() throws MascotaNotFoundException {

        Long idMascota = 1L;

        when(repository.findById(idMascota)).thenReturn(Optional.of(new Mascota()));

        service.deleteById(idMascota);

        verify(repository).deleteById(idMascota);

    }

    @Test
    @DisplayName("Service should shows an error if pet don't exist")
    void deleteByIdMascotaNotFoundExceptionTest() throws MascotaNotFoundException {

        Long idMascota = 1L;

        when(repository.findById(idMascota)).thenReturn(Optional.empty());

        assertThrows(MascotaNotFoundException.class, () -> service.deleteById(idMascota));

    }

}
