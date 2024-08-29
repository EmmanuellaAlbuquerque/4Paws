package app.com._paws.controllers;

import app.com._paws.domain.dtos.ServiceDTO;
import app.com._paws.domain.models.ServiceProvided;
import app.com._paws.services.ServiceProvidedService;
import app.com._paws.utils.RegistrationResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service")
@RequiredArgsConstructor
public class ServiceController {

    private final ServiceProvidedService serviceProvidedService;

    @PostMapping
    public ResponseEntity<Object> registerService(@RequestBody ServiceDTO serviceDTO) {
        ServiceProvided serviceProvided = serviceProvidedService.registerService(serviceDTO);

        return RegistrationResponseUtil.build(serviceProvided);
    }
}