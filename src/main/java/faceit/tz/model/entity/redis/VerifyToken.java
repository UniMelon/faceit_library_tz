package faceit.tz.model.entity.redis;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.util.concurrent.TimeUnit;

@Data
@NoArgsConstructor
@RedisHash("verifyToken")
public class VerifyToken {

    @Id
    private String id;

    private String email;

    @TimeToLive(unit = TimeUnit.MINUTES)
    private Long expiration;
}
