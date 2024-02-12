package org.bedu.java.backend.veterinaria.repository;

import org.bedu.java.backend.veterinaria.model.Vet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VetRepository extends CrudRepository<Vet, Long> {

    List<Vet> findAll();

    List<Vet> findByName(String name);

    List<Vet> findBySpecialty(String specialty);

}