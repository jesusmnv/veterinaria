package org.bedu.java.backend.veterinaria.repository;

import java.time.LocalDate;
import java.util.List;

import org.bedu.java.backend.veterinaria.model.Medicamento;
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
class MedicamentoRepositoryTest {

    @Autowired
    private MedicamentoRepository repository;

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
        Medicamento medicamento1 = new Medicamento();
        Medicamento medicamento2 = new Medicamento();
        Medicamento medicamento3 = new Medicamento();

        medicamento1.setNombre("Amoxicilina");
        medicamento1.setClasificacion("Antibiótico");
        medicamento1.setDescripcion("Antibiótico para gatos");
        medicamento1.setFechaCaducidad(LocalDate.parse("2023-12-01"));
        medicamento1.setExistencia(120);
        medicamento1.setPrecio(12.75F);
        medicamento1.setInstruccionesUso("Según indicaciones veterinarias");

        medicamento2.setNombre("Paracetamol");
        medicamento2.setClasificacion("Analgésico");
        medicamento2.setDescripcion("Alivia el dolor y reduce la fiebre");
        medicamento2.setFechaCaducidad(LocalDate.parse("2023-12-01"));
        medicamento2.setExistencia(150);
        medicamento2.setPrecio(10.5F);
        medicamento2.setInstruccionesUso("Tomar 1 tableta cada 6 horas");

        medicamento3.setNombre("Amoxicilina_Gato");
        medicamento3.setClasificacion("Antibiótico");
        medicamento3.setDescripcion("Antibiótico para gatos");
        medicamento3.setFechaCaducidad(LocalDate.parse("2026-06-06"));
        medicamento3.setExistencia(260);
        medicamento3.setPrecio(26.66F);
        medicamento3.setInstruccionesUso("Según indicaciones veterinarias");

        manager.persist(medicamento1);
        manager.persist(medicamento2);
        manager.persist(medicamento3);

        List<Medicamento> result = repository.findByClasificacion("Antibiótico");
        assertEquals(2, result.size());
    }

}