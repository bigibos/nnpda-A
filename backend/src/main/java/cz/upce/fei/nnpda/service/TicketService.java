package cz.upce.fei.nnpda.service;

import cz.upce.fei.nnpda.domain.Project;
import cz.upce.fei.nnpda.domain.Ticket;
import cz.upce.fei.nnpda.domain.TicketLog;
import cz.upce.fei.nnpda.dto.Ticket.TicketAddDTO;
import cz.upce.fei.nnpda.dto.Ticket.TicketUpdateDTO;
import cz.upce.fei.nnpda.repository.TicketRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Slf4j
@AllArgsConstructor
public class TicketService {
    private final ModelMapper modelMapper;
    private final TicketRepository ticketRepository;
    private final ProjectService projectService;
    private final TicketLogService ticketLogService;

    @Transactional
    public Ticket addTicket(Long projectId, TicketAddDTO ticketDto) {
        Project project = projectService.findProject(projectId);

        Ticket ticket = modelMapper.map(ticketDto, Ticket.class);
        ticket.setProject(project);
        ticket.setStatus(Ticket.Status.OPEN);
        ticket.setType(ticketDto.getType());
        ticket.setPriority(ticketDto.getPriority());

        ticket = ticketRepository.save(ticket);
        project.getTickets().add(ticket);

        return ticket;
    }

    @Transactional
    public Ticket updateTicket(Long projectId, Long ticketId, TicketUpdateDTO ticketDto) {
        String username =  SecurityContextHolder.getContext().getAuthentication().getName();

        Ticket ticket = ticketRepository.findByProjectIdAndIdAndProjectUserUsername(projectId, ticketId, username)
                .orElseThrow(EntityNotFoundException::new);

        ticketLogService.addTicketLog(ticket);

        modelMapper.map(ticketDto, ticket);
        ticket.setStatus(ticketDto.getStatus());
        ticket.setType(ticketDto.getType());
        ticket.setPriority(ticketDto.getPriority());

        ticket = ticketRepository.save(ticket);


        return ticket;
    }

    @Transactional
    public void deleteTicket(Long projectId, Long ticketId) {
        String username =  SecurityContextHolder.getContext().getAuthentication().getName();

        Ticket ticket = ticketRepository.findByProjectIdAndIdAndProjectUserUsername(projectId, ticketId, username)
                .orElseThrow(EntityNotFoundException::new);
        ticketRepository.delete(ticket);
    }

    public Collection<Ticket> findTickets(Long projectId) {
        String username =  SecurityContextHolder.getContext().getAuthentication().getName();
        return ticketRepository.findByProjectIdAndProjectUserUsername(projectId, username);
    }

    public Ticket findTicket(Long projectId, Long ticketId) {
        String username =  SecurityContextHolder.getContext().getAuthentication().getName();

        return ticketRepository.findByProjectIdAndIdAndProjectUserUsername(projectId, ticketId, username)
                .orElseThrow(EntityNotFoundException::new);
    }


    @Transactional
    public Ticket updateTicketUser(Long userId, Long ticketId) {
        String username =  SecurityContextHolder.getContext().getAuthentication().getName();

        // TODO: Uzivatel nema pristup k projektu, kte kteremu je prirazen dany tiket - vyresit co a jak?
        Ticket ticket = ticketRepository.findByProjectIdAndIdAndProjectUserUsername(userId, ticketId, username)
                .orElseThrow(EntityNotFoundException::new);


        ticket = ticketRepository.save(ticket);


        return ticket;
    }

    @Transactional
    public void deleteTicketUser(Long userId, Long ticketId) {
        String username =  SecurityContextHolder.getContext().getAuthentication().getName();

        // TODO: Uzivatel nema pristup k projektu, kte kteremu je prirazen dany tiket - vyresit co a jak?
        Ticket ticket = ticketRepository.findByProjectIdAndIdAndProjectUserUsername(userId, ticketId, username)
                .orElseThrow(EntityNotFoundException::new);
        ticketRepository.delete(ticket);
    }
}
