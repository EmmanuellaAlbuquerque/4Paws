package app.com._paws.services;

import app.com._paws.domain.dtos.LoginDTO;
import app.com._paws.domain.models.UserProfile;
import app.com._paws.domain.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration.token}")
    private String expirationJWT;

    public UserService(@Lazy UserRepository userRepository,
                       @Lazy AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
    }

    public String login(LoginDTO loginDTO) {

        UsernamePasswordAuthenticationToken dtoLogin = new UsernamePasswordAuthenticationToken(
                loginDTO.getEmail(),
                loginDTO.getPassword()
        );

        try {
            Authentication authentication = authenticationManager.authenticate(dtoLogin);

            Object usuarioAutenticado = authentication.getPrincipal();
            UserProfile userProfile = (UserProfile) usuarioAutenticado;

            return generateJWT(userProfile);

        } catch (AuthenticationException ex) {
            throw new RuntimeException("Usuário e senha inválidos");
        }
    }

    public Optional<UserProfile> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    private String generateJWT(UserProfile userProfile) {
        List<String> rolesNames = List.of(userProfile.getRole().getName());

        Date actualDate = new Date();
        Date expirationDate = new Date(actualDate.getTime() + Long.parseLong(expirationJWT));
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        String gereratedJWT = Jwts.builder()
                .issuer("4paws-api")
                .claim("ROLES", rolesNames)
                .subject(userProfile.getId().toString())
                .issuedAt(actualDate)
                .expiration(expirationDate)
                .signWith(key, Jwts.SIG.HS256)
                .compact();

        return gereratedJWT;
    }
}
