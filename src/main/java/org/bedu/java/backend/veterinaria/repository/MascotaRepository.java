package org.bedu.java.backend.veterinaria.repository;

import org.bedu.java.backend.veterinaria.model.Mascota;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MascotaRepository extends CrudRepository<Mascota, Long> {

    List<Mascota> findAll();

    // SELECT * FROM mascotas WHERE nombre LIKE = :nombre
    List<Mascota> findByNombreContaining(String nombre);

}