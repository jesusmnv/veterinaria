package org.bedu.java.backend.veterinaria.repository;

import org.bedu.java.backend.veterinaria.model.Invoice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Long> {

    List<Invoice> findAll();

    List<Invoice> findByIssuanceDate(LocalDate issuanceDate);

    Optional<Invoice> findById(Long id);
}