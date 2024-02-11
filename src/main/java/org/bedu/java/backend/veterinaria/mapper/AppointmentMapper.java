package org.bedu.java.backend.veterinaria.mapper;

import org.bedu.java.backend.veterinaria.dto.appointment.AppointmentDTO;
import org.bedu.java.backend.veterinaria.dto.appointment.CreateAppointmentDTO;
import org.bedu.java.backend.veterinaria.dto.appointment.UpdateAppointmentDTO;
import org.bedu.java.backend.veterinaria.model.Appointment;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AppointmentMapper {

    AppointmentDTO toDTO(Appointment model);

    List<AppointmentDTO> toDTO(List<Appointment> model);

    @Mapping(target = "id", ignore = true)
    Appointment toModel(CreateAppointmentDTO dto);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Appointment appointment, UpdateAppointmentDTO data);

}