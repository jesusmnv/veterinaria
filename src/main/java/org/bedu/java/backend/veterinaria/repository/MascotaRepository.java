package org.bedu.java.backend.veterinaria.repository;

import org.bedu.java.backend.veterinaria.model.Mascota;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MascotaRepository extends CrudRepository<Mascota, Long> {
    
    @SuppressWarnings("null")
    List<Mascota> findAll();

}