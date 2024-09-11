package app.com._paws.domain.models;

import app.com._paws.domain.dtos.ExamDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "exams")
@NoArgsConstructor
public class Exam {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    String result;

    LocalDateTime scheduledDate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "exam_type_id")
    ExamType examType;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "appointment_id")
    Appointment appointment;

    public Exam(ExamType examType, Appointment appointment) {
        this.examType = examType;
        this.appointment = appointment;
    }
}