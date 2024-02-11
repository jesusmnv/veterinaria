package org.bedu.java.backend.veterinaria.repository;

import org.bedu.java.backend.veterinaria.model.Medication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationRepository extends CrudRepository<Medication, Long> {

    List<Medication> findAll();

    // SELECT * FROM medication WHERE name LIKE :name
    List<Medication> findByNameContaining(String name);

    List<Medication> findByClassification(String classification);

}