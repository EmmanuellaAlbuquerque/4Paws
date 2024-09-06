package app.com._paws.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity(name = "prescriptions")
public class Prescription {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    String medicine;

    String dosageDescription;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "appointment_id")
    Appointment appointment;
}