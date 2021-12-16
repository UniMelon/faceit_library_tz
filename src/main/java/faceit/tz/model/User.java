package faceit.tz.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.*;

@Getter
@Setter
@RequiredArgsConstructor
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    @NotEmpty(message = "username must not be empty!")
    @Size(min = 6, max = 32, message = "username must be between 6 and 32 characters")
    private String username;

    @Column(name = "password")
    @NotEmpty(message = "password must not be empty!")
    @Size(min = 8, message = "password must be at least 8 characters")
    private String password;

    @Column(name = "active")
    private boolean active;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private Set<Role> roles = new HashSet<>();


    @OneToMany(mappedBy = "user")
    private Set<Reader> books = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return active == user.active
                && Objects.equals(id, user.id)
                && Objects.equals(username, user.username)
                && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, active);
    }
}