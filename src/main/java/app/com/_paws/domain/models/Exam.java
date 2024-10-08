package app.com._paws.domain.models;

import app.com._paws.domain.dtos.exam.ExamDTO;
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

    @Column(name = "scheduled_date")
    LocalDateTime scheduledDate;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_type_id", nullable = false)
    ExamType examType;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id", nullable = false)
    Appointment appointment;

    public Exam(ExamDTO examDTO, ExamType examType, Appointment appointment) {
        this.examType = examType;
        this.appointment = appointment;

        this.id = examDTO.id();
        this.result = examDTO.result();
        this.scheduledDate = examDTO.scheduledDate();
    }
}