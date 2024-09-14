package app.com._paws.domain.models;

import app.com._paws.domain.dtos.AddressDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "addresses")
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String neighborhood;

    @Column(nullable = false)
    private Long number;

    @Column(nullable = false)
    private String street;

    @JsonIgnore
    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Tutor tutor;

    public Address(AddressDTO addressDTO) {
        this.neighborhood = addressDTO.neighborhood();
        this.number = addressDTO.number();
        this.street = addressDTO.street();
    }
}