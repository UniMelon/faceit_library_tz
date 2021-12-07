package faceit.tz.repository.security;

import faceit.tz.model.Book;
import faceit.tz.model.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT b FROM users u JOIN u.booksList b WHERE u.id = :user_id")
    List<Book> findBooksByUserId(@Param("user_id") Long user_id);

    User findByUsername(String username);
}
