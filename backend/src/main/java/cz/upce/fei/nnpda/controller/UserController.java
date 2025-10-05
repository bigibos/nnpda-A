package cz.upce.fei.nnpda.controller;

import cz.upce.fei.nnpda.domain.User;
import cz.upce.fei.nnpda.dto.UserLoginDTO;
import cz.upce.fei.nnpda.dto.UserRegisterDTO;
import cz.upce.fei.nnpda.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
        String token = userService.login(request);
        return ResponseEntity.ok().body(token);
    }


}
