package cz.upce.fei.nnpiacv.repository;

import cz.upce.fei.nnpiacv.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
