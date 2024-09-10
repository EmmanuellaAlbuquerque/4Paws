package app.com._paws.domain.models;

import app.com._paws.domain.dtos.AppointmentDTO;
import app.com._paws.utils.Identifiable;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private String notes;

    @OneToMany(mappedBy = "appointment", cascade = CascadeType.PERSIST)
    private List<Exam> exams;

    @OneToMany(mappedBy = "appointment", cascade = CascadeType.PERSIST)
    private List<Prescription> prescriptions;

    @ManyToOne
    @JoinColumn(name = "appointment_type_id")
    private AppointmentType appointmentType;

    public Appointment(AppointmentDTO appointmentDTO, AppointmentType appointmentType, List<Veterinarian> veterinarians, Pet pet) {
        this.appointmentType = appointmentType;
        this.scheduledDate = appointmentDTO.scheduledDate();
        this.veterinarians = veterinarians;
        this.pet = pet;
        this.notes = appointmentDTO.notes();
        this.prescriptions = appointmentDTO.prescriptions();
    }
}