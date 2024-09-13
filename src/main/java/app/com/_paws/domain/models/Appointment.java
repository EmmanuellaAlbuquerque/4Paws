package app.com._paws.domain.models;

import app.com._paws.domain.dtos.AppointmentDTOForReceptionist;
import app.com._paws.utils.Identifiable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity(name = "appointments")
@NoArgsConstructor
public class Appointment implements Identifiable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "scheduled_date", nullable = false)
    private LocalDateTime scheduledDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "appointment_veterinarian",
            joinColumns = @JoinColumn(name = "appointment_id"),
            inverseJoinColumns = @JoinColumn(name = "veterinarian_id")
    )
    private List<Veterinarian> veterinarians;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;

    private String notes;

    @OneToMany(mappedBy = "appointment", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Exam> exams;

    @OneToMany(mappedBy = "appointment", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Prescription> prescriptions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_type_id", nullable = false)
    private AppointmentType appointmentType;

    public Appointment(AppointmentDTOForReceptionist appointmentDTOForReceptionist, AppointmentType appointmentType, List<Veterinarian> veterinarians, Pet pet) {
        this.appointmentType = appointmentType;
        this.scheduledDate = appointmentDTOForReceptionist.scheduledDate();
        this.veterinarians = veterinarians;
        this.pet = pet;
    }
}