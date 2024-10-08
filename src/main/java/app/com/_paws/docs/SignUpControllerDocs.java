package app.com._paws.docs;

import app.com._paws.domain.dtos.UserProfileDTO;
import app.com._paws.domain.dtos.veterinarian.VeterinarianDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "SignUpController - Cadastro de Usuários", description = "Operações relacionadas ao cadastro de Usuários")
public interface SignUpControllerDocs {

    @Operation(
            summary = "Registra um Recepcionista",
            description = """
                    Um(a) **Admin** consegue cadastrar um **Recepcionista**, especificando:\s
                    
                    - O Email (email);\s
                    
                    - A senha (password);\s
                    
                    - O nome (name);\s
                    
                    - E o CPF (cpf).\s
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "O(A) Recepcionista foi cadastrado(a) com sucesso.",
                    content = @Content
            )
    })
    ResponseEntity<Object> registerReceptionist(UserProfileDTO userProfileDTO);

    @Operation(
            summary = "Registra um Admin",
            description = """
                    Um(a) **Admin** consegue cadastrar um **Admin**, especificando:\s
                    
                    - O Email (email);\s
                    
                    - A senha (password);\s
                    
                    - O nome (name);\s
                    
                    - E o CPF (cpf).\s
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "O(A) Admin foi cadastrado(a) com sucesso.",
                    content = @Content
            )
    })
    ResponseEntity<Object> registerAdmin(UserProfileDTO userProfileDTO);

    @Operation(
            summary = "Registra um Veterinário",
            description = """
                    Um(a) **Admin** consegue cadastrar um **Veterinário**, especificando:\s
                    
                    - O Email (email);\s
                    
                    - A senha (password);\s
                    
                    - O nome (name);\s
                    
                    - A Especialidade (specialty) (OBS.: Veja o Schema para mais detalhes referentes aos ENUMS válidos);\s
                    
                    - O CRMV (crmv);\s
                    
                    - E a UF (uf) (OBS.: Veja o Schema para mais detalhes referentes aos ENUMS válidos).\s
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "O(A) Veterinário foi cadastrado(a) com sucesso.",
                    content = @Content
            )
    })
    ResponseEntity<Object> registerVeterinarian(VeterinarianDTO veterinarianDTO);
}