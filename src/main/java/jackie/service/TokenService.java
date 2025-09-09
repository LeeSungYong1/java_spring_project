package jackie.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class TokenService {

    private final StringRedisTemplate redisTemplate;

    public TokenService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void storeRefreshToken(String username, String refreshToken, long expiryInMs) {
        redisTemplate.opsForValue().set(
                buildKey(username),
                refreshToken,
                Duration.ofMillis(expiryInMs)
        );
    }

    public String getRefreshToken(String username) {
        return redisTemplate.opsForValue().get(buildKey(username));
    }

    public void deleteRefreshToken(String username) {
        redisTemplate.delete(buildKey(username));
    }

    private String buildKey(String username) {
        return "refresh_token:" + username;
    }
}
