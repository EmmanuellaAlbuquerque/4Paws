package app.com._paws.exceptions;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;

import java.net.URI;
import java.time.LocalDateTime;

public record ApiProblemDetail(
        String title,
        HttpStatusCode status,
        String detail,
        URI instance,
        ErrorType errorType
) {

    public static ProblemDetail build(ApiProblemDetail apiProblemDetail) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                apiProblemDetail.status,
                apiProblemDetail.detail
        );

        problem.setTitle(apiProblemDetail.title);
        problem.setInstance(apiProblemDetail.instance);
        problem.setType(apiProblemDetail.errorType.getTypeUri());

        problem.setProperty("errorCode", apiProblemDetail.errorType.getCode());
        problem.setProperty("timestamp", LocalDateTime.now());

        return problem;
    }
}
