package org.bedu.java.backend.veterinaria.repository;

import org.bedu.java.backend.veterinaria.model.Consulta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ConsultaRepository extends CrudRepository<Consulta, Long> {

    List<Consulta> findAll();

    List<Consulta> findByFechaConsulta(LocalDate fechaConsulta); //Revisar tipo de dato de fecha

}