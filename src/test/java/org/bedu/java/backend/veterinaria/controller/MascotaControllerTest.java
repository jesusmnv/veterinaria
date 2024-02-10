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

import org.bedu.java.backend.veterinaria.dto.mascota.CreateMascotaDTO;
import org.bedu.java.backend.veterinaria.dto.mascota.MascotaDTO;
import org.bedu.java.backend.veterinaria.dto.mascota.UpdateMascotaDTO;
import org.bedu.java.backend.veterinaria.exception.MascotaNotFoundException;
import org.bedu.java.backend.veterinaria.service.MascotaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class MascotaControllerTest {

    @MockBean
    private MascotaService service;

    @Autowired
    private MascotaController controller;

    @Test
    @DisplayName("Controller should be injected")
    void smokeTest() {
        assertNotNull(controller);
    }

    @Test
    @DisplayName("Controller sould return a list of pets")
    void findAllTest() {
        List<MascotaDTO> data = new LinkedList<>();

        MascotaDTO mascota = new MascotaDTO();

        mascota.setId(56);
        mascota.setNombre("China");
        mascota.setEdad(3);

        data.add(mascota);

        when(service.findAll()).thenReturn(data);

        List<MascotaDTO> result = controller.findAll();

        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertEquals(mascota.getId(), result.get(0).getId());
        assertEquals(mascota.getNombre(), result.get(0).getNombre());
        assertEquals(mascota.getEdad(), result.get(0).getEdad());

    }

    @Test
    @DisplayName("Controller should save a mascota")
    void saveTest() {
        CreateMascotaDTO dto = new CreateMascotaDTO();

        dto.setNombre("Luigi");
        dto.setEdad(3);

        MascotaDTO mascota = new MascotaDTO();

        mascota.setId(546);
        mascota.setNombre(dto.getNombre());
        mascota.setEdad(dto.getEdad());

        when(service.save(any(CreateMascotaDTO.class))).thenReturn(mascota);

        MascotaDTO result = controller.save(dto);

        assertNotNull(result);
        assertEquals(mascota.getId(), result.getId());
        assertEquals(mascota.getNombre(), result.getNombre());
        assertEquals(mascota.getEdad(), result.getEdad());
    }

    @Test
    @DisplayName("Controller should update a pet")
    void updateTest() throws MascotaNotFoundException {
        UpdateMascotaDTO dto = UpdateMascotaDTO.builder().build();

        Long idMascota = 3L;

        dto.setNombre("Goliat");
        dto.setEdad(13);

        controller.update(idMascota, dto);

        verify(service).update(idMascota, dto);
    }

    @Test
    @DisplayName("Controller should delete a movie")
    void deleteByIdTest() throws MascotaNotFoundException {
        controller.deleteById(3456L);

        verify(service, times(1)).deleteById(3456L);
    }

}
