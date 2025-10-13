package cz.upce.fei.nnpda.service;

import cz.upce.fei.nnpda.component.JwtService;
import cz.upce.fei.nnpda.domain.User;
import cz.upce.fei.nnpda.dto.*;
import cz.upce.fei.nnpda.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<?> register(UserRegisterDTO userDto) {
        User user = modelMapper.map(userDto, User.class);

        String passwordHash = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordHash);

        userRepository.save(user);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> login(UserLoginDTO userDto) {

        User user = userRepository.findByUsername(userDto.getUsername());

        if (user == null)
            return ResponseEntity.status(401).body("Invalid username");

        if (!passwordEncoder.matches(userDto.getPassword(), user.getPassword()))
            return ResponseEntity.status(401).body("Invalid password");

        String tokenInput = user.getUsername();

        return ResponseEntity.ok(jwtService.generateToken(tokenInput, JwtService.JwtType.AUTH, 15));
    }

    public ResponseEntity<?> resetPasswordRequest(UserPasswordResetRequestDTO userDto) {
        User user = userRepository.findByEmail(userDto.getEmail());

        if (user == null)
            return ResponseEntity.status(401).body("Invalid email");

        String tokenInput = user.getEmail();

        return ResponseEntity.ok().body(jwtService.generateToken(tokenInput, JwtService.JwtType.PASSWORD_RESET, 10));
    }

    public ResponseEntity<?> passwordReset(UserPasswordResetDTO userDto) {
        String email = jwtService.extractSubject(userDto.getToken());
        String passwordHash = passwordEncoder.encode(userDto.getPassword());

        User user = userRepository.findByEmail(email);

        if (user == null)
            return ResponseEntity.status(401).body("Invalid email");

        user.setPassword(passwordHash);
        userRepository.save(user);

        return ResponseEntity.ok().body("Password reset successful");
    }

    public ResponseEntity<?> changePassword(UserChangePasswordDTO userDto) {

        String username =  SecurityContextHolder.getContext().getAuthentication().getName();
        String newPasswordHash = passwordEncoder.encode(userDto.getNewPassword());

        User user = userRepository.findByUsername(username);

        if (user == null)
            return ResponseEntity.status(401).body("Invalid username");

        if (!passwordEncoder.matches(userDto.getOldPassword(), user.getPassword()))
            return ResponseEntity.status(401).body("Invalid password");

        user.setPassword(newPasswordHash);
        userRepository.save(user);

        return ResponseEntity.ok().build();
    }

}
