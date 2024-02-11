package org.bedu.java.backend.veterinaria.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.bedu.java.backend.veterinaria.model.Consultation;
import org.bedu.java.backend.veterinaria.model.Mascota;
import org.bedu.java.backend.veterinaria.model.Propietario;
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

                Mascota pet = new Mascota();
                pet.setId(7L);
                pet.setNombre("Bobby");
                pet.setEspecie("Perro");
                pet.setRaza("Poodle");
                pet.setEdad(3);
                pet.setAltura(20.3F);
                pet.setPeso(10.1F);
                pet.setSexo("Macho");
                pet.setColor("Blanco");

                Propietario owner = new Propietario();
                owner.setId(7L);
                owner.setNombre("Carmen");
                owner.setApellidoPaterno("Sanchez");
                owner.setApellidoMaterno("Gomez");
                owner.setDireccion("Avenida 567");
                owner.setCelular("1231231234");
                owner.setCorreo("carmen@example.com");
                owner.setFechaNacimiento(LocalDate.parse("1978-06-15"));
                owner.setOcupacion("Arquitecta");
                pet.setPropietario(manager.merge(owner));

                Veterinario vet = new Veterinario();
                vet.setId(7L);
                vet.setNombre("Sofía");
                vet.setApellidoPaterno("Rodríguez");
                vet.setApellidoMaterno("Santos");
                vet.setFechaNacimiento(LocalDate.parse("1987-11-28"));
                vet.setCelular("555-789-0123");
                vet.setCorreo("sofia.rodriguez@email.com");
                vet.setEspecialidad("Cardiología");
                vet.setHoraEntrada(LocalTime.parse("10:00"));
                vet.setHoraSalida(LocalTime.parse("18:00"));

                consultation.setPet(manager.merge(pet));
                consultation.setVeterinarian(manager.merge(vet));

                return consultation;
        }

        private Consultation createConsultation2() {
                Consultation consultation = new Consultation();

                consultation.setDiagnosis("Ear infection");
                consultation.setPrescribedTreatment("Administer ear drops");
                consultation.setObservations("Avoid water exposure");
                consultation.setConsultationDate(LocalDate.parse("2023-12-01"));

                Mascota pet = new Mascota();
                pet.setId(9L);
                pet.setNombre("Misty");
                pet.setEspecie("Gato");
                pet.setRaza("Angora");
                pet.setEdad(1);
                pet.setAltura(18.2F);
                pet.setPeso(3.0F);
                pet.setSexo("Hembra");
                pet.setColor("Blanco");

                Propietario owner = new Propietario();
                owner.setId(9L);
                owner.setNombre("Isabel");
                owner.setApellidoPaterno("Fuentes");
                owner.setApellidoMaterno("Jimenez");
                owner.setDireccion("Avenida 345");
                owner.setCelular("9991112222");
                owner.setCorreo("isabel@example.com");
                owner.setFechaNacimiento(LocalDate.parse("1984-02-17"));
                owner.setOcupacion("Psicóloga");
                pet.setPropietario(manager.merge(owner));

                Veterinario vet = new Veterinario();
                vet.setId(9L);
                vet.setNombre("María");
                vet.setApellidoPaterno("López");
                vet.setApellidoMaterno("Herrera");
                vet.setFechaNacimiento(LocalDate.parse("1984-08-23"));
                vet.setCelular("555-901-2345");
                vet.setCorreo("maria.lopez@email.com");
                vet.setEspecialidad("Gastroenterología");
                vet.setHoraEntrada(LocalTime.parse("08:45"));
                vet.setHoraSalida(LocalTime.parse("16:45"));

                consultation.setPet(manager.merge(pet));
                consultation.setVeterinarian(manager.merge(vet));

                return consultation;
        }

        private Consultation createConsultation3() {
                Consultation consultation = new Consultation();

                consultation.setDiagnosis("Leg fracture");
                consultation.setPrescribedTreatment("Apply bandage");
                consultation.setObservations("Absolute rest");
                consultation.setConsultationDate(LocalDate.parse("2023-12-01"));

                Mascota pet = new Mascota();
                pet.setId(3L);
                pet.setNombre("Firulais");
                pet.setEspecie("Perro");
                pet.setRaza("Chihuahua");
                pet.setEdad(1);
                pet.setAltura(15.0F);
                pet.setPeso(2.0F);
                pet.setSexo("Macho");
                pet.setColor("Café");

                Propietario owner = new Propietario();
                owner.setId(3L);
                owner.setNombre("Laura");
                owner.setApellidoPaterno("Diaz");
                owner.setApellidoMaterno("Santos");
                owner.setDireccion("Calle 456");
                owner.setCelular("9876543210");
                owner.setCorreo("laura@example.com");
                owner.setFechaNacimiento(LocalDate.parse("1992-08-20"));
                owner.setOcupacion("Enfermera");
                pet.setPropietario(manager.merge(owner));

                Veterinario vet = new Veterinario();
                vet.setId(3L);
                vet.setNombre("Laura");
                vet.setApellidoPaterno("Sánchez");
                vet.setApellidoMaterno("Ramírez");
                vet.setFechaNacimiento(LocalDate.parse("1988-09-18"));
                vet.setCelular("555-345-6789");
                vet.setCorreo("laura.sanchez@email.com");
                vet.setEspecialidad("Dermatología");
                vet.setHoraEntrada(LocalTime.parse("08:00"));
                vet.setHoraSalida(LocalTime.parse("16:00"));

                consultation.setPet(manager.merge(pet));
                consultation.setVeterinarian(manager.merge(vet));

                return consultation;
        }

}
