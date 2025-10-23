package cz.upce.fei.nnpda.repository;

import cz.upce.fei.nnpda.domain.TicketLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketLogRepository extends JpaRepository<TicketLog, Long> {
}
