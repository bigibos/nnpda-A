package cz.upce.fei.nnpda.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.upce.fei.nnpda.domain.Project;
import cz.upce.fei.nnpda.domain.Ticket;
import cz.upce.fei.nnpda.domain.TicketLog;
import cz.upce.fei.nnpda.domain.User;
import cz.upce.fei.nnpda.dto.Ticket.TicketAddDTO;
import cz.upce.fei.nnpda.dto.Ticket.TicketSearchDTO;
import cz.upce.fei.nnpda.dto.Ticket.TicketUpdateDTO;
import cz.upce.fei.nnpda.repository.ProjectRepository;
import cz.upce.fei.nnpda.repository.TicketRepository;
import cz.upce.fei.nnpda.repository.TicketSearchRepository;
import cz.upce.fei.nnpda.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

@Service
@Slf4j
@AllArgsConstructor
public class TicketService {
    private final ModelMapper modelMapper;
    private final TicketRepository ticketRepository;
    private final ProjectService projectService;
    private final TicketLogService ticketLogService;
    private final UserService userService;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ElasticsearchClient elasticsearchClient;
    private final RestTemplateBuilder restTemplateBuilder;
    private final ObjectMapper objectMapper;
    private final TicketSearchRepository ticketSearchRepository;

    private <T> T getRandomEnum(T[] values) {
        Random random = new Random();
        return values[random.nextInt(values.length)];
    }

    // @Scheduled(fixedDelay = 1 * 60 * 1000)
    public void generateTicket() {
        Random random = new Random();

        System.out.println("Jdu generovat");

        List<Project> projects = projectRepository.findAll();
        if (projects.isEmpty()) {
            return;
        }
        Project project = projects.get(random.nextInt(projects.size()));

        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            return;
        }
        User user = users.get(random.nextInt(users.size()));

        Ticket.Status status = getRandomEnum(Ticket.Status.values());
        Ticket.Type type = getRandomEnum(Ticket.Type.values());
        Ticket.Priority priority = getRandomEnum(Ticket.Priority.values());

        Ticket ticket = new Ticket();
        ticket.setTime(new Timestamp(System.currentTimeMillis()));
        ticket.setName("Ticket " + type.name() + "(" + ticket.getTime() + ")");
        ticket.setStatus(status);
        ticket.setType(type);
        ticket.setPriority(priority);
        ticket.setProject(project);
        ticket.setUser(user);

        System.out.println("Vygenerovano");

        ticketRepository.save(ticket);

    }


    public Collection<TicketSearchDTO> searchTicket(String keyword) {
        List<TicketSearchDTO> searchResults = ticketSearchRepository.findByNameContaining(keyword);

        return searchResults;
    }


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

    public Ticket findTicket(Long ticketId) {
        String username =  SecurityContextHolder.getContext().getAuthentication().getName();

        return ticketRepository.findById(ticketId)
                .orElseThrow(EntityNotFoundException::new);
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
