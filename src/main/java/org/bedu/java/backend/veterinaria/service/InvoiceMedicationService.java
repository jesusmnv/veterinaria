package org.bedu.java.backend.veterinaria.service;

import org.bedu.java.backend.veterinaria.dto.medication.MedicationDTO;
import org.bedu.java.backend.veterinaria.exception.MedicationNotFoundException;
import org.bedu.java.backend.veterinaria.mapper.InvoiceMedicationMapper;
import org.bedu.java.backend.veterinaria.mapper.MedicationMapper;
import org.bedu.java.backend.veterinaria.repository.InvoiceMedicationRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceMedicationService {

    private MedicationService medicationService;

    private InvoiceMedicationRepository repository;

    private InvoiceMedicationMapper mapper;

    private MedicationMapper medicationMapper;

    @Autowired
    public InvoiceMedicationService(InvoiceMedicationRepository repository, InvoiceMedicationMapper mapper,
                                    MedicationMapper medicationMapper, MedicationService medicationService) {
        this.repository = repository;
        this.mapper = mapper;
        this.medicationMapper = medicationMapper;
        this.medicationService = medicationService;

    }

    public void addMedication(Long invoiceId, Long medicationId, int quantity) throws MedicationNotFoundException {

        Optional<MedicationDTO> dto = medicationService.findById(medicationId);
        
        if (!dto.isPresent()) {
            throw new MedicationNotFoundException(medicationId);
        }

        MedicationDTO medicationDTO = dto.get();

        repository.save(mapper.toModel(invoiceId, medicationId, medicationDTO.getPrice(), quantity));
    }

    public List<MedicationDTO> findMedicationsByInvoice(Long invoiceId) {
        return medicationMapper.toDTO(repository.findMedicationsByInvoice(invoiceId));
    }
}
