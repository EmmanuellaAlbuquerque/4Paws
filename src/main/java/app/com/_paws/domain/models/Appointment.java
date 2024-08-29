package app.com._paws.domain.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private LocalDateTime scheduledDate;

    @ManyToMany
    @JoinTable(
            name = "appointment_veterinarian",
            joinColumns = @JoinColumn(name = "appointment_id"),
            inverseJoinColumns = @JoinColumn(name = "veterinarian_id")
    )
    private List<Veterinarian> veterinarians;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    private String note;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private ServiceProvided serviceProvided;
}
