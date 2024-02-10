package org.bedu.java.backend.veterinaria.mapper;

import org.bedu.java.backend.veterinaria.dto.mascota.CreateMascotaDTO;
import org.bedu.java.backend.veterinaria.dto.mascota.MascotaDTO;
// import org.bedu.java.backend.veterinaria.dto.mascota.UpdateMascotaDTO;
import org.bedu.java.backend.veterinaria.model.Mascota;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MascotaMapper {

    MascotaDTO toDTO(Mascota model);

    List<MascotaDTO> toDTO(List<Mascota> model);

    @Mapping(target = "id", ignore = true)
    Mascota toModel(CreateMascotaDTO dto);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Mascota mascota, CreateMascotaDTO data);

}