package cz.upce.fei.nnpda.controller;

import cz.upce.fei.nnpda.dto.User.*;
import cz.upce.fei.nnpda.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/auth/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegisterDTO request) {
        return userService.register(request);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginDTO request) {
        return userService.login(request);
    }

    @PostMapping("/auth/request-password-reset")
    public ResponseEntity<?> requestPasswordReset(@Valid @RequestBody UserPasswordResetRequestDTO request) {
        return userService.resetPasswordRequest(request);
    }

    @PostMapping("/auth/password-reset")
    public ResponseEntity<?> passwordReset(@Valid @RequestBody UserPasswordResetDTO request) {
        return userService.passwordReset(request);
    }

    @PostMapping("/auth/change-password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody UserChangePasswordDTO request) {
        return userService.changePassword(request);
    }


}
