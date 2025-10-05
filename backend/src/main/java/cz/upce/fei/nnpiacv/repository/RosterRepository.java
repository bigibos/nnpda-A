package cz.upce.fei.nnpiacv.repository;

import cz.upce.fei.nnpiacv.domain.Roster;
import cz.upce.fei.nnpiacv.id.RosterId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RosterRepository extends JpaRepository<Roster, RosterId> {
}
