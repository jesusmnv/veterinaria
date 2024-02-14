package org.bedu.java.backend.veterinaria.repository;

import java.util.List;

import org.bedu.java.backend.veterinaria.model.Owner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends CrudRepository<Owner, Long> {
    List<Owner> findAll();

    // SELECT * FROM propietarios WHERE nombre LIKE = :nombre
    List<Owner> findByNameContaining(String name);

}