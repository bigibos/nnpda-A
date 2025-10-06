package cz.upce.fei.nnpda.repository;

import cz.upce.fei.nnpda.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByUsername(String username);
    User findByUsernameAndPassword(String username, String password);
    User findByPassword(String password);
}
