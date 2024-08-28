package app.com._paws.domain.models;

import app.com._paws.domain.enums.Sex;
import app.com._paws.domain.enums.Specie;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity(name = "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private Double weight;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    private String breed;

    @Enumerated(EnumType.STRING)
    private Specie specie;

    private LocalDateTime birthDate;

    @ManyToOne
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;

    @OneToMany(mappedBy = "pet", fetch = FetchType.LAZY)
    private List<Appointment> appointments;
}