package org.bedu.java.backend.veterinaria.service;

import org.bedu.java.backend.veterinaria.dto.mascota.CreateMascotaDTO;
import org.bedu.java.backend.veterinaria.dto.mascota.MascotaDTO;
import org.bedu.java.backend.veterinaria.dto.mascota.UpdateMascotaDTO;
import org.bedu.java.backend.veterinaria.exception.MascotaNotFoundException;
import org.bedu.java.backend.veterinaria.mapper.MascotaMapper;
import org.bedu.java.backend.veterinaria.model.Mascota;
import org.bedu.java.backend.veterinaria.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MascotaService {
    
    @Autowired
    private MascotaRepository repository;

    @Autowired
    private MascotaMapper mapper;

    public List<MascotaDTO> findAll() {
        return mapper.toDTO(repository.findAll());
    }

  
    public Optional<MascotaDTO> findById(Long id) {
        return repository.findById(id).map(mapper::toDTO);
    }

  
    public MascotaDTO save(CreateMascotaDTO data) {
        Mascota entity = repository.save(mapper.toModel(data));
        return mapper.toDTO(entity);
    }

    
    public void update(Long mascotaId, UpdateMascotaDTO data) throws MascotaNotFoundException {
        Optional<Mascota> result = repository.findById(mascotaId);

        if (!result.isPresent()) {
            throw new MascotaNotFoundException(mascotaId);
        }

        Mascota mascota = result.get();
        mapper.update(mascota, data);
        repository.save(mascota);
    }

    
    public void deleteById(Long id) throws MascotaNotFoundException {

        Optional<Mascota> result = repository.findById(id);

        if(!result.isPresent()) {
            throw new MascotaNotFoundException(id);
        }

        repository.deleteById(id);
    }

}