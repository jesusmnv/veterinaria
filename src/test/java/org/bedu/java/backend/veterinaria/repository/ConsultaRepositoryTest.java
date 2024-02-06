package org.bedu.java.backend.veterinaria.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.bedu.java.backend.veterinaria.model.Consulta;
import org.bedu.java.backend.veterinaria.model.Mascota;
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
        Consulta consulta1 = new Consulta();
        Consulta consulta2 = new Consulta();
        Consulta consulta3 = new Consulta();

        consulta1.setDiagnostico("Dolor abdominal");
        consulta1.setTratamientoIndicado("Realizar ecografía");
        consulta1.setObservaciones("Controlar la alimentación");
        consulta1.setFechaConsulta(LocalDate.parse("2023-12-01"));

        Mascota m1 = new Mascota();
        m1.setId(7L);

        Veterinario v1 = new Veterinario();
        v1.setId(7L);

        consulta1.setMascota(m1);
        consulta1.setVeterinario(v1);
        
        
        consulta2.setDiagnostico("Infección de oído");
        consulta2.setTratamientoIndicado("Administrar gotas");
        consulta2.setObservaciones("Evitar exposición al agua");
        consulta2.setFechaConsulta(LocalDate.parse("2023-12-01"));

        Mascota m2 = new Mascota();
        m2.setId(9L);

        Veterinario v2 = new Veterinario();
        v2.setId(9L);

        consulta2.setMascota(m2);
        consulta2.setVeterinario(v2);


        consulta3.setDiagnostico("Fractura en la pata");
        consulta3.setTratamientoIndicado("Colocar vendaje");
        consulta3.setObservaciones("Reposo absoluto");
        consulta3.setFechaConsulta(LocalDate.parse("2023-12-01"));

        Mascota m3 = new Mascota();
        m3.setId(3L);

        Veterinario v3 = new Veterinario();
        v3.setId(3L);

        consulta3.setMascota(m3);
        consulta3.setVeterinario(v3);

        // Crea los registros en la BD de prueba (h2)
        manager.persist(consulta1);
        manager.persist(consulta2);
        manager.persist(consulta3);

        List<Consulta> result;
        result = repository.findByFechaConsulta(LocalDate.parse("2023-12-01"));
        
        assertTrue(result.size() == 3);
    }

}
