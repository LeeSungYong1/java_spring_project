package jackie.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/redis/message")
@Tag(name = "레디스 API", description = "레디스 관련 API")
public class RedisMessageController {

    private final StringRedisTemplate redisTemplate;

    public RedisMessageController(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PutMapping("/{key}")
    public String putMessage(@PathVariable String key, @RequestBody MessageRequest request) {
        redisTemplate.opsForValue().set(key, request.getValue());
        return "OK";
    }

    // ✅ 조회 (GET /api/message/{key})
    @GetMapping("/{key}")
    public String getMessage(@PathVariable String key) {
        String value = redisTemplate.opsForValue().get(key);
        return value != null ? value : "(없음)";
    }

    @DeleteMapping("/{key}")
    public String deleteMessage(@PathVariable String key) {
        redisTemplate.delete(key);
        return "OK";
    }

    // ✅ 요청 바디 DTO
    public static class MessageRequest {
        private String value;

        public String getValue() { return value; }
        public void setValue(String value) { this.value = value; }
    }
}