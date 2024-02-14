package org.bedu.java.backend.veterinaria.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "owner")
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 70)
    private String name;

    @Column(nullable = false, length = 70)
    private String surname;

    @Column(name = "maternal_surname", nullable = false, length = 70)
    private String maternalSurname;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(nullable = false, length = 15)
    private String cellphone;

    @Column(nullable = false, length = 150)
    private String email;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Column(nullable = false, length = 60)
    private String occupation;

}
