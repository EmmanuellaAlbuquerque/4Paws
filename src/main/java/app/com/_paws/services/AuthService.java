package app.com._paws.services;

import app.com._paws.domain.dtos.LoginDTO;
import app.com._paws.domain.models.UserProfile;
import app.com._paws.domain.repositories.UserProfileRepository;
import app.com._paws.exceptions.ForbiddenException;
import app.com._paws.exceptions.JWTAuthException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    private final UserProfileRepository userProfileRepository;

    private final AuthenticationManager authenticationManager;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration.token}")
    private String expirationJWT;

    public AuthService(@Lazy UserProfileRepository userProfileRepository,
                       @Lazy AuthenticationManager authenticationManager) {
        this.userProfileRepository = userProfileRepository;
        this.authenticationManager = authenticationManager;
    }

    public String login(LoginDTO loginDTO) {

        UsernamePasswordAuthenticationToken dtoLogin = new UsernamePasswordAuthenticationToken(
                loginDTO.getEmail(),
                loginDTO.getPassword()
        );

        try {
            Authentication authentication = authenticationManager.authenticate(dtoLogin);

            Object authenticatedUser = authentication.getPrincipal();
            UserProfile userProfile = (UserProfile) authenticatedUser;

            return generateJWT(userProfile);

        } catch (AuthenticationException ex) {
            throw new JWTAuthException("Email e/ou senha inválida!");
        }
    }

    public Optional<UserProfile> findByEmail(String email) {
        return userProfileRepository.findByEmail(email);
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

    public UsernamePasswordAuthenticationToken validateToken(String bearerToken) {

        if (bearerToken == null) {
            return null;
        }

        String token = bearerToken.replace("Bearer ", "");
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        String userUUID = claims.getSubject();
        List<String> roles = claims.get("ROLES", List.class);
        List<SimpleGrantedAuthority> rolesList = roles
                .stream()
                .map(roleStr -> new SimpleGrantedAuthority(roleStr))
                .toList();

        UsernamePasswordAuthenticationToken springToken = new UsernamePasswordAuthenticationToken(
                userUUID,
                null,
                rolesList
        );

        return springToken;
    }

    public UUID obtainAuthenticatedUserUUID() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof AnonymousAuthenticationToken) {
            throw new ForbiddenException();
        }

        Object authenticatedUUIDString = authentication.getPrincipal();
        return UUID.fromString((String) authenticatedUUIDString);
    }
}