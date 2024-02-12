package org.bedu.java.backend.veterinaria.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.bedu.java.backend.veterinaria.dto.pet.CreatePetDTO;
import org.bedu.java.backend.veterinaria.dto.pet.PetDTO;
import org.bedu.java.backend.veterinaria.dto.pet.UpdatePetDTO;
import org.bedu.java.backend.veterinaria.exception.PetNotFoundException;
import org.bedu.java.backend.veterinaria.service.PetService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class PetControllerTest {

    @MockBean
    private PetService service;

    @Autowired
    private PetController controller;

    @Test
    @DisplayName("Controller should be injected")
    void smokeTest() {
        assertNotNull(controller);
    }

    @Test
    @DisplayName("Controller sould return a list of pets")
    void findAllTest() {
        List<PetDTO> data = new LinkedList<>();

        PetDTO pet = new PetDTO();

        pet.setId(56);
        pet.setName("China");
        pet.setAge(3);

        data.add(pet);

        when(service.findAll()).thenReturn(data);

        List<PetDTO> result = controller.findAll();

        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertEquals(pet.getId(), result.get(0).getId());
        assertEquals(pet.getName(), result.get(0).getName());
        assertEquals(pet.getAge(), result.get(0).getAge());

    }

    @Test
    @DisplayName("Controller should save a pet")
    void saveTest() {
        CreatePetDTO dto = new CreatePetDTO();

        dto.setName("Luigi");
        dto.setAge(3);

        PetDTO pet = new PetDTO();

        pet.setId(546);
        pet.setName(dto.getName());
        pet.setAge(dto.getAge());

        when(service.save(any(CreatePetDTO.class))).thenReturn(pet);

        PetDTO result = controller.save(dto);

        assertNotNull(result);
        assertEquals(pet.getId(), result.getId());
        assertEquals(pet.getName(), result.getName());
        assertEquals(pet.getAge(), result.getAge());
    }

    @Test
    @DisplayName("Controller should update a pet")
    void updateTest() throws PetNotFoundException {
        UpdatePetDTO dto = new UpdatePetDTO();

        Long idPet = 3L;

        dto.setNameU("Goliat");
        dto.setAgeU(13);

        controller.update(idPet, dto);

        verify(service).update(idPet, dto);
    }

    @Test
    @DisplayName("Controller should delete a pet")
    void deleteByIdTest() throws PetNotFoundException {
        controller.deleteById(3456L);

        verify(service, times(1)).deleteById(3456L);
    }

}
