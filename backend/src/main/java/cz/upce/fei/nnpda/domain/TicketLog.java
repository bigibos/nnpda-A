package cz.upce.fei.nnpda.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;


@Data
@AllArgsConstructor
@Entity
@Table(name="ticket_log")
public class TicketLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Timestamp time;
    private Ticket.Status status;
    private Ticket.Priority priority;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName="id")
    @JsonIgnoreProperties("tickets")
    private User user;

    @ManyToOne
    @JoinColumn(name="ticket_id", referencedColumnName="id")
    @JsonIgnoreProperties("logs")
    private Ticket ticket;

    public TicketLog() {}
}
