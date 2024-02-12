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
    @Mapping(target = "consultationDate", source = "consultationDateU")
    @Mapping(target = "diagnosis", source = "diagnosisU")
    @Mapping(target = "prescribedTreatment", source = "prescribedTreatmentU")
    @Mapping(target = "observations", source = "observationsU")
    @Mapping(target = "pet", source = "petU")
    @Mapping(target = "vet", source = "vetU")
    void update(@MappingTarget Consultation consultation, UpdateConsultationDTO data);

}