package app.com._paws.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Tag(name = "ExamController - Exames", description = "Operações relacionadas ao gerenciamento de Exames")
public interface ExamControllerDocs {

    @Operation(
            summary = "Deleta um Exame",
            description = "Um(a) **Veterinário** consegue deletar um Exame."
    )
    @ApiResponse(
            responseCode = "200",
            description = "O Exame foi deletada com sucesso.",
            content = @Content
    )
    ResponseEntity<Void> deleteExam(UUID examId);
}