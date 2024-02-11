package org.bedu.java.backend.veterinaria.mapper;

import org.bedu.java.backend.veterinaria.dto.propietario.CreatePropietarioDTO;
import org.bedu.java.backend.veterinaria.dto.propietario.PropietarioDTO;
import org.bedu.java.backend.veterinaria.dto.propietario.UpdatePropietarioDTO;
import org.bedu.java.backend.veterinaria.model.Owner;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PropietarioMapper {

    PropietarioDTO toDTO(Owner model);

    List<PropietarioDTO> toDTO(List<Owner> model);

    @Mapping(target = "id", ignore = true)
    Owner toModel(CreatePropietarioDTO dto);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Owner propietario, UpdatePropietarioDTO data);

}