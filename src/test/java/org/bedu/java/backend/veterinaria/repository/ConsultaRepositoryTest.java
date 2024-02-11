package org.bedu.java.backend.veterinaria.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.bedu.java.backend.veterinaria.model.Consulta;
import org.bedu.java.backend.veterinaria.model.Pet;
import org.bedu.java.backend.veterinaria.model.Owner;
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
class ConsultaRepositoryTest {

        @Autowired
        private ConsultaRepository repository;

        @Autowired
        private TestEntityManager manager;

        @Test
        @DisplayName("Repository should be injected")
        void smokeTest() {
                assertNotNull(repository);
        }

        @Test
        @DisplayName("Repository should filter consultations by consultation date")
        void filterByFechaConsultaTest() {
                Consulta consulta1 = crearConsulta1();
                Consulta consulta2 = crearConsulta2();
                Consulta consulta3 = crearConsulta3();
                manager.persist(consulta1);
                manager.persist(consulta2);
                manager.persist(consulta3);
                List<Consulta> result;
                result = repository.findByFechaConsulta(LocalDate.parse("2023-12-01"));
                assertEquals(2, result.size());
        }

        private Consulta crearConsulta1() {
                Consulta consulta = new Consulta();

                consulta.setDiagnostico("Dolor abdominal");
                consulta.setTratamientoIndicado("Realizar ecografía");
                consulta.setObservaciones("Controlar la alimentación");
                consulta.setFechaConsulta(LocalDate.parse("2024-04-04"));

                Pet m = new Pet();
                m.setId(7L);
                m.setName("Bobby");
                m.setSpecies("Perro");
                m.setBreed("Poodle");
                m.setAge(3);
                m.setHeight(20.3F);
                m.setWeight(10.1F);
                m.setGender("Macho");
                m.setColor("Blanco");

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
                m.setOwner(manager.merge(p));

                Veterinario v = new Veterinario();
                v.setId(7L);
                v.setNombre("Sofía");
                v.setApellidoPaterno("Rodríguez");
                v.setApellidoMaterno("Santos");
                v.setFechaNacimiento(LocalDate.parse("1987-11-28"));
                v.setCelular("555-789-0123");
                v.setCorreo("sofia.rodriguez@email.com");
                v.setEspecialidad("Cardiología");
                v.setHoraEntrada(LocalTime.parse("10:00"));
                v.setHoraSalida(LocalTime.parse("18:00"));

                consulta.setMascota(manager.merge(m));
                consulta.setVeterinario(manager.merge(v));

                return consulta;
        }

        private Consulta crearConsulta2() {
                Consulta consulta = new Consulta();

                consulta.setDiagnostico("Infección de oído");
                consulta.setTratamientoIndicado("Administrar gotas");
                consulta.setObservaciones("Evitar exposición al agua");
                consulta.setFechaConsulta(LocalDate.parse("2023-12-01"));

                Pet m = new Pet();
                m.setId(9L);
                m.setName("Misty");
                m.setSpecies("Gato");
                m.setBreed("Angora");
                m.setAge(1);
                m.setHeight(18.2F);
                m.setWeight(3.0F);
                m.setGender("Hembra");
                m.setColor("Blanco");

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
                m.setOwner(manager.merge(p));

                Veterinario v = new Veterinario();
                v.setId(9L);
                v.setNombre("María");
                v.setApellidoPaterno("López");
                v.setApellidoMaterno("Herrera");
                v.setFechaNacimiento(LocalDate.parse("1984-08-23"));
                v.setCelular("555-901-2345");
                v.setCorreo("maria.lopez@email.com");
                v.setEspecialidad("Gastroenterología");
                v.setHoraEntrada(LocalTime.parse("08:45"));
                v.setHoraSalida(LocalTime.parse("16:45"));

                consulta.setMascota(manager.merge(m));
                consulta.setVeterinario(manager.merge(v));

                return consulta;
        }

        private Consulta crearConsulta3() {
                Consulta consulta = new Consulta();

                consulta.setDiagnostico("Fractura en la pata");
                consulta.setTratamientoIndicado("Colocar vendaje");
                consulta.setObservaciones("Reposo absoluto");
                consulta.setFechaConsulta(LocalDate.parse("2023-12-01"));

                Pet m = new Pet();
                m.setId(3L);
                m.setName("Firulais");
                m.setSpecies("Perro");
                m.setBreed("Chihuahua");
                m.setAge(1);
                m.setHeight(15.0F);
                m.setWeight(2.0F);
                m.setGender("Macho");
                m.setColor("Café");

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
                m.setOwner(manager.merge(p));

                Veterinario v = new Veterinario();
                v.setId(3L);
                v.setNombre("Laura");
                v.setApellidoPaterno("Sánchez");
                v.setApellidoMaterno("Ramírez");
                v.setFechaNacimiento(LocalDate.parse("1988-09-18"));
                v.setCelular("555-345-6789");
                v.setCorreo("laura.sanchez@email.com");
                v.setEspecialidad("Dermatología");
                v.setHoraEntrada(LocalTime.parse("08:00"));
                v.setHoraSalida(LocalTime.parse("16:00"));

                consulta.setMascota(manager.merge(m));
                consulta.setVeterinario(manager.merge(v));

                return consulta;
        }

}
