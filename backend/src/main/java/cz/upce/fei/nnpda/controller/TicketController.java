package cz.upce.fei.nnpda.controller;

import cz.upce.fei.nnpda.dto.Comment.CommentRequestDTO;
import cz.upce.fei.nnpda.dto.Comment.CommentRespondDTO;
import cz.upce.fei.nnpda.dto.Ticket.TicketAddDTO;
import cz.upce.fei.nnpda.dto.Ticket.TicketRespondDTO;
import cz.upce.fei.nnpda.dto.Ticket.TicketUpdateDTO;
import cz.upce.fei.nnpda.service.CommentService;
import cz.upce.fei.nnpda.service.TicketService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class TicketController {
    private final ModelMapper modelMapper;
    private final TicketService ticketService;
    private final CommentService commentService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/projects/{projectId}/tickets")
    public TicketRespondDTO addTicket(@PathVariable Long projectId, @Valid @RequestBody TicketAddDTO ticket) {
        return modelMapper.map(ticketService.addTicket(projectId, ticket), TicketRespondDTO.class);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/projects/{projectId}/tickets/{id}")
    public TicketRespondDTO updateTicket(@PathVariable Long projectId, @PathVariable Long id, @Valid @RequestBody TicketUpdateDTO ticket) {
        return modelMapper.map(ticketService.updateTicket(projectId, id, ticket), TicketRespondDTO.class);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/projects/{projectId}/tickets/{id}")
    public ResponseEntity<?> deleteTicket(@PathVariable Long projectId, @PathVariable Long id) {
        ticketService.deleteTicket(projectId, id);

        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/projects/{projectId}/tickets")
    public Collection<TicketRespondDTO> getTickets(@PathVariable Long projectId) {
        return ticketService.findTickets(projectId).stream()
                .map(ticket -> modelMapper.map(ticket, TicketRespondDTO.class))
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/projects/{projectId}/tickets/{id}")
    public TicketRespondDTO findTicket(@PathVariable Long projectId, @PathVariable Long id) {
        return modelMapper.map(ticketService.findTicket(projectId, id), TicketRespondDTO.class);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/users/{userId}/tickets/{id}")
    public TicketRespondDTO updateTicketUser(@PathVariable Long userId, @PathVariable Long id) {
        return modelMapper.map(ticketService.updateTicketUser(userId, id), TicketRespondDTO.class);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/users/{userId}/tickets/{id}")
    public ResponseEntity<?> deleteTicketUser(@PathVariable Long userId, @PathVariable Long id) {
        ticketService.deleteTicketUser(userId, id);

        return ResponseEntity.noContent().build();
    }
}
