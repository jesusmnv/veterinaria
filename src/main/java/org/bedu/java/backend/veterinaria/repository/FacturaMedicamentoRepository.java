package org.bedu.java.backend.veterinaria.repository;

import java.util.List;
import org.bedu.java.backend.veterinaria.model.FacturaMedicamento;
import org.bedu.java.backend.veterinaria.model.FacturaMedicamentoKey;
import org.bedu.java.backend.veterinaria.model.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaMedicamentoRepository extends JpaRepository<FacturaMedicamento, FacturaMedicamentoKey> {

    @Query("SELECT f.medicamento FROM FacturaMedicamento f WHERE f.factura.id = :facturaId")
    List<Medicamento> findMedicamentosByFactura(Long facturaId);

}