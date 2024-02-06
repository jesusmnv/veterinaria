package org.bedu.java.backend.veterinaria.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.bedu.java.backend.veterinaria.model.Consulta;
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
public class ConsultaRepositoryTest {

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
        assertTrue(result.size() == 2);
    }

    private Consulta crearConsulta1() {
        Consulta consulta = new Consulta();
    
        consulta.setDiagnostico("Dolor abdominal");
        consulta.setTratamientoIndicado("Realizar ecografía");
        consulta.setObservaciones("Controlar la alimentación");
        consulta.setFechaConsulta(LocalDate.parse("2024-04-04"));
    
        Mascota m = new Mascota();
        m.setId(7L);
        m.setNombre("Bobby");
        m.setEspecie("Perro");
        m.setRaza("Poodle");
        m.setEdad(3);
        m.setAltura(20.3F);
        m.setPeso(10.1F);
        m.setSexo("Macho");
        m.setColor("Blanco");
    
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
        m.setPropietario(manager.merge(p));
    
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

        Mascota m = new Mascota();
        m.setId(9L);
        m.setNombre("Misty");
        m.setEspecie("Gato");
        m.setRaza("Angora");
        m.setEdad(1);
        m.setAltura(18.2F);
        m.setPeso(3.0F);
        m.setSexo("Hembra");
        m.setColor("Blanco");

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
        m.setPropietario(manager.merge(p));
        
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

        Mascota m = new Mascota();
        m.setId(3L);
        m.setNombre("Firulais");
        m.setEspecie("Perro");
        m.setRaza("Chihuahua");
        m.setEdad(1);
        m.setAltura(15.0F);
        m.setPeso(2.0F);
        m.setSexo("Macho");
        m.setColor("Café");

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
        m.setPropietario(manager.merge(p));

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
