package org.bedu.java.backend.veterinaria.service;

import org.bedu.java.backend.veterinaria.dto.cita.CitaDTO;
import org.bedu.java.backend.veterinaria.dto.cita.CreateCitaDTO;
import org.bedu.java.backend.veterinaria.dto.cita.UpdateCitaDTO;
import org.bedu.java.backend.veterinaria.exception.CitaNotFoundException;
import org.bedu.java.backend.veterinaria.mapper.CitaMapper;
import org.bedu.java.backend.veterinaria.model.Cita;
import org.bedu.java.backend.veterinaria.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitaService {

    private CitaRepository repository;
    private CitaMapper mapper;

    @Autowired
    public CitaService(CitaRepository repository, CitaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<CitaDTO> findAll() {
        return mapper.toDTO(repository.findAll());
    }

    public Optional<CitaDTO> findById(Long id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    public CitaDTO save(CreateCitaDTO data) {
        Cita entity = repository.save(mapper.toModel(data));
        return mapper.toDTO(entity);
    }

    public void update(Long citaId, UpdateCitaDTO data) throws CitaNotFoundException {
        Optional<Cita> result = repository.findById(citaId);

        if (!result.isPresent()) {
            throw new CitaNotFoundException(citaId);
        }

        Cita cita = result.get();
        mapper.update(cita, data);
        repository.save(cita);
    }

    public void deleteById(Long citaId) throws CitaNotFoundException {

        Optional<Cita> result = repository.findById(citaId);

        if (!result.isPresent()) {
            throw new CitaNotFoundException(citaId);
        }

        repository.deleteById(citaId);
    }

}