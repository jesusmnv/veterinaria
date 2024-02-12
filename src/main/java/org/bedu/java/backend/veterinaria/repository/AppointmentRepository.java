package org.bedu.java.backend.veterinaria.repository;

import org.bedu.java.backend.veterinaria.model.Appointment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Long> {

    List<Appointment> findAll();

    List<Appointment> findByAppointmentDate(LocalDate appointmentDate);

}