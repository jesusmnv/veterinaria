package org.bedu.java.backend.veterinaria.mapper;

import org.bedu.java.backend.veterinaria.dto.consultation.CreateConsultationDTO;
import org.bedu.java.backend.veterinaria.dto.consultation.ConsultationDTO;
import org.bedu.java.backend.veterinaria.dto.consultation.UpdateConsultationDTO;
import org.bedu.java.backend.veterinaria.model.Consultation;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ConsultationMapper {

    ConsultationDTO toDTO(Consultation model);

    List<ConsultationDTO> toDTO(List<Consultation> model);

    @Mapping(target = "id", ignore = true)
    Consultation toModel(CreateConsultationDTO dto);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Consultation consultation, UpdateConsultationDTO data);

}