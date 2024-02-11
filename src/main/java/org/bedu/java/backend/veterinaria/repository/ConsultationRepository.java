package org.bedu.java.backend.veterinaria.repository;

import org.bedu.java.backend.veterinaria.model.Consultation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ConsultationRepository extends CrudRepository<Consultation, Long> {

    List<Consultation> findAll();

    List<Consultation> findByConsultationDate(LocalDate consultationDate);

}