package cz.upce.fei.nnpda.repository;

import cz.upce.fei.nnpda.dto.Ticket.TicketSearchDTO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface TicketSearchRepository extends ElasticsearchRepository<TicketSearchDTO, Long> {
    List<TicketSearchDTO> findByNameContaining(String name);
}
