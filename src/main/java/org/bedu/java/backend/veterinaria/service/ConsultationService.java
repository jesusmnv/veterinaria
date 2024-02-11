package org.bedu.java.backend.veterinaria.service;

import org.bedu.java.backend.veterinaria.dto.consultation.ConsultationDTO;
import org.bedu.java.backend.veterinaria.dto.consultation.CreateConsultationDTO;
import org.bedu.java.backend.veterinaria.dto.consultation.UpdateConsultationDTO;
import org.bedu.java.backend.veterinaria.exception.ConsultationNotFoundException;
import org.bedu.java.backend.veterinaria.mapper.ConsultationMapper;
import org.bedu.java.backend.veterinaria.model.Consultation;
import org.bedu.java.backend.veterinaria.repository.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultationService {

    private ConsultationRepository repository;

    private ConsultationMapper mapper;

    @Autowired
    public ConsultationService(ConsultationRepository repository, ConsultationMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<ConsultationDTO> findAll() {
        return mapper.toDTO(repository.findAll());
    }

    public Optional<ConsultationDTO> findById(Long id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    public ConsultationDTO save(CreateConsultationDTO data) {
        Consultation entity = repository.save(mapper.toModel(data));
        return mapper.toDTO(entity);
    }

    public void update(Long consultationId, UpdateConsultationDTO data) throws ConsultationNotFoundException {
        Optional<Consultation> result = repository.findById(consultationId);

        if (!result.isPresent()) {
            throw new ConsultationNotFoundException(consultationId);
        }

        Consultation consultation = result.get();
        mapper.update(consultation, data);
        repository.save(consultation);
    }

    public void deleteById(Long consultationId) throws ConsultationNotFoundException {

        Optional<Consultation> result = repository.findById(consultationId);

        if (!result.isPresent()) {
            throw new ConsultationNotFoundException(consultationId);
        }

        repository.deleteById(consultationId);
    }

}