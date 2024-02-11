package org.bedu.java.backend.veterinaria.repository;

import org.bedu.java.backend.veterinaria.model.Pet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends CrudRepository<Pet, Long> {

    List<Pet> findAll();

    // SELECT * FROM pets WHERE name LIKE = :name
    List<Pet> findByNameContaining(String name);

}