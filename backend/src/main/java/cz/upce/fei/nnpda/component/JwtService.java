package cz.upce.fei.nnpda.component;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    public enum JwtType {
        AUTH,
        PASSWORD_RESET
    }

    private final Key key = Keys.hmacShaKeyFor("tajny_super_klic_123456789012345678901234567890".getBytes());

    public String generateToken(String subject, JwtType type) {
        return Jwts.builder()
                .setSubject(subject)
                .claim("type", type)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1h
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractSubject(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token, JwtType expectedType) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            JwtType type = JwtType.valueOf(claims.get("type", String.class));

            return expectedType.equals(type);
        } catch (JwtException e) {
            return false;
        }
    }

    public void deleteToken(String token, JwtType expectedType) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();

        JwtType type = claims.get("type", JwtType.class);

        if (expectedType.equals(type)) {

        }

    }
}
