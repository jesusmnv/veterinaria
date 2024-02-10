package org.bedu.java.backend.veterinaria.service;

import org.bedu.java.backend.veterinaria.dto.medicamento.MedicamentoDTO;
import org.bedu.java.backend.veterinaria.exception.MedicamentoNotFoundException;
import org.bedu.java.backend.veterinaria.mapper.FacturaMedicamentoMapper;
import org.bedu.java.backend.veterinaria.mapper.MedicamentoMapper;
import org.bedu.java.backend.veterinaria.repository.FacturaMedicamentoRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacturaMedicamentoService {

    private MedicamentoService serviceMService;

    private FacturaMedicamentoRepository repository;

    private FacturaMedicamentoMapper mapper;

    private MedicamentoMapper medicamentoMapper;

    @Autowired
    public FacturaMedicamentoService(FacturaMedicamentoRepository repository, FacturaMedicamentoMapper mapper,
            MedicamentoMapper medicamentoMapper, MedicamentoService serviceMService) {
        this.repository = repository;
        this.mapper = mapper;
        this.medicamentoMapper = medicamentoMapper;
        this.serviceMService = serviceMService;

    }

    public void addMedicamento(Long facturaId, Long medicamentoId, int cantidad) throws MedicamentoNotFoundException {

        Optional<MedicamentoDTO> dto = serviceMService.findById(medicamentoId);
        if (!dto.isPresent()) {
            throw new MedicamentoNotFoundException(medicamentoId);
        }

        MedicamentoDTO medicamento = dto.get();

        repository.save(mapper.toModel(facturaId, medicamentoId, medicamento.getPrecio(), cantidad));
    }

    public List<MedicamentoDTO> findMedicamentosByFactura(Long facturaId) {
        return medicamentoMapper.toDTO(repository.findMedicamentosByFactura(facturaId));
    }
}
