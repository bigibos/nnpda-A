package cz.upce.fei.nnpda.repository;

import cz.upce.fei.nnpda.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
