package org.bedu.java.backend.veterinaria.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.bedu.java.backend.veterinaria.model.Veterinario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class VeterinarioRepositoryTest {
    

    @Autowired
    private VeterinarioRepository repository;

    @Autowired
    private TestEntityManager manager;

    @Test
    @DisplayName("Repository should be injected")
    void smokeTest(){
        assertNotNull(repository);
    }

    @Test
    @DisplayName("Repository should filter a vet by name")
    void findByNameTest(){

        Veterinario vet1 = new Veterinario();
        Veterinario vet2 = new Veterinario();
        Veterinario vet3 = new Veterinario();

        vet1.setId(151l);
        vet1.setNombre("Juan");
        vet1.setApellidoPaterno("Perez");
        vet1.setApellidoMaterno("Castellanos");
        vet1.setFechaNacimiento(LocalDate.parse("1998-02-25"));
        vet1.setCelular("8523697415");
        vet1.setCorreo("juan@gmail.com");
        vet1.setEspecialidad("Cirujano");
        vet1.setHoraEntrada(LocalTime.parse("02:25"));
        vet1.setHoraSalida(LocalTime.parse("15:25"));

        vet2.setId(150l);
        vet2.setNombre("Roberto");
        vet2.setApellidoPaterno("Valdez");
        vet2.setApellidoMaterno("Hernandez");
        vet2.setFechaNacimiento(LocalDate.parse("1991-08-25"));
        vet2.setCelular("1478963254");
        vet2.setCorreo("roberto@gmail.com");
        vet2.setEspecialidad("Cirujano");
        vet2.setHoraEntrada(LocalTime.parse("23:50"));
        vet2.setHoraSalida(LocalTime.parse("07:00"));

        vet3.setId(141l);
        vet3.setNombre("Yamileth");
        vet3.setApellidoPaterno("Robles");
        vet3.setApellidoMaterno("Quintero");
        vet3.setFechaNacimiento(LocalDate.parse("2000-12-15"));
        vet3.setCelular("7896523148");
        vet3.setCorreo("yamileth@gmail.com");
        vet3.setEspecialidad("Rehabilitacion");
        vet3.setHoraEntrada(LocalTime.parse("20:00"));
        vet3.setHoraSalida(LocalTime.parse("06:00"));

        manager.persist(vet1);
        manager.persist(vet2);
        manager.persist(vet3);

        List<Veterinario> result = repository.findByName("Yamileth", "Robles", "Quintero");

        assertEquals(1, result.size());

    }

    @Test
    @DisplayName("Repository should filter a vet by specialty")
    void findBySpecialtyTest(){

        Veterinario vet1 = new Veterinario();
        Veterinario vet2 = new Veterinario();
        Veterinario vet3 = new Veterinario();

        vet1.setId(151l);
        vet1.setNombre("Juan");
        vet1.setApellidoPaterno("Perez");
        vet1.setApellidoMaterno("Castellanos");
        vet1.setFechaNacimiento(LocalDate.parse("1998-02-25"));
        vet1.setCelular("8523697415");
        vet1.setCorreo("juan@gmail.com");
        vet1.setEspecialidad("Cirujano");
        vet1.setHoraEntrada(LocalTime.parse("02:25"));
        vet1.setHoraSalida(LocalTime.parse("15:25"));

        vet2.setId(150l);
        vet2.setNombre("Roberto");
        vet2.setApellidoPaterno("Valdez");
        vet2.setApellidoMaterno("Hernandez");
        vet2.setFechaNacimiento(LocalDate.parse("1991-08-25"));
        vet2.setCelular("1478963254");
        vet2.setCorreo("roberto@gmail.com");
        vet2.setEspecialidad("Cirujano");
        vet2.setHoraEntrada(LocalTime.parse("23:50"));
        vet2.setHoraSalida(LocalTime.parse("07:00"));

        vet3.setId(141l);
        vet3.setNombre("Yamileth");
        vet3.setApellidoPaterno("Robles");
        vet3.setApellidoMaterno("Quintero");
        vet3.setFechaNacimiento(LocalDate.parse("2000-12-15"));
        vet3.setCelular("7896523148");
        vet3.setCorreo("yamileth@gmail.com");
        vet3.setEspecialidad("Rehabilitacion");
        vet3.setHoraEntrada(LocalTime.parse("20:00"));
        vet3.setHoraSalida(LocalTime.parse("06:00"));

        manager.persist(vet1);
        manager.persist(vet2);
        manager.persist(vet3);

        List<Veterinario> result = repository.findBySpecialty("Cirujano");

        assertEquals(2, result.size());

    }

}
