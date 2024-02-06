package org.bedu.java.backend.veterinaria.service;

import org.bedu.java.backend.veterinaria.dto.consulta.ConsultaDTO;
import org.bedu.java.backend.veterinaria.dto.consulta.CreateConsultaDTO;
import org.bedu.java.backend.veterinaria.dto.consulta.UpdateConsultaDTO;
import org.bedu.java.backend.veterinaria.exception.ConsultaNotFoundException;
import org.bedu.java.backend.veterinaria.mapper.ConsultaMapper;
import org.bedu.java.backend.veterinaria.model.Consulta;
import org.bedu.java.backend.veterinaria.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    private ConsultaRepository repository;

    private ConsultaMapper mapper;

    @Autowired
    public ConsultaService(ConsultaRepository repository, ConsultaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<ConsultaDTO> findAll() {
        return mapper.toDTO(repository.findAll());
    }

    public Optional<ConsultaDTO> findById(Long id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    public ConsultaDTO save(CreateConsultaDTO data) {
        Consulta entity = repository.save(mapper.toModel(data));
        return  mapper.toDTO(entity);
    }

    public void update(Long consultaId, UpdateConsultaDTO data) throws ConsultaNotFoundException {
        Optional<Consulta> result = repository.findById(consultaId);

        if(!result.isPresent()) {
            throw new ConsultaNotFoundException(consultaId);
        }

        Consulta consulta = result.get();
        mapper.update(consulta, data);
        repository.save(consulta);
    }

    public void deleteById(Long consultaId) throws ConsultaNotFoundException {

        Optional<Consulta> result = repository.findById(consultaId);

        if(!result.isPresent()) {
            throw new ConsultaNotFoundException(consultaId);
        }

        repository.deleteById(consultaId);
    }

}