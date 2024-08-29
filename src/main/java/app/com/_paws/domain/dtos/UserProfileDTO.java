package app.com._paws.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserProfileDTO {

    private String email;

    private String password;

    private String name;

    private String cpf;
}