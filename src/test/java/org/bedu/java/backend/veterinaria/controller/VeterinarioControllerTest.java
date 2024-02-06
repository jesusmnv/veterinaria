package org.bedu.java.backend.veterinaria.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bedu.java.backend.veterinaria.dto.veterinario.CreateVeterinarioDTO;
import org.bedu.java.backend.veterinaria.dto.veterinario.UpdateVeterinarioDTO;
import org.bedu.java.backend.veterinaria.dto.veterinario.VeterinarioDTO;
import org.bedu.java.backend.veterinaria.exception.VeterinarioNotFoundException;
import org.bedu.java.backend.veterinaria.service.VeterinarioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.LinkedList;
import java.text.ParseException;
import java.time.LocalTime;
import java.text.SimpleDateFormat;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class VeterinarioControllerTest {
    
    @MockBean
    private VeterinarioService service;

    @Autowired
    private VeterinarioController controller;
    
    @Test
    @DisplayName("Comprobar si Spring inyecta correctamente el controlador")
    void smokeTest(){
        assertNotNull(controller);
    }

    @Test
    @DisplayName("Comprobar si retorna la lista de Veterinarios")
    void findAllTest(){
        List<VeterinarioDTO> data = new LinkedList<>();

        VeterinarioDTO veterinario = new VeterinarioDTO();

        veterinario.setId(159l);
        veterinario.setNombre("Fernando");
        veterinario.setApellidoPaterno("Ramos");
        veterinario.setApellidoMaterno("Carvajal");
        try {
            veterinario.setFechaNacimiento(new SimpleDateFormat("yyyy-MM-dd").parse("1985-12-12"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        veterinario.setCelular("8332641597");
        veterinario.setCorreo("fernandoRC@gmail.com");
        veterinario.setEspecialidad("Cirujano");
        veterinario.setHoraEntrada(LocalTime.parse("12:25"));
        veterinario.setHoraSalida(LocalTime.parse("16:15"));

        data.add(veterinario);

        when(service.findAll()).thenReturn(data);

        List<VeterinarioDTO> result = controller.findAll();

        assertNotNull(result);
        assertTrue(result.size() > 0);

        //assertEquals(data, result);

        assertEquals(veterinario.getId(), result.get(0).getId());
        assertEquals(veterinario.getNombre(), result.get(0).getNombre());
        assertEquals(veterinario.getApellidoPaterno(), result.get(0).getApellidoPaterno());
        assertEquals(veterinario.getApellidoMaterno(), result.get(0).getApellidoMaterno());
        assertEquals(veterinario.getFechaNacimiento(), result.get(0).getFechaNacimiento());
        assertEquals(veterinario.getCelular(), result.get(0).getCelular());
        assertEquals(veterinario.getCorreo(), result.get(0).getCorreo());
        assertEquals(veterinario.getEspecialidad(), result.get(0).getEspecialidad());
        assertEquals(veterinario.getHoraEntrada(), result.get(0).getHoraEntrada());
        assertEquals(veterinario.getHoraSalida(), result.get(0).getHoraSalida());

    }

    @Test
    @DisplayName("Comprobando si guarda correctamente a un Veterinario")
    void saveTest(){

        CreateVeterinarioDTO veterinarioDTO = new CreateVeterinarioDTO();

        veterinarioDTO.setNombre("Alonso");
        veterinarioDTO.setApellidoPaterno("Perez");
        veterinarioDTO.setApellidoMaterno("Esquivel");
        try {
            veterinarioDTO.setFechaNacimiento(new SimpleDateFormat("yyyy-MM-dd").parse("1995-11-02"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        veterinarioDTO.setCelular("5522641597");
        veterinarioDTO.setCorreo("alonso@gmail.com");
        veterinarioDTO.setEspecialidad("Dentista");
        veterinarioDTO.setHoraEntrada(LocalTime.parse("06:00"));
        veterinarioDTO.setHoraSalida(LocalTime.parse("12:30"));

        VeterinarioDTO veterinario = new VeterinarioDTO();

        veterinario.setId(151l);
        veterinario.setNombre(veterinarioDTO.getNombre());
        veterinario.setApellidoPaterno(veterinarioDTO.getApellidoPaterno());
        veterinario.setApellidoMaterno(veterinarioDTO.getApellidoMaterno());
        veterinario.setFechaNacimiento(veterinarioDTO.getFechaNacimiento());
        veterinario.setCelular(veterinarioDTO.getCelular());
        veterinario.setCorreo(veterinarioDTO.getCorreo());
        veterinario.setEspecialidad(veterinarioDTO.getEspecialidad());
        veterinario.setHoraEntrada(veterinarioDTO.getHoraEntrada());
        veterinario.setHoraSalida(veterinarioDTO.getHoraSalida());

        when(service.save(any(CreateVeterinarioDTO.class))).thenReturn(veterinario);

        VeterinarioDTO result = controller.save(veterinarioDTO);

        assertNotNull(result);

        //assertEquals(veterinario, result);
        assertEquals(veterinario.getId(), result.getId());
        assertEquals(veterinario.getNombre(), result.getNombre());
        assertEquals(veterinario.getApellidoPaterno(), result.getApellidoPaterno());
        assertEquals(veterinario.getApellidoMaterno(), result.getApellidoMaterno());
        assertEquals(veterinario.getFechaNacimiento(), result.getFechaNacimiento());
        assertEquals(veterinario.getCelular(), result.getCelular());
        assertEquals(veterinario.getCorreo(), result.getCorreo());
        assertEquals(veterinario.getEspecialidad(), result.getEspecialidad());
        assertEquals(veterinario.getHoraEntrada(), result.getHoraEntrada());
        assertEquals(veterinario.getHoraSalida(), result.getHoraSalida());

    }    

    @Test
    @DisplayName("El controlador debería actualizar al Veterinario")
    void updateTest(){

        UpdateVeterinarioDTO veterinarioDto = new UpdateVeterinarioDTO();

        veterinarioDto.setNombre("Julio");
        veterinarioDto.setApellidoPaterno("Avila");
        veterinarioDto.setApellidoMaterno("Robles");

        try {
            veterinarioDto.setFechaNacimiento(new SimpleDateFormat("yyyy-MM-dd").parse("1990-01-24"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        veterinarioDto.setCelular("5514785236");
        veterinarioDto.setCorreo("julio@gmail.com");
        veterinarioDto.setEspecialidad("Cirujano");
        veterinarioDto.setHoraEntrada(LocalTime.parse("06:00"));
        veterinarioDto.setHoraSalida(LocalTime.parse("15:50"));

        try {
            controller.update(450l, veterinarioDto);
            verify(service, times(1)).update(450l, veterinarioDto);
        } catch (VeterinarioNotFoundException e) {
            e.printStackTrace();
        }


    }

    @Test 
    @DisplayName("El controlador debería eliminar a un Veterinario")
    void deleteByIdTest(){
        try {
            controller.deleteById(1593l);
            verify(service,times(1)).deleteById(1593l);
        } catch (VeterinarioNotFoundException e) {
            e.printStackTrace();
        }
    }


}
