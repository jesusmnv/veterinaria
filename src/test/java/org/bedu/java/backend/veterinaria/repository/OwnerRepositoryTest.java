package org.bedu.java.backend.veterinaria.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import org.bedu.java.backend.veterinaria.model.Owner;
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
class OwnerRepositoryTest {

    @Autowired
    private OwnerRepository repository;

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

        Owner owner1 = crearOwner1();
        Owner owner2 = crearOwner2();
        Owner owner3 = crearOwner3();
        manager.persist(owner1);
        manager.persist(owner2);
        manager.persist(owner3);
        List<Owner> result = repository.findByNameContaining("Carmen");
        assertEquals(1, result.size());
    }

    private Owner crearOwner1() {

        Owner p = new Owner();
        // p.setId(7L);
        p.setName("Carmen");
        p.setPLastName("Sanchez");
        p.setMLastName("Gomez");
        p.setAddress("Avenida 567");
        p.setCellPhone("1231231234");
        p.setEmail("carmen@example.com");
        p.setBirthDate(LocalDate.parse("1978-06-15"));
        p.setOccupation("Arquitecta");

        return p;

    }

    private Owner crearOwner2() {

        Owner p = new Owner();
        // p.setId(9L);
        p.setName("Isabel");
        p.setPLastName("Fuentes");
        p.setMLastName("Jimenez");
        p.setAddress("Avenida 345");
        p.setCellPhone("9991112222");
        p.setEmail("isabel@example.com");
        p.setBirthDate(LocalDate.parse("1984-02-17"));
        p.setOccupation("Psicóloga");

        return p;

    }

    private Owner crearOwner3() {

        Owner p = new Owner();
        // p.setId(3L);
        p.setName("Laura");
        p.setPLastName("Diaz");
        p.setMLastName("Santos");
        p.setAddress("Calle 456");
        p.setCellPhone("9876543210");
        p.setEmail("laura@example.com");
        p.setBirthDate(LocalDate.parse("1992-08-20"));
        p.setOccupation("Enfermera");

        return p;
    }
}
