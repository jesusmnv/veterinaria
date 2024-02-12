package org.bedu.java.backend.veterinaria.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.bedu.java.backend.veterinaria.model.Appointment;
import org.bedu.java.backend.veterinaria.model.Owner;
import org.bedu.java.backend.veterinaria.model.Vet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class AppointmentRepositoryTest {

  @Autowired
  private AppointmentRepository repository;

  @Autowired
  private TestEntityManager manager;

  @Test
  @DisplayName("Repository should be injected")
  void smokeTest() {
    assertNotNull(repository);
  }

  @Test
  @DisplayName("Repository should filter appointments by appointment date")
  void filterByConsultationDateTest() {
    Appointment appointment1 = createAppointment1();
    Appointment appointment2 = createAppointment2();
    Appointment appointment3 = createAppointment3();

    manager.persist(appointment1);
    manager.persist(appointment2);
    manager.persist(appointment3);

    List<Appointment> result;
    result = repository.findByAppointmentDate(LocalDate.parse("2025-05-05"));

    assertEquals(3, result.size());
  }

  private Appointment createAppointment1() {
    Appointment appointment = new Appointment();
    appointment.setAppointmentDate(LocalDate.parse("2025-05-05"));
    appointment.setAppointmentTime(LocalTime.parse("14:00"));
    appointment.setFirstAppointment(true);

    appointment.setAppointmentReason("Check");

    Owner owner = new Owner();
    owner.setId(7L);
    owner.setName("Marcos");
    owner.setPLastName("Solis");
    owner.setMLastName("Cruz");
    owner.setAddress("Centro 789");
    owner.setCellPhone("1234567890");
    owner.setEmail("marcos@example.com");
    owner.setBirthDate(LocalDate.parse("1980-08-08"));
    owner.setOccupation("Maestro");

    Vet vet = new Vet();
    vet.setId(7L);
    vet.setName("Sara");
    vet.setPLastName("Rodríguez");
    vet.setMLastName("Santos");
    vet.setBirthDate(LocalDate.parse("1987-11-28"));
    vet.setCellPhone("555-789-0123");
    vet.setEmail("sara@email.com");
    vet.setSpecialty("Cardiología");
    vet.setEntryTime(LocalTime.parse("10:00"));
    vet.setExitTime(LocalTime.parse("18:00"));

    appointment.setOwner(manager.merge(owner));
    appointment.setVet(manager.merge(vet));

    return appointment;
  }

  private Appointment createAppointment2() {
    Appointment appointment = new Appointment();
    appointment.setAppointmentDate(LocalDate.parse("2025-05-05"));
    appointment.setAppointmentTime(LocalTime.parse("15:00"));
    appointment.setFirstAppointment(true);

    appointment.setAppointmentReason("Check");

    Owner owner = new Owner();
    owner.setId(7L);
    owner.setName("María");
    owner.setPLastName("Solis");
    owner.setMLastName("Cruz");
    owner.setAddress("Centro 789");
    owner.setCellPhone("1234567890");
    owner.setEmail("maría@example.com");
    owner.setBirthDate(LocalDate.parse("1980-08-08"));
    owner.setOccupation("Comerciante");

    Vet vet = new Vet();
    vet.setId(7L);
    vet.setName("Fernanda");
    vet.setPLastName("Rodríguez");
    vet.setMLastName("Santos");
    vet.setBirthDate(LocalDate.parse("1987-11-28"));
    vet.setCellPhone("555-789-0123");
    vet.setEmail("fernanda@email.com");
    vet.setSpecialty("Cardiología");
    vet.setEntryTime(LocalTime.parse("10:00"));
    vet.setExitTime(LocalTime.parse("18:00"));

    appointment.setOwner(manager.merge(owner));
    appointment.setVet(manager.merge(vet));

    return appointment;
  }

  private Appointment createAppointment3() {
    Appointment appointment = new Appointment();
    appointment.setAppointmentDate(LocalDate.parse("2025-05-05"));
    appointment.setAppointmentTime(LocalTime.parse("16:00"));
    appointment.setFirstAppointment(true);

    appointment.setAppointmentReason("Check");

    Owner owner = new Owner();
    owner.setId(7L);
    owner.setName("Mónica");
    owner.setPLastName("Solis");
    owner.setMLastName("Cruz");
    owner.setAddress("Centro 789");
    owner.setCellPhone("1234567890");
    owner.setEmail("monica@example.com");
    owner.setBirthDate(LocalDate.parse("1980-08-08"));
    owner.setOccupation("Abogado");

    Vet vet = new Vet();
    vet.setId(7L);
    vet.setName("Guillermo");
    vet.setPLastName("Rodríguez");
    vet.setMLastName("Santos");
    vet.setBirthDate(LocalDate.parse("1987-11-28"));
    vet.setCellPhone("555-789-0123");
    vet.setEmail("guillermo@email.com");
    vet.setSpecialty("Cardiología");
    vet.setEntryTime(LocalTime.parse("10:00"));
    vet.setExitTime(LocalTime.parse("18:00"));

    appointment.setOwner(manager.merge(owner));
    appointment.setVet(manager.merge(vet));

    return appointment;
  }

}
