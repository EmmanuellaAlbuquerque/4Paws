package app.com._paws.domain.dtos.servicetype;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ServiceTypeDTO {

        @JsonIgnore
        private Integer id;

        @NotBlank(message = "O nome é obrigatório")
        private String name;

        private String description;

        @Positive(message = "O Preço Base deve ser maior que zero")
        @NotNull(message = "O Preço Base é obrigatório")
        private Double basePrice;
}