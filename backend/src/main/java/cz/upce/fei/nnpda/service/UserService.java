package cz.upce.fei.nnpda.service;

import cz.upce.fei.nnpda.component.JwtService;
import cz.upce.fei.nnpda.domain.User;
import cz.upce.fei.nnpda.dto.UserLoginDTO;
import cz.upce.fei.nnpda.dto.UserRegisterDTO;
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

    public String login(UserLoginDTO user) {
        User newUser = modelMapper.map(user, User.class);
        newUser = userRepository.findByUsernameAndPassword(newUser.getUsername(), newUser.getPassword());

        String tokenInput = newUser.getEmail() + newUser.getPassword() + newUser.getUsername();

        return jwtService.generateToken(tokenInput);
    }

}
