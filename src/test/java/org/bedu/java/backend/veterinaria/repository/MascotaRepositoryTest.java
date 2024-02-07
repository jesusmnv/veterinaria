package org.bedu.java.backend.veterinaria.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import org.bedu.java.backend.veterinaria.model.Mascota;
import org.bedu.java.backend.veterinaria.model.Propietario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
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
class MascotaRepositoryTest {

    @Autowired
    private MascotaRepository repository;

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

        Mascota mascota1 = crearMascota1();
        Mascota mascota2 = crearMascota2();
        Mascota mascota3 = crearMascota3();
        manager.persist(mascota1);
        manager.persist(mascota2);
        manager.persist(mascota3);
        List<Mascota> result = repository.findByNombreContaining("Fury");
        assertEquals(1, result.size());
    }

    private Mascota crearMascota1() {

        Mascota mascota = new Mascota();

        mascota.setNombre("Fury");
        mascota.setEspecie("Perro");
        mascota.setRaza("Schnauzer");
        mascota.setEdad(2);
        mascota.setAltura(0.25F);
        mascota.setPeso(1.45F);
        mascota.setSexo("Macho");
        mascota.setColor("gris");

        Propietario p = new Propietario();
        p.setId(7L);
        p.setNombre("Carmen");
        p.setApellidoPaterno("Sanchez");
        p.setApellidoMaterno("Gomez");
        p.setDireccion("Avenida 567");
        p.setCelular("1231231234");
        p.setCorreo("carmen@example.com");
        p.setFechaNacimiento(LocalDate.parse("1978-06-15"));
        p.setOcupacion("Arquitecta");

        mascota.setPropietario(manager.merge(p));

        return mascota;

    }

    private Mascota crearMascota2() {

        Mascota mascota = new Mascota();

        mascota.setNombre("Daenerys");
        mascota.setEspecie("Perro");
        mascota.setRaza("Chihuahua");
        mascota.setEdad(2);
        mascota.setAltura(0.12F);
        mascota.setPeso(0.8F);
        mascota.setSexo("Hembra");
        mascota.setColor("cafe claro");

        Propietario p = new Propietario();
        p.setId(9L);
        p.setNombre("Isabel");
        p.setApellidoPaterno("Fuentes");
        p.setApellidoMaterno("Jimenez");
        p.setDireccion("Avenida 345");
        p.setCelular("9991112222");
        p.setCorreo("isabel@example.com");
        p.setFechaNacimiento(LocalDate.parse("1984-02-17"));
        p.setOcupacion("Psicóloga");

        mascota.setPropietario(manager.merge(p));

        return mascota;

    }

    private Mascota crearMascota3() {

        Mascota mascota = new Mascota();

        mascota.setNombre("Perseo");
        mascota.setEspecie("Tortuga");
        mascota.setRaza("reeves");
        mascota.setEdad(67);
        mascota.setAltura(0.10F);
        mascota.setPeso(0.6F);
        mascota.setSexo("Macho");
        mascota.setColor("cafe pardo con manchas");

        Propietario p = new Propietario();
        p.setId(3L);
        p.setNombre("Laura");
        p.setApellidoPaterno("Diaz");
        p.setApellidoMaterno("Santos");
        p.setDireccion("Calle 456");
        p.setCelular("9876543210");
        p.setCorreo("laura@example.com");
        p.setFechaNacimiento(LocalDate.parse("1992-08-20"));
        p.setOcupacion("Enfermera");

        mascota.setPropietario(manager.merge(p));

        return mascota;

    }

    @Test
    @Tag("propietario")
    @Tag("mascota")
    @DisplayName("Probando relaciones entre mascotas y propietarios")
    void testRelacionMascotaPropietario() {
        Mascota m = new Mascota();
        m.setNombre("Perseo");
        m.setEspecie("Tortuga");
        m.setRaza("reeves");
        m.setEdad(67);
        m.setAltura(0.10F);
        m.setPeso(0.6F);
        m.setSexo("Macho");
        m.setColor("cafe pardo con manchas");

        Propietario p = new Propietario();
        p.setId(3L);
        p.setNombre("Laura");
        p.setApellidoPaterno("Diaz");
        p.setApellidoMaterno("Santos");
        p.setDireccion("Calle 456");
        p.setCelular("9876543210");
        p.setCorreo("laura@example.com");
        p.setFechaNacimiento(LocalDate.parse("1992-08-20"));
        p.setOcupacion("Enfermera");
        m.setPropietario(p);

        assertEquals(Propietario.class, m.getPropietario().getClass());
    }
}
