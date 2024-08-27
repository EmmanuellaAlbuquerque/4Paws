package app.com._paws.security;

import app.com._paws.domain.models.UserProfile;
import app.com._paws.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {

    private final AuthService authService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserProfile> userOptional = authService.findByEmail(username);

        if(userOptional.isPresent()) {
            return userOptional.get();
        }

        throw new UsernameNotFoundException("Usuário não encontrado!");
    }
}
