package org.bedu.java.backend.veterinaria.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import org.bedu.java.backend.veterinaria.model.Propietario;
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
public class PropietarioRepositoryTest {

    @Autowired
    private PropietarioRepository repository;

    @Autowired
    private TestEntityManager manager;

    @Test
    @DisplayName("Repository should be injected")
    void smokeTest() {
        assertNotNull(repository);
    }

    @Test
    @DisplayName("Repository should filter pets by name")
    void findByNameTest() {

        Propietario propietario1 = crearPropietario1();
        Propietario propietario2 = crearPropietario2();
        Propietario propietario3 = crearPropietario3();
        manager.persist(propietario1);
        manager.persist(propietario2);
        manager.persist(propietario3);
        List<Propietario> result = repository.findByNombreContaining("Carmen");
        assertEquals(1, result.size());
    }

    private Propietario crearPropietario1() {

        Propietario p = new Propietario();
        // p.setId(7L);
        p.setNombre("Carmen");
        p.setApellidoPaterno("Sanchez");
        p.setApellidoMaterno("Gomez");
        p.setDireccion("Avenida 567");
        p.setCelular("1231231234");
        p.setCorreo("carmen@example.com");
        p.setFechaNacimiento(LocalDate.parse("1978-06-15"));
        p.setOcupacion("Arquitecta");

        return p;

    }

    private Propietario crearPropietario2() {

        Propietario p = new Propietario();
        // p.setId(9L);
        p.setNombre("Isabel");
        p.setApellidoPaterno("Fuentes");
        p.setApellidoMaterno("Jimenez");
        p.setDireccion("Avenida 345");
        p.setCelular("9991112222");
        p.setCorreo("isabel@example.com");
        p.setFechaNacimiento(LocalDate.parse("1984-02-17"));
        p.setOcupacion("Psicóloga");

        return p;

    }

    private Propietario crearPropietario3() {

        Propietario p = new Propietario();
        // p.setId(3L);
        p.setNombre("Laura");
        p.setApellidoPaterno("Diaz");
        p.setApellidoMaterno("Santos");
        p.setDireccion("Calle 456");
        p.setCelular("9876543210");
        p.setCorreo("laura@example.com");
        p.setFechaNacimiento(LocalDate.parse("1992-08-20"));
        p.setOcupacion("Enfermera");

        return p;
    }
}
