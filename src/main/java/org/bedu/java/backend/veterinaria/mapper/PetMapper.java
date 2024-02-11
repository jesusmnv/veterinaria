package org.bedu.java.backend.veterinaria.mapper;

import org.bedu.java.backend.veterinaria.dto.pet.CreatePetDTO;
import org.bedu.java.backend.veterinaria.dto.pet.PetDTO;
import org.bedu.java.backend.veterinaria.dto.pet.UpdatePetDTO;
import org.bedu.java.backend.veterinaria.model.Pet;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PetMapper {

    PetDTO toDTO(Pet model);

    List<PetDTO> toDTO(List<Pet> model);

    @Mapping(target = "id", ignore = true)
    Pet toModel(CreatePetDTO dto);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Pet pet, UpdatePetDTO data);

}