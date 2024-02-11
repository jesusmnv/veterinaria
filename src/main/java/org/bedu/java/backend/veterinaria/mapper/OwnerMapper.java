package org.bedu.java.backend.veterinaria.mapper;

import org.bedu.java.backend.veterinaria.dto.owner.CreateOwnerDTO;
import org.bedu.java.backend.veterinaria.dto.owner.OwnerDTO;
import org.bedu.java.backend.veterinaria.dto.owner.UpdateOwnerDTO;
import org.bedu.java.backend.veterinaria.model.Owner;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface OwnerMapper {

    OwnerDTO toDTO(Owner model);

    List<OwnerDTO> toDTO(List<Owner> model);

    @Mapping(target = "id", ignore = true)
    Owner toModel(CreateOwnerDTO dto);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Owner owner, UpdateOwnerDTO data);

}