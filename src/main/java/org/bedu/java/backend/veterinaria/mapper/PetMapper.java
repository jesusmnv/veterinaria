package org.bedu.java.backend.veterinaria.mapper;

import org.bedu.java.backend.veterinaria.dto.pet.CreatePetDTO;
import org.bedu.java.backend.veterinaria.dto.pet.PetDTO;
import org.bedu.java.backend.veterinaria.dto.pet.UpdatePetDTO;
import org.bedu.java.backend.veterinaria.model.Pet;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PetMapper {

    PetDTO toDTO(Pet model);

    List<PetDTO> toDTO(List<Pet> model);

    @Mapping(target = "id", ignore = true)
    Pet toModel(CreatePetDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "nameU")
    @Mapping(target = "species", source = "speciesU")
    @Mapping(target = "breed", source = "breedU")
    @Mapping(target = "age", source = "ageU")
    @Mapping(target = "height", source = "heightU")
    @Mapping(target = "weight", source = "weightU")
    @Mapping(target = "gender", source = "genderU")
    @Mapping(target = "color", source = "colorU")
    @Mapping(target = "owner", source = "ownerU")
    void update(@MappingTarget Pet pet, UpdatePetDTO data);

}