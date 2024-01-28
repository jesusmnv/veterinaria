package org.bedu.java.backend.veterinaria.mapper;

import org.bedu.java.backend.veterinaria.dto.consulta.CreateConsultaDTO;
import org.bedu.java.backend.veterinaria.dto.consulta.ConsultaDTO;
import org.bedu.java.backend.veterinaria.dto.consulta.UpdateConsultaDTO;
import org.bedu.java.backend.veterinaria.model.Consulta;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ConsultaMapper {

    ConsultaDTO toDTO(Consulta model);

    List<ConsultaDTO> toDTO(List<Consulta> model);

    @Mapping(target = "id", ignore = true)
    Consulta toModel(CreateConsultaDTO dto);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Consulta consulta, UpdateConsultaDTO data);

}