package app.com._paws.security;

import app.com._paws.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
            authz.requestMatchers(antMatcher("/auth/**")).permitAll();
            authz.requestMatchers(antMatcher("/profile/**")).permitAll();
            authz.requestMatchers(antMatcher("/sign_up/receptionist")).hasRole("ADMIN");
            authz.requestMatchers(antMatcher("/sign_up/veterinarian")).hasRole("ADMIN");
            authz.requestMatchers(antMatcher("/tutors/new")).hasRole("RECEPCIONISTA");
            authz.requestMatchers(antMatcher("/pets/new")).hasRole("RECEPCIONISTA");
            authz.requestMatchers(antMatcher("/service/**")).hasRole("ADMIN");
            authz.requestMatchers(antMatcher("/appointment_type/**")).hasRole("ADMIN");
            authz.requestMatchers(antMatcher("/exame_type/**")).hasRole("ADMIN");
            authz.requestMatchers(antMatcher("/veterinarian/**")).hasRole("VETERINARIO");
            authz.requestMatchers(antMatcher("/appointments/*")).hasRole("RECEPCIONISTA");
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
