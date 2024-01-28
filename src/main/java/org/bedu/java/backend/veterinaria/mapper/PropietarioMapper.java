package org.bedu.java.backend.veterinaria.mapper;

import org.bedu.java.backend.veterinaria.dto.propietario.CreatePropietarioDTO;
import org.bedu.java.backend.veterinaria.dto.propietario.PropietarioDTO;
import org.bedu.java.backend.veterinaria.dto.propietario.UpdatePropietarioDTO;
import org.bedu.java.backend.veterinaria.model.Propietario;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PropietarioMapper {

    PropietarioDTO toDTO(Propietario model);

    List<PropietarioDTO> toDTO(List<Propietario> model);

    @Mapping(target = "id", ignore = true)
    Propietario toModel(CreatePropietarioDTO dto);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Propietario propietario, UpdatePropietarioDTO data);

}