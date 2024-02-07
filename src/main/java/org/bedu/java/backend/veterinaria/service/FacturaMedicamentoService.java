package org.bedu.java.backend.veterinaria.service;

import org.bedu.java.backend.veterinaria.dto.medicamento.MedicamentoDTO;
import org.bedu.java.backend.veterinaria.mapper.FacturaMedicamentoMapper;
import org.bedu.java.backend.veterinaria.mapper.MedicamentoMapper;
import org.bedu.java.backend.veterinaria.repository.FacturaMedicamentoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacturaMedicamentoService {

    private FacturaMedicamentoRepository repository;
    private FacturaMedicamentoMapper mapper;
    private MedicamentoMapper medicamentoMapper;

    @Autowired
    public FacturaMedicamentoService(FacturaMedicamentoRepository repository, FacturaMedicamentoMapper mapper,
            MedicamentoMapper medicamentoMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.medicamentoMapper = medicamentoMapper;
    }

    public void addMedicamento(Long facturaId, Long medicamentoId, float precio, int cantidad) {
        repository.save(mapper.toModel(facturaId, medicamentoId, precio, cantidad));
    }

    public List<MedicamentoDTO> findMedicamentosByFactura(Long facturaId) {
        return medicamentoMapper.toDTO(repository.findMedicamentosByFactura(facturaId));
    }
}