package app.com._paws.docs;

import app.com._paws.domain.dtos.TutorDTO;
import app.com._paws.domain.dtos.TutorResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "TutorController - Tutores", description = "Operações relacionadas ao gerenciamento de um Tutor")
public interface TutorControllerDocs {

    @Operation(
            summary = "Registre um Tutor",
            description = """
                    Um(a) **Recepcionista** consegue cadastrar um **Tutor**, especificando:\s
                    
                    - O nome (name);\s
                    
                    - O número de Celular (phone);\s
                    
                    - O CPF (cpf);\s
                    
                    - E o Endereço (address).\s
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "O Tutor foi cadastrado com sucesso.",
                    content = @Content
            )
    })
    ResponseEntity<Object> registerTutor(TutorDTO tutorDTO);

    @Operation(
            summary = "Retorna detalhes de um Tutor específico"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Um(a) **Recepcionista** consegue obter detalhes de um Tutor específico."
            )
    })
    ResponseEntity<TutorResponseDTO> obtainTutorByCpf(String tutorCpf);
}