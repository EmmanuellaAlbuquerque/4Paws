package app.com._paws.docs;

import app.com._paws.domain.dtos.appointment.AppointmentDTOForReceptionist;
import app.com._paws.domain.dtos.ErrorResponse;
import app.com._paws.domain.dtos.appointment.AppointmentResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "AppointmentController - Consultas", description = "Operações relacionadas a cadastro de Consultas por um Recepcionista")
public interface AppointmentControllerDocs {

    @Operation(
            summary = "Registra uma nova Consulta",
            description = """
                    Um(a) **Recepcionista** consegue cadastrar uma nova **Consulta**, especificando:\s
                    
                    - O Id do Tipo de Consulta (appointmentType);\s
                    
                    - A Data da Consulta (scheduledDate);\s
                    
                    - Os Ids dos Veterinários alocados na Consulta (veterinarians);\s
                    
                    - E o Id do Pet que será o paciente da Consulta (pet).\s
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "A Consulta foi cadastrada com sucesso.",
                    content = @Content
            ),

            @ApiResponse(
                    responseCode = "400",
                    description = "Retorna um erro, caso a requisição esteja inválida, " +
                            "por campos obrigatórios não fornecidos ou não atendendo os requisitos necessários.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Exemplo - Campos obrigatórios não fornecidos",
                                            value = """
                                                        {
                                                          "timestamp": "2024-09-16T12:58:17.1912034",
                                                          "statusCode": 400,
                                                          "errors": {
                                                            "veterinarians": "Ao menos 1 Veterinário deve ser alocado para a Consulta",
                                                            "appointmentType": "O Tipo de Consulta é obrigatório",
                                                            "scheduledDate": "A Data da Consulta é obrigatória",
                                                            "pet": "O Pet é obrigatório"
                                                          }
                                                        }
                                                    """
                                    ),
                                    @ExampleObject(
                                            name = "Exemplo - A Data da Consulta marcada para o passado",
                                            value = """
                                                        {
                                                          "timestamp": "2024-09-16T13:19:45.9763123",
                                                          "statusCode": 400,
                                                          "errors": {
                                                            "scheduledDate": "A Data da Consulta deve ser atual ou futura"
                                                          }
                                                        }
                                                    """
                                    )
                            }
                    )
            ),

            @ApiResponse(
                    responseCode = "403",
                    description = "Retorna um erro, caso as credenciais de acesso sejam inválidas.",
                    content = @Content
            ),

            @ApiResponse(
                    responseCode = "404",
                    description = "Retorna um erro, caso não sejam encontrados: o Tipo de Consulta, os Veterinários ou o Pet.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Exemplo - Tipo de Consulta não encontrada",
                                            value = "{\"timestamp\": \"2035-10-10T18:02:42.517Z\", " +
                                                    "\"statusCode\" : 404, " +
                                                    "\"errors\" : { \"message\": \"Tipo de Consulta não encontrada!\" }}"
                                    ),
                                    @ExampleObject(
                                            name = "Exemplo - Veterinários não encontrados",
                                            value = "{\"timestamp\": \"2035-10-10T18:02:42.517Z\", " +
                                                    "\"statusCode\" : 404, " +
                                                    "\"errors\" : { \"message\": \"Veterinário(s) não encontrado(s)!\" }}"
                                    ),
                                    @ExampleObject(
                                            name = "Exemplo - Pet não encontrado",
                                            value = "{\"timestamp\": \"2035-10-10T18:02:42.517Z\", " +
                                                    "\"statusCode\" : 404, " +
                                                    "\"errors\" : { \"message\": \"Pet não encontrado!\" }}"
                                    ),
                            }
                    )
            ),

            @ApiResponse(
                    responseCode = "500",
                    description = "Caso ocorra um erro inesperado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Exemplo - Erro interno",
                                            value = """
                                                        {
                                                          "timestamp": "2024-09-16T13:17:08.7816948",
                                                          "statusCode": 500,
                                                          "errors": {
                                                            "message": "Um erro inesperado ocorreu. Contate o Administrador!"
                                                          }
                                                        }
                                                    """
                                    )
                            }
                    )
            ),
    })
    ResponseEntity<Object> receptionistRegisterAppointment(AppointmentDTOForReceptionist appointmentDTOForReceptionist);

    @Operation(
            summary = "Lista todas as Consultas"
    )
    ResponseEntity<List<AppointmentResponseDTO>> obtainAllAppointments();

    @Operation(
            summary = "Deleta uma Consulta"
    )
    ResponseEntity<Void> deleteAppointment(Integer appointmentId);

    @Operation(
            summary = "Atualiza uma Consulta",
            description = """
                    Um(a) **Recepcionista** consegue atualizar uma **Consulta**, especificando:\s
                    
                    - O Id da Consulta (appointmentId)l;\s
                    
                    - O Id do Tipo de Consulta (appointmentType);\s
                    
                    - A Data da Consulta (scheduledDate);\s
                    
                    - Os Ids dos Veterinários alocados na Consulta (veterinarians);\s
                    
                    - E o Id do Pet que será o paciente da Consulta (pet).\s
                    """
    )

    ResponseEntity<Void> updateAppointment(Integer appointmentId,
                                           AppointmentDTOForReceptionist appointmentDTOForReceptionist);
}
