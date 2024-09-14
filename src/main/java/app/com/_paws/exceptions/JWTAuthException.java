package app.com._paws.exceptions;

public class JWTAuthException extends RuntimeException {

    public JWTAuthException(String message) {
        super(message);
    }
}