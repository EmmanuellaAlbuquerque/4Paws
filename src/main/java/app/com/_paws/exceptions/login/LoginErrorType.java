package app.com._paws.exceptions.login;

import app.com._paws.exceptions.ErrorType;

import java.net.URI;

public enum LoginErrorType implements ErrorType {
    LOGIN_REVOKED("LOGIN_REVOKED", "https://myapp.com/errors/login-revoked"),
    TOKEN_EXPIRED("TOKEN_EXPIRED", "https://myapp.com/errors/token-expired");

    private final String code;
    private final String typeUri;

    LoginErrorType(String code, String typeUri) {
        this.code = code;
        this.typeUri = typeUri;
    }

    public String getCode() {
        return code;
    }

    public URI getTypeUri() {
        return URI.create(typeUri);
    }
}