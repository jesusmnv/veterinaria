package org.bedu.java.backend.veterinaria.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.bedu.java.backend.veterinaria.model.Consultation;
import org.bedu.java.backend.veterinaria.model.Pet;
import org.bedu.java.backend.veterinaria.model.Owner;
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
class ConsultationRepositoryTest {

        @Autowired
        private ConsultationRepository repository;

        @Autowired
        private TestEntityManager manager;

        @Test
        @DisplayName("Repository should be injected")
        void smokeTest() {
                assertNotNull(repository);
        }

        @Test
        @DisplayName("Repository should filter consultations by consultation date")
        void filterByConsultationDateTest() {
                Consultation consultation1 = createConsultation1();
                Consultation consultation2 = createConsultation2();
                Consultation consultation3 = createConsultation3();
                manager.persist(consultation1);
                manager.persist(consultation2);
                manager.persist(consultation3);
                List<Consultation> result;
                result = repository.findByConsultationDate(LocalDate.parse("2023-12-01"));
                assertEquals(2, result.size());
        }

        private Consultation createConsultation1() {
                Consultation consultation = new Consultation();

                consultation.setDiagnosis("Abdominal pain");
                consultation.setPrescribedTreatment("Perform ultrasound");
                consultation.setObservations("Monitor feeding");
                consultation.setConsultationDate(LocalDate.parse("2024-04-04"));

                Pet pet = new Pet();
                pet.setId(7L);
                pet.setName("Bobby");
                pet.setSpecies("Perro");
                pet.setBreed("Poodle");
                pet.setAge(3);
                pet.setHeight(20.3F);
                pet.setWeight(10.1F);
                pet.setGender("Macho");
                pet.setColor("Blanco");

                Owner owner = new Owner();
                owner.setId(7L);
                owner.setName("Carmen");
                owner.setSurname("Sanchez");
                owner.setMaternalSurname("Gomez");
                owner.setAddress("Avenida 567");
                owner.setCellphone("1231231234");
                owner.setEmail("carmen@example.com");
                owner.setBirthdate(LocalDate.parse("1978-06-15"));
                owner.setOccupation("Arquitecta");
                pet.setOwner(manager.merge(owner));

                Vet vet = new Vet();
                vet.setId(7L);
                vet.setName("Sofía");
                vet.setSurname("Rodríguez");
                vet.setMaternalSurname("Santos");
                vet.setBirthdate(LocalDate.parse("1987-11-28"));
                vet.setCellphone("555-789-0123");
                vet.setEmail("sofia.rodriguez@email.com");
                vet.setSpecialty("Cardiología");
                vet.setEntryTime(LocalTime.parse("10:00"));
                vet.setExitTime(LocalTime.parse("18:00"));

                consultation.setPet(manager.merge(pet));
                consultation.setVet(manager.merge(vet));

                return consultation;
        }

        private Consultation createConsultation2() {
                Consultation consultation = new Consultation();

                consultation.setDiagnosis("Ear infection");
                consultation.setPrescribedTreatment("Administer ear drops");
                consultation.setObservations("Avoid water exposure");
                consultation.setConsultationDate(LocalDate.parse("2023-12-01"));

                Pet pet = new Pet();
                pet.setId(9L);
                pet.setName("Misty");
                pet.setSpecies("Gato");
                pet.setBreed("Angora");
                pet.setAge(1);
                pet.setHeight(18.2F);
                pet.setWeight(3.0F);
                pet.setGender("Hembra");
                pet.setColor("Blanco");

                Owner owner = new Owner();
                owner.setId(9L);
                owner.setName("Isabel");
                owner.setSurname("Fuentes");
                owner.setMaternalSurname("Jimenez");
                owner.setAddress("Avenida 345");
                owner.setCellphone("9991112222");
                owner.setEmail("isabel@example.com");
                owner.setBirthdate(LocalDate.parse("1984-02-17"));
                owner.setOccupation("Psicóloga");
                pet.setOwner(manager.merge(owner));

                Vet vet = new Vet();
                vet.setId(9L);
                vet.setName("María");
                vet.setSurname("López");
                vet.setMaternalSurname("Herrera");
                vet.setBirthdate(LocalDate.parse("1984-08-23"));
                vet.setCellphone("555-901-2345");
                vet.setEmail("maria.lopez@email.com");
                vet.setSpecialty("Gastroenterología");
                vet.setEntryTime(LocalTime.parse("08:45"));
                vet.setExitTime(LocalTime.parse("16:45"));

                consultation.setPet(manager.merge(pet));
                consultation.setVet(manager.merge(vet));

                return consultation;
        }

        private Consultation createConsultation3() {
                Consultation consultation = new Consultation();

                consultation.setDiagnosis("Leg fracture");
                consultation.setPrescribedTreatment("Apply bandage");
                consultation.setObservations("Absolute rest");
                consultation.setConsultationDate(LocalDate.parse("2023-12-01"));

                Pet pet = new Pet();
                pet.setId(3L);
                pet.setName("Firulais");
                pet.setSpecies("Perro");
                pet.setBreed("Chihuahua");
                pet.setAge(1);
                pet.setHeight(15.0F);
                pet.setWeight(2.0F);
                pet.setGender("Macho");
                pet.setColor("Café");

                Owner owner = new Owner();
                owner.setId(3L);
                owner.setName("Laura");
                owner.setSurname("Diaz");
                owner.setMaternalSurname("Santos");
                owner.setAddress("Calle 456");
                owner.setCellphone("9876543210");
                owner.setEmail("laura@example.com");
                owner.setBirthdate(LocalDate.parse("1992-08-20"));
                owner.setOccupation("Enfermera");
                pet.setOwner(manager.merge(owner));

                Vet vet = new Vet();
                vet.setId(3L);
                vet.setName("Laura");
                vet.setSurname("Sánchez");
                vet.setMaternalSurname("Ramírez");
                vet.setBirthdate(LocalDate.parse("1988-09-18"));
                vet.setCellphone("555-345-6789");
                vet.setEmail("laura.sanchez@email.com");
                vet.setSpecialty("Dermatología");
                vet.setEntryTime(LocalTime.parse("08:00"));
                vet.setExitTime(LocalTime.parse("16:00"));

                consultation.setPet(manager.merge(pet));
                consultation.setVet(manager.merge(vet));

                return consultation;
        }

}
