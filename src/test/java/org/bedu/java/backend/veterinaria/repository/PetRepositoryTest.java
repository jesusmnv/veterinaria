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
        pet.setColor("Gris");

        Owner owner = new Owner();
        owner.setId(7L);
        owner.setName("Carmen");
        owner.setSurname("Sanchez");
        owner.setMaternalSurname("Gomez");
        owner.setAddress("567 Avenue");
        owner.setCellphone("1231231234");
        owner.setEmail("carmen@example.com");
        owner.setBirthdate(LocalDate.parse("1978-06-15"));
        owner.setOccupation("Architect");

        pet.setOwner(manager.merge(owner));

        return pet;

    }

    private Pet createPet2() {

        Pet pet = new Pet();

        pet.setName("Daenerys");
        pet.setSpecies("Dog");
        pet.setBreed("Chihuahua");
        pet.setAge(2);
        pet.setHeight(0.12F);
        pet.setWeight(0.8F);
        pet.setGender("Female");
        pet.setColor("Light brown");
        

        Owner owner = new Owner();
        owner.setId(9L);
        owner.setName("Isabel");
        owner.setSurname("Fuentes");
        owner.setMaternalSurname("Jimenez");
        owner.setAddress("345 Avenue");
        owner.setCellphone("9991112222");
        owner.setEmail("isabel@example.com");
        owner.setBirthdate(LocalDate.parse("1984-02-17"));
        owner.setOccupation("Psychologist");

        pet.setOwner(manager.merge(owner));

        return pet;

    }

    private Pet createPet3() {

        Pet pet = new Pet();

        pet.setName("Perseus");
        pet.setSpecies("Turtle");
        pet.setBreed("Reeves");
        pet.setAge(67);
        pet.setHeight(0.10F);
        pet.setWeight(0.6F);
        pet.setGender("Male");
        pet.setColor("Brown with spots");        

        Owner owner = new Owner();
        owner.setId(3L);
        owner.setName("Laura");
        owner.setSurname("Diaz");
        owner.setMaternalSurname("Santos");
        owner.setAddress("Calle 456");
        owner.setCellphone("9876543210");
        owner.setEmail("laura@example.com");
        owner.setBirthdate(LocalDate.parse("1992-08-20"));
        owner.setOccupation("Nurse");

        pet.setOwner(manager.merge(owner));

        return pet;

    }

    @Test
    @Tag("owner")
    @Tag("mascota")
    @DisplayName("Testing relationships between pets and owners")
    void testRelacionMascotaPropietario() {
        Pet pet = new Pet();
        pet.setName("Perseus");
        pet.setSpecies("Turtle");
        pet.setBreed("Reeves");
        pet.setAge(67);
        pet.setHeight(0.10F);
        pet.setWeight(0.6F);
        pet.setGender("Male");
        pet.setColor("Brown with spots");
        

        Owner owner = new Owner();
        owner.setId(3L);
        owner.setName("Laura");
        owner.setSurname("Diaz");
        owner.setMaternalSurname("Santos");
        owner.setAddress("456 Street");
        owner.setCellphone("9876543210");
        owner.setEmail("laura@example.com");
        owner.setBirthdate(LocalDate.parse("1992-08-20"));
        owner.setOccupation("Nurse");
        pet.setOwner(owner);        

        assertEquals(Owner.class, pet.getOwner().getClass());
    }
}
