package org.bedu.java.backend.veterinaria.mapper;

import org.bedu.java.backend.veterinaria.dto.factura.CreateFacturaDTO;
import org.bedu.java.backend.veterinaria.dto.factura.FacturaDTO;
import org.bedu.java.backend.veterinaria.dto.factura.UpdateFacturaDTO;
import org.bedu.java.backend.veterinaria.model.Factura;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface FacturaMapper {

    FacturaDTO toDTO(Factura model);

    List<FacturaDTO> toDTO(List<Factura> model);

    @Mapping(target = "id", ignore = true)
    Factura toModel(CreateFacturaDTO dto);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Factura factura, UpdateFacturaDTO data);

}