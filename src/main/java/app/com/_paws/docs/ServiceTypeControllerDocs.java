package app.com._paws.docs;

import app.com._paws.domain.dtos.DetailedServiceTypeResponseDTO;
import app.com._paws.domain.dtos.ServiceTypeDTO;
import app.com._paws.domain.dtos.ServiceTypeResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "ServiceTypeController - Tipos de Serviço", description = "Operações relacionadas ao gerenciamento de Serviços")
public interface ServiceTypeControllerDocs {

    @Operation(
            summary = "Registre um Tipo de Consulta",
            description = """
                    Um(a) **Admin** consegue cadastrar um **Tipo de Consulta**, especificando:\s
                    
                    - O Nome do Tipo de Consulta (name);\s
                    
                    - A Descrição do Tipo de Consulta (description);\s
                    
                    - E o Preço Base da Consulta (basePrice).\s
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "O Tipo de Consulta foi cadastrado com sucesso.",
                    content = @Content
            )
    })
    ResponseEntity<Object> registerAppointmentType(ServiceTypeDTO appointmentTypeDTO);

    @Operation(
            summary = "Registre um Tipo de Exame",
            description = """
                    Um(a) **Admin** consegue cadastrar um **Tipo de Exame**, especificando:\s
                    
                    - O Nome do Tipo de Exame (name);\s
                    
                    - A Descrição do Tipo de Exame (description);\s
                    
                    - E o Preço Base do Exame (basePrice).\s
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "O Tipo de Exame foi cadastrado com sucesso.",
                    content = @Content
            )
    })
    ResponseEntity<Object> registerExamType(ServiceTypeDTO examTypeDTO);

    @Operation(
            summary = "Lista todos os Tipos de Consulta"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Um(a) **Admin** ou um(a) **Recepcionista** conseguem obter todos os **Tipos de Consultas** cadastradas."
            )
    })
    ResponseEntity<List<ServiceTypeResponseDTO>> obtainAllAppointmentTypes();

    @Operation(
            summary = "Lista todos os Tipos de Exames"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Um(a) **Admin** ou um(a) **Veterinário** conseguem obter todos os **Tipos de Exames** cadastrados."
            )
    })
    ResponseEntity<List<ServiceTypeResponseDTO>> obtainAllExamsTypes();

    @Operation(
            summary = "Retorna detalhes de um Tipo de Consulta específico"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Um(a) **Admin** consegue obter detalhes de um Tipo de Consulta específico."
            )
    })
    ResponseEntity<DetailedServiceTypeResponseDTO> obtainOneAppointmentType(Integer appointmentTypeId);

    @Operation(
            summary = "Retorna detalhes de um Tipo de Exame específico"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Um(a) **Admin** consegue obter detalhes de um Tipo de Exame específico."
            )
    })
    ResponseEntity<DetailedServiceTypeResponseDTO> obtainOneExamType(Integer examTypeId);
}