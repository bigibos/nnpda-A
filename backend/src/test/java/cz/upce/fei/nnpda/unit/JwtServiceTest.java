package cz.upce.fei.nnpda.unit;

import cz.upce.fei.nnpda.component.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JwtServiceTest {

    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        jwtService = new JwtService();
    }

    @Test
    void shouldGenerateValidToken() {
        String username = "testuser";

        String token = jwtService.generateToken(username, JwtService.JwtType.AUTH);

        assertNotNull(token);
        assertTrue(jwtService.validateToken(token, JwtService.JwtType.AUTH));
        assertEquals(username, jwtService.extractSubject(token));
    }

    @Test
    void shouldFailValidationForTamperedToken() {
        String username = "testuser";
        String token = jwtService.generateToken(username, JwtService.JwtType.AUTH);

        // Úmyslně upravený token (např. odebrání posledního znaku)
        String tamperedToken = token.substring(0, token.length() - 1);

        assertFalse(jwtService.validateToken(tamperedToken, JwtService.JwtType.AUTH));
    }

    @Test
    void shouldFailValidationForExpiredToken() {
        // Pokud budeš chtít testovat expiraci, musíš vytvořit vlastní službu nebo mockovat čas.
        // Tento test ponecháme jako příklad:
        // - použitelné až s upraveným constructor injection pro `Clock` nebo vlastní `Key`.

        // Zatím tedy nepovinné.
    }
}
