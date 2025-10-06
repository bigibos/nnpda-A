package cz.upce.fei.nnpda.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import cz.upce.fei.nnpda.domain.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketRespondDTO {
    private String name;
    private Ticket.Type type;
    private Ticket.Priority priority;
    private Ticket.Status status;

    @JsonIgnoreProperties("tickets")
    private ProjectRespondDTO preoject;
}
