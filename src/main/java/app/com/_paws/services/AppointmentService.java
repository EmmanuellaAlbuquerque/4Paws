package app.com._paws.services;

import app.com._paws.domain.dtos.AppointmentDTO;
import app.com._paws.domain.models.*;
import app.com._paws.domain.repositories.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentTypeRepository appointmentTypeRepository;
    private final VeterinarianRepository veterinarianRepository;
    private final PetRepository petRepository;
    private final ExamTypeRepository examTypeRepository;
    private final ExamRepository examRepository;

    @Transactional
    public Appointment registerAppointment(AppointmentDTO appointmentDTO) {

        AppointmentType appointmentType = this.appointmentTypeRepository.findById(appointmentDTO.appointmentType())
                .orElseThrow(() -> new RuntimeException("Tipo de Consulta não encontrada!"));

        List<Veterinarian> veterinarians = this.veterinarianRepository.findAllById(appointmentDTO.veterinarians());

        Pet pet = this.petRepository.findById(appointmentDTO.pet())
                .orElseThrow(() -> new RuntimeException("Pet não encontrado!"));

        Appointment appointment = new Appointment(appointmentDTO, appointmentType, veterinarians, pet);

        appointment.getPrescriptions().forEach((prescription) -> {
            prescription.setAppointment(appointment);
        });

        Appointment newAppointment = this.appointmentRepository.save(appointment);

        List<Exam> exams = appointmentDTO.examTypes().stream().map((examTypeId) -> {

            ExamType examType = this.examTypeRepository.findById(examTypeId)
                    .orElseThrow(() -> new RuntimeException("Tipo do Exame não encontrado!"));

            return new Exam(examType, newAppointment);
        }).toList();

        this.examRepository.saveAll(exams);

        return newAppointment;
    }

    public List<Appointment> findAllAppointmentsByVeterinarian(UUID veterinarianId) {
        return this.appointmentRepository.findByVeterinariansIdOrderByScheduledDate(veterinarianId);
    }
}