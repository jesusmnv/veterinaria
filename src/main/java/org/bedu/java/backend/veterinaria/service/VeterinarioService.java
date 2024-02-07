package org.bedu.java.backend.veterinaria.service;

import java.util.List;
import java.util.Optional;

import org.bedu.java.backend.veterinaria.dto.veterinario.CreateVeterinarioDTO;
import org.bedu.java.backend.veterinaria.dto.veterinario.UpdateVeterinarioDTO;
import org.bedu.java.backend.veterinaria.dto.veterinario.VeterinarioDTO;
import org.bedu.java.backend.veterinaria.exception.VeterinarioNotFoundException;
import org.bedu.java.backend.veterinaria.mapper.VeterinarioMapper;
import org.bedu.java.backend.veterinaria.model.Veterinario;
import org.bedu.java.backend.veterinaria.repository.VeterinarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VeterinarioService {

    private VeterinarioRepository veterinarioRepository;
    private VeterinarioMapper mapper;

    @Autowired
    public VeterinarioService(VeterinarioRepository veterinarioRepository, VeterinarioMapper mapper) {
        this.veterinarioRepository = veterinarioRepository;
        this.mapper = mapper;
    }

    public List<VeterinarioDTO> findAll() {
        return mapper.toDTO(veterinarioRepository.findAll());
    }

    public VeterinarioDTO save(CreateVeterinarioDTO data) {
        Veterinario entity = veterinarioRepository.save(mapper.toModel(data));
        return mapper.toDTO(entity);
    }

    public void update(long veterinarioId, UpdateVeterinarioDTO data) throws VeterinarioNotFoundException {
        Optional<Veterinario> result = veterinarioRepository.findById(veterinarioId);

        if (!result.isPresent()) {
            throw new VeterinarioNotFoundException(veterinarioId);
        }

        Veterinario veterinario = result.get();
        mapper.update(veterinario, data);
        veterinarioRepository.save(veterinario);
    }

    public void deleteById(Long veterinarioId) throws VeterinarioNotFoundException {
        Optional<Veterinario> result = veterinarioRepository.findById(veterinarioId);

        if (!result.isPresent()) {
            throw new VeterinarioNotFoundException(veterinarioId);
        }

        veterinarioRepository.deleteById(veterinarioId);
    }

}