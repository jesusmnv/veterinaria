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
@Table(name = "owners")
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 70)
    private String name;

    @Column(name = "p_last_name", nullable = false, length = 70)
    private String pLastName;

    @Column(name = "m_last_name", nullable = false, length = 70)
    private String mLastName;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(nullable = false, length = 15)
    private String cellPhone;

    @Column(nullable = false, length = 150)
    private String email;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false, length = 60)
    private String occupation;

}
