package cz.upce.fei.nnpda.service;

import cz.upce.fei.nnpda.domain.Project;
import cz.upce.fei.nnpda.domain.Ticket;
import cz.upce.fei.nnpda.domain.TicketLog;
import cz.upce.fei.nnpda.dto.Ticket.TicketAddDTO;
import cz.upce.fei.nnpda.dto.Ticket.TicketUpdateDTO;
import cz.upce.fei.nnpda.dto.TicketLog.TicketLogAddDTO;
import cz.upce.fei.nnpda.repository.TicketLogRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

@Service
@Slf4j
@AllArgsConstructor
public class TicketLogService {
    private final ModelMapper modelMapper;
    private final TicketLogRepository ticketLogRepository;

    @Transactional
    public TicketLog addTicketLog(Ticket ticket) {
        TicketLog ticketLog = new TicketLog();

        ticketLog.setTicket(ticket);
        ticketLog.setUser(ticket.getUser());
        ticketLog.setStatus(ticket.getStatus());
        ticketLog.setPriority(ticket.getPriority());

        Date now = new Date();
        Timestamp timestamp = new Timestamp(now.getTime());
        ticketLog.setTime(timestamp);

        ticketLog = ticketLogRepository.save(ticketLog);
        ticket.getLogs().add(ticketLog);

        return ticketLog;
    }

    @Transactional
    public void deleteTicketLog(TicketLog ticketLog) {
        ticketLogRepository.delete(ticketLog);
        // ticket.getLogs().add(ticketLog);
    }

}
