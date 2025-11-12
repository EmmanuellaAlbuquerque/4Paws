package app.com._paws.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class LoginRevokedException extends RuntimeException {

    public LoginRevokedException() {
        super("Sessão expirada. Faça login novamente.");
    }
}
