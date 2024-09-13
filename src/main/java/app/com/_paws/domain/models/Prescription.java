package app.com._paws.domain.models;

import app.com._paws.domain.dtos.PrescriptionDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity(name = "prescriptions")
@NoArgsConstructor
public class Prescription {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(nullable = false)
    String medicine;

    @Column(name = "dosage_description", nullable = false)
    String dosageDescription;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id", nullable = false)
    Appointment appointment;

    public Prescription(PrescriptionDTO prescriptionDTO, Appointment appointment) {
        this.medicine = prescriptionDTO.medicine();
        this.dosageDescription = prescriptionDTO.dosageDescription();
        this.appointment = appointment;
    }
}