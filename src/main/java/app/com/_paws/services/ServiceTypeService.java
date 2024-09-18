package app.com._paws.services;

import app.com._paws.domain.dtos.DetailedServiceTypeResponseDTO;
import app.com._paws.domain.dtos.servicetype.ServiceTypeDTO;
import app.com._paws.domain.dtos.servicetype.ServiceTypeResponseDTO;
import app.com._paws.domain.models.AppointmentType;
import app.com._paws.domain.models.ExamType;
import app.com._paws.domain.models.ServiceType;
import app.com._paws.domain.repositories.AppointmentTypeRepository;
import app.com._paws.domain.repositories.ExamTypeRepository;
import app.com._paws.exceptions.BusinessException;
import app.com._paws.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceTypeService {

    private final ExamTypeRepository examTypeRepository;
    private final AppointmentTypeRepository appointmentTypeRepository;

    public ExamType registerExamType(ServiceTypeDTO examTypeDTO) {
        this.examTypeRepository.findByName(examTypeDTO.name())
                .ifPresent((examType) -> {
                    throw new BusinessException("'" + examType.getName() + "'" + " - Tipo de Exame já cadastrado!");
                });

        ExamType examType = new ExamType(examTypeDTO);

        return this.examTypeRepository.save(examType);
    }

    public AppointmentType registerAppointmentType(ServiceTypeDTO appointmentTypeDTO) {
        this.appointmentTypeRepository.findByName(appointmentTypeDTO.name())
                .ifPresent((appointmentType) -> {
                    throw new BusinessException("'" + appointmentType.getName() + "'" + " - Tipo de Consulta já cadastrada!");
                });

        AppointmentType appointmentType = new AppointmentType(appointmentTypeDTO);

        return this.appointmentTypeRepository.save(appointmentType);
    }

    private List<ServiceTypeResponseDTO> getAllServiceTypesAsDTO(List<? extends ServiceType> serviceTypes) {
        return serviceTypes.stream().map((serviceType) -> {
            return this.getOneServiceTypeAsDTO(serviceType);
        }).toList();
    }

    private ServiceTypeResponseDTO getOneServiceTypeAsDTO(ServiceType serviceType) {
        return new ServiceTypeResponseDTO(
                serviceType.getId(),
                serviceType.getName()
        );
    }

    private DetailedServiceTypeResponseDTO getOneServiceTypeAsDetailedDTO(ServiceType serviceType) {
        return new DetailedServiceTypeResponseDTO(
                serviceType.getId(),
                serviceType.getName(),
                serviceType.getDescription(),
                serviceType.getBasePrice()
        );
    }

    public List<ServiceTypeResponseDTO> findAllAppointmentTypes() {

        List<AppointmentType> appointmentTypes = this.appointmentTypeRepository.findAll();

        return this.getAllServiceTypesAsDTO(appointmentTypes);
    }

    public List<ServiceTypeResponseDTO> findAllExamsTypes() {

        List<ExamType> examTypes = this.examTypeRepository.findAll();

        return this.getAllServiceTypesAsDTO(examTypes);
    }

    public DetailedServiceTypeResponseDTO findOneAppointmentType(Integer appointmentTypeId) {

        AppointmentType appointmentType = this.appointmentTypeRepository.findById(appointmentTypeId)
                .orElseThrow(() -> new NotFoundException("Tipo de consulta não encontrado!"));

        return getOneServiceTypeAsDetailedDTO(appointmentType);
    }

    public DetailedServiceTypeResponseDTO findOneExamType(Integer examTypeId) {

        ExamType examType = this.examTypeRepository.findById(examTypeId)
                .orElseThrow(() -> new NotFoundException("Tipo de Exame não encontrado!"));

        return getOneServiceTypeAsDetailedDTO(examType);
    }
}