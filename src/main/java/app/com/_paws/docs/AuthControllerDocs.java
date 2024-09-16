package app.com._paws.docs;

import app.com._paws.domain.dtos.LoginDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@Tag(name = "AuthController - Login de Usuários", description = "Operações relacionadas a autenticação de um usuário")
public interface AuthControllerDocs {

    @Operation(
            summary = "Faz Login de um Usuário",
            description = """
                    Para fazer login o **Usuário** deve especificar:\s
                    
                    - Seu email (email);\s
                    
                    - E sua senha (password).\s
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "O Login foi feito com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(
                                            name = "Exemplo - Login com sucesso",
                                            value = """
                                                        {
                                                          "token": "token-string"
                                                        }
                                                    """
                                    )
                            }
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "O Login não foi feito com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(
                                            name = "Exemplo - Login inválido",
                                            value = """
                                                        {
                                                          "timestamp": "2024-09-16T15:40:44.7797945",
                                                          "statusCode": 401,
                                                          "errors": {
                                                            "message": "Email e/ou senha inválida!"
                                                          }
                                                        }
                                                    """
                                    )
                            }
                    )
            )
    })
    ResponseEntity<Map<String, String>> login(LoginDTO loginDTO);
}