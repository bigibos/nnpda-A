package cz.upce.fei.nnpda.repository;

import cz.upce.fei.nnpda.domain.Racer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RacerRepository extends JpaRepository<Racer, Long> {
}
