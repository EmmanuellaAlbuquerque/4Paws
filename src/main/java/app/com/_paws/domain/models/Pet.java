package app.com._paws.domain.models;

import app.com._paws.domain.dtos.PetDTO;
import app.com._paws.domain.enums.Sex;
import app.com._paws.domain.enums.Specie;
import app.com._paws.utils.Identifiable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Entity(name = "pets")
@NoArgsConstructor
public class Pet implements Identifiable<UUID> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    private Double weight;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    private String breed;

    @Enumerated(EnumType.STRING)
    private Specie specie;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutor_id", nullable = false)
    private Tutor tutor;

    @JsonIgnore
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