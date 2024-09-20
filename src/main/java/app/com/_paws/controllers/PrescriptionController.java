package app.com._paws.controllers;

import app.com._paws.docs.PrescriptionControllerDocs;
import app.com._paws.services.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/prescriptions", produces = {"application/json"})
@RequiredArgsConstructor
public class PrescriptionController implements PrescriptionControllerDocs {

    private final PrescriptionService prescriptionService;

    @DeleteMapping("{prescriptionId}")
    public ResponseEntity<Void> deletePrescription(@PathVariable(value = "prescriptionId") UUID prescriptionId) {
        this.prescriptionService.deleteById(prescriptionId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}