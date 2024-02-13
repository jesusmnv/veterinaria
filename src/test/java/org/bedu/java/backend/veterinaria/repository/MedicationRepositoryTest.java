package org.bedu.java.backend.veterinaria.repository;

import java.time.LocalDate;
import java.util.List;

import org.bedu.java.backend.veterinaria.model.Medication;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class MedicationRepositoryTest {

    @Autowired
    private MedicationRepository repository;

    @Autowired
    private TestEntityManager manager;

    @Test
    @DisplayName("Repository should be injected")
    void smokeTest() {
        assertNotNull(repository);
    }

    @Test
    @DisplayName("Repository should filter medications by classification or type")
    void filterByClassificationTest() {
        Medication medication1 = new Medication();
        Medication medication2 = new Medication();
        Medication medication3 = new Medication();

        medication1.setName("Amoxicillin");
        medication1.setClassification("Antibiotic");
        medication1.setDescription("Treats bacterial infections in dogs and cats");
        medication1.setExpirationDate(LocalDate.parse("2023-12-01"));
        medication1.setStock(120);
        medication1.setPrice(12.75F);
        medication1.setUsageInstructions("Administer 1 capsule every 8 hours");

        medication2.setName("Metronidazole");
        medication2.setClassification("Antibiotic");
        medication2.setDescription("Treats bacterial infections and certain parasites in dogs and cats");
        medication2.setExpirationDate(LocalDate.parse("2023-12-01"));
        medication2.setStock(150);
        medication2.setPrice(10.5F);
        medication2.setUsageInstructions("Administer according to the dose prescribed by the vetU");

        medication3.setName("Furosemide");
        medication3.setClassification("Loop Diuretic");
        medication3.setDescription("Treats fluid retention and congestion associated with congestive heart failure in dogs and cats");
        medication3.setExpirationDate(LocalDate.parse("2026-06-06"));
        medication3.setStock(260);
        medication3.setPrice(26.66F);
        medication3.setUsageInstructions("Administer according to the dose prescribed by the vetU");

        manager.persist(medication1);
        manager.persist(medication2);
        manager.persist(medication3);

        List<Medication> result = repository.findByClassification("Antibiotic");
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Repository should filter medications by name")
    void filterByNameTest() {
        Medication medication1 = new Medication();
        Medication medication2 = new Medication();
        Medication medication3 = new Medication();

        medication1.setName("Ibuprofen");
        medication1.setClassification("Anti-inflammatory");
        medication1.setDescription("Relieves pain in dogs and cats");
        medication1.setExpirationDate(LocalDate.parse("2023-12-01"));
        medication1.setStock(120);
        medication1.setPrice(12.75F);
        medication1.setUsageInstructions("Administer 1 tablet with food as directed by the veterinarian");

        medication2.setName("Metronidazole");
        medication2.setClassification("Antibiotic");
        medication2.setDescription("Treats bacterial infections and certain parasites in dogs and cats");
        medication2.setExpirationDate(LocalDate.parse("2023-12-01"));
        medication2.setStock(150);
        medication2.setPrice(10.5F);
        medication2.setUsageInstructions("Administer according to the dose prescribed by the vet");

        medication3.setName("Furosemide");
        medication3.setClassification("Loop Diuretic");
        medication3.setDescription("Treats fluid retention and congestion associated with congestive heart failure in dogs and cats");
        medication3.setExpirationDate(LocalDate.parse("2026-06-06"));
        medication3.setStock(260);
        medication3.setPrice(26.66F);
        medication3.setUsageInstructions("Administer according to the dose prescribed by the vet");

        manager.persist(medication1);
        manager.persist(medication2);
        manager.persist(medication3);

        List<Medication> result = repository.findByNameContaining("ro");
        assertEquals(3, result.size());
    }

}