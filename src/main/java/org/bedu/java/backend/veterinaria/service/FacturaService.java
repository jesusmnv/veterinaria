package org.bedu.java.backend.veterinaria.service;

import lombok.extern.slf4j.Slf4j;
import org.bedu.java.backend.veterinaria.dto.factura.CreateFacturaDTO;
import org.bedu.java.backend.veterinaria.dto.factura.FacturaDTO;
import org.bedu.java.backend.veterinaria.dto.factura.UpdateFacturaDTO;
import org.bedu.java.backend.veterinaria.exception.FacturaNotFoundException;
import org.bedu.java.backend.veterinaria.mapper.FacturaMapper;
import org.bedu.java.backend.veterinaria.model.Factura;
import org.bedu.java.backend.veterinaria.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class FacturaService {

    @Autowired
    private FacturaRepository repository;

    @Autowired
    private FacturaMapper mapper;

    public List<FacturaDTO> findAll() {
        return mapper.toDTO(repository.findAll());
    }

    public Optional<FacturaDTO> findById(Long id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    public FacturaDTO save(CreateFacturaDTO data) {
        Factura temp = mapper.toModel(data);
        temp.setIva(data.getSubtotal() * 0.16F);
        temp.setTotal(temp.getIva() + data.getSubtotal());

        Factura entity = repository.save(temp);

        return mapper.toDTO(entity);
    }

    public void update(Long facturaId, UpdateFacturaDTO data) throws FacturaNotFoundException {
        Optional<Factura> result = repository.findById(facturaId);

        if (!result.isPresent()) {
            throw new FacturaNotFoundException(facturaId);
        }

        Factura factura = result.get();

        UpdateFacturaDTO temp = data;
        log.error("Datos: " + temp);
        temp.setSubtotal(data.getSubtotal());
        temp.setIva(temp.getSubtotal() * 0.16F);
        float total = temp.getIva() + temp.getSubtotal();
        temp.setTotal(total);

        log.error("============TOTAL===============: " + total);
        mapper.update(factura, temp);
        repository.save(factura);
    }

    public void deleteById(Long id) throws FacturaNotFoundException {

        Optional<Factura> result = repository.findById(id);

        if (!result.isPresent()) {
            throw new FacturaNotFoundException(id);
        }

        repository.deleteById(id);
    }

}