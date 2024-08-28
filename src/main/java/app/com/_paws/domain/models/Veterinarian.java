package app.com._paws.domain.models;

import app.com._paws.domain.enums.Specialty;
import app.com._paws.domain.enums.UF;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity(name = "veterinarians")
public class Veterinarian {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    private long crmv;

    @Enumerated(EnumType.STRING)
    private UF uf;

    @ManyToMany(mappedBy = "veterinarians", fetch = FetchType.LAZY)
    private List<Appointment> appointments;
}