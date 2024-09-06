package app.com._paws.domain.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "exams")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    String result;

    LocalDateTime scheduledDate;

    @ManyToOne
    @JoinColumn(name = "exam_type_id")
    ExamType examType;

    @ManyToOne
    @JoinColumn(name = "appointment_id")
    Appointment appointment;
}