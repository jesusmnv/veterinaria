package org.bedu.java.backend.veterinaria.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
@ToString
@Entity
@Table(name = "pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 40)
    private String species;

    @Column(nullable = false, length = 60)
    private String breed;

    @Range(min = 1, max = 200)
    private int age;

    @DecimalMin(value = "0.01")
    private float height;

    @DecimalMin(value = "0.01")
    private float weight;

    @Column(nullable = false, length = 20)
    private String gender;

    @Column(nullable = false, length = 50)
    private String color;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private Owner owner;

}