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

    @Column(nullable = false)
    private String neighborhood;

    @Column(nullable = false)
    private Long number;

    @Column(nullable = false)
    private String street;

    @JsonIgnore
    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Tutor tutor;
}