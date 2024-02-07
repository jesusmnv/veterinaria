package org.bedu.java.backend.veterinaria.repository;

import org.bedu.java.backend.veterinaria.model.Factura;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface FacturaRepository extends CrudRepository<Factura, Long> {

    List<Factura> findAll();

    List<Factura> findByFechaEmision(LocalDate fechaEmision);

    Optional<Factura> findById(Long id);
}