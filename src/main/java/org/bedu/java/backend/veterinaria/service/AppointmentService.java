package org.bedu.java.backend.veterinaria.service;

import org.bedu.java.backend.veterinaria.dto.appointment.AppointmentDTO;
import org.bedu.java.backend.veterinaria.dto.appointment.CreateAppointmentDTO;
import org.bedu.java.backend.veterinaria.dto.appointment.UpdateAppointmentDTO;
import org.bedu.java.backend.veterinaria.exception.AppointmentNotFoundException;
import org.bedu.java.backend.veterinaria.mapper.AppointmentMapper;
import org.bedu.java.backend.veterinaria.model.Appointment;
import org.bedu.java.backend.veterinaria.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private AppointmentRepository repository;
    private AppointmentMapper mapper;

    @Autowired
    public AppointmentService(AppointmentRepository repository, AppointmentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<AppointmentDTO> findAll() {
        return mapper.toDTO(repository.findAll());
    }

    public Optional<AppointmentDTO> findById(Long id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    public AppointmentDTO save(CreateAppointmentDTO data) {
        Appointment entity = repository.save(mapper.toModel(data));
        return mapper.toDTO(entity);
    }

    public void update(Long appointmentId, UpdateAppointmentDTO data) throws AppointmentNotFoundException {
        Optional<Appointment> result = repository.findById(appointmentId);

        if (!result.isPresent()) {
            throw new AppointmentNotFoundException(appointmentId);
        }

        Appointment appointment = result.get();
        mapper.update(appointment, data);
        repository.save(appointment);
    }

    public void deleteById(Long appointmentId) throws AppointmentNotFoundException {

        Optional<Appointment> result = repository.findById(appointmentId);

        if (!result.isPresent()) {
            throw new AppointmentNotFoundException(appointmentId);
        }

        repository.deleteById(appointmentId);
    }

}