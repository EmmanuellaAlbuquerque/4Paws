package app.com._paws.domain.models;

import app.com._paws.domain.dtos.VeterinarianDTO;
import app.com._paws.domain.enums.Specialty;
import app.com._paws.domain.enums.UF;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity(name = "veterinarians")
@NoArgsConstructor
@AllArgsConstructor
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

    public Veterinarian(VeterinarianDTO veterinarianDTO) {
        this.specialty = veterinarianDTO.specialty();
        this.crmv = veterinarianDTO.crmv();
        this.uf = veterinarianDTO.uf();
    }
}