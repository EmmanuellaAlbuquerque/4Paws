package app.com._paws.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String neighborhood;

    private Long number;

    private String street;

    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL)
    private Tutor tutor;
}
