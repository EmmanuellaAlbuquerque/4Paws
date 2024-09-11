package app.com._paws.services;

import app.com._paws.domain.dtos.ServiceTypeDTO;
import app.com._paws.domain.dtos.ServiceTypeResponseDTO;
import app.com._paws.domain.models.AppointmentType;
import app.com._paws.domain.models.ExamType;
import app.com._paws.domain.models.ServiceType;
import app.com._paws.domain.repositories.AppointmentTypeRepository;
import app.com._paws.domain.repositories.ExamTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    private List<ServiceTypeResponseDTO> getAllServiceTypes(List<? extends ServiceType> serviceTypes) {
        return serviceTypes.stream().map((serviceType) -> {
            return new ServiceTypeResponseDTO(
                    serviceType.getId(),
                    serviceType.getName()
            );
        }).toList();
    }

    public List<ServiceTypeResponseDTO> findAllAppointmentTypes() {

        List<AppointmentType> appointmentTypes = this.appointmentTypeRepository.findAll();

        return this.getAllServiceTypes(appointmentTypes);
    }

    public List<ServiceTypeResponseDTO> findAllExamsTypes() {

        List<ExamType> examTypes = this.examTypeRepository.findAll();

        return this.getAllServiceTypes(examTypes);
    }
}