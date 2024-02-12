package org.bedu.java.backend.veterinaria.service;

import java.util.List;
import java.util.Optional;

import org.bedu.java.backend.veterinaria.dto.vet.CreateVetDTO;
import org.bedu.java.backend.veterinaria.dto.vet.UpdateVetDTO;
import org.bedu.java.backend.veterinaria.dto.vet.VetDTO;
import org.bedu.java.backend.veterinaria.exception.VetNotFoundException;
import org.bedu.java.backend.veterinaria.mapper.VetMapper;
import org.bedu.java.backend.veterinaria.model.Vet;
import org.bedu.java.backend.veterinaria.repository.VetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VetService {

    private VetRepository vetRepository;
    private VetMapper mapper;

    @Autowired
    public VetService(VetRepository vetRepository, VetMapper mapper) {
        this.vetRepository = vetRepository;
        this.mapper = mapper;
    }

    public List<VetDTO> findAll() {
        return mapper.toDTO(vetRepository.findAll());
    }

    public VetDTO save(CreateVetDTO data) {
        Vet entity = vetRepository.save(mapper.toModel(data));
        return mapper.toDTO(entity);
    }

    public void update(long vetId, UpdateVetDTO data) throws VetNotFoundException {
        Optional<Vet> result = vetRepository.findById(vetId);

        if (!result.isPresent()) {
            throw new VetNotFoundException(vetId);
        }

        Vet vet = result.get();
        mapper.update(vet, data);
        vetRepository.save(vet);
    }

    public void deleteById(Long vetId) throws VetNotFoundException {
        Optional<Vet> result = vetRepository.findById(vetId);

        if (!result.isPresent()) {
            throw new VetNotFoundException(vetId);
        }

        vetRepository.deleteById(vetId);
    }

}