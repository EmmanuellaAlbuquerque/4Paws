package app.com._paws.domain.dtos;

import lombok.Data;

@Data
public class LoginDTO {
    private String email;
    private String password;
}
