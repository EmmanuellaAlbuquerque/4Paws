package app.com._paws.exceptions;

import app.com._paws.domain.dtos.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static app.com._paws.exceptions.login.LoginErrorType.LOGIN_REVOKED;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({Exception.class, DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleGenericException(Exception exception) {

        int httpStatus = HttpStatus.INTERNAL_SERVER_ERROR.value();

        logger.error("Erro inesperado: ", exception);

        return ResponseEntity.status(httpStatus).body(
                new ErrorResponse(
                        LocalDateTime.now(),
                        httpStatus,
                        Map.of("message", "Um erro inesperado ocorreu. Contate o Administrador!")
                ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleJakartaValidationExceptions(MethodArgumentNotValidException methodArgumentNotValidException) {
        HashMap<String, String> errors = new HashMap<>();

        methodArgumentNotValidException.getBindingResult().getAllErrors().forEach((error) -> {
           String fieldName = ((FieldError) error).getField();
           String errorMessage = error.getDefaultMessage();
           errors.put(fieldName, errorMessage);
        });

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                errors
        );

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException notFoundException) {

        return this.handleExceptions(notFoundException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException businessException) {

        return this.handleExceptions(businessException, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(JWTAuthException.class)
    public ResponseEntity<ErrorResponse> handleLoginException(JWTAuthException jwtAuthException) {

        return this.handleExceptions(jwtAuthException, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(PermissionException.class)
    public ResponseEntity<ErrorResponse> handlePermissionsLackException(PermissionException permissionException) {

        return this.handleExceptions(permissionException, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorResponse> handleForbiddenException(ForbiddenException forbiddenException) {

        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @ExceptionHandler(LoginRevokedException.class)
    public ResponseEntity<ProblemDetail> handleLoginRevoked(LoginRevokedException ex, HttpServletRequest request) {

        String title = "Sessão Expirada";
        URI instance = URI.create(request.getRequestURI());

        ApiProblemDetail apiProblemDetail = new ApiProblemDetail(title, HttpStatus.FORBIDDEN, ex.getMessage(), instance, LOGIN_REVOKED);

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(ApiProblemDetail.build(apiProblemDetail));
    }

    private ResponseEntity<ErrorResponse> handleExceptions(RuntimeException runtimeException, HttpStatus httpStatus) {

        return ResponseEntity.status(httpStatus).body(
                new ErrorResponse(
                        LocalDateTime.now(),
                        httpStatus.value(),
                        Map.of("message", runtimeException.getMessage())
                )
        );
    }
}