package org.bedu.java.backend.veterinaria.service;

import org.bedu.java.backend.veterinaria.dto.owner.CreateOwnerDTO;
import org.bedu.java.backend.veterinaria.dto.owner.OwnerDTO;
import org.bedu.java.backend.veterinaria.dto.owner.UpdateOwnerDTO;
import org.bedu.java.backend.veterinaria.exception.OwnerNotFoundException;
import org.bedu.java.backend.veterinaria.mapper.OwnerMapper;
import org.bedu.java.backend.veterinaria.model.Owner;
import org.bedu.java.backend.veterinaria.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {

    private OwnerRepository repository;
    private OwnerMapper mapper;

    @Autowired
    public OwnerService(OwnerRepository repository, OwnerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<OwnerDTO> findAll() {
        return mapper.toDTO(repository.findAll());
    }

    public Optional<OwnerDTO> findById(Long id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    public OwnerDTO save(CreateOwnerDTO data) {
        Owner entity = repository.save(mapper.toModel(data));
        return mapper.toDTO(entity);
    }

    public void update(Long ownerId, UpdateOwnerDTO data) throws OwnerNotFoundException {
        Optional<Owner> result = repository.findById(ownerId);

        if (!result.isPresent()) {
            throw new OwnerNotFoundException(ownerId);
        }

        Owner owner = result.get();
        mapper.update(owner, data);
        repository.save(owner);
    }

    public void deleteById(Long id) throws OwnerNotFoundException {
        Optional<Owner> result = repository.findById(id);

        if (!result.isPresent()) {
            throw new OwnerNotFoundException(id);
        }

        repository.deleteById(id);
    }

}