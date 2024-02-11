package org.bedu.java.backend.veterinaria.service;

import org.bedu.java.backend.veterinaria.dto.medication.CreateMedicationDTO;
import org.bedu.java.backend.veterinaria.dto.medication.MedicationDTO;
import org.bedu.java.backend.veterinaria.dto.medication.UpdateMedicationDTO;
import org.bedu.java.backend.veterinaria.exception.MedicationNotFoundException;
import org.bedu.java.backend.veterinaria.mapper.MedicationMapper;
import org.bedu.java.backend.veterinaria.model.Medication;
import org.bedu.java.backend.veterinaria.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicamentoService {

    private MedicationRepository repository;
    private MedicationMapper mapper;

    @Autowired
    public MedicamentoService(MedicationRepository repository, MedicationMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<MedicationDTO> findAll() {
        return mapper.toDTO(repository.findAll());
    }

    public Optional<MedicationDTO> findById(Long id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    public MedicationDTO save(CreateMedicationDTO data) {
        Medication entity = repository.save(mapper.toModel(data));
        return mapper.toDTO(entity);
    }

    public void update(Long medicamentoId, UpdateMedicationDTO data) throws MedicationNotFoundException {
        Optional<Medication> result = repository.findById(medicamentoId);

        if (!result.isPresent()) {
            throw new MedicationNotFoundException(medicamentoId);
        }

        Medication medication = result.get();
        mapper.update(medication, data);
        repository.save(medication);
    }

    public void deleteById(Long medicamentoId) throws MedicationNotFoundException {

        Optional<Medication> result = repository.findById(medicamentoId);

        if (!result.isPresent()) {
            throw new MedicationNotFoundException(medicamentoId);
        }

        repository.deleteById(medicamentoId);
    }

}