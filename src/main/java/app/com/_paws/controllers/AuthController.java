package app.com._paws.controllers;

import app.com._paws.domain.dtos.LoginDTO;
import app.com._paws.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginDTO) {
        return authService.login(loginDTO);
    }
}
