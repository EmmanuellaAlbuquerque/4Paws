package app.com._paws.docs;

import app.com._paws.domain.dtos.IUserProfile;
import app.com._paws.domain.dtos.UserProfileResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "UserProfileController - Profile", description = "Operações relacionadas ao gerenciamento de um Perfil de Usuário")
public interface UserProfileControllerDocs {

    @Operation(
            summary = "Retorna dados do Perfil do Usuário logado"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Retorna o Perfil do Usuário",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserProfileResponseDTO.class)
                    )
            )
    })
    ResponseEntity<IUserProfile> obtainUserProfile();
}