package org.bedu.java.backend.veterinaria.repository;

import org.bedu.java.backend.veterinaria.model.Factura;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacturaRepository extends CrudRepository<Factura, Long> {

    List<Factura> findAll();

}