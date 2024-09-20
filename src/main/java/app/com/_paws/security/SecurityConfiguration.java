package app.com._paws.security;

import app.com._paws.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final AuthService authService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.cors(Customizer.withDefaults());
        httpSecurity.authorizeHttpRequests((authz) -> {

            authz.requestMatchers(antMatcher("/api/v1/auth/**")).permitAll();
            authz.requestMatchers(antMatcher("/api/v1/profiles/**")).permitAll();

            authz.requestMatchers(antMatcher("/api/v1/sign_up/receptionists")).hasRole("ADMIN");
            authz.requestMatchers(antMatcher("/api/v1/sign_up/veterinarians")).hasRole("ADMIN");
            authz.requestMatchers(antMatcher("/api/v1/sign_up/admins")).hasRole("ADMIN");

            authz.requestMatchers(antMatcher("/api/v1/tutors/**")).hasRole("RECEPCIONISTA");

            authz.requestMatchers(antMatcher(HttpMethod.GET, "/api/v1/pets/**")).hasAnyRole("VETERINARIO", "RECEPCIONISTA");;
            authz.requestMatchers(antMatcher("/api/v1/pets/**")).hasRole("RECEPCIONISTA");

            authz.requestMatchers(antMatcher("/api/v1/service_types/appointments_types")).hasAnyRole("RECEPCIONISTA", "ADMIN");
            authz.requestMatchers(antMatcher("/api/v1/service_types/exams_types")).hasAnyRole("VETERINARIO", "ADMIN");
            authz.requestMatchers(antMatcher("/api/v1/service_types/**")).hasRole("ADMIN");

            authz.requestMatchers(antMatcher(HttpMethod.GET,"/api/v1/veterinarians")).hasAnyRole("ADMIN", "RECEPCIONISTA");
            authz.requestMatchers(antMatcher("/api/v1/veterinarians/**")).hasRole("VETERINARIO");
            authz.requestMatchers(antMatcher("/api/v1/appointments/**")).hasRole("RECEPCIONISTA");

            authz.requestMatchers(antMatcher("/api/v1/prescriptions/**")).hasRole("VETERINARIO");
            authz.requestMatchers(antMatcher("/api/v1/exams/**")).hasAnyRole("VETERINARIO", "RECEPCIONISTA");
        });

        httpSecurity.addFilterBefore(
                new TokenAuthenticationFilter(authService),
                UsernamePasswordAuthenticationFilter.class
        );

        return httpSecurity.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(
                antMatcher("/v3/api-docs"),
                antMatcher("/v3/api-docs/**"),
                antMatcher("/swagger-resources/**"),
                antMatcher("/swagger-ui/**"),
                antMatcher("/"),
                antMatcher("/h2-console/**")
        );
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
