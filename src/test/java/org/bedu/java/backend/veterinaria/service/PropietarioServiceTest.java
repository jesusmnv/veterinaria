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

import org.bedu.java.backend.veterinaria.dto.propietario.CreatePropietarioDTO;
import org.bedu.java.backend.veterinaria.dto.propietario.PropietarioDTO;
import org.bedu.java.backend.veterinaria.dto.propietario.UpdatePropietarioDTO;
import org.bedu.java.backend.veterinaria.exception.PropietarioNotFoundException;
import org.bedu.java.backend.veterinaria.model.Owner;
import org.bedu.java.backend.veterinaria.repository.PropietarioRepository;
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
class PropietarioServiceTest {

    @MockBean
    private PropietarioRepository repository;

    @Autowired
    private PropietarioService service;

    @Test
    @DisplayName("Service should be injected")
    void smokeTest() {
        assertNotNull(service);
    }

    @Test
    @DisplayName("Service should return owner from repository")
    void findAllTest() {
        List<Owner> data = new LinkedList<>();

        Owner p = new Owner();
        p.setId(7L);
        p.setNombre("Carmen");
        p.setApellidoPaterno("Sanchez");
        p.setApellidoMaterno("Gomez");
        p.setDireccion("Avenida 567");
        p.setCelular("1231231234");
        p.setCorreo("carmen@example.com");
        p.setFechaNacimiento(LocalDate.parse("1978-06-15"));
        p.setOcupacion("Arquitecta");

        data.add(p);

        when(repository.findAll()).thenReturn(data);

        List<PropietarioDTO> result = service.findAll();

        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertEquals(p.getId(), result.get(0).getId());
        assertEquals(p.getNombre(), result.get(0).getNombre());
        assertEquals(p.getApellidoPaterno(), result.get(0).getApellidoPaterno());
        assertEquals(p.getApellidoMaterno(), result.get(0).getApellidoMaterno());
        assertEquals(p.getDireccion(), result.get(0).getDireccion());
        assertEquals(p.getCelular(), result.get(0).getCelular());
        assertEquals(p.getCorreo(), result.get(0).getCorreo());
        assertEquals(p.getFechaNacimiento(), result.get(0).getFechaNacimiento());
        assertEquals(p.getOcupacion(), result.get(0).getOcupacion());
    }

    @Test
    @DisplayName("Service should save a owner in repository")
    void saveTest() {
        CreatePropietarioDTO dto = new CreatePropietarioDTO();

        dto.setNombre("Isabel");
        dto.setApellidoPaterno("Fuentes");
        dto.setApellidoMaterno("Macias");

        Owner model = new Owner();

        model.setId(2345L);
        model.setNombre(dto.getNombre());
        model.setApellidoPaterno(dto.getApellidoPaterno());
        model.setApellidoMaterno(dto.getApellidoMaterno());

        when(repository.save(any(Owner.class))).thenReturn(model);

        PropietarioDTO result = service.save(dto);

        assertNotNull(result);
        assertEquals(model.getId(), result.getId());
        assertEquals(model.getNombre(), result.getNombre());
        assertEquals(model.getApellidoMaterno(), result.getApellidoMaterno());
        assertEquals(model.getApellidoMaterno(), result.getApellidoMaterno());

    }

    @Test
    @DisplayName("Service should throws an error if owner was not found")
    void updateWithErrorTest() {
        UpdatePropietarioDTO dto = new UpdatePropietarioDTO();
        Optional<Owner> dummy = Optional.empty();

        when(repository.findById(anyLong())).thenReturn(dummy);

        assertThrows(PropietarioNotFoundException.class, () -> service.update(100L, dto));
    }

    @Test
    @DisplayName("Service should update a owner in repository")
    void updateTest() throws PropietarioNotFoundException {
        UpdatePropietarioDTO dto = new UpdatePropietarioDTO();

        dto.setNombre("Ramiro");
        dto.setApellidoPaterno("Ramirez");

        Owner propietario = new Owner();

        propietario.setId(12L);
        propietario.setNombre("Rogelio");
        propietario.setApellidoPaterno("Renteria");

        when(repository.findById(anyLong())).thenReturn(Optional.of(propietario));

        service.update(12L, dto);

        assertEquals(dto.getNombre(), propietario.getNombre());
        assertEquals(dto.getApellidoPaterno(), propietario.getApellidoPaterno());
        verify(repository, times(1)).save(propietario);
    }

    @Test
    @DisplayName("Service should shows an error if owner don't exist")
    void updatePropietarioNotFoundExceptionTest() throws PropietarioNotFoundException {
        UpdatePropietarioDTO dto = new UpdatePropietarioDTO();
        Optional<Owner> empty = Optional.empty();

        when(repository.findById(anyLong())).thenReturn(empty);
        assertThrows(PropietarioNotFoundException.class, () -> service.update(35L, dto));
    }

    @Test
    @DisplayName("Service should delete a owner by id in repository")
    void deleteByIdTest() throws PropietarioNotFoundException {

        Long idPropietario = 1L;

        when(repository.findById(idPropietario)).thenReturn(Optional.of(new Owner()));

        service.deleteById(idPropietario);

        verify(repository).deleteById(idPropietario);

    }

    @Test
    @DisplayName("Service should shows an error if owner don't exist")
    void deleteByIdPropietarioNotFoundExceptionTest() throws PropietarioNotFoundException {

        Long idPropietario = 1L;

        when(repository.findById(idPropietario)).thenReturn(Optional.empty());

        assertThrows(PropietarioNotFoundException.class, () -> service.deleteById(idPropietario));

    }

}
