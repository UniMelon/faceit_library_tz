package faceit.tz.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
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
    private List<Role> roles = new ArrayList<>();


    @OneToMany(mappedBy = "user")
    private List<Reader> books = new ArrayList<>();

    public User() {
    }
}