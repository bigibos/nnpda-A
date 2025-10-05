package cz.upce.fei.nnpda.repository;

import cz.upce.fei.nnpda.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
