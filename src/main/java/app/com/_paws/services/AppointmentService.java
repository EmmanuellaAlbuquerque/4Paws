package app.com._paws.services;

import app.com._paws.domain.dtos.AppointmentDTOForReceptionist;
import app.com._paws.domain.dtos.AppointmentDTOForVeterinarian;
import app.com._paws.domain.dtos.ExamDTO;
import app.com._paws.domain.dtos.PrescriptionDTO;
import app.com._paws.domain.models.*;
import app.com._paws.domain.repositories.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    private final PrescriptionRepository prescriptionRepository;

    @Transactional
    public Appointment receptionistRegisterAppointment(AppointmentDTOForReceptionist appointmentDTOForReceptionist) {

        AppointmentType appointmentType = this.appointmentTypeRepository.findById(appointmentDTOForReceptionist.appointmentType())
                .orElseThrow(() -> new RuntimeException("Tipo de Consulta não encontrada!"));

        List<Veterinarian> veterinarians = this.veterinarianRepository.findAllById(appointmentDTOForReceptionist.veterinarians());

        Pet pet = this.petRepository.findById(appointmentDTOForReceptionist.pet())
                .orElseThrow(() -> new RuntimeException("Pet não encontrado!"));

        Appointment appointment = new Appointment(appointmentDTOForReceptionist, appointmentType, veterinarians, pet);

        Appointment newAppointment = this.appointmentRepository.save(appointment);

        return newAppointment;
    }

    public List<Appointment> findAllAppointmentsByVeterinarian(UUID veterinarianId) {
        return this.appointmentRepository
                .findByVeterinariansIdAndScheduledDateGreaterThanEqualOrderByScheduledDate(veterinarianId, LocalDateTime.now());
    }

    public Appointment findAppointmentByIdAndVeterinarianId(UUID veterinarianId, Integer appointmentId) {
        Appointment appointment = this.appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada!"));

        appointment.getVeterinarians().stream()
                .filter((vet -> vet.getId().equals(veterinarianId)))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Você não possui acesso a essa consulta!"));

        return appointment;
    }

    public Appointment veterinarianUpdateAppointment(AppointmentDTOForVeterinarian appointmentDTOForVeterinarian, UUID veterinarianId) {

        Appointment appointment = this.findAppointmentByIdAndVeterinarianId(veterinarianId, appointmentDTOForVeterinarian.id());
        appointment.setNotes(appointmentDTOForVeterinarian.notes());

        List<PrescriptionDTO> prescriptionDTOS = appointmentDTOForVeterinarian.prescriptions();

        List<Prescription> prescriptions = prescriptionDTOS.stream().map((prescriptionDTO -> {
            return new Prescription(prescriptionDTO, appointment);
        })).toList();

        this.prescriptionRepository.saveAll(prescriptions);

        List<ExamDTO> examsDTOS = appointmentDTOForVeterinarian.exams();

        List<Exam> exams = examsDTOS.stream().map((examDTO) -> {

            ExamType examType = this.examTypeRepository.findById(examDTO.examTypeId())
                    .orElseThrow(() -> new RuntimeException("Tipo do Exame não encontrado!"));

            return new Exam(examType, appointment);
        }).toList();

        this.examRepository.saveAll(exams);

        return this.appointmentRepository.save(appointment);
    }
}