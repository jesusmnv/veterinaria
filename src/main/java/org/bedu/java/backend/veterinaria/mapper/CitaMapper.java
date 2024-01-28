package org.bedu.java.backend.veterinaria.mapper;

import org.bedu.java.backend.veterinaria.dto.cita.CitaDTO;
import org.bedu.java.backend.veterinaria.dto.cita.CreateCitaDTO;
import org.bedu.java.backend.veterinaria.dto.cita.UpdateCitaDTO;
import org.bedu.java.backend.veterinaria.model.Cita;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CitaMapper {

    CitaDTO toDTO(Cita model);

    List<CitaDTO> toDTO(List<Cita> model);

    @Mapping(target = "id", ignore = true)
    Cita toModel(CreateCitaDTO dto);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Cita cita, UpdateCitaDTO data);

}