package app.com._paws.domain.models;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "services")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private Double price;

    @OneToMany(mappedBy = "service", fetch = FetchType.LAZY)
    private List<Appointment> appointments;
}
