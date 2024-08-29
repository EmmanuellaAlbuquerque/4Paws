package app.com._paws.services;

import app.com._paws.domain.dtos.ServiceDTO;
import app.com._paws.domain.models.ServiceProvided;
import app.com._paws.domain.repositories.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceProvidedService {

    private final ServiceRepository serviceRepository;

    public ServiceProvided registerService(ServiceDTO serviceDTO) {
        ServiceProvided serviceProvided = new ServiceProvided(serviceDTO);

        return this.serviceRepository.save(serviceProvided);
    }
}
