package org.bedu.java.backend.veterinaria.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import org.bedu.java.backend.veterinaria.model.Pet;
import org.bedu.java.backend.veterinaria.model.Owner;
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
class PetRepositoryTest {

    @Autowired
    private PetRepository repository;

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

        Pet pet1 = createPet1();
        Pet pet2 = createPet2();
        Pet pet3 = createPet3();
        manager.persist(pet1);
        manager.persist(pet2);
        manager.persist(pet3);
        List<Pet> result = repository.findByNameContaining("Fury");
        assertEquals(1, result.size());
    }

    private Pet createPet1() {

        Pet pet = new Pet();

        pet.setName("Fury");
        pet.setSpecies("Perro");
        pet.setBreed("Schnauzer");
        pet.setAge(2);
        pet.setHeight(0.25F);
        pet.setWeight(1.45F);
        pet.setGender("Macho");
        pet.setColor("gris");

        Owner p = new Owner();
        p.setId(7L);
        p.setName("Carmen");
        p.setPLastName("Sanchez");
        p.setMLastName("Gomez");
        p.setAddress("Avenida 567");
        p.setCellPhone("1231231234");
        p.setEmail("carmen@example.com");
        p.setBirthDate(LocalDate.parse("1978-06-15"));
        p.setOccupation("Arquitecta");

        pet.setOwner(manager.merge(p));

        return pet;

    }

    private Pet createPet2() {

        Pet mascota = new Pet();

        mascota.setName("Daenerys");
        mascota.setSpecies("Perro");
        mascota.setBreed("Chihuahua");
        mascota.setAge(2);
        mascota.setHeight(0.12F);
        mascota.setWeight(0.8F);
        mascota.setGender("Hembra");
        mascota.setColor("cafe claro");

        Owner p = new Owner();
        p.setId(9L);
        p.setName("Isabel");
        p.setPLastName("Fuentes");
        p.setMLastName("Jimenez");
        p.setAddress("Avenida 345");
        p.setCellPhone("9991112222");
        p.setEmail("isabel@example.com");
        p.setBirthDate(LocalDate.parse("1984-02-17"));
        p.setOccupation("Psicóloga");

        mascota.setOwner(manager.merge(p));

        return mascota;

    }

    private Pet createPet3() {

        Pet mascota = new Pet();

        mascota.setName("Perseo");
        mascota.setSpecies("Tortuga");
        mascota.setBreed("reeves");
        mascota.setAge(67);
        mascota.setHeight(0.10F);
        mascota.setWeight(0.6F);
        mascota.setGender("Macho");
        mascota.setColor("cafe pardo con manchas");

        Owner p = new Owner();
        p.setId(3L);
        p.setName("Laura");
        p.setPLastName("Diaz");
        p.setMLastName("Santos");
        p.setAddress("Calle 456");
        p.setCellPhone("9876543210");
        p.setEmail("laura@example.com");
        p.setBirthDate(LocalDate.parse("1992-08-20"));
        p.setOccupation("Enfermera");

        mascota.setOwner(manager.merge(p));

        return mascota;

    }

    @Test
    @Tag("owner")
    @Tag("mascota")
    @DisplayName("Testing relationships between pets and owners")
    void testRelacionMascotaPropietario() {
        Pet m = new Pet();
        m.setName("Perseo");
        m.setSpecies("Tortuga");
        m.setBreed("reeves");
        m.setAge(67);
        m.setHeight(0.10F);
        m.setWeight(0.6F);
        m.setGender("Macho");
        m.setColor("cafe pardo con manchas");

        Owner p = new Owner();
        p.setId(3L);
        p.setName("Laura");
        p.setPLastName("Diaz");
        p.setMLastName("Santos");
        p.setAddress("Calle 456");
        p.setCellPhone("9876543210");
        p.setEmail("laura@example.com");
        p.setBirthDate(LocalDate.parse("1992-08-20"));
        p.setOccupation("Enfermera");
        m.setOwner(p);

        assertEquals(Owner.class, m.getOwner().getClass());
    }
}
