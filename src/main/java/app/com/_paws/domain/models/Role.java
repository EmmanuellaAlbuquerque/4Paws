package app.com._paws.domain.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Data
@Entity(name = "roles")

public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "role")
    private Set<UserProfile> userProfiles;

    @Override
    public String getAuthority() {
        return name;
    }
}
