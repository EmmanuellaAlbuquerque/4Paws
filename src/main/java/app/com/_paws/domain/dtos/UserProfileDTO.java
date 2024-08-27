package app.com._paws.domain.dtos;

import lombok.Data;

@Data
public class UserProfileDTO {

    private String email;

    private String password;

    private String name;

    private String cpf;
}