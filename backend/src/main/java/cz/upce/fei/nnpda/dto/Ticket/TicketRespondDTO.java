package cz.upce.fei.nnpda.dto.Ticket;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import cz.upce.fei.nnpda.domain.Ticket;
import cz.upce.fei.nnpda.dto.Project.ProjectRespondDTO;
import cz.upce.fei.nnpda.dto.TicketLog.TicketLogRespondDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketRespondDTO {
    private Long id;
    private String name;
    private Ticket.Type type;
    private Ticket.Priority priority;
    private Ticket.Status status;

    @JsonIgnoreProperties("tickets")
    private ProjectRespondDTO project;

    @JsonIgnoreProperties("ticket")
    private List<TicketLogRespondDTO> logs;
}
