package cz.upce.fei.nnpiacv.controller;

import cz.upce.fei.nnpiacv.domain.User;
import cz.upce.fei.nnpiacv.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @DeleteMapping("/users/{id}")
    public User deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    @GetMapping("/users/{id}")
    public User findUser(@PathVariable Long id) {
        return userService.findUser(id);
    }

    @GetMapping("/users")
    public Collection<User> getUsers(@RequestParam(required = false) String email) {
        if (email != null) {
            User user = userService.findByEmail(email);

            if (user != null)
                return Collections.singleton(user);

            return Collections.emptyList();
        }
        return userService.findUsers();
    }
}
