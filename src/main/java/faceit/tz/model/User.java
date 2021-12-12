package faceit.tz.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    private boolean active;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<UserBook> books = new ArrayList<>();

    public User() {
    }

    public void removeBook(Book book) {
        for (Iterator<UserBook> iterator = books.iterator();
             iterator.hasNext(); ) {
            UserBook userBook = iterator.next();

            if (userBook.getUser().equals(this) &&
                    userBook.getBook().equals(book)) {
                iterator.remove();
                userBook.getBook().getUsers().remove(userBook);
                userBook.setUser(null);
                userBook.setBook(null);
            }
        }
    }

}