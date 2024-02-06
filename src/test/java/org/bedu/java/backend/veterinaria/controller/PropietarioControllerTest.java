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

import org.bedu.java.backend.veterinaria.dto.propietario.CreatePropietarioDTO;
import org.bedu.java.backend.veterinaria.dto.propietario.PropietarioDTO;
import org.bedu.java.backend.veterinaria.dto.propietario.UpdatePropietarioDTO;
import org.bedu.java.backend.veterinaria.exception.PropietarioNotFoundException;
import org.bedu.java.backend.veterinaria.service.PropietarioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class PropietarioControllerTest {

    @MockBean
    private PropietarioService service;

    @Autowired
    private PropietarioController controller;

    @Test
    @DisplayName("Controller should be injected")
    void smokeTest() {
        assertNotNull(controller);
    }

    @Test
    @DisplayName("Controller sould return a list of owners")
    void findAllTest() {
        List<PropietarioDTO> data = new LinkedList<>();

        PropietarioDTO p = new PropietarioDTO();

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

        when(service.findAll()).thenReturn(data);

        List<PropietarioDTO> result = controller.findAll();

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
    @DisplayName("Controller should save a propietario")
    void saveTest() {
        CreatePropietarioDTO dto = new CreatePropietarioDTO();

        dto.setNombre("Laura");
        dto.setApellidoPaterno("Diaz");
        dto.setApellidoMaterno("Santos");
        dto.setDireccion("Calle 456");
        dto.setCelular("9876543210");
        dto.setCorreo("laura@example.com");
        dto.setFechaNacimiento(LocalDate.parse("1992-08-20"));
        dto.setOcupacion("Enfermera");

        PropietarioDTO p = new PropietarioDTO();

        p.setId(546L);
        p.setNombre(dto.getNombre());
        p.setApellidoMaterno(dto.getApellidoMaterno());
        p.setApellidoMaterno(dto.getApellidoMaterno());
        p.setDireccion(dto.getDireccion());
        p.setCelular(dto.getCelular());
        p.setCorreo(dto.getCorreo());
        p.setFechaNacimiento(dto.getFechaNacimiento());
        p.setOcupacion(dto.getOcupacion());

        when(service.save(any(CreatePropietarioDTO.class))).thenReturn(p);

        PropietarioDTO result = controller.save(dto);

        assertNotNull(result);
        assertEquals(p.getId(), result.getId());
        assertEquals(p.getNombre(), result.getNombre());
        assertEquals(p.getApellidoPaterno(), result.getApellidoPaterno());
        assertEquals(p.getApellidoMaterno(), result.getApellidoMaterno());
        assertEquals(p.getDireccion(), result.getDireccion());
        assertEquals(p.getCelular(), result.getCelular());
        assertEquals(p.getCorreo(), result.getCorreo());
        assertEquals(p.getFechaNacimiento(), result.getFechaNacimiento());
        assertEquals(p.getOcupacion(), result.getOcupacion());
    }

    @Test
    @DisplayName("Controller should update a pet")
    void updateTest() throws PropietarioNotFoundException {
        UpdatePropietarioDTO dto = new UpdatePropietarioDTO();

        Long idPropietario = 3L;

        dto.setNombre("Isabel");
        dto.setApellidoPaterno("Fuentes");
        dto.setApellidoMaterno("Jimenez");
        dto.setDireccion("Avenida 345");
        dto.setCelular("9991112222");
        dto.setCorreo("isabel@example.com");
        dto.setFechaNacimiento(LocalDate.parse("1984-02-17"));
        dto.setOcupacion("Psicóloga");

        controller.update(idPropietario, dto);

        verify(service).update(idPropietario, dto);
    }

    @Test
    @DisplayName("Controller should delete a movie")
    void deleteByIdTest() throws PropietarioNotFoundException {
        controller.deleteById(3456L);

        verify(service, times(1)).deleteById(3456L);
    }

}
