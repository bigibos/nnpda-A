package cz.upce.fei.nnpda.repository;

import cz.upce.fei.nnpda.domain.Project;
import cz.upce.fei.nnpda.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Optional<Ticket> findByProjectIdAndIdAndProjectUserUsername(Long projectId, Long id, String username);
    List<Ticket> findByProjectIdAndProjectUserUsername(Long projectId, String username);
}
