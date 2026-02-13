package me.librostats.userservice.util;

import lombok.RequiredArgsConstructor;
import me.librostats.userservice.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtUtil {
    private static final String CLAIM_ROLE = "role";

    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiration}")
    private long expirationTime;

    public String generateToken(User user) {
        Instant now = Instant.now();
        JwsHeader header = JwsHeader.with(MacAlgorithm.HS256).build();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(issuer)
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expirationTime))
                .subject(user.getId())
                .claim(CLAIM_ROLE, user.getRole())
                .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(header, claims)).getTokenValue();
    }

    public String getUserId(String token) {
        return jwtDecoder.decode(token).getSubject();
    }

    public Map<String, Object> getClaims(String token) {
        return jwtDecoder.decode(token).getClaims();
    }
}


