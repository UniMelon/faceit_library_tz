package faceit.tz.repository;

import faceit.tz.model.Reader;
import faceit.tz.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Long> {

    Page<Reader> findAll(Pageable pageable);

    List<Reader> findByUser(User user);
}
