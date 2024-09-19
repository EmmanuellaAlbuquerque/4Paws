package app.com._paws.docs;

import app.com._paws.domain.dtos.pet.DetailedPetResponseDTO;
import app.com._paws.domain.dtos.pet.PetDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Tag(name = "PetController - Pets", description = "Operações relacionadas ao gerenciamento de Pets")
public interface PetControllerDocs {

    @Operation(
            summary = "Registra um Pet",
            description = """
                    Um(a) **Recepcionista** consegue cadastrar um **Pet**, especificando:\s
                    
                    - O Nome do Pet (name);\s
                    
                    - O Peso do Pet em KG (weight);\s
                    
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

    @Operation(
            summary = "Atualiza um Pet",
            description = """
                    Um(a) **Recepcionista** consegue atualizar dados de um **Pet**, especificando:\s
                    
                    - O Id do pet (petId);
                    
                    - O Nome do Pet (name);\s
                    
                    - O Peso do Pet em KG (weight);\s
                    
                    - O Sexo do Pet (sex) (OBS.: Veja o Schema para mais detalhes referentes aos ENUMS válidos);\s
                    
                    - A Raça do Pet (breed);\s
                    
                    - A Espécie do Pet (specie) (OBS.: Veja o Schema para mais detalhes referentes aos ENUMS válidos);\s
                    
                    - A Data de Nascimento do Pet (birthDate);\s
                    
                    - E o Id do Tutor do Pet (tutorId).\s
                    """
    )
    ResponseEntity<Void> updatePet(UUID petId, PetDTO petDTO);
}