package app.com._paws.controllers;

import app.com._paws.docs.AuthControllerDocs;
import app.com._paws.domain.dtos.LoginDTO;
import app.com._paws.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/auth", produces = {"application/json"})
public class AuthController implements AuthControllerDocs {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody LoginDTO loginDTO) {

        return ResponseEntity.ok(Map.of("token", this.authService.login(loginDTO)));
    }
}