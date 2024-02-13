package org.bedu.java.backend.veterinaria.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import org.bedu.java.backend.veterinaria.dto.owner.CreateOwnerDTO;
import org.bedu.java.backend.veterinaria.dto.owner.OwnerDTO;
import org.bedu.java.backend.veterinaria.dto.owner.UpdateOwnerDTO;
import org.bedu.java.backend.veterinaria.exception.OwnerNotFoundException;
import org.bedu.java.backend.veterinaria.service.OwnerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class OwnerControllerTest {

    @MockBean
    private OwnerService service;

    @Autowired
    private OwnerController controller;

    @Test
    @DisplayName("Controller should be injected")
    void smokeTest() {
        assertNotNull(controller);
    }

    @Test
    @DisplayName("Controller sould return a list of owners")
    void findAllTest() {
        List<OwnerDTO> data = new LinkedList<>();

        OwnerDTO p = new OwnerDTO();

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

        when(service.findAll()).thenReturn(data);

        List<OwnerDTO> result = controller.findAll();

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
    @DisplayName("Controller should save a owner")
    void saveTest() {
        CreateOwnerDTO dto = new CreateOwnerDTO();

        dto.setName("Laura");
        dto.setSurname("Diaz");
        dto.setMaternalSurname("Santos");
        dto.setAddress("Calle 456");
        dto.setCellphone("9876543210");
        dto.setEmail("laura@example.com");
        dto.setBirthdate(LocalDate.parse("1992-08-20"));
        dto.setOccupation("Enfermera");

        OwnerDTO p = new OwnerDTO();

        p.setId(546L);
        p.setName(dto.getName());
        p.setSurname(dto.getSurname());
        p.setMaternalSurname(dto.getMaternalSurname());
        p.setAddress(dto.getAddress());
        p.setCellphone(dto.getCellphone());
        p.setEmail(dto.getEmail());
        p.setBirthdate(dto.getBirthdate());
        p.setOccupation(dto.getOccupation());

        when(service.save(any(CreateOwnerDTO.class))).thenReturn(p);

        OwnerDTO result = controller.save(dto);

        assertNotNull(result);
        assertEquals(p.getId(), result.getId());
        assertEquals(p.getName(), result.getName());
        assertEquals(p.getSurname(), result.getSurname());
        assertEquals(p.getMaternalSurname(), result.getMaternalSurname());
        assertEquals(p.getAddress(), result.getAddress());
        assertEquals(p.getCellphone(), result.getCellphone());
        assertEquals(p.getEmail(), result.getEmail());
        assertEquals(p.getBirthdate(), result.getBirthdate());
        assertEquals(p.getOccupation(), result.getOccupation());
    }

    @Test
    @DisplayName("Controller should update a pet")
    void updateTest() throws OwnerNotFoundException {
        UpdateOwnerDTO dto = new UpdateOwnerDTO();

        Long idOwner = 3L;

        dto.setNameU("Isabel");
        dto.setSurnameU("Fuentes");
        dto.setMaternalSurnameU("Jimenez");
        dto.setAddressU("Avenida 345");
        dto.setCellphoneU("9991112222");
        dto.setEmailU("isabel@example.com");
        dto.setBirthdateU(LocalDate.parse("1984-02-17"));
        dto.setOccupationU("Psicóloga");

        controller.update(idOwner, dto);

        verify(service).update(idOwner, dto);
    }

    @Test
    @DisplayName("Controller should delete an owner")
    void deleteByIdTest() throws OwnerNotFoundException {
        controller.deleteById(3456L);

        verify(service, times(1)).deleteById(3456L);
    }

}
