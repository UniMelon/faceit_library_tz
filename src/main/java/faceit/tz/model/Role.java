package faceit.tz.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users;

}
