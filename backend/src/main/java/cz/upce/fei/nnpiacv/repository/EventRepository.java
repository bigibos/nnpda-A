package cz.upce.fei.nnpiacv.repository;

import cz.upce.fei.nnpiacv.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
