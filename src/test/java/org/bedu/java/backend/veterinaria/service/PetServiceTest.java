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

import org.bedu.java.backend.veterinaria.dto.pet.CreatePetDTO;
import org.bedu.java.backend.veterinaria.dto.pet.PetDTO;
import org.bedu.java.backend.veterinaria.dto.pet.UpdatePetDTO;
import org.bedu.java.backend.veterinaria.exception.PetNotFoundException;
import org.bedu.java.backend.veterinaria.model.Pet;
import org.bedu.java.backend.veterinaria.model.Owner;
import org.bedu.java.backend.veterinaria.repository.PetRepository;
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
class PetServiceTest {

    @MockBean
    private PetRepository repository;

    @Autowired
    private PetService service;

    @Test
    @DisplayName("Service should be injected")
    void smokeTest() {
        assertNotNull(service);
    }

    @Test
    @DisplayName("Service should return pets from repository")
    void findAllTest() {
        List<Pet> data = new LinkedList<>();

        Pet pet = new Pet();
        Owner owner = new Owner();

        owner.setId(32L);
        pet.setId(8746L);
        pet.setName("Ninja");
        pet.setSpecies("Perro");
        pet.setBreed("Pitbull");
        pet.setAge(2);
        pet.setHeight(0.45F);
        pet.setWeight(30);
        pet.setGender("Macho");
        pet.setColor("negro");
        pet.setOwner(owner);

        data.add(pet);

        when(repository.findAll()).thenReturn(data);

        List<PetDTO> result = service.findAll();

        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertEquals(pet.getId(), result.get(0).getId());
        assertEquals(pet.getName(), result.get(0).getName());
        assertEquals(pet.getSpecies(), result.get(0).getSpecies());
        assertEquals(pet.getBreed(), result.get(0).getBreed());
        assertEquals(pet.getAge(), result.get(0).getAge());
        assertEquals(pet.getHeight(), result.get(0).getHeight());
        assertEquals(pet.getWeight(), result.get(0).getWeight());
        assertEquals(pet.getGender(), result.get(0).getGender());
        assertEquals(pet.getColor(), result.get(0).getColor());
        assertEquals(pet.getOwner(), result.get(0).getOwner());
    }

    @Test
    @DisplayName("Service should save a pet in repository")
    void saveTest() {
        CreatePetDTO dto = new CreatePetDTO();

        dto.setName("Bones");
        dto.setSpecies("Can");
        dto.setBreed("Dalmata");

        Pet model = new Pet();

        model.setId(2345L);
        model.setName(dto.getName());
        model.setSpecies(dto.getSpecies());
        model.setBreed(dto.getBreed());

        when(repository.save(any(Pet.class))).thenReturn(model);

        PetDTO result = service.save(dto);

        assertNotNull(result);
        assertEquals(model.getId(), result.getId());
        assertEquals(model.getName(), result.getName());
        assertEquals(model.getSpecies(), result.getSpecies());
        assertEquals(model.getBreed(), result.getBreed());

    }

    @Test
    @DisplayName("Service should throws an error if pet was not found")
    void updateWithErrorTest() {
        UpdatePetDTO dto = new UpdatePetDTO();
        Optional<Pet> dummy = Optional.empty();

        when(repository.findById(anyLong())).thenReturn(dummy);

        assertThrows(PetNotFoundException.class, () -> service.update(100L, dto));
    }

    @Test
    @DisplayName("Service should update a pet in repository")
    void updateTest() throws PetNotFoundException {
        UpdatePetDTO dto = new UpdatePetDTO();

        dto.setNameU("Romeo");
        dto.setAgeU(35);

        Pet pet = new Pet();

        pet.setId(12L);
        pet.setName("Romulo");
        pet.setAge(2);

        when(repository.findById(anyLong())).thenReturn(Optional.of(pet));

        service.update(12L, dto);

        assertEquals(dto.getNameU(), pet.getName());
        assertEquals(dto.getAgeU(), pet.getAge());
        verify(repository, times(1)).save(pet);
    }

    @Test
    @DisplayName("Service should shows an error if pet don't exist")
    void updatePetNotFoundExceptionTest() throws PetNotFoundException {
        UpdatePetDTO dto = new UpdatePetDTO();
        Optional<Pet> empty = Optional.empty();

        when(repository.findById(anyLong())).thenReturn(empty);
        assertThrows(PetNotFoundException.class, () -> service.update(35L, dto));
    }

    @Test
    @DisplayName("Service should delete a pet by id in repository")
    void deleteByIdTest() throws PetNotFoundException {

        Long idPet = 1L;

        when(repository.findById(idPet)).thenReturn(Optional.of(new Pet()));

        service.deleteById(idPet);

        verify(repository).deleteById(idPet);

    }

    @Test
    @DisplayName("Service should shows an error if pet don't exist")
    void deleteByIdPetNotFoundExceptionTest() throws PetNotFoundException {

        Long idPet = 1L;

        when(repository.findById(idPet)).thenReturn(Optional.empty());

        assertThrows(PetNotFoundException.class, () -> service.deleteById(idPet));

    }

}
