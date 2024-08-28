package app.com._paws.domain.models;

import jakarta.persistence.*;

@Entity(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String neighborhood;

    private long number;

    private String street;

    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL)
    private Tutor tutor;
}
