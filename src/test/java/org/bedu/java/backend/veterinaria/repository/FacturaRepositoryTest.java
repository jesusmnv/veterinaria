package org.bedu.java.backend.veterinaria.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.bedu.java.backend.veterinaria.model.Factura;
import org.bedu.java.backend.veterinaria.model.Owner;
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
class FacturaRepositoryTest {

   @Autowired
   private FacturaRepository repository;

   @Autowired
   private TestEntityManager manager;

   @Test
   @DisplayName("Repository should be injected")
   void smokeTest() {
      assertNotNull(repository);
   }

   @Test
   @DisplayName("Repository should filter invoices by issue date")
   void findByFechaEmisionTest() {
      Factura factura = factura1();
      Factura factura2 = factura2();

      manager.persist(factura);
      manager.persist(factura2);

      List<Factura> result = repository.findByFechaEmision(LocalDate.parse("2023-12-12"));
      assertEquals(2, result.size());

   }

   private Factura factura1() {
      Factura factura = new Factura();

      factura.setFechaEmision(LocalDate.parse("2023-12-12"));
      factura.setIva(1);
      factura.setRazonSocial("qwerty");
      factura.setRfcCliente("1234567891234");
      factura.setSubtotal(150);
      factura.setTotal(1500);

      Owner propietario = new Owner();
      propietario.setId(8L);
      propietario.setName("Job");
      propietario.setPLastName("Martinez");
      propietario.setMLastName("Moreno");
      propietario.setAddress("Paseo");
      propietario.setCellPhone("33333");
      propietario.setEmail("a@a.com");
      propietario.setBirthDate(LocalDate.parse("2023-12-01"));
      propietario.setOccupation("empleado");

      factura.setPropietario(manager.merge(propietario));
      return factura;

   }

   private Factura factura2() {
      Factura factura = new Factura();

      factura.setFechaEmision(LocalDate.parse("2023-12-12"));
      factura.setIva(1);
      factura.setRazonSocial("qwerty");
      factura.setRfcCliente("1234567891234");
      factura.setSubtotal(150);
      factura.setTotal(1500);

      Owner propietario = new Owner();
      propietario.setId(45L);
      propietario.setName("Job");
      propietario.setMLastName("Martinez");
      propietario.setPLastName("Moreno");
      propietario.setAddress("Paseo");
      propietario.setCellPhone("33333");
      propietario.setEmail("a@a.com");
      propietario.setBirthDate(LocalDate.parse("2023-12-01"));
      propietario.setOccupation("empleado");

      factura.setPropietario(manager.merge(propietario));
      return factura;
   }

   private Factura factura3() {
      Factura factura = new Factura();

      // factura.setId(33L);
      factura.setFechaEmision(LocalDate.parse("2023-12-12"));
      factura.setIva(1);
      factura.setRazonSocial("qwerty");
      factura.setRfcCliente("1234567891234");
      factura.setSubtotal(150);
      factura.setTotal(1500);
      factura.setPropietario(null);
      return factura;

   }

   @Test
   @DisplayName("Repository should filter invoices by id")
   void findByIdTest() {
      Factura factura = factura3();

      manager.persist(factura);

      Optional<Factura> result = repository.findById(factura.getId());
      assertTrue(result.isPresent());

      Factura fResult = result.get();
      assertEquals(factura.getId(), fResult.getId());

   }

}
