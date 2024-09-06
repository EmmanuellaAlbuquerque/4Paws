package app.com._paws.domain.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity(name = "prescriptions")
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    String medicine;

    String dosageDescription;

    @ManyToOne
    @JoinColumn(name = "appointment_id")
    Appointment appointment;
}