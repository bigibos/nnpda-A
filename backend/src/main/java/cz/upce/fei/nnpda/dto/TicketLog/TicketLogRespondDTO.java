package cz.upce.fei.nnpda.dto.TicketLog;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import cz.upce.fei.nnpda.domain.Ticket;
import cz.upce.fei.nnpda.dto.Project.ProjectRespondDTO;
import cz.upce.fei.nnpda.dto.Ticket.TicketRespondDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketLogRespondDTO {
    private Long id;
    private Timestamp time;
    private Ticket.Status status;
    private Ticket.Priority priority;
}
