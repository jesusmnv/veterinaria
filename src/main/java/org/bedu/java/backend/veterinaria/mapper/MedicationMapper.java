package org.bedu.java.backend.veterinaria.mapper;

import org.bedu.java.backend.veterinaria.dto.medication.CreateMedicationDTO;
import org.bedu.java.backend.veterinaria.dto.medication.MedicationDTO;
import org.bedu.java.backend.veterinaria.dto.medication.UpdateMedicationDTO;
import org.bedu.java.backend.veterinaria.model.Medication;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MedicationMapper {

    MedicationDTO toDTO(Medication model);

    List<MedicationDTO> toDTO(List<Medication> model);

    @Mapping(target = "id", ignore = true)
    Medication toModel(CreateMedicationDTO dto);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Medication medication, UpdateMedicationDTO data);

}