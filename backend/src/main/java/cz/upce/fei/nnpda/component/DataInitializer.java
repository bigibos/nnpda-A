package cz.upce.fei.nnpda.component;

import cz.upce.fei.nnpda.domain.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import cz.upce.fei.nnpda.repository.UserRepository;

@Component
@Slf4j
@AllArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private UserRepository userRepository;


    @Override
    public void run(String... args) {
        User user = new User(0L, "admin@upce.cz", "password123");
        /*
        Team t1 = new Team(0L, "Dementi", new HashSet<>());

        Racer r1 = new Racer(0L,"Elfyn", "Evans", 12, t1);
        Racer r2 = new Racer(1L, "Marko", "Salminen", 45, t1);
        Racer r3 = new Racer(2L, "Sami", "Pajari", 77, t1);
        Racer r4 = new Racer(3L, "Martijn", "Wydaeghe", 21, t1);

        t1.getMembers().addAll(Set.of(r1, r2, r3, r4));
        */


        if (!userRepository.existsById(user.getId())) {
            log.debug("Admin user created: " + user);
            userRepository.save(user);
        }
    }
}
