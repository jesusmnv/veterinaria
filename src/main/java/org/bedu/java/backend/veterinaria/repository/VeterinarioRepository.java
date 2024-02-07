package org.bedu.java.backend.veterinaria.repository;

import java.util.List;

import org.bedu.java.backend.veterinaria.model.Veterinario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeterinarioRepository extends CrudRepository<Veterinario,Long> {

    
    List<Veterinario> findAll();
    
}