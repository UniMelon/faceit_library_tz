package faceit.tz.repository;

import faceit.tz.model.Reader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Long> {

    Page<Reader> findAll(Pageable pageable);
}
