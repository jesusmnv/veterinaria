package org.bedu.java.backend.veterinaria.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.bedu.java.backend.veterinaria.model.Factura;
import org.bedu.java.backend.veterinaria.model.Propietario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class FacturaRepositoryTest {

   @Autowired
   private FacturaRepository repository;

   @Autowired
   private PropietarioRepository propietarioRepository;

   @AfterEach
   public void setup() {
      repository.deleteAll();
      propietarioRepository.deleteAll();
   }

   @Test
   @DisplayName("Repository should be injected")
   void smokeTest() {
      assertNotNull(repository);
   }

   @Test

   @DisplayName("FindAll")
   void findAllTest() {
      LocalDate fecha = LocalDate.parse("2023-12-12");
      Factura factura = new Factura();
      Factura factura2 = new Factura();

      Propietario propietario = new Propietario();
      propietario.setId(1L);
      propietario.setNombre("Job");
      propietario.setApellidoMaterno("Martinez");
      propietario.setApellidoPaterno("Moreno");
      propietario.setDireccion("Paseo");
      propietario.setCelular("33333");
      propietario.setCorreo("a@a.com");
      propietario.setFechaNacimiento(LocalDate.parse("2023-12-01"));
      propietario.setOcupacion("empleado");
      propietarioRepository.save(propietario);

      factura.setId(1000L);

      factura.setFechaEmision(fecha);
      factura.setIva(1);
      factura.setRazonSocial("qwerty");
      factura.setRfcCliente("1234567891234");
      factura.setSubtotal(150);
      factura.setTotal(1500);
      factura.setPropietario(propietario);

      repository.save(factura);

      factura2.setId(2L);

      factura2.setFechaEmision(fecha);
      factura2.setIva(1);
      factura2.setRazonSocial("qwerty");
      factura2.setRfcCliente("1234567891234");
      factura2.setSubtotal(150);
      factura2.setTotal(1500);
      factura2.setPropietario(propietario);

      repository.save(factura2);

      List<Factura> result = repository.findAll();
      assertEquals(2, result.size());

   }

   @Test

   @DisplayName("Repository should filter by Id")
   void findByIdtest() {
      Factura factura = new Factura();
      Factura factura2 = new Factura();

      LocalDate fecha = LocalDate.parse("2023-12-12");
      factura.setId(1L);

      factura.setFechaEmision(fecha);
      factura.setIva(1);
      factura.setRazonSocial("qwerty");
      factura.setRfcCliente("ytrewq");
      factura.setSubtotal(150);
      factura.setTotal(1500);
      factura.setPropietario(null);

      factura2.setId(2L);

      factura2.setFechaEmision(fecha);
      factura2.setIva(1);
      factura2.setRazonSocial("qwerty");
      factura2.setRfcCliente("ytrewq");
      factura2.setSubtotal(150);
      factura2.setTotal(1500);
      factura2.setPropietario(null);

      repository.save(factura);
      repository.save(factura2);

      Optional<Factura> result = repository.findById(1L);

      assertEquals(1L, result.get().getId());
   }

}
