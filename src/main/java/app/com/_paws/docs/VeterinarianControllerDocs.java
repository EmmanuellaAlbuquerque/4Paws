package app.com._paws.docs;

import app.com._paws.domain.dtos.appointment.AppointmentDTOForVeterinarian;
import app.com._paws.domain.dtos.appointment.AppointmentResponseDTO;
import app.com._paws.domain.dtos.appointment.DetailedAppointmentResponseDTO;
import app.com._paws.domain.dtos.veterinarian.VeterinarianResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "VeterinarianController - Veterinários", description = "Operações relacionadas ao gerenciamento de Consultas de um Veterinário")
public interface VeterinarianControllerDocs {

    @Operation(
            summary = "Lista todos os Veterinários"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Um(a) **Recepcionista** e um **Admin** conseguem obter todos os **Veterinários** cadastrados."
            )
    })
    ResponseEntity<PagedModel<VeterinarianResponseDTO>> obtainAllVeterinarians(int pageIndex, PagedResourcesAssembler assembler);

    @Operation(
            summary = "Lista todas as Consultas de um Veterinário"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Um(a) **Veterinário** consegue obter todas as **Consultas** que ele irá realizar por ordem de Data."
            )
    })
    ResponseEntity<List<AppointmentResponseDTO>> obtainAllVetAppointments();

    @Operation(
            summary = "Retorna detalhes de uma Consulta específica"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Um(a) **Veterinário** consegue obter detalhes de uma Consulta específica."
            )
    })
    ResponseEntity<DetailedAppointmentResponseDTO> obtainOneVetAppointment(Integer appointmentId);

    @Operation(
            summary = "Atualiza uma Consulta",
            description = """
                    Um(a) **Veterinário** consegue atualizar dados da **Consulta** a qual pertence, especificando:\s
                    
                    - O Id da Consulta que quer atualizar (appointmentId);\s
                    
                    - As Anotações da Consulta (notes);\s
                    
                    - Os Exames que foram pedidos (exams), se necessário; (use Id para editar, sem id cria um novo)\s
                    
                    - E as Prescrições da Consulta (prescriptions), se necessário. (use Id para editar, sem id cria um novo)\s
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "A Consulta foi atualizada com sucesso.",
                    content = @Content
            )
    })
    ResponseEntity<Void> veterinarianUpdateAppointment(Integer appointmentId, AppointmentDTOForVeterinarian appointmentDTOForVeterinarian);
}