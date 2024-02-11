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

import org.bedu.java.backend.veterinaria.dto.owner.CreateOwnerDTO;
import org.bedu.java.backend.veterinaria.dto.owner.OwnerDTO;
import org.bedu.java.backend.veterinaria.dto.owner.UpdateOwnerDTO;
import org.bedu.java.backend.veterinaria.exception.OwnerNotFoundException;
import org.bedu.java.backend.veterinaria.model.Owner;
import org.bedu.java.backend.veterinaria.repository.OwnerRepository;
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
class OwnerServiceTest {

    @MockBean
    private OwnerRepository repository;

    @Autowired
    private OwnerService service;

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
        p.setName("Carmen");
        p.setPLastName("Sanchez");
        p.setMLastName("Gomez");
        p.setAddress("Avenida 567");
        p.setCellPhone("1231231234");
        p.setEmail("carmen@example.com");
        p.setBirthDate(LocalDate.parse("1978-06-15"));
        p.setOccupation("Arquitecta");

        data.add(p);

        when(repository.findAll()).thenReturn(data);

        List<OwnerDTO> result = service.findAll();

        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertEquals(p.getId(), result.get(0).getId());
        assertEquals(p.getName(), result.get(0).getName());
        assertEquals(p.getPLastName(), result.get(0).getPLastName());
        assertEquals(p.getMLastName(), result.get(0).getMLastName());
        assertEquals(p.getAddress(), result.get(0).getAddress());
        assertEquals(p.getCellPhone(), result.get(0).getCellPhone());
        assertEquals(p.getEmail(), result.get(0).getEmail());
        assertEquals(p.getBirthDate(), result.get(0).getBirthDate());
        assertEquals(p.getOccupation(), result.get(0).getOccupation());
    }

    @Test
    @DisplayName("Service should save a owner in repository")
    void saveTest() {
        CreateOwnerDTO dto = new CreateOwnerDTO();

        dto.setName("Isabel");
        dto.setPLastName("Fuentes");
        dto.setMLastName("Macias");

        Owner model = new Owner();

        model.setId(2345L);
        model.setName(dto.getName());
        model.setPLastName(dto.getPLastName());
        model.setMLastName(dto.getMLastName());

        when(repository.save(any(Owner.class))).thenReturn(model);

        OwnerDTO result = service.save(dto);

        assertNotNull(result);
        assertEquals(model.getId(), result.getId());
        assertEquals(model.getName(), result.getName());
        assertEquals(model.getPLastName(), result.getPLastName());
        assertEquals(model.getMLastName(), result.getMLastName());

    }

    @Test
    @DisplayName("Service should throws an error if owner was not found")
    void updateWithErrorTest() {
        UpdateOwnerDTO dto = new UpdateOwnerDTO();
        Optional<Owner> dummy = Optional.empty();

        when(repository.findById(anyLong())).thenReturn(dummy);

        assertThrows(OwnerNotFoundException.class, () -> service.update(100L, dto));
    }

    @Test
    @DisplayName("Service should update a owner in repository")
    void updateTest() throws OwnerNotFoundException {
        UpdateOwnerDTO dto = new UpdateOwnerDTO();

        dto.setName("Ramiro");
        dto.setPLastName("Ramirez");

        Owner owner = new Owner();

        owner.setId(12L);
        owner.setName("Rogelio");
        owner.setPLastName("Renteria");

        when(repository.findById(anyLong())).thenReturn(Optional.of(owner));

        service.update(12L, dto);

        assertEquals(dto.getName(), owner.getName());
        assertEquals(dto.getPLastName(), owner.getPLastName());
        verify(repository, times(1)).save(owner);
    }

    @Test
    @DisplayName("Service should shows an error if owner don't exist")
    void updateOwnerNotFoundExceptionTest() throws OwnerNotFoundException {
        UpdateOwnerDTO dto = new UpdateOwnerDTO();
        Optional<Owner> empty = Optional.empty();

        when(repository.findById(anyLong())).thenReturn(empty);
        assertThrows(OwnerNotFoundException.class, () -> service.update(35L, dto));
    }

    @Test
    @DisplayName("Service should delete a owner by id in repository")
    void deleteByIdTest() throws OwnerNotFoundException {

        Long idOwner = 1L;

        when(repository.findById(idOwner)).thenReturn(Optional.of(new Owner()));

        service.deleteById(idOwner);

        verify(repository).deleteById(idOwner);

    }

    @Test
    @DisplayName("Service should shows an error if owner don't exist")
    void deleteByIdPropietarioNotFoundExceptionTest() throws OwnerNotFoundException {

        Long idOwner = 1L;

        when(repository.findById(idOwner)).thenReturn(Optional.empty());

        assertThrows(OwnerNotFoundException.class, () -> service.deleteById(idOwner));

    }

}
