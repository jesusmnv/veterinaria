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
    @DisplayName("Service should return owners from repository")
    void findAllTest() {
        List<Owner> data = new LinkedList<>();

        Owner p = new Owner();
        p.setId(7L);
        p.setName("Carmen");
        p.setSurname("Sanchez");
        p.setMaternalSurname("Gomez");
        p.setAddress("Avenida 567");
        p.setCellphone("1231231234");
        p.setEmail("carmen@example.com");
        p.setBirthdate(LocalDate.parse("1978-06-15"));
        p.setOccupation("Arquitecta");

        data.add(p);

        when(repository.findAll()).thenReturn(data);

        List<OwnerDTO> result = service.findAll();

        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertEquals(p.getId(), result.get(0).getId());
        assertEquals(p.getName(), result.get(0).getName());
        assertEquals(p.getSurname(), result.get(0).getSurname());
        assertEquals(p.getMaternalSurname(), result.get(0).getMaternalSurname());
        assertEquals(p.getAddress(), result.get(0).getAddress());
        assertEquals(p.getCellphone(), result.get(0).getCellphone());
        assertEquals(p.getEmail(), result.get(0).getEmail());
        assertEquals(p.getBirthdate(), result.get(0).getBirthdate());
        assertEquals(p.getOccupation(), result.get(0).getOccupation());
    }

    @Test
    @DisplayName("Service should return an owner from the repository")
    void findByIdTest() {
        Long id = 300L;

        Owner owner = new Owner();
        owner.setId(id);
        owner.setName("Ford");
        owner.setSurname("Smith");
        owner.setEmail("ford@gmail.com");
        owner.setBirthdate(LocalDate.parse("2000-01-01"));

        when(repository.findById(id)).thenReturn(Optional.of(owner));

        Optional<OwnerDTO> result = service.findById(id);

        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals(owner.getId(), result.get().getId());
        assertEquals(owner.getName(), result.get().getName());
        assertEquals(owner.getSurname(), result.get().getSurname());
        assertEquals(owner.getEmail(), result.get().getEmail());
        assertEquals(owner.getBirthdate(), result.get().getBirthdate());
    }

    @Test
    @DisplayName("Service should save an owner in repository")
    void saveTest() {
        CreateOwnerDTO dto = new CreateOwnerDTO();

        dto.setName("Isabel");
        dto.setSurname("Fuentes");
        dto.setMaternalSurname("Macias");

        Owner model = new Owner();

        model.setId(2345L);
        model.setName(dto.getName());
        model.setSurname(dto.getSurname());
        model.setMaternalSurname(dto.getMaternalSurname());

        when(repository.save(any(Owner.class))).thenReturn(model);

        OwnerDTO result = service.save(dto);

        assertNotNull(result);
        assertEquals(model.getId(), result.getId());
        assertEquals(model.getName(), result.getName());
        assertEquals(model.getSurname(), result.getSurname());
        assertEquals(model.getMaternalSurname(), result.getMaternalSurname());

    }

    @Test
    @DisplayName("Service should throw an error if owner was not found")
    void updateWithErrorTest() {
        UpdateOwnerDTO dto = new UpdateOwnerDTO();
        Optional<Owner> dummy = Optional.empty();

        when(repository.findById(anyLong())).thenReturn(dummy);

        assertThrows(OwnerNotFoundException.class, () -> service.update(100L, dto));
    }

    @Test
    @DisplayName("Service should update an owner in repository")
    void updateTest() throws OwnerNotFoundException {
        UpdateOwnerDTO dto = new UpdateOwnerDTO();

        dto.setNameU("Ramiro");
        dto.setSurnameU("Ramirez");

        Owner owner = new Owner();

        owner.setId(12L);
        owner.setName("Rogelio");
        owner.setSurname("Renteria");

        when(repository.findById(anyLong())).thenReturn(Optional.of(owner));

        service.update(12L, dto);

        assertEquals(dto.getNameU(), owner.getName());
        assertEquals(dto.getSurnameU(), owner.getSurname());
        verify(repository, times(1)).save(owner);
    }

    @Test
    @DisplayName("Service should show an error if owner doesn't exist")
    void updateOwnerNotFoundExceptionTest() throws OwnerNotFoundException {
        UpdateOwnerDTO dto = new UpdateOwnerDTO();
        Optional<Owner> empty = Optional.empty();

        when(repository.findById(anyLong())).thenReturn(empty);
        assertThrows(OwnerNotFoundException.class, () -> service.update(35L, dto));
    }

    @Test
    @DisplayName("Service should delete an owner by id in repository")
    void deleteByIdTest() throws OwnerNotFoundException {

        Long idOwner = 1L;

        when(repository.findById(idOwner)).thenReturn(Optional.of(new Owner()));

        service.deleteById(idOwner);

        verify(repository, times(1)).deleteById(idOwner);

    }

    @Test
    @DisplayName("Service should show an error if owner doesn't exist")
    void deleteByIdPropietarioNotFoundExceptionTest() throws OwnerNotFoundException {

        Long idOwner = 1L;

        when(repository.findById(idOwner)).thenReturn(Optional.empty());

        assertThrows(OwnerNotFoundException.class, () -> service.deleteById(idOwner));

    }

}
