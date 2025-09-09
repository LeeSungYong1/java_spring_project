package jackie.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jackie.config.JwtProperties;
import jackie.config.JwtTokenProvider;
import jackie.dao.RefreshTokenRequest;
import jackie.exception.UnauthorizedException;
import jackie.service.TokenService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "토큰 API", description = "토큰 관련 API")
public class AuthController {

    private final JwtTokenProvider jwtTokenProvider;
    private final TokenService tokenService;
    private final JwtProperties jwtProperties;

    public AuthController(JwtTokenProvider jwtTokenProvider, TokenService tokenService, JwtProperties jwtProperties) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.tokenService = tokenService;
        this.jwtProperties = jwtProperties;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestParam String username) {
        String accessToken = jwtTokenProvider.generateAccessToken(username);
        String refreshToken = jwtTokenProvider.generateRefreshToken(username);

        tokenService.storeRefreshToken(username, refreshToken, jwtProperties.getRefreshTokenValidity());

        return Map.of(
                "accessToken", accessToken,
                "refreshToken", refreshToken
        );
    }

    @PostMapping("/token/refresh")
    public Map<String, String> refresh(@RequestBody RefreshTokenRequest request) {
        String storedRefreshToken = tokenService.getRefreshToken(request.getUsername());

        if (storedRefreshToken == null || !storedRefreshToken.equals(request.getRefreshToken())) {
            throw new UnauthorizedException("유효하지 않은 리프레시 토큰입니다.");
        }

        boolean isValid = jwtTokenProvider.validateToken(storedRefreshToken);
        if (!isValid) {
            throw new UnauthorizedException("리프레시 토큰이 만료되었거나 위조되었습니다.");
        }

        // ✅ 새 토큰 발급
        String newAccessToken = jwtTokenProvider.generateAccessToken(request.getUsername());
        String newRefreshToken = jwtTokenProvider.generateRefreshToken(request.getUsername());

        // ✅ 이전 토큰 삭제 후 새 토큰 저장 (중요!)
        tokenService.deleteRefreshToken(request.getUsername());
        tokenService.storeRefreshToken(request.getUsername(), newRefreshToken, jwtProperties.getRefreshTokenValidity());

        return Map.of("accessToken", newAccessToken, "refreshToken", newRefreshToken);
    }
}