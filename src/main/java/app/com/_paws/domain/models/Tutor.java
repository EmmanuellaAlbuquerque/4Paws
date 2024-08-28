package app.com._paws.domain.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity(name = "tutors")
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String phone;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "tutor", fetch = FetchType.LAZY)
    private List<Pet> pets;
}
