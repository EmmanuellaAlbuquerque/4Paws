package app.com._paws.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

@Tag(name = "PrescriptionController - Prescrições", description = "Operações relacionadas ao gerenciamento de Prescrições")
public interface PrescriptionControllerDocs {

    @Operation(
            summary = "Deleta uma Prescrição",
            description = "Um(a) **Veterinário** consegue deletar uma prescrição."
    )
    @ApiResponse(
            responseCode = "200",
            description = "A Prescrição foi deletada com sucesso.",
            content = @Content
    )
    ResponseEntity<Void> deletePrescription(UUID prescriptionId);
}