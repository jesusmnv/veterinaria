package org.bedu.java.backend.veterinaria.repository;

import java.util.List;

import org.bedu.java.backend.veterinaria.model.Propietario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropietarioRepository extends CrudRepository<Propietario, Long> {
    List<Propietario> findAll();

    // SELECT * FROM propietarios WHERE nombre LIKE = :nombre
    List<Propietario> findByNombreContaining(String nombre);

}