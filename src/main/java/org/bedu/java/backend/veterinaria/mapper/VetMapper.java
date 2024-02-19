package org.bedu.java.backend.veterinaria.mapper;

import java.util.List;

import org.bedu.java.backend.veterinaria.dto.vet.CreateVetDTO;
import org.bedu.java.backend.veterinaria.dto.vet.UpdateVetDTO;
import org.bedu.java.backend.veterinaria.dto.vet.VetDTO;
import org.bedu.java.backend.veterinaria.model.Vet;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface VetMapper {

    VetDTO toDTO(Vet model);

    List<VetDTO> toDTO(List<Vet> model);

    @Mapping(target = "id", ignore = true)
    Vet toModel(CreateVetDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "nameVet", source = "nameU")
    @Mapping(target = "surnameVet", source = "surnameU")
    @Mapping(target = "maternalSurnameVet", source = "maternalSurnameU")
    @Mapping(target = "birthdate", source = "birthdateU")
    @Mapping(target = "cellphone", source = "cellphoneU")
    @Mapping(target = "email", source = "emailU")
    @Mapping(target = "specialty", source = "specialtyU")
    @Mapping(target = "entryTime", source = "entryTimeU")
    @Mapping(target = "exitTime", source = "exitTimeU")
    void update(@MappingTarget Vet vet, UpdateVetDTO data);
}