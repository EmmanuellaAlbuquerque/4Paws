package app.com._paws.services;

import app.com._paws.domain.dtos.ServiceTypeDTO;
import app.com._paws.domain.models.AppointmentType;
import app.com._paws.domain.models.ExamType;
import app.com._paws.domain.repositories.AppointmentTypeRepository;
import app.com._paws.domain.repositories.ExamTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceTypeService {

    private final ExamTypeRepository examTypeRepository;
    private final AppointmentTypeRepository appointmentTypeRepository;

    public ExamType registerExamType(ServiceTypeDTO examTypeDTO) {
        ExamType examType = new ExamType(examTypeDTO);

        return this.examTypeRepository.save(examType);
    }

    public AppointmentType registerAppointmentType(ServiceTypeDTO appointmentTypeDTO) {
        AppointmentType appointmentType = new AppointmentType(appointmentTypeDTO);

        return this.appointmentTypeRepository.save(appointmentType);
    }
}