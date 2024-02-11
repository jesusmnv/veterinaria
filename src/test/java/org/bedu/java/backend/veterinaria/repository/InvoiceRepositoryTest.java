package org.bedu.java.backend.veterinaria.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.bedu.java.backend.veterinaria.model.Invoice;
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
class InvoiceRepositoryTest {

   @Autowired
   private InvoiceRepository repository;

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
      Invoice invoice = factura1();
      Invoice invoice2 = factura2();

      manager.persist(invoice);
      manager.persist(invoice2);

      List<Invoice> result = repository.findByIssuanceDate(LocalDate.parse("2023-12-12"));
      assertEquals(2, result.size());

   }

   private Invoice factura1() {
      Invoice invoice = new Invoice();

      invoice.setIssuanceDate(LocalDate.parse("2023-12-12"));
      invoice.setVat(1);
      invoice.setLegalName("qwerty");
      invoice.setClientRFC("1234567891234");
      invoice.setSubtotal(150);
      invoice.setTotal(1500);

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

      invoice.setOwner(manager.merge(propietario));
      return invoice;

   }

   private Invoice factura2() {
      Invoice invoice = new Invoice();

      invoice.setIssuanceDate(LocalDate.parse("2023-12-12"));
      invoice.setVat(1);
      invoice.setLegalName("qwerty");
      invoice.setClientRFC("1234567891234");
      invoice.setSubtotal(150);
      invoice.setTotal(1500);

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

      invoice.setOwner(manager.merge(propietario));
      return invoice;
   }

   private Invoice factura3() {
      Invoice invoice = new Invoice();

      // invoice.setId(33L);
      invoice.setIssuanceDate(LocalDate.parse("2023-12-12"));
      invoice.setVat(1);
      invoice.setLegalName("qwerty");
      invoice.setClientRFC("1234567891234");
      invoice.setSubtotal(150);
      invoice.setTotal(1500);
      invoice.setOwner(null);
      return invoice;

   }

   @Test
   @DisplayName("Repository should filter invoices by id")
   void findByIdTest() {
      Invoice invoice = factura3();

      manager.persist(invoice);

      Optional<Invoice> result = repository.findById(invoice.getId());
      assertTrue(result.isPresent());

      Invoice fResult = result.get();
      assertEquals(invoice.getId(), fResult.getId());

   }

}
