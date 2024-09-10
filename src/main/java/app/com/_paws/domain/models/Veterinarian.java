package app.com._paws.domain.models;

import app.com._paws.domain.dtos.VeterinarianDTO;
import app.com._paws.domain.enums.Specialty;
import app.com._paws.domain.enums.UF;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "veterinarians")
@NoArgsConstructor
@AllArgsConstructor
public class Veterinarian extends UserProfile {

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
        super.setEmail(veterinarianDTO.email());
        super.setName(veterinarianDTO.name());
        super.setCpf(veterinarianDTO.cpf());

        this.specialty = veterinarianDTO.specialty();
        this.crmv = veterinarianDTO.crmv();
        this.uf = veterinarianDTO.uf();
    }
}