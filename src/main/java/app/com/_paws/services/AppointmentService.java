package app.com._paws.services;

import app.com._paws.domain.dtos.appointment.AppointmentDTOForReceptionist;
import app.com._paws.domain.dtos.appointment.AppointmentDTOForVeterinarian;
import app.com._paws.domain.dtos.exam.ExamDTO;
import app.com._paws.domain.dtos.PrescriptionDTO;
import app.com._paws.domain.models.*;
import app.com._paws.domain.repositories.*;
import app.com._paws.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
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

    public Appointment findAppointmentById(Integer id) {
        return this.appointmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Consulta não encontrada!"));
    }

    public List<Appointment> findAllAppointments() {
        return this.appointmentRepository.findAll(Sort.by("scheduledDate"));
    }

    public void deleteAppointmentById(Integer id) {
        this.appointmentRepository.deleteById(id);
    }

    public void updateAppointmentById(Integer appointmentId, AppointmentDTOForReceptionist appointmentDTOForReceptionist) {
        appointmentDTOForReceptionist.setId(appointmentId);
        this.receptionistRegisterAppointment(appointmentDTOForReceptionist);
    }

    @Transactional
    public Appointment receptionistRegisterAppointment(AppointmentDTOForReceptionist appointmentDTOForReceptionist) {
        AppointmentType appointmentType = this.appointmentTypeRepository.findById(appointmentDTOForReceptionist.getAppointmentType())
                .orElseThrow(() -> new NotFoundException("Tipo de Consulta não encontrada!"));

        List<Veterinarian> veterinarians = this.veterinarianRepository.findAllById(appointmentDTOForReceptionist.getVeterinarians());

        if (veterinarians.size() != appointmentDTOForReceptionist.getVeterinarians().size()) {
            throw new NotFoundException("Veterinário(s) não encontrado(s)!");
        }

        Pet pet = this.petRepository.findById(appointmentDTOForReceptionist.getPet())
                .orElseThrow(() -> new NotFoundException("Pet não encontrado!"));

        Appointment appointment = new Appointment(appointmentDTOForReceptionist, appointmentType, veterinarians, pet);

        Appointment newAppointment = this.appointmentRepository.save(appointment);

        return newAppointment;
    }

    public List<Appointment> findAllAppointmentsByVeterinarian(UUID veterinarianId) {
        return this.appointmentRepository
                .findByVeterinariansIdAndScheduledDateGreaterThanEqualOrderByScheduledDate(veterinarianId, LocalDateTime.now());
    }

    public Appointment findAppointmentByIdAndVeterinarianId(UUID veterinarianId, Integer appointmentId) {
        Appointment appointment = this.appointmentRepository.findByIdAndVeterinariansId(appointmentId, veterinarianId)
                .orElseThrow(() -> new NotFoundException("Consulta não encontrada!"));

        return appointment;
    }

    @Transactional
    public Appointment veterinarianUpdateAppointment(AppointmentDTOForVeterinarian appointmentDTOForVeterinarian, Integer appointmentId, UUID veterinarianId) {

        Appointment appointment = this.findAppointmentByIdAndVeterinarianId(veterinarianId, appointmentId);
        appointment.setNotes(appointmentDTOForVeterinarian.notes());

        List<PrescriptionDTO> prescriptionDTOS = appointmentDTOForVeterinarian.prescriptions();

        if (prescriptionDTOS != null && !prescriptionDTOS.isEmpty()) {
            List<Prescription> prescriptions = prescriptionDTOS.stream().map((prescriptionDTO -> {
                return new Prescription(prescriptionDTO, appointment);
            })).toList();

            this.prescriptionRepository.saveAll(prescriptions);
        }


        List<ExamDTO> examsDTOS = appointmentDTOForVeterinarian.exams();

        if (examsDTOS != null && !examsDTOS.isEmpty()) {
            List<Exam> exams = examsDTOS.stream().map((examDTO) -> {

                ExamType examType = this.examTypeRepository.findById(examDTO.examTypeId())
                        .orElseThrow(() -> new NotFoundException("Tipo do Exame não encontrado!"));

                return new Exam(examDTO, examType, appointment);
            }).toList();

            this.examRepository.saveAll(exams);
        }

        return this.appointmentRepository.save(appointment);
    }
}