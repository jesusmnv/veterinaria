package org.bedu.java.backend.veterinaria.service;

import org.bedu.java.backend.veterinaria.dto.medication.MedicationDTO;
import org.bedu.java.backend.veterinaria.exception.MedicationNotFoundException;
import org.bedu.java.backend.veterinaria.mapper.FacturaMedicamentoMapper;
import org.bedu.java.backend.veterinaria.mapper.MedicationMapper;
import org.bedu.java.backend.veterinaria.repository.FacturaMedicamentoRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacturaMedicamentoService {

    private MedicationService serviceMService;

    private FacturaMedicamentoRepository repository;

    private FacturaMedicamentoMapper mapper;

    private MedicationMapper medicationMapper;

    @Autowired
    public FacturaMedicamentoService(FacturaMedicamentoRepository repository, FacturaMedicamentoMapper mapper,
                                     MedicationMapper medicationMapper, MedicationService serviceMService) {
        this.repository = repository;
        this.mapper = mapper;
        this.medicationMapper = medicationMapper;
        this.serviceMService = serviceMService;

    }

    public void addMedicamento(Long facturaId, Long medicamentoId, int cantidad) throws MedicationNotFoundException {

        Optional<MedicationDTO> dto = serviceMService.findById(medicamentoId);
        if (!dto.isPresent()) {
            throw new MedicationNotFoundException(medicamentoId);
        }

        MedicationDTO medicamento = dto.get();

        repository.save(mapper.toModel(facturaId, medicamentoId, medicamento.getPrice(), cantidad));
    }

    public List<MedicationDTO> findMedicamentosByFactura(Long facturaId) {
        return medicationMapper.toDTO(repository.findMedicamentosByFactura(facturaId));
    }
}
