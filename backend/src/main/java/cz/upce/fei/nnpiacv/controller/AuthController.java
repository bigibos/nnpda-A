package cz.upce.fei.nnpiacv.controller;

import cz.upce.fei.nnpiacv.component.JwtService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class AuthController {

    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // TODO: ověřit z databáze
        if ("admin@upce.cz".equals(request.getEmail()) && "password123".equals(request.getPassword())) {
            String token = jwtService.generateToken(request.getEmail());
            return ResponseEntity.ok(new LoginResponse(token));
        }
        return ResponseEntity.status(401).build();
    }

    @Data
    public static class LoginRequest {
        private String email;
        private String password;
    }

    @Data
    @AllArgsConstructor
    public static class LoginResponse {
        private String token;
    }
}
