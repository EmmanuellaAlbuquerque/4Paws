package app.com._paws.docs;

import app.com._paws.domain.dtos.tutor.TutorDTO;
import app.com._paws.domain.dtos.tutor.TutorResponseDTO;
import app.com._paws.domain.dtos.tutor.TutorUpdateDTO;
import app.com._paws.domain.dtos.tutor.TutorsDTO;
import app.com._paws.domain.models.Tutor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

@Tag(name = "TutorController - Tutores", description = "Operações relacionadas ao gerenciamento de um Tutor")
public interface TutorControllerDocs {

    @Operation(
            summary = "Registra um Tutor",
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
    ResponseEntity<Void> registerTutor(TutorDTO tutorDTO);

    @Operation(
            summary = "Atualiza um Tutor",
            description = """
                    Um(a) **Recepcionista** consegue atualizar um **Tutor**, especificando:\s
                    
                    - O Id do Tutor (tutorId);\s
                    
                    - O número de Celular (phone);\s
                    
                    - E o Endereço (address).\s
                    """
    )
    ResponseEntity<Void> updateTutor(UUID tutorId, TutorUpdateDTO tutorUpdateDTO);

    @Operation(
            summary = "Lista todos os Tutores"
    )
    ResponseEntity<PagedModel<TutorsDTO>> obtainAllTutors(int pageIndex, PagedResourcesAssembler assembler);

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