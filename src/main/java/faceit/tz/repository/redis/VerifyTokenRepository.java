package faceit.tz.repository.redis;

import faceit.tz.model.entity.redis.VerifyToken;
import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerifyTokenRepository extends CrudRepository<VerifyToken, String> {

    Optional<VerifyToken> findByToken(String token);

    Optional<VerifyToken> findByEmail(String email);
}
