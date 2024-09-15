package app.com._paws.controllers;

import app.com._paws.domain.dtos.LoginDTO;
import app.com._paws.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/auth", produces = {"application/json"})
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginDTO loginDTO) {

        return authService.login(loginDTO);
    }
}