package app.com._paws.controllers;

import app.com._paws.domain.dtos.LoginDTO;
import app.com._paws.domain.dtos.UserProfileDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SignUpControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;

    private static String authToken;

    @BeforeAll
    public static void setup(@Autowired TestRestTemplate restTemplate) {
        authToken = getAuthToken(restTemplate);
    }

    private static  String getAuthToken(TestRestTemplate restTemplate) {
        LoginDTO loginDTO = new LoginDTO("admin@example.com", "123");

        ResponseEntity<Map> response = restTemplate.postForEntity(
                "/api/v1/auth/login",
                loginDTO,
                Map.class
        );

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());

        return (String) response.getBody().get("token");
    }

    @Test
    public void registerReceptionist() {
        UserProfileDTO receptionistDTO = new UserProfileDTO(
                "julia@gmail.com",
                "julia123",
                "Julia Almeida",
                "242.911.960-91"
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);
        HttpEntity<UserProfileDTO> request = new HttpEntity<>(receptionistDTO, headers);

        ResponseEntity<Void> responseEntity = restTemplate.postForEntity("/api/v1/sign-up/receptionists", request, Void.class);

        System.out.println(responseEntity);
        URI location = responseEntity.getHeaders().getLocation();

        String[] locationSegments = location.getPath().split("/");

        String receptionistUUID = locationSegments[locationSegments.length - 1];

        assertNotNull(location);
        assertEquals(201, responseEntity.getStatusCodeValue());
        assertDoesNotThrow(() -> UUID.fromString(receptionistUUID));
    }
}