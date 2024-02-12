package org.bedu.java.backend.veterinaria.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.bedu.java.backend.veterinaria.dto.vet.VetDTO;
import org.bedu.java.backend.veterinaria.model.Vet;
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
class VetRepositoryTest {
    
    @Autowired
    private VetRepository repository;

    @Autowired
    private TestEntityManager manager;

    @Test
    @DisplayName("Repository should be injected")

    void smokeTest(){
        assertNotNull(repository);
    }

    @Test
    @DisplayName("Repository should filter a vet by name")
    void findByNombrTest(){

        VetDTO vet1 = new VetDTO();
        VetDTO vet2 = new VetDTO();
        VetDTO vet3 = new VetDTO();

        vet1.setName("Juan");
        vet1.setPLastName("Perez");
        vet1.setMLastName("Castellanos");
        vet1.setBirthDate(LocalDate.parse("1998-02-25"));
        vet1.setCellPhone("8523697415");
        vet1.setEmail("juan@gmail.com");
        vet1.setSpecialty("Cirujano");
        vet1.setEntryTime(LocalTime.parse("02:25"));
        vet1.setExitTime(LocalTime.parse("15:25"));

        vet2.setName("Roberto");
        vet2.setPLastName("Valdez");
        vet2.setMLastName("Hernandez");
        vet2.setBirthDate(LocalDate.parse("1991-08-25"));
        vet2.setCellPhone("1478963254");
        vet2.setEmail("roberto@gmail.com");
        vet2.setSpecialty("Cirujano");
        vet2.setEntryTime(LocalTime.parse("23:50"));
        vet2.setExitTime(LocalTime.parse("07:00"));

        vet3.setName("Yamileth");
        vet3.setPLastName("Robles");
        vet3.setMLastName("Quintero");
        vet3.setBirthDate(LocalDate.parse("2000-12-15"));
        vet3.setCellPhone("7896523148");
        vet3.setEmail("yamileth@gmail.com");
        vet3.setSpecialty("Rehabilitacion");
        vet3.setEntryTime(LocalTime.parse("20:00"));
        vet3.setExitTime(LocalTime.parse("06:00"));

        manager.persist(vet1);
        manager.persist(vet2);
        manager.persist(vet3);

        List<VetDTO> result = repository.findByName("Yamileth", "Robles", "Quintero");

        assertEquals(1, result.size());

    }

    @Test
    @DisplayName("Repository should filter a vet by specialty")
    void findBySpecialtyTest(){

        Vet vet1 = new Vet();
        Vet vet2 = new Vet();
        Vet vet3 = new Vet();

        vet1.setId(151l);
        vet1.setName("Juan");
        vet1.setPLastName("Perez");
        vet1.setMLastName("Castellanos");
        vet1.setBirthDate(LocalDate.parse("1998-02-25"));
        vet1.setCellPhone("8523697415");
        vet1.setEmail("juan@gmail.com");
        vet1.setSpecialty("Cirujano");
        vet1.setEntryTime(LocalTime.parse("02:25"));
        vet1.setExitTime(LocalTime.parse("15:25"));

        vet2.setId(150l);
        vet2.setName("Roberto");
        vet2.setPLastName("Valdez");
        vet2.setMLastName("Hernandez");
        vet2.setBirthDate(LocalDate.parse("1991-08-25"));
        vet2.setCellPhone("1478963254");
        vet2.setEmail("roberto@gmail.com");
        vet2.setSpecialty("Cirujano");
        vet2.setEntryTime(LocalTime.parse("23:50"));
        vet2.setExitTime(LocalTime.parse("07:00"));

        vet3.setId(141l);
        vet3.setName("Yamileth");
        vet3.setPLastName("Robles");
        vet3.setMLastName("Quintero");
        vet3.setBirthDate(LocalDate.parse("2000-12-15"));
        vet3.setCellPhone("7896523148");
        vet3.setEmail("yamileth@gmail.com");
        vet3.setSpecialty("Rehabilitacion");
        vet3.setEntryTime(LocalTime.parse("20:00"));
        vet3.setExitTime(LocalTime.parse("06:00"));

        manager.persist(vet1);
        manager.persist(vet2);
        manager.persist(vet3);

        List<Vet> result = repository.findBySpecialty("Cirujano");

        assertEquals(2, result.size());

    }

}
