package org.bedu.java.backend.veterinaria.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.bedu.java.backend.veterinaria.model.Mascota;
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
public class MascotaRepositoryTest {

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

        Mascota mascota1 = new Mascota();
        Mascota mascota2 = new Mascota();
        Mascota mascota3 = new Mascota();

        mascota1.setNombre("Fury");
        mascota1.setEspecie("Perro");
        mascota1.setRaza("Schnauzer");
        mascota1.setEdad(2);
        mascota1.setAltura(0.25F);
        mascota1.setPeso(1.45F);
        mascota1.setSexo("Macho");
        mascota1.setColor("gris");

        mascota2.setNombre("Daenerys");
        mascota2.setEspecie("Perro");
        mascota2.setRaza("Chihuahua");
        mascota2.setEdad(2);
        mascota2.setAltura(0.12F);
        mascota2.setPeso(0.8F);
        mascota2.setSexo("Hembra");
        mascota2.setColor("cafe claro");

        mascota3.setNombre("Perseo");
        mascota3.setEspecie("Tortuga");
        mascota3.setRaza("reeves");
        mascota3.setEdad(67);
        mascota3.setAltura(0.10F);
        mascota3.setPeso(0.6F);
        mascota3.setSexo("Macho");
        mascota3.setColor("cafe pardo con manchas");

        manager.persist(mascota1);
        manager.persist(mascota2);
        manager.persist(mascota3);

        List<Mascota> result = repository.findByNombreContaining("Fury");

        assertEquals(1, result.size());
    }

}
