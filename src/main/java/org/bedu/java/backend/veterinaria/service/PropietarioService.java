package org.bedu.java.backend.veterinaria.service;

import org.bedu.java.backend.veterinaria.dto.propietario.CreatePropietarioDTO;
import org.bedu.java.backend.veterinaria.dto.propietario.PropietarioDTO;
import org.bedu.java.backend.veterinaria.dto.propietario.UpdatePropietarioDTO;
import org.bedu.java.backend.veterinaria.exception.PropietarioNotFoundException;
import org.bedu.java.backend.veterinaria.mapper.PropietarioMapper;
import org.bedu.java.backend.veterinaria.model.Owner;
import org.bedu.java.backend.veterinaria.repository.PropietarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropietarioService {

    private PropietarioRepository repository;
    private PropietarioMapper mapper;

    @Autowired
    public PropietarioService(PropietarioRepository repository, PropietarioMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<PropietarioDTO> findAll() {
        return mapper.toDTO(repository.findAll());
    }

    public Optional<PropietarioDTO> findById(Long id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    public PropietarioDTO save(CreatePropietarioDTO data) {
        Owner entity = repository.save(mapper.toModel(data));
        return mapper.toDTO(entity);
    }

    public void update(Long propietarioId, UpdatePropietarioDTO data) throws PropietarioNotFoundException {
        Optional<Owner> result = repository.findById(propietarioId);

        if (!result.isPresent()) {
            throw new PropietarioNotFoundException(propietarioId);
        }

        Owner propietario = result.get();
        mapper.update(propietario, data);
        repository.save(propietario);
    }

    public void deleteById(Long id) throws PropietarioNotFoundException {
        Optional<Owner> result = repository.findById(id);

        if (!result.isPresent()) {
            throw new PropietarioNotFoundException(id);
        }

        repository.deleteById(id);
    }

}