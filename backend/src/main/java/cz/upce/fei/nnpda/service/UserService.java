package cz.upce.fei.nnpda.service;

import cz.upce.fei.nnpda.component.JwtService;
import cz.upce.fei.nnpda.domain.User;
import cz.upce.fei.nnpda.dto.*;
import cz.upce.fei.nnpda.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;

    public ResponseEntity<?> register(UserRegisterDTO user) {
        User newUser = modelMapper.map(user, User.class);
        newUser = userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> login(UserLoginDTO user) {

        User newUser = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());

        if (newUser == null)
            return ResponseEntity.status(401).body("Invalid username or password");

        String tokenInput = newUser.getUsername();

        return ResponseEntity.ok(jwtService.generateToken(tokenInput, JwtService.JwtType.AUTH));
    }

    public ResponseEntity<?> resetPasswordRequest(UserPasswordResetRequestDTO user) {
        User newUser = userRepository.findByEmail(user.getEmail());

        if (newUser == null)
            return ResponseEntity.notFound().build();

        String tokenInput = newUser.getEmail();

        return ResponseEntity.ok().body(jwtService.generateToken(tokenInput, JwtService.JwtType.PASSWORD_RESET));
    }

    public ResponseEntity<?> passwordReset(UserPasswordResetDTO user) {
        String email = jwtService.extractSubject(user.getToken());
        User newUser = userRepository.findByEmail(email);

        if (newUser == null)
            return ResponseEntity.notFound().build();

        newUser.setPassword(user.getPassword());
        userRepository.save(newUser);

        return ResponseEntity.ok().body("Heslo resetovano");
    }

    public ResponseEntity<?> changePassword(UserChangePasswordDTO user) {

        User newUser = userRepository.findByPassword(user.getOldPassword());

        if (newUser == null)
            return ResponseEntity.notFound().build();

        newUser.setPassword(user.getNewPassword());
        newUser = userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }

}
