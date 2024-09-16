package app.com._paws.docs;

import app.com._paws.domain.dtos.DetailedPetResponseDTO;
import app.com._paws.domain.dtos.PetDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

@Tag(name = "PetController - Pets", description = "Operações relacionadas ao gerenciamento de Pets")
public interface PetControllerDocs {

    @Operation(
            summary = "Registre um Pet",
            description = """
                    Um(a) **Recepcionista** consegue cadastrar um **Pet**, especificando:\s
                    
                    - O Nome do Pet (name);\s
                    
                    - O Peso do Pet (weight);\s
                    
                    - O Sexo do Pet (sex) (OBS.: Veja o Schema para mais detalhes referentes aos ENUMS válidos);\s
                    
                    - A Raça do Pet (breed);\s
                    
                    - A Espécie do Pet (specie) (OBS.: Veja o Schema para mais detalhes referentes aos ENUMS válidos);\s
                    
                    - A Data de Nascimento do Pet (birthDate);\s
                    
                    - E o Id do Tutor do Pet (tutorId).\s
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "O Pet foi cadastrado com sucesso.",
                    content = @Content
            )
    })
    ResponseEntity<Object> registerPet(PetDTO petDTO);

    @Operation(
            summary = "Retorna detalhes de um Pet específico"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Um(a) **Recepcionista** consegue obter detalhes de um Pet específico."
            )
    })
    ResponseEntity<DetailedPetResponseDTO> obtainOnePet(UUID petId);
}