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
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ServiceTypeService {

    private final ExamTypeRepository examTypeRepository;
    private final AppointmentTypeRepository appointmentTypeRepository;

    public ExamType registerExamType(ServiceTypeDTO examTypeDTO) {

        this.examTypeRepository.findByName(examTypeDTO.getName())
                .ifPresent((examType) -> {
                    if (!Objects.equals(examType.getId(), examTypeDTO.getId())) {
                        throw new BusinessException("'" + examType.getName() + "'" + " - Tipo de Exame já cadastrado!");
                    }
                });

        ExamType examType = new ExamType(examTypeDTO);

        return this.examTypeRepository.save(examType);
    }

    public AppointmentType registerAppointmentType(ServiceTypeDTO appointmentTypeDTO) {
        this.appointmentTypeRepository.findByName(appointmentTypeDTO.getName())
                .ifPresent((appointmentType) -> {
                    if (!Objects.equals(appointmentType.getId(), appointmentTypeDTO.getId())) {
                        throw new BusinessException("'" + appointmentType.getName() + "'" + " - Tipo de Consulta já cadastrada!");
                    }
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

    public void updateAppointmentType(Integer appointmentTypeId, ServiceTypeDTO appointmentTypeDTO) {

        if (this.appointmentTypeRepository.existsById(appointmentTypeId)) {
            appointmentTypeDTO.setId(appointmentTypeId);
            this.registerAppointmentType(appointmentTypeDTO);
        }
        else {
            throw new NotFoundException("Tipo de Consulta não encontrada!");
        }
    }

    public void updateExamType(Integer examTypeId, ServiceTypeDTO examTypeDTO) {

        if (this.examTypeRepository.existsById(examTypeId)) {
            examTypeDTO.setId(examTypeId);
            this.registerExamType(examTypeDTO);
        }
        else {
            throw new NotFoundException("Tipo de Exame não encontrado!");
        }
    }
}