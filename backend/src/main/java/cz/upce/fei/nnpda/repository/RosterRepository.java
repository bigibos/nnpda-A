package cz.upce.fei.nnpda.repository;

import cz.upce.fei.nnpda.domain.Roster;
import cz.upce.fei.nnpda.id.RosterId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RosterRepository extends JpaRepository<Roster, RosterId> {
}
