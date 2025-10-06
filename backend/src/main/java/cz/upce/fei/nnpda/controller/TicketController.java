package cz.upce.fei.nnpda.controller;

import cz.upce.fei.nnpda.dto.TicketAddDTO;
import cz.upce.fei.nnpda.dto.TicketRespondDTO;
import cz.upce.fei.nnpda.dto.TicketUpdateDTO;
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
}
