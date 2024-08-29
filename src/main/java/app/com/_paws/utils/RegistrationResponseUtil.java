package app.com._paws.utils;

import app.com._paws.domain.models.UserProfile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class RegistrationResponseUtil {
    public static <T> ResponseEntity<Object> build(Identifiable<T> entity) {
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entity.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}