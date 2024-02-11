package org.bedu.java.backend.veterinaria.service;

import org.bedu.java.backend.veterinaria.dto.pet.CreatePetDTO;
import org.bedu.java.backend.veterinaria.dto.pet.PetDTO;
import org.bedu.java.backend.veterinaria.dto.pet.UpdatePetDTO;
import org.bedu.java.backend.veterinaria.exception.PetNotFoundException;
import org.bedu.java.backend.veterinaria.mapper.PetMapper;
import org.bedu.java.backend.veterinaria.model.Pet;
import org.bedu.java.backend.veterinaria.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    private PetRepository repository;
    private PetMapper mapper;

    @Autowired
    public PetService(PetRepository repository, PetMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<PetDTO> findAll() {
        return mapper.toDTO(repository.findAll());
    }

    public Optional<PetDTO> findById(Long id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    public PetDTO save(CreatePetDTO data) {
        Pet entity = repository.save(mapper.toModel(data));
        return mapper.toDTO(entity);
    }

    public void update(Long petId, UpdatePetDTO data) throws PetNotFoundException {
        Optional<Pet> result = repository.findById(petId);

        if (!result.isPresent()) {
            throw new PetNotFoundException(petId);
        }

        Pet pet = result.get();
        mapper.update(pet, data);
        repository.save(pet);
    }

    public void deleteById(Long id) throws PetNotFoundException {

        Optional<Pet> result = repository.findById(id);

        if (!result.isPresent()) {
            throw new PetNotFoundException(id);
        }

        repository.deleteById(id);
    }

}