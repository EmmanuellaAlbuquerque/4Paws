package app.com._paws.domain.models;

import app.com._paws.domain.dtos.tutor.TutorDTO;
import app.com._paws.domain.dtos.tutor.TutorUpdateDTO;
import app.com._paws.utils.Identifiable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Entity(name = "tutors")
@NoArgsConstructor
public class Tutor implements Identifiable<UUID> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false, unique = true)
    private String cpf;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @OneToMany(mappedBy = "tutor", fetch = FetchType.LAZY)
    private List<Pet> pets;

    public Tutor(TutorDTO tutorDTO) {
        this.name = tutorDTO.name();
        this.phone = tutorDTO.phone();
        this.cpf = tutorDTO.cpf();
        this.address = new Address(tutorDTO.address());
    }
}