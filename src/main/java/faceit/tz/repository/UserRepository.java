package faceit.tz.repository;

import faceit.tz.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findAll(Pageable pageable);

    User findByUsername(String username);

    User findByActivationCode(String code);

    User findByEmail(String email);
}
