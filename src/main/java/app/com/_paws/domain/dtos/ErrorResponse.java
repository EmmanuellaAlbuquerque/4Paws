package app.com._paws.domain.dtos;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class ErrorResponse {

    private LocalDateTime timestamp;
    private int statusCode;
    private Map<String, String> errors;

    public ErrorResponse(LocalDateTime timestamp, int statusCode, Map<String, String> errors) {
        this.timestamp = timestamp;
        this.statusCode = statusCode;
        this.errors = errors;
    }
}