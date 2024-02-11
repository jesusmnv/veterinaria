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
        p.setPLastName("Sanchez");
        p.setMLastName("Gomez");
        p.setAddress("Avenida 567");
        p.setCellPhone("1231231234");
        p.setEmail("carmen@example.com");
        p.setBirthDate(LocalDate.parse("1978-06-15"));
        p.setOccupation("Arquitecta");

        data.add(p);

        when(service.findAll()).thenReturn(data);

        List<OwnerDTO> result = controller.findAll();

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
    @DisplayName("Controller should save a propietario")
    void saveTest() {
        CreateOwnerDTO dto = new CreateOwnerDTO();

        dto.setName("Laura");
        dto.setPLastName("Diaz");
        dto.setMLastName("Santos");
        dto.setAddress("Calle 456");
        dto.setCellPhone("9876543210");
        dto.setEmail("laura@example.com");
        dto.setBirthDate(LocalDate.parse("1992-08-20"));
        dto.setOccupation("Enfermera");

        OwnerDTO p = new OwnerDTO();

        p.setId(546L);
        p.setName(dto.getName());
        p.setPLastName(dto.getPLastName());
        p.setMLastName(dto.getMLastName());
        p.setAddress(dto.getAddress());
        p.setCellPhone(dto.getCellPhone());
        p.setEmail(dto.getEmail());
        p.setBirthDate(dto.getBirthDate());
        p.setOccupation(dto.getOccupation());

        when(service.save(any(CreateOwnerDTO.class))).thenReturn(p);

        OwnerDTO result = controller.save(dto);

        assertNotNull(result);
        assertEquals(p.getId(), result.getId());
        assertEquals(p.getName(), result.getName());
        assertEquals(p.getPLastName(), result.getPLastName());
        assertEquals(p.getMLastName(), result.getMLastName());
        assertEquals(p.getAddress(), result.getAddress());
        assertEquals(p.getCellPhone(), result.getCellPhone());
        assertEquals(p.getEmail(), result.getEmail());
        assertEquals(p.getBirthDate(), result.getBirthDate());
        assertEquals(p.getOccupation(), result.getOccupation());
    }

    @Test
    @DisplayName("Controller should update a pet")
    void updateTest() throws OwnerNotFoundException {
        UpdateOwnerDTO dto = new UpdateOwnerDTO();

        Long idOwner = 3L;

        dto.setName("Isabel");
        dto.setPLastName("Fuentes");
        dto.setMLastName("Jimenez");
        dto.setAddress("Avenida 345");
        dto.setCellPhone("9991112222");
        dto.setEmail("isabel@example.com");
        dto.setBirthDate(LocalDate.parse("1984-02-17"));
        dto.setOccupation("Psicóloga");

        controller.update(idOwner, dto);

        verify(service).update(idOwner, dto);
    }

    @Test
    @DisplayName("Controller should delete a movie")
    void deleteByIdTest() throws OwnerNotFoundException {
        controller.deleteById(3456L);

        verify(service, times(1)).deleteById(3456L);
    }

}
