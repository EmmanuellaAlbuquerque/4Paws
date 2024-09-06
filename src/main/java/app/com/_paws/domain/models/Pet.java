package app.com._paws.domain.models;

import app.com._paws.domain.dtos.PetDTO;
import app.com._paws.domain.enums.Sex;
import app.com._paws.domain.enums.Specie;
import app.com._paws.utils.Identifiable;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Entity(name = "pets")
public class Pet implements Identifiable<UUID> {

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

    private LocalDate birthDate;

    @ManyToOne
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;

    @OneToMany(mappedBy = "pet", fetch = FetchType.LAZY)
    private List<Appointment> appointments;

    public Pet(PetDTO petDTO, Tutor tutor) {
        this.name = petDTO.name();
        this.weight = petDTO.weight();
        this.sex = petDTO.sex();
        this.breed = petDTO.breed();
        this.specie = petDTO.specie();
        this.birthDate = petDTO.birthDate();
        this.tutor = tutor;
    }
}